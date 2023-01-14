import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class ChefEquipe extends Personnel {
    private ArrayList<Ouvrier> equipe = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static boolean recruterOuvrier;
    private static boolean licencierOuvrier;

    public ChefEquipe(String prenom, String nom) {
        super(prenom, nom);
    }

    public void RecrutementLicenciementOuvriers(Entrepot entrepot) {
        System.out.println("Bienvenue dans le menu de Recrutement/licenciement des ouvriers, choisissez le type d'action à effectuer: ");

        int choix = 0;
        do {
            System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
            System.out.println("(1) recrutement");
            System.out.println("(2) licenciement");

            try {
                int numAction = scanner.nextInt();

                if (numAction != 1 && numAction != 2)
                    System.out.println("Votre nombre n'est pas compris dans la selection.");

                switch (numAction) {
                    case 1 -> {
                        do {
                            if (ChefEquipe.isRecruterOuvrier()) {
                                System.out.println("Bienvenue dans le menu de Recrutement des ouvriers ! ");

                                if (equipe.size() < 4) {
                                    System.out.println("Choisisser le nom de l'ouvrier :" + scanner.nextLine());

                                    String nomOuvrier = scanner.nextLine();

                                    System.out.println("Choisisser le prenom de l'ouvrier :");
                                    String prenomOuvrier = scanner.nextLine();

                                    int choixSpecialite = 0;

                                    do {
                                        System.out.println("Choisisser la spécialité de l'ouvrier :");
                                        System.out.println("(0)Cuisine");
                                        System.out.println("(1)Chambre");
                                        System.out.println("(2)Salon");
                                        System.out.println("(3)Bureau");

                                        choixSpecialite = scanner.nextInt();
                                    } while (choixSpecialite != 0 && choixSpecialite != 1 && choixSpecialite != 2 && choixSpecialite != 3);

                                    String specialiteOuvrier = "";

                                    switch (choixSpecialite) {
                                        case 0:
                                            specialiteOuvrier = "Cuisine";
                                            break;
                                        case 1:
                                            specialiteOuvrier = "Chambre";
                                            break;
                                        case 2:
                                            specialiteOuvrier = "Salon";
                                            break;
                                        case 3:
                                            specialiteOuvrier = "Bureau";
                                            break;
                                    }

                                    Ouvrier ouvrier = new Ouvrier(prenomOuvrier, nomOuvrier, specialiteOuvrier);

                                    entrepot.getOuvriers().add(ouvrier);
                                    equipe.add(ouvrier);
                                    System.out.println("L'ouvrier " + nomOuvrier + " " + prenomOuvrier + " à bien été recruté.");
                                } else {
                                    System.out.println("L'équipe de ce chef n'a plus de place.");
                                }

                                System.out.println("Souhaitez-vous effectuer un nouveau recrutement ?");
                                System.out.println("(1) Oui");
                                System.out.println("(0) Non");

                                choix = scanner.nextInt();

                                if (choix != 0 && choix != 1) {
                                    System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de Recrutement/Licenciement.");
                                    choix = 0;
                                }
                                ChefEquipe.setRecruterOuvrier(false);
                            } else {
                                System.out.println("Vous ne pouvez plus recruter aujourd'hui");
                                choix = 0;
                            }
                        } while (choix != 0);
                    }
                    case 2 -> {
                        if (ChefEquipe.isLicencierOuvrier()) {
                            System.out.println("Bienvenue dans le menu de licenciement des ouvrier, choisissez la personne à licencier : \n");
                            do {
                                if (equipe.size() >= 1) {
                                    System.out.println("Validez votre choix en tapant l'un des numéros suivants: ");
                                    for (int i = 0; i < equipe.size(); i++) {
                                        System.out.println("(" + i + ") " + "Ouvrier n°" + getOuvrier(i).id + ": " + getOuvrier(i).nom + " " + getOuvrier(i).prenom);
                                    }

                                    int numLicenciement = scanner.nextInt();

                                    System.out.println(getOuvrier(numLicenciement).nom + " " + getOuvrier(numLicenciement).prenom + " à bien été licencier.");

                                    equipe.remove(getOuvrier(numLicenciement));

                                    System.out.println("Souhaitez-vous effectuer un nouveau licenciement ?");
                                    System.out.println("(1) Oui");
                                    System.out.println("(0) Non");

                                    choix = scanner.nextInt();

                                    if (choix != 0 && choix != 1) {
                                        System.out.println("Votre nombre n'est pas compris dans la selection.\nVous allez retourner au menu de Recrutement/Licenciement.");
                                        choix = 0;
                                    }
                                    ChefEquipe.setLicencierOuvrier(false);
                                } else {
                                    System.out.println("La liste des chefs d'équipe est vide... \n Vous ne pouvez donc pas licencier quelqu'un pour le moment.");
                                    choix = 0;
                                }
                            } while (choix != 0);

                        } else {
                            System.out.println("Vous ne pouvez plus recruter aujourd'hui");
                        }
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

    public ArrayList<Ouvrier> getEquipe() {
        return equipe;
    }

    public Ouvrier getOuvrier(int i) {
        return equipe.get(i);
    }

    public static boolean isRecruterOuvrier() {
        return recruterOuvrier;
    }

    public static boolean isLicencierOuvrier() {
        return licencierOuvrier;
    }

    public void setEquipe(ArrayList<Ouvrier> equipe) {
        this.equipe = equipe;
    }

    public static void setRecruterOuvrier(boolean recruterOuvrier) {
        ChefEquipe.recruterOuvrier = recruterOuvrier;
    }

    public static void setLicencierOuvrier(boolean licencierOuvrier) {
        ChefEquipe.licencierOuvrier = licencierOuvrier;
    }
}
