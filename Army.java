public class Army{    
    public static double numSiege = 0;
    public static double numArcher = 0;
    public static double numCavalry = 0;
    public static double numFootmen = 0;
    public static double totalTroops =  0;
    public static double seigeProb;
    public static double archerProb;
    public static double cavalryProb;
    public static double genProb;
    public static boolean defense;
    public static boolean castleDefense;


    public Army(double siege, double archer, double cavalry, double footmen, boolean defending, boolean castle){
        numSiege = siege;
        numArcher = archer;
        numCavalry = cavalry;
        numFootmen = footmen;
        totalTroops = siege + archer + cavalry + footmen;
        defense = defending;
        castleDefense = castle;
    }
}