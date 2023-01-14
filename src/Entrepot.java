import java.util.*;

public class Entrepot {

    private int m, n;
    private double tresorerie;
    private LinkedList<ChefEquipe> chefEquipe = new LinkedList<>();
    private LinkedList<ChefStock> chefStocks = new LinkedList<>();
    private LinkedList<ChefBrico> chefBricos = new LinkedList<>();
    private LinkedList<Ouvrier> ouvriers = new LinkedList<>();
    private LinkedList<LotDePieces> lotDePieces = new LinkedList<>();
    private LinkedList<Commande> commandes = new LinkedList<>();
    private LinkedList<Meuble> meubles = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);
    private LotDePieces[][] espace = new LotDePieces[n][m];
    private Map<Integer, String> strategies = new HashMap<>();
    private int choixStrategie;

    public static boolean recruter = true;
    public static boolean licencier = true;

    public Entrepot() {
        tresorerie = 1000;
        strategies.put(1, "Une strategie ? Laissez moi faire !");
        strategies.put(2, "Si c'est pas bien rangé, je me casse !");
        strategies.put(3, "Travailler esclaves ! Travailler !");
    }

    // Permet de mettre en place L'entrepot (espace entrepot, strategie entrepot)
    public void start(Entrepot entrepot) {
        System.out.println("Bienvenue dans ... ENTREPOT MANIA !");
        System.out.println("Le but est simple ! Gagner un max d'argent en gérant au mieux votre propre entrepot !");
        System.out.println("Vous avez 1000 euros, essayer de gagner plus !");
        System.out.println("Choisissez les dimensions de l'entrepot :");

        try {
            System.out.println("Nombre de rangées dans l'entrepot ?");
            int varNombreRange = scanner.nextInt();

            System.out.println("Nombre de place par rangées ?");
            int varNombrePlace = scanner.nextInt();

            entrepot.setM(varNombreRange);
            entrepot.setN(varNombrePlace);

            espace = new LotDePieces[m][n];

            System.out.println("FELICITATION ! VOUS AVEZ CREER VOTRE PREMIER ENTREPOT ( vous ne pouvez pas en creer un deuxieme par manque de budget et de talent...)");

            int numChoix = 0;
            do {
                System.out.println("Qu'elle est la stratégie de l'entrepot ? \n");

                strategies.forEach((key, value) -> System.out.println("(" + key + ") " + value));
                numChoix = scanner.nextInt();
                if (numChoix != 1 && numChoix != 2 && numChoix != 3)
                    System.out.println("Votre nombre n'est pas compris dans la sélection.");

            } while (numChoix != 1 && numChoix != 2 && numChoix != 3);


            choixStrategie = numChoix;

            System.out.println("Votre entrepot à bien été créer ! Bonne aventure ! \n");

        } catch (InputMismatchException exception) {
            System.out.println("--** le type de valeur ne correspond pas **-- \n"); /* CONTINUER BOUCLE APRES CATCH*/
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Menu de l'entrepot
    public void startEntrepot(Entrepot entrepot) {
        int choix = 0;
        panneauDeControle();
        entrepot.nouvelleCommandes();
        System.out.println("Bienvenue dans l'entrepot ! \n");
        do {
            try {
                System.out.println("Votre solde est de " + getTresorerie());
                System.out.println("Voici le menu");
                System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
                System.out.println("(1) Commande de Lots");
                System.out.println("(2) Recruter/Licencier Chef d'Equipe");
                System.out.println("(3) Inventaire");
                System.out.println("(4) Gestion des Equipes");
                System.out.println("(5) Gestion des Tâches");
                System.out.println("(6) Commandes à Honorer");

                int numChoix = scanner.nextInt();

                if (numChoix != 1 && numChoix != 2 && numChoix != 3 && numChoix != 4 && numChoix != 5 && numChoix != 6)
                    System.out.println("Votre nombre n'est pas compris dans la selection.");

                switch (numChoix) {
                    case 1 -> entrepot.CommandeLotDePiece();
                    case 2 -> entrepot.RecrutementLicenciementChefs();
                    case 3 -> entrepot.Inventaire();
                    case 4 -> entrepot.GestionEquipe(entrepot);
                    case 5 -> entrepot.GestionTaches(entrepot);
                    case 6 -> entrepot.Commande_a_Honorer();
                }

                System.out.println("Souhaitez-vous passer au jour suivant?");
                System.out.println("(1) NON");
                System.out.println("(0) OUI");

                choix = scanner.nextInt();

                if (choix != 0 && choix != 1) {
                    System.out.println("Votre nombre n'est pas compris dans la selection.\nVotre action n'a pas pu être prise en compte.");
                    choix = 1;
                }

                if(choix == 0){
                    startEntrepot(entrepot);
                }
            } catch (InputMismatchException exception) {
                System.out.println("--** le type de valeur ne correspond pas **-- \n"); /* CONTINUER BOUCLE APRES CATCH*/
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } while (choix != 0);
        scanner.close();
    }

    // permet la commande de piece
    public void CommandeLotDePiece() {
        System.out.println("Bienvenue dans le menu de commande, choisissez le type de pièce souhaiter : ");

        int choix = 0;
        do {
            System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
            System.out.println("(1) planche");
            System.out.println("(2) vis");
            System.out.println("(3) tissu");

            try {
                int numPieces = scanner.nextInt();

                if (numPieces != 1 && numPieces != 2 && numPieces != 3)
                    System.out.println("Votre nombre n'est pas compris dans la selection.");

                else {
                    String nomPieces = "";
                    double prix = 0;
                    double poids = 0;

                    switch (numPieces) {
                        case 1 -> {
                            nomPieces = "planche";
                            prix = 1.99;
                            poids = 2.3;
                        }
                        case 2 -> {
                            nomPieces = "vis";
                            prix = 0.3;
                            poids = 0.02;
                        }
                        case 3 -> {
                            nomPieces = "tissu";
                            prix = 2.99;
                            poids = 0.4;
                        }
                    }


                    System.out.print("Veuillez saisir le volume souhaité: ");

                    int volume = scanner.nextInt();

                    // Gérer erreur si volume n'est pas un int

                    LotDePieces lotDePiece = new LotDePieces(nomPieces, volume);
                    this.lotDePieces.add(lotDePiece);

                    for (int i = 1; i <= volume; i++) {
                        lotDePiece.addPieces(nomPieces + i, poids, prix);
                    }
                    System.out.println("La commande de lot de " + nomPieces + " à été passée.");
                }

                System.out.println("Souhaitez-vous effectuer une nouvelle commande ?");
                System.out.println("(1) Oui");
                System.out.println("(0) Non");

                choix = scanner.nextInt();

                if (choix != 0 && choix != 1) {
                    System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de l'entrepot.");
                    choix = 0;
                }

            } catch (InputMismatchException exception) {
                System.out.println("--** le type de valeur ne correspond pas **-- \n");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } while (choix != 0);
    }

    // Permet de recruter licencier chefs
    public void RecrutementLicenciementChefs() {
        System.out.println("Bienvenue dans le menu de Recrutement/licenciement des chefs d'équipe, choisissez le type d'action à effectuer: ");

        int choix = 0;
        do {
            System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
            System.out.println("(1) recrutement");
            System.out.println("(2) licenciement");

            try {
                int numAction = scanner.nextInt();

                if (numAction != 1 && numAction != 2)
                    System.out.println("Votre nombre n'est pas compris dans la sélection.");

                switch (numAction) {
                    case 1 -> {
                        do {
                            if (recruter) {
                                System.out.println("Bienvenue dans le menu de Recrutement des chefs d'équipe, choisissez le type de chef que vous voulez: ");

                                System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
                                System.out.println("(1) Chef Stock");
                                System.out.println("(2) Chef Brico");

                                int numChef = scanner.nextInt();
                                System.out.println(numChef);

                                if (numChef != 1 && numChef != 2)
                                    System.out.println("Votre nombre n'est pas compris dans la sélection.");

                                System.out.println("Choisisser le nom du chef :" + scanner.nextLine());

                                String nomChef = scanner.nextLine();

                                System.out.println("Choisisser le prenom du chef :");
                                String prenomChef = scanner.nextLine();

                                if (numChef == 1) {
                                    ChefStock chefStock = new ChefStock(prenomChef, nomChef);
                                    chefEquipe.add(chefStock);
                                    chefStocks.add(chefStock);
                                } else {
                                    ChefBrico chefBrico = new ChefBrico(prenomChef, nomChef);
                                    chefEquipe.add(chefBrico);
                                    chefBricos.add(chefBrico);
                                }

                                System.out.println("Le chef " + nomChef + " " + prenomChef + " à bien été recruté.");

                                System.out.println("Souhaitez-vous effectuer un nouveau recrutement ?");
                                System.out.println("(1) Oui");
                                System.out.println("(0) Non");

                                choix = scanner.nextInt();

                                if (choix != 0 && choix != 1) {
                                    System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de Recrutement/Licenciement.");
                                    choix = 0;
                                }
                                setRecruter(false);
                            } else {
                                System.out.println("Vous avez déjà recruter aujourd'hui");
                                choix = 0;
                            }
                        } while (choix != 0);
                    }
                    case 2 -> {
                        System.out.println("Bienvenue dans le menu de licenciement des chefs d'équipe, choisissez la personne à licencier : \n");
                        do {
                            if (licencier) {
                                if (chefEquipe.size() >= 1) {
                                    System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
                                    for (int i = 0; i < chefEquipe.size(); i++) {
                                        System.out.println("(" + i + ") " + "Chef d'équipe n°" + getChefEquipe(i).id + ": " + getChefEquipe(i).nom + " " + getChefEquipe(i).prenom);
                                    }

                                    int numLicenciement = scanner.nextInt();

                                    System.out.println(getChefEquipe(numLicenciement).nom + " " + getChefEquipe(numLicenciement).prenom + " à bien été licencier.");

                                    chefEquipe.remove(getChefEquipe(numLicenciement));

                                    System.out.println("Souhaitez-vous effectuer un nouveau licenciement ?");
                                    System.out.println("(1) Oui");
                                    System.out.println("(0) Non");

                                    choix = scanner.nextInt();

                                    if (choix != 0 && choix != 1) {
                                        System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de Recrutement/Licenciement.");
                                        choix = 0;
                                    }
                                } else {
                                    System.out.println("La liste des chefs d'équipe est vide... \n Vous ne pouvez donc pas licencier quelqu'un pour le moment.");
                                    choix = 0;
                                }
                                setLicencier(false);
                            } else {
                                System.out.println("Vous avez déjà licencier aujourd'hui");
                                choix = 0;
                            }
                        } while (choix != 0);
                    }
                }

                System.out.println("Souhaitez-vous effectuer une nouvelle action (Recrutement/Licenciement) ?");
                System.out.println("(1) Oui");
                System.out.println("(0) Non");

                choix = scanner.nextInt();

                if (choix != 0 && choix != 1) {
                    System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de l'entrepot.");
                    choix = 0;
                }

            } catch (InputMismatchException exception) {
                System.out.println("--** le type de valeur ne correspond pas **-- \n");
                choix = 0;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } while (choix != 0);
    }

    // permet de faire l'inventaire
    public void Inventaire() {
        for (int i = 0; i < getEspace().length; i++) {
            for (int j = 0; j < getN(); j++) {
                if (getOneEspace(i, j) != null) {
                    System.out.println("Place " + i + " " + j + ": " + getOneEspace(i, j));
                }
            }
        }
    }

    // permet de gerer les les equipes
    public void GestionEquipe(Entrepot entrepot) {
        int choix = 0;
        System.out.println("Bienvenue dans le menu de gestion des équipes : \n");
        do {
            try {
                if (chefEquipe.size() >= 1) {
                    System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
                    for (int i = 0; i < chefEquipe.size(); i++) {
                        System.out.println("(" + i + ") " + "Chef d'équipe n°" + getChefEquipe(i).id + ": " + getChefEquipe(i).nom + " " + getChefEquipe(i).prenom);
                    }

                    int numGestion = scanner.nextInt();

                    System.out.println(getChefEquipe(numGestion).nom + " " + getChefEquipe(numGestion).prenom + " à été selectionné.");

                    do {
                        System.out.println("Choisisser l'action à effectuer :");
                        System.out.println("(1) Recruter/licencier un ouvrier");
                        System.out.println("(2) Voir équipe");

                        int numActionEquipe = scanner.nextInt();

                        if (numActionEquipe != 1 && numActionEquipe != 2)
                            System.out.println("Votre nombre n'est pas compris dans la selection.");

                        switch (numActionEquipe) {
                            case 1 -> getChefEquipe(numGestion).RecrutementLicenciementOuvriers(entrepot);
                            case 2 -> System.out.println(getChefEquipe(numGestion).getEquipe());
                        }

                        System.out.println("Souhaitez-vous effectuer une nouvelle action avec  " + getChefEquipe(numGestion).nom + " " + getChefEquipe(numGestion).prenom + " ?");
                        System.out.println("(1) Oui");
                        System.out.println("(0) Non");

                        choix = scanner.nextInt();

                    } while (choix != 0);

                    System.out.println("Souhaitez-vous gerer une nouvelle équipe ?");
                    System.out.println("(1) Oui");
                    System.out.println("(0) Non");

                    choix = scanner.nextInt();

                    if (choix != 0 && choix != 1) {
                        System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de l'entrepot.");
                        choix = 0;
                    }
                } else {
                    System.out.println("La liste des chefs d'équipe est vide... \n Vous ne pouvez donc pas gérer les équipes.");
                    choix = 0;
                }
            } catch (InputMismatchException exception) {
                System.out.println("--** le type de valeur ne correspond pas **-- \n");
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("--** Votre choix est en dehors de la sélection permise **-- \n");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } while (choix != 0);
    }

    // permet de gerer les tâches en choisissant un employé
    public void GestionTaches(Entrepot entrepot) {
        //A supprimer
        Ouvrier ouvrier = new Ouvrier("damien", "dodo", "cuisine");

        ouvriers.add(ouvrier);

        ChefStock chefStock = new ChefStock("tom", "fall");
        chefEquipe.add(chefStock);
        chefStocks.add(chefStock);

        Ouvrier ouvrier2 = new Ouvrier("melvin", "guigui", "bureau");
        ouvriers.add(ouvrier2);

        ChefBrico chefBrico = new ChefBrico("zefir", "cul");
        chefEquipe.add(chefBrico);
        chefBricos.add(chefBrico);

        Ouvrier ouvrier1 = new Ouvrier("lucas", "ango", "chambre");
        ouvriers.add(ouvrier1);

        LotDePieces lotDePiece = new LotDePieces("zizi", 5);
        this.lotDePieces.add(lotDePiece);

        int choix = 0;
        System.out.println("Bienvenue dans le menu de gestion des tâches : \n");
        do{
        System.out.println("Quelle liste voulez-vous afficher ? ");
        System.out.println("(1) Liste Chef Equipe");
        System.out.println("(2) Liste Ouvrier");

        try {
            int numList = scanner.nextInt();
            int typeChefEquipe = 0;
            boolean listVide = true;

            if (numList == 1) {
                System.out.println("Liste Chef Equipe :");
                System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");

                if (chefEquipe.size() > 0) {
                    for (int i = 0; i < chefEquipe.size(); i++) {
                        if (chefEquipe.get(i).disponible) {
                            if (getChefEquipe(i) instanceof ChefBrico) {
                                System.out.println("(" + i + ")" + " Chef d'équipe Brico" + ": " + getChefEquipe(i).nom + " " + getChefEquipe(i).prenom);
                            } else {
                                System.out.println("(" + i + ")" + " Chef d'équipe Stock" + ": " + getChefEquipe(i).nom + " " + getChefEquipe(i).prenom);
                            }
                        }
                    }
                } else {
                    System.out.println("Vous n'avez pas engagé de chef d'équipe");
                    listVide = false;
                }
            } else {
                typeChefEquipe = 2;
                System.out.println("\nListe Ouvrier : ");
                System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");

                if (ouvriers.size() > 0) {
                    for (int i = 0; i < ouvriers.size(); i++) {
                        if (ouvriers.get(i).disponible) {
                            System.out.println("(" + i + ")" + "Ouvrier " + ": " + getOuvriers(i).nom + " " + getOuvriers(i).prenom + ", spécialité : " + getOuvriers(i).getSpecialite());
                        }
                    }
                } else {
                    System.out.println("Vous n'avez pas engagé d'ouvrier");
                    listVide = false;
                }
            }

            if (listVide) {
                int numEmploye = scanner.nextInt();

                if (typeChefEquipe == 0) {
                    if (getChefEquipe(numEmploye) instanceof ChefBrico) typeChefEquipe = 1;
                }

                switch (typeChefEquipe) {
                    case 0 -> System.out.println("vous avez choisi " + getChefEquipe(numEmploye).nom + " " + getChefEquipe(numEmploye).prenom);
                    case 1 -> System.out.println("vous avez choisi " + getChefEquipe(numEmploye).nom + " " + getChefEquipe(numEmploye).prenom);
                    case 2 -> System.out.println("vous avez choisi " + getOuvriers(numEmploye).nom + " " + getOuvriers(numEmploye).prenom);
                }

                System.out.println("Quelle tâches voulez-vous choisir ?");

                switch (typeChefEquipe) {
                    case 0 -> {
                        System.out.println("(0) Ajouter");
                        System.out.println("(1) Retirer");
                        System.out.println("(2) Déplacer");
                    }
                    case 1 -> System.out.println("(3) Monter");
                    case 2 -> {
                        System.out.println("(0) Ajouter");
                        System.out.println("(1) Retirer");
                        System.out.println("(2) Déplacer");
                        System.out.println("(3) Monter");
                    }
                }

                int numTache = scanner.nextInt();

                switch (numTache) {
                    case 0 -> {
                        if (typeChefEquipe == 0)
                            getChefStock(numEmploye).ajouterLot(this.choixStrategie, lotDePieces, entrepot);
                        else getOuvriers(numEmploye).ajouterLot(choixStrategie, lotDePieces, entrepot);

                    }
                    case 1 -> {
                        if (typeChefEquipe == 0) getChefStock(numEmploye).retirerLot(entrepot);
                        else getOuvriers(numEmploye).retirerLot(entrepot);
                    }
                    case 2 -> {
                        if (typeChefEquipe == 0) getChefStock(numEmploye).deplacerLot(entrepot);
                        else getOuvriers(numEmploye).deplacerLot(entrepot);
                    }
                    case 3 -> {
                        if (typeChefEquipe == 1) getChefBrico(numEmploye).monterUnMeuble("Brico", meubles);
                        else getOuvriers(numEmploye).monterUnMeuble(getOuvriers(numEmploye).getSpecialite(), meubles);
                    }
                }
            }
            System.out.println("Voulez vous effectuer une autre tâches ?");
            System.out.println("(0)NON");
            System.out.println("(1)OUI");
            choix = scanner.nextInt();

        } catch (InputMismatchException exception) {
            System.out.println("--** le type de valeur ne correspond pas **-- \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("--** Votre choix est en dehors de la sélection permise **-- \n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        }while(choix!=0);
    }


    private void nouvelleCommandes() {
        System.out.println("\n Nouvelles commandes du jour ! :\n");
        int value = (int) (Math.random() * (10 - 6));

        switch (value) {
            case 0 -> {
                Commande table = new Commande("table");
                commandes.add(table);
                System.out.println("une nouvelle commande de table à été ajouté !");
            }
            case 1 -> {
                Commande canape = new Commande("canape");
                commandes.add(canape);
                System.out.println("une nouvelle commande de canape à été ajouté !");
            }
            case 2 -> {
                Commande bureau = new Commande("bureau");
                commandes.add(bureau);
                System.out.println("une nouvelle commande de bureau à été ajouté !");
            }
            case 3 -> {
                Commande lit = new Commande("lit");
                commandes.add(lit);
                System.out.println("une nouvelle commande de lit à été ajouté !");
            }
            case 4 -> {
                Commande meubleTV = new Commande("meubleTv");
                commandes.add(meubleTV);
                System.out.println("une nouvelle commande de meubleTv à été ajouté !");
            }
        }
        System.out.println("Fin des nouvelles commandes pour aujourd'hui !\n");
    }

    public void Commande_a_Honorer() {
        System.out.println("Voici les commandes à honorer :");

        for (int i = 0; i < commandes.size(); i++) {
            System.out.println("(" + i + ") Commande n°" + commandes.get(i).getId() + ": " + commandes.get(i).getNom());
        }

        System.out.println("Vous souhaiter finaliser une commande ? ");
        System.out.println("(0) Oui");
        System.out.println("(1) Non");

        int numChoix = scanner.nextInt();

        if (numChoix == 0 && commandes.size() > 0) {
            System.out.println("Saisisser le numéro de commande à finaliser dans la liste ci-dessus");

            int numCommande = scanner.nextInt();

            System.out.println("Pour pouvoir honorer cette commande, il vous faut un meuble " + commandes.get(numCommande).getNom());

            for (int i = 0; i < meubles.size(); i++) {
                System.out.println("(" + i + ") " + meubles.get(i).getNom());
            }

            System.out.println("Choisissez le meuble qui vous voulez vendre.");

            int choixMeuble = scanner.nextInt();

            commandes.get(numCommande).setMeubles(meubles.get(choixMeuble));
            commandes.get(numCommande).setPrix(meubles.get(choixMeuble).getPrix());
            commandes.get(numCommande).setStatut("Validée");
            this.setTresorerie(this.getTresorerie() + meubles.get(choixMeuble).getPrix());
            meubles.remove(meubles.get(choixMeuble));

            System.out.println("Meubles vendu, à nous l'argent !");

        } else if (commandes.size() == 0) {
            System.out.println("Vous n'avez créer aucun meuble pour le moment. Revenez plus tard.");
        }
    }

    private void panneauDeControle() {
        // Payer le personnel et rendre dispo employé
        double argent = getTresorerie();
        for (ChefEquipe chefEquipe : chefEquipe) {
            if(argent - 10 < 0) Banqueroute();
            chefEquipe.setDisponible(true);
            setTresorerie(getTresorerie() - 10);
            chefEquipe.setSolde(chefEquipe.getSolde() + 10);
        }
        for (Ouvrier ouvrier : ouvriers) {
            if(argent - 5 < 0) Banqueroute();
            ouvrier.setDisponible(true);
            setTresorerie(getTresorerie() - 5);
            ouvrier.setSolde(ouvrier.getSolde() + 5);
        }

        //rendre dispo les méthodes
        setLicencier(true);
        setRecruter(true);
        ChefEquipe.setLicencierOuvrier(true);
        ChefEquipe.setRecruterOuvrier(true);
        Ouvrier.setRetirerOuvrier(true);
        ChefStock.setRetirerChefStock(true);
    }

    public void Banqueroute(){
        System.out.println("GAME OVER");
        System.out.println("Vous n'avez plus asser d'argent pour faire tourner votre entrepôt !");
        System.out.println("Vendre vos lot restant et vos employé, n'a malheuresement pas suffit...");
        System.exit(0);
    }

    public void addLotDePiece(LotDePieces lotDePieces, int m, int n) {
        espace[m][n] = lotDePieces;
    }

    ///////////////////////////////// Getters

    public ChefEquipe getChefEquipe(int i) {
        return chefEquipe.get(i);
    }

    public Ouvrier getOuvriers(int i) {
        return ouvriers.get(i);
    }

    public LinkedList<Ouvrier> getOuvriers() {
        return ouvriers;
    }

    public ChefStock getChefStock(int i) {
        return chefStocks.get(i);
    }

    public ChefBrico getChefBrico(int i) {
        return chefBricos.get(i);
    }

    public LinkedList<LotDePieces> getLotDePieces() {
        return lotDePieces;
    }

    public int getN() {
        return n;
    }

    public LotDePieces getOneEspace(int m, int n) {
        return espace[m][n];
    }

    public LotDePieces[][] getEspace() {
        return espace;
    }

    public int getChoixStrategie() {
        return choixStrategie;
    }

    public Meuble getMeuble(int i) {
        return meubles.get(i);
    }

    public double getTresorerie() {
        return tresorerie;
    }

    public static boolean isRecruter() {
        return recruter;
    }

    public static boolean isLicencier() {
        return licencier;
    }

    ///////////////////////////////// Setters

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setOneEspaceToNull(int m, int n) {
        this.espace[m][n] = null;
    }

    public void setTresorerie(double tresorerie) {
        this.tresorerie = tresorerie;
    }

    public void setRecruter(boolean recruter) {
        this.recruter = recruter;
    }

    public void setLicencier(boolean licencier) {
        this.licencier = licencier;
    }
}
