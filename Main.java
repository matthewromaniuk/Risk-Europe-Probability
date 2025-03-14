import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Risk Europe Battle Probability Calculator!");
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        double seige;
        double archer;
        double cavalry;
        double footmen;
        boolean valid = false;
        String in;

        while(cont){
            System.out.println("Enter the number of Seige Machines in the attacking army: ");
            seige = sc.nextDouble();
            System.out.println("Enter the number of Archers in the attacking army: ");
            archer = sc.nextDouble();
            System.out.println("Enter the number of Cavalry in the attacking army: ");
            cavalry = sc.nextDouble();
            System.out.println("Enter the number of Footmen in the attacking army: ");
            footmen = sc.nextDouble();
            Army attackingArmy = new Army(seige, archer, cavalry, footmen, false, false);
            System.out.println("Enter the number of Seige Machines in the defending army: ");
            seige = sc.nextDouble();
            System.out.println("Enter the number of Archers in the defending army: ");
            archer = sc.nextDouble();
            System.out.println("Enter the number of Cavalry in the defending army: ");
            cavalry = sc.nextDouble();
            System.out.println("Enter the number of Footmen in the defending army: ");
            footmen = sc.nextDouble();
            while(!valid){
                System.out.println("Is the defending army defending a castle? (y/n)");
                in = sc.next();
                if(in.equals("y")){
                    valid = true;
                    Army defendingArmy = new Army(seige, archer, cavalry, footmen, true, true);
                } else if(in.equals("n")){
                    valid = true;
                    Army defendingArmy = new Army(seige, archer, cavalry, footmen, true, false);
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }
            Probability battle = new Probability(attackingArmy, defendingArmy);
            System.out.println("The probability of the attacking army winning is: " + battle.getWinProb());
            
            while(!valid){
                System.out.println("Would you like to calculate another battle? (y/n)");
                in = sc.next();
                if(in.equals("y")){
                    valid = true;
                } else if(in.equals("n")){
                    valid = true;
                    cont = false;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

        }
    }
}