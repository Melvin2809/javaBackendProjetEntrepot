import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ChefBrico extends ChefEquipe implements Brico {
    static Scanner scanner = new Scanner(System.in);
    private int tpsIndisponible;

    public ChefBrico(String prenom, String nom) {
        super(prenom, nom);
    }

    @Override
    public void monterUnMeuble(String specialite, LinkedList meuble) {
        LotDePieces lotDePiece = new LotDePieces("planche", 100);
        LotDePieces lotDePiece1 = new LotDePieces("vis", 100);
        LotDePieces lotDePiece2 = new LotDePieces("tissu", 100);

        construction.add(lotDePiece);
        construction.add(lotDePiece1);
        construction.add(lotDePiece2);

        int choix = 0;
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
                System.out.println("Vous avez actuelement " + visBefore + "*vis, " + plancheBefore + "*planche, " + tissuBefore + "*tissu, ");

                System.out.println("Que souhaitez-vous faire ?");
                System.out.println("(0) Construire TABLE");
                System.out.println("(1) Construire LIT");
                System.out.println("(2) Construire CANAPE");
                System.out.println("(3) Construire BUREAU");
                System.out.println("(4) Construire MEUBLETV");

                int numChoix = scanner.nextInt();

                switch (numChoix) {
                    case 0 -> {
                        if (visBefore >= 1 && plancheBefore >= 2 && tissuBefore >= 1) {
                            visAfter = 1;
                            plancheAfter = 2;
                            tissuAfter = 1;

                            Meuble table = new Meuble("table", "cuisine", 1);

                            table.sumPrixPieces(1, 2, 1);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(table);
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 1 -> {
                        if (visBefore >= 3 && plancheBefore >= 4 && tissuBefore >= 3) {
                            visAfter = 3;
                            plancheAfter = 4;
                            tissuAfter = 3;

                            Meuble lit = new Meuble("lit", "chambre", 1);

                            lit.sumPrixPieces(3, 4, 3);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(lit);
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 2 -> {
                        if (visBefore >= 2 && plancheBefore >= 3 && tissuBefore >= 2) {
                            visAfter = 2;
                            plancheAfter = 3;
                            tissuAfter = 2;

                            Meuble canape = new Meuble("canape", "salon", 1);

                            canape.sumPrixPieces(2, 3, 2);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(canape);
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 3 -> {
                        if (visBefore >= 5 && plancheBefore >= 2) {
                            visAfter = 5;
                            plancheAfter = 2;

                            Meuble bureau = new Meuble("bureau", "bureau", 1);

                            bureau.sumPrixPieces(5, 2, 0);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(bureau);
                        } else System.out.println("Vous n'avez pas les matériaux requis.");
                    }
                    case 4 -> {
                        if (visBefore >= 4 && plancheBefore >= 2 && tissuBefore >= 1) {
                            visAfter = 4;
                            plancheAfter = 2;
                            tissuAfter = 1;

                            Meuble meubleTV = new Meuble("meubleTV", "salon", 1);

                            meubleTV.sumPrixPieces(4, 2, 1);

                            System.out.println("Le meuble à bien été créer");

                            meuble.add(meubleTV);
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
            } else {
                System.out.println("Vous n'avez pas mis de lot de pièce à disposition pour construire des meubles...");
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
}
