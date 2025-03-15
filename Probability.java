import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Probability {
    private final Army[] army = new Army[2];
    private final Random dice = new Random();
    private double simulationCount = 0;
    private final double[] hits = new double[2];
    private double aWinCount = 0;
    private double dWinCount = 0;
    private double drawCount = 0;
    private boolean simFinished;

    public Probability(Army a, Army d, double sim){
        army[0] = a;
        army[1] = d;
        simulationCount = sim;
        simFinished = false;
        hits[0] = 0;
        hits[1] = 0;
        army[0].setCurrent();
        army[1].setCurrent();
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
        boolean full = false;
        ArrayList<List<Integer>> diceRolls = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            diceRolls.add(new ArrayList<>());
            for(int j = 0; j < 3 && j < army[i].currentTotal; j++){
                if(j == 2 && i == 1){
                    break;
                }else{
                    if(i == 0 && j == 2){
                        full = true;
                    }
                    diceRolls.get(i).add(dice.nextInt(6) + 1);
                }
            }
        }

        Collections.sort(diceRolls.get(0), Collections.reverseOrder());
        Collections.sort(diceRolls.get(1), Collections.reverseOrder());

        if(full){
            diceRolls.get(0).remove(2); //removes the lowest dice roll from attacker, if they have all three rolls
        }

        for(int i = 0; i < army[0].currentTotal && i < army[1].currentTotal && i < 2; i++){
            if(diceRolls.get(0).get(i) > diceRolls.get(1).get(i)){
                if(army[1].castleDefense){
                    if(diceRolls.get(0).get(i) > (dice.nextInt(6) + 1)){
                        hits[0]++;
                    }else{
                        hits[1]++;
                    }
                }else{
                    hits[0]++;
                }
            }else{
                hits[1]++;
            }
        }

        removeTroops();
    }

    public void removeTroops(){
        for (int i = 0; i < 2; i++){
            while(hits[i] > 0){
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
        } else if(army[0].currentTotal <= 0){
            dWinCount++;
            simFinished = true;
        } else if(army[1].currentTotal <= 0){
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

    public void printResult(){
        System.out.println(aWinCount + " " + dWinCount + " " + drawCount);
        System.out.printf("Attacker wins %.2f%% of the time\n", (aWinCount/simulationCount * 100));
        System.out.printf("Defender wins %.2f%% of the time\n", (dWinCount/simulationCount * 100));
        System.out.printf("Draws occurs %.2f%% of the time\n\n", (drawCount/simulationCount * 100));
    }
}