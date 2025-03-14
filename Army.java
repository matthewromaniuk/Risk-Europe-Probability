public class Army{    
    public double numSiege = 0;
    public double numArcher = 0;
    public double numCavalry = 0;
    public double numFootmen = 0;
    public double totalTroops =  0;
    public double currentSiege = 0;
    public double currentArcher = 0;
    public double currentCavalry = 0;
    public double currentFootmen = 0;
    public double currentTotal = 0;
    public boolean defense;
    public boolean castleDefense;


    public Army(double siege, double archer, double cavalry, double footmen, boolean defending, boolean castle){
        numSiege = siege;
        numArcher = archer;
        numCavalry = cavalry;
        numFootmen = footmen;
        totalTroops = siege + archer + cavalry + footmen;
        defense = defending;
        castleDefense = castle;
    }

    public void setCurrent(){
        currentSiege = numSiege;
        currentArcher = numArcher;
        currentCavalry = numCavalry;
        currentFootmen = numFootmen;
        currentTotal = totalTroops;
    }
}