import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ChefStock extends ChefEquipe implements Stock {
    private int tpsIndisponible;
    static Scanner scanner = new Scanner(System.in);
    static boolean retirerChefStock = true;

    public ChefStock(String prenom, String nom) {
        super(prenom, nom);
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
                    if (isRetirerChefStock()) {
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
                        setRetirerChefStock(false);
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

    public static boolean isRetirerChefStock() {
        return retirerChefStock;
    }

    public static void setRetirerChefStock(boolean retirerChefStock) {
        ChefStock.retirerChefStock = retirerChefStock;
    }
}
