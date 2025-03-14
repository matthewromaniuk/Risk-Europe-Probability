import java.util.Random;

public class Probability {
    private final double siegeProb = 2/3; //66.6% chance of siege hitting (4-6)
    private final double archerProb = 1/3; //33.3% chance of archer hitting (5 or 6)
    private final double cavalryProb = 2/3; //66.6% chance of cavalry hitting (3-6)
    private final Army[] army = new Army[2];
    private final Random dice = new Random();
    private double simulationCount = 0;
    private double[] hits = new double[2];
    private double aWinCount = 0;
    private double dWinCount = 0;
    private double drawCount = 0;
    private boolean simFinished;

    public Probability(Army a, Army d, double sim){
        army[0] = a;
        army[1] = d;
        simulationCount = sim;
        hits[0] = 0;
        hits[1] = 0;
        calculateProb();
    }

    public void calculateProb(){
        for(int i = 0; i < simulationCount; i++){
            simulate();
        }

        
    }


    public void simulate(){
        while(!simFinished){
            siegeAttack();
            if (simFinished) break;
            archerAttack();
            if (simFinished) break;
            cavalryAttack();
            if (simFinished) break;
            generalAttack();
        }


        reset();
    }

    public void siegeAttack(){
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < (army[i].currentSiege * 2); j++){
                if((dice.nextInt(6) + 1) > 2){
                    hits[i]++;
                }
            }
        }
        removeTroops();
    }

    public void archerAttack(){
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < army[i].currentArcher; j++){
                if(dice.nextInt(6) > 4){
                    hits[i]++;
                }
            }
        }
        removeTroops();
    }

    public void cavalryAttack(){
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < army[i].currentCavalry; j++){
                if((dice.nextInt(6) + 1) > 2){
                    hits[i]++;
                }
            }
        }
        removeTroops();
    }

    public void generalAttack(){
        int[][] diceRolls = new int[2][3];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                if()
                diceRolls[i][j] = dice.nextInt(6) + 1;
            }
        }

        for(int i = 0; i < army)
    }//use stack?

    public void removeTroops(){
        for (int i = 0; i < 2; i++){
            while(hits[i] > 0 && !simFinished){
                if(army[(i + 1) % 2].currentFootmen > 0){
                    army[(i + 1) % 2].currentFootmen--;
                    army[(i + 1) % 2].currentTotal--;
                    hits[i]--;
                }else if(army[(i + 1) % 2].currentArcher > 0){
                    army[(i + 1) % 2].currentArcher--;
                    army[(i + 1) % 2].currentTotal--;
                    hits[i]--;
                }else if(army[(i + 1) % 2].currentCavalry > 0){
                    army[(i + 1) % 2].currentCavalry--;
                    army[(i + 1) % 2].currentTotal--;
                }else{
                    army[(i + 1) % 2].currentSiege--;
                    army[(i + 1) % 2].currentTotal--;
                    hits[i]--;
                }//assumes the order of troop removal to be footmen, archer, cavalry, siege
            }
        }
        checkWin();
    }

    public void checkWin(){
        if(army[0].currentTotal <= 0 && army[1].currentTotal <= 0){
            drawCount++;
            simFinished = true;
        } else if(army[0].currentTotal == 0){
            dWinCount++;
            simFinished = true;
        } else if(army[1].currentTotal == 0){
            aWinCount++;
            simFinished = true;
        }
    }

    public void reset(){
        army[0].setCurrent();
        army[1].setCurrent();
        hits[0] = 0;
        hits[1] = 0;
        simFinished = false;
    }//resets fields for next simulation
}