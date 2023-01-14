import java.util.*;

public class Ouvrier extends Personnel implements Stock, Brico {

    private String specialite;
    private ChefEquipe chefEquipe;
    private int tpsIndisponible;
    static Scanner scanner = new Scanner(System.in);
    static boolean retirerOuvrier = true;

    public Ouvrier() {
        tpsIndisponible = 0;
    }

    public Ouvrier(String prenom, String nom, String specialite) {
        super(prenom, nom);
        this.specialite = specialite;
    }

    @Override
    public void monterUnMeuble(String specialite, LinkedList meuble) {
        LotDePieces lotDePiece = new LotDePieces("planche", 100);
        LotDePieces lotDePiece1 = new LotDePieces("vis", 100);
        LotDePieces lotDePiece2 = new LotDePieces("tissu", 100);

        construction.add(lotDePiece);
        construction.add(lotDePiece1);
        construction.add(lotDePiece2);

        System.out.println("Bienvenue dans le menu de construction");
        System.out.println("Voici un bref rappel des matériaux (unité : lot de pièce) pour pouvoir construire les différents meubles");
        System.out.println("\n TABLE : 1*VIS, 2*PLANCHE, 1*TISSU");
        System.out.println(" LIT : 3*VIS, 4*PLANCHE, 3*TISSU");
        System.out.println(" CANAPE : 2*VIS, 3*PLANCHE, 2*TISSU");
        System.out.println(" BUREAU : 5*VIS, 2*PLANCHE");
        System.out.println(" MEUBLETV : 4*VIS, 1*PLANCHE, 1*TISSU\n");

        try {
            for (LotDePieces lotDePieces : construction) {
                System.out.println(lotDePieces);
            }
            if (construction.size() > 0) {
                int visBefore = 0;
                int plancheBefore = 0;
                int tissuBefore = 0;

                int visAfter = 0;
                int plancheAfter = 0;
                int tissuAfter = 0;

                for (LotDePieces lotDePieces : construction) {
                    if (lotDePieces.getNomLot().equals("vis")) {
                        visBefore = visBefore + lotDePieces.getVolume();
                    }
                    if (lotDePieces.getNomLot().equals("planche")) {
                        plancheBefore = plancheBefore + lotDePieces.getVolume();
                    }
                    if (lotDePieces.getNomLot().equals("tissu")) {
                        tissuBefore = tissuBefore + lotDePieces.getVolume();
                    }
                }
                System.out.println("Vous avez actuellement " + visBefore + "*vis, " + plancheBefore + "*planche, " + tissuBefore + "*tissu, ");

                System.out.println("Que souhaitez-vous faire ?");
                System.out.println("(0) Construire TABLE (spécialité : cuisine)");
                System.out.println("(1) Construire LIT (spécialité : chambre)");
                System.out.println("(2) Construire CANAPE (spécialité : salon)");
                System.out.println("(3) Construire BUREAU (spécialité : bureau)");
                System.out.println("(4) Construire MEUBLETV (spécialité : salon)");

                int numChoix = scanner.nextInt();

                switch (numChoix) {
                    case 0 -> {
                        if (visBefore >= 1 && plancheBefore >= 2 && tissuBefore >= 1 && specialite.equals("cuisine")) {
                            visAfter = 1;
                            plancheAfter = 2;
                            tissuAfter = 1;

                            Meuble table = new Meuble("table", this.getSpecialite(), 1);

                            table.sumPrixPieces(1, 2, 1);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(table);
                        } else if (!specialite.equals("cuisine")) {
                            System.out.println("Il faut la spécialité cuisine pour pouvoir construire ce meuble. " + this.getPrenom() + "  à la spécialité " + this.getSpecialite() + ".");
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 1 -> {
                        if (visBefore >= 3 && plancheBefore >= 4 && tissuBefore >= 3 && specialite.equals("chambre")) {
                            visAfter = 3;
                            plancheAfter = 4;
                            tissuAfter = 3;

                            Meuble lit = new Meuble("lit", this.getSpecialite(), 1);

                            lit.sumPrixPieces(3, 4, 3);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(lit);
                        } else if (!specialite.equals("chambre")) {
                            System.out.println("Il faut la spécialité chambre pour pouvoir construire ce meuble. " + this.getPrenom() + "  à la spécialité " + this.getSpecialite() + ".");
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 2 -> {
                        if (visBefore >= 2 && plancheBefore >= 3 && tissuBefore >= 2 && specialite.equals("salon")) {
                            visAfter = 2;
                            plancheAfter = 3;
                            tissuAfter = 2;

                            Meuble canape = new Meuble("canape", this.getSpecialite(), 1);

                            canape.sumPrixPieces(2, 3, 2);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(canape);
                        } else if (!specialite.equals("salon")) {
                            System.out.println("Il faut la spécialité salon pour pouvoir construire ce meuble. " + this.getPrenom() + "  à la spécialité " + this.getSpecialite() + ".");
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 3 -> {
                        if (visBefore >= 5 && plancheBefore >= 2 && specialite.equals("bureau")) {
                            visAfter = 5;
                            plancheAfter = 2;

                            Meuble bureau = new Meuble("bureau", this.getSpecialite(), 1);

                            bureau.sumPrixPieces(5, 2, 0);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(bureau);
                        } else if (!specialite.equals("bureau")) {
                            System.out.println("Il faut la spécialité bureau pour pouvoir construire ce meuble. " + this.getPrenom() + "  à la spécialité " + this.getSpecialite() + ".");
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 4 -> {
                        if (visBefore >= 4 && plancheBefore >= 2 && tissuBefore >= 1 && specialite.equals("salon")) {
                            visAfter = 4;
                            plancheAfter = 2;
                            tissuAfter = 1;

                            Meuble meubleTV = new Meuble("meubleTV", this.getSpecialite(), 1);

                            meubleTV.sumPrixPieces(4, 2, 1);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(meubleTV);
                        } else if (!specialite.equals("salon")) {
                            System.out.println("Il faut la spécialité salon pour pouvoir construire ce meuble. " + this.getPrenom() + "  à la spécialité " + this.getSpecialite() + ".");
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                }

                while (visAfter > 0 && plancheAfter > 0 && tissuAfter > 0) {
                    for (LotDePieces lotDePieces : construction) {
                        if (lotDePieces.getNomLot().equals("vis")) {
                            if (visAfter > 0) {
                                if (lotDePieces.getVolume() - visAfter < 0) {
                                    int i = 1;

                                    while (lotDePieces.getVolume() - visAfter < 0) {
                                        if (lotDePieces.getVolume() - i > 0) {
                                            i++;
                                        } else {
                                            lotDePieces.setVolume(lotDePieces.getVolume() - i);
                                            visAfter = visAfter - i;
                                        }
                                    }
                                } else {
                                    lotDePieces.setVolume(lotDePieces.getVolume() - visAfter);

                                    visAfter = 0;
                                }
                            }
                        }
                        if (lotDePieces.getNomLot().equals("planche")) {
                            if (plancheAfter > 0) {
                                if (lotDePieces.getVolume() - plancheAfter < 0) {
                                    int i = 1;

                                    while (lotDePieces.getVolume() - plancheAfter < 0) {
                                        if (lotDePieces.getVolume() - i > 0) {
                                            i++;
                                        } else {
                                            lotDePieces.setVolume(lotDePieces.getVolume() - i); //0
                                            plancheAfter = plancheAfter - i;
                                            break;
                                        }
                                    }
                                } else {
                                    lotDePieces.setVolume(lotDePieces.getVolume() - plancheAfter);

                                    plancheAfter = 0;
                                }
                            }
                        }
                        if (lotDePieces.getNomLot().equals("tissu")) {
                            if (tissuAfter > 0) {
                                if (lotDePieces.getVolume() - tissuAfter < 0) {
                                    int i = 1;

                                    while (lotDePieces.getVolume() - tissuAfter < 0) {
                                        if (lotDePieces.getVolume() - i > 0) {
                                            i++;
                                        } else {
                                            lotDePieces.setVolume(lotDePieces.getVolume() - i);
                                            tissuAfter = tissuAfter - i;
                                        }
                                    }
                                } else {
                                    lotDePieces.setVolume(lotDePieces.getVolume() - tissuAfter);

                                    tissuAfter = 0;
                                }
                            }
                        }
                    }
                }
                construction.removeIf(lotDePieces -> lotDePieces.getVolume() <= 0);
                this.setDisponible(false);
            } else {
                System.out.println("Vous n'avez pas mis de lot de pièce à disposition pour construire des meubles...");
            }
        } catch (InputMismatchException exception) {
            System.out.println("--** le type de valeur ne correspond pas **-- \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("--** Votre choix est en dehors de la sélection permise **-- \n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void ajouterLot(int strategie, LinkedList lotDePiece, Entrepot entrepot) {
        try {
            for (int i = 0; i < lotDePiece.size(); i++) {
                System.out.println("(" + i + ") " + lotDePiece.get(i).toString());
            }

            int numLot = scanner.nextInt();

            if (strategie == 2) {

            } else {
                LotDePieces volume = (LotDePieces) lotDePiece.get(numLot);
                LotDePieces lotDePieces = (LotDePieces) lotDePiece.get(numLot);

                if (!parcoursEspaceDisponibleEtAjouteLot(entrepot, lotDePieces, volume.getVolume())) {
                    System.out.println("error");
                } else System.out.println("L'objet à été ajouté au stock de l'entrepot !");
            }
            lotDePiece.remove(lotDePiece.get(numLot));
            this.setDisponible(false);
        } catch (InputMismatchException exception) {
            System.out.println("--** le type de valeur ne correspond pas **-- \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("--** Votre choix est en dehors de la sélection permise **-- \n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void retirerLot(Entrepot entrepot) {
        ArrayList<LotDePieces> lotDePieces = new ArrayList<>();
        Ajoute_A_ArrayList_Lot_Dans_Espace(entrepot, lotDePieces);

        try {
            if (lotDePieces.isEmpty()) {
                System.out.println("l'entrepot est vide. Il n'y a pas de lot à retirer.");
            } else {
                int choix = 0;
                do {
                    if (isRetirerOuvrier()) {
                        System.out.println("Choisissez l'ID' de l'objet à retirer");
                        LotDePieces dePieces1 = new LotDePieces();
                        for (LotDePieces dePiece : lotDePieces) {
                            if (!dePieces1.equals(dePiece)) {
                                System.out.println(dePiece);
                                dePieces1 = dePiece;
                            }
                        }

                        int idVerif = 0;
                        int idLot = 0;
                        do {
                            System.out.println("Entrer un ID :");
                            idLot = scanner.nextInt();

                            for (LotDePieces lotDePiece : lotDePieces) {
                                if (lotDePiece.getId() == idLot) {
                                    idVerif = 1;
                                    break;
                                }
                            }

                            if (idVerif != 1) {
                                System.out.println("Votre ID n'est pas valide.");
                            }
                        } while (idVerif != 1);

                        System.out.println("Le lot à bien été choisi.");


                        int volumeMax = 0;
                        int volume;

                        do {
                            System.out.println("nombre de volume à retirer :");
                            volume = scanner.nextInt();

                            for (LotDePieces lotDePiece : lotDePieces) {
                                if (lotDePiece.getId() == idLot) {
                                    volumeMax = lotDePiece.getVolume();
                                }
                            }

                            if (volume > volumeMax) {
                                System.out.println("Votre volume est trop grand par rapport au volume total du lot.");
                            }
                        } while (volume > volumeMax);

                        if (!retireLotDansEspace(entrepot, idLot, volume)) {
                            System.out.println("Une erreur s'est produite");

                            System.out.println("Souhaitez-vous réessayer ?");
                            System.out.println("(0) non");
                            System.out.println("(1) oui");

                            choix = scanner.nextInt();
                        } else {
                            choix = 0;

                            for (LotDePieces lotDePiece : lotDePieces) {
                                if (lotDePiece.getId() == idLot) {
                                    int volumeTotal = volumeMax - volume;
                                    lotDePiece.setVolume(volumeTotal);
                                }
                            }
                        }
                        setRetirerOuvrier(false);
                    } else {
                        System.out.println("Vous avez déjà retirer aujourd'hui");
                        choix = 0;
                    }
                } while (choix != 0);
                this.setDisponible(false);
                System.out.println("opération terminé.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("--** le type de valeur ne correspond pas **-- \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("--** Votre choix est en dehors de la sélection permise **-- \n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deplacerLot(Entrepot entrepot) {
        ArrayList<LotDePieces> lotDePieces = new ArrayList<>();
        Ajoute_A_ArrayList_Lot_Dans_Espace(entrepot, lotDePieces);

        try {
            if (!lotDePieces.isEmpty()) {
                System.out.println("Vous pouvez déplacer un lot au sein de l'entrepot (si votre stratégie n'est pas \"Si c'est pas bien rangé, je me casse !\") ou bien déplacer un lot afin de l'utiliser dans la construction d'un meuble.");
                System.out.println("Quel est votre choix ?");
                System.out.println("(0) Déplacer un lot dans l'entrepot");
                System.out.println("(1) Déplacer un lot pour une construction");

                try {
                    int numChoix = scanner.nextInt();

                    if (entrepot.getChoixStrategie() == 2 && numChoix == 0) {
                        System.out.println("CHEF ! Notre stratégie nous permet de garder un entrepot impecable CHEF ! Pour des raisons de sécurité et sauf votre respect, nous preferions que vous nous laissiez faire CHEF !");
                        numChoix = 2;
                    } else {
                        if (numChoix != 0 && numChoix != 1) {
                            System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de l'entrepot.");
                        }

                        System.out.println("Choisissez l'ID' de l'objet à retirer");

                        LotDePieces dePieces1 = new LotDePieces();
                        for (LotDePieces dePiece : lotDePieces) {
                            if (!dePieces1.equals(dePiece)) {
                                System.out.println(dePiece);
                                dePieces1 = dePiece;
                            }
                        }

                        LotDePieces lot = new LotDePieces();

                        int idVerif = 0;
                        int volumeMax = 0;
                        int idLot = 0;
                        do {
                            System.out.println("Entrer l'ID de l'objet à déplacer :");
                            idLot = scanner.nextInt();

                            for (LotDePieces lotDePiece : lotDePieces) {
                                if (lotDePiece.getId() == idLot) {
                                    idVerif = 1;
                                    lot = lotDePiece;
                                    volumeMax = lotDePiece.getVolume();
                                    break;
                                }
                            }

                            if (idVerif != 1) {
                                System.out.println("Votre ID n'est pas valide.");
                            }
                        } while (idVerif != 1);


                        System.out.println("Le lot à bien été choisi.");

                        switch (numChoix) {
                            case 0 -> {
                                ArrayList<int[]> espaceDispo = parcoursEspaceDisponible(entrepot, volumeMax);

                                for (int[] s : espaceDispo) {
                                    System.out.println(toStringArray(s[0], s[1], s[2]));
                                }

                                System.out.println("Choisisser parmi les numéros ci-dessus (ex : (1)), l'espace que vous voulez");

                                int numEspaceDispo = scanner.nextInt();

                                // On récupere les futurs coordonnées dans l'espace de l'entrepot
                                int mAfter = 0;
                                int nAfter = 0;
                                for (int[] ints : espaceDispo) {
                                    if (ints[0] == numEspaceDispo) {
                                        mAfter = ints[1];
                                        nAfter = ints[2];
                                    }
                                }

                                retireLotDansEspace(entrepot, idLot, volumeMax);

                                for (int i = 0; i < volumeMax; i++) {
                                    entrepot.addLotDePiece(lot, mAfter, nAfter + i);
                                }

                                System.out.println("le lot à bien été déplacé");
                            }
                            case 1 -> {
                                construction.add(lot);
                                retireLotDansEspace(entrepot, idLot, volumeMax);

                                System.out.println("le lot à bien été déplacé pour la construction ");
                            }
                        }
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("--** le type de valeur ne correspond pas **-- \n"); /* CONTINUER BOUCLE APRES CATCH*/
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                System.out.println("l'entrepot est vide. Il n'y a pas de lot à retirer.");
            }
            this.setDisponible(false);
        } catch (InputMismatchException exception) {
            System.out.println("--** le type de valeur ne correspond pas **-- \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("--** Votre choix est en dehors de la sélection permise **-- \n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public String toStringArray(int count, int i, int j) {
        return "(" + count + ")" + "L'espace " + i + "," + j + " est disponible";
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public static void setRetirerOuvrier(boolean retirerOuvrier) {
        Ouvrier.retirerOuvrier = retirerOuvrier;
    }

    public static boolean isRetirerOuvrier() {
        return retirerOuvrier;
    }
}
