public class Probability {
    private static final double seigeProb = 2/3; //66.6% chance of seige machine hitting (3-6)
    private static final double archerProb = 1/3; //33.3% chance of archer hitting (5 or 6)
    private static final double cavalryProb = 2/3; //66.6% chance of cavalry hitting (3-6)
    private static Army attackingArmy;
    private static Army defendingArmy;
    private static double genProb;
    private static double winProb;

    public Probability(Army a, Army d){
        attackingArmy = a;
        defendingArmy = d;
        calculateProb();
    }

    public static void calculateProb(){
       
    }

    public static double getWinProb(){
        return winProb;
    }
}