import java.util.ArrayList;
import java.util.Collections;
public class Hand {


    private ArrayList<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }
    public void addCard(Card card){
        this.hand.add(card);
    }
    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }
    public void removeCard(int index){
        this.hand.remove(index);
    }
    public void printHand(){
        for(Card card: hand){
            System.out.println(card.toString());
        }
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    public static void calculateBestHand(Hand h){
        ArrayList<Card> currentHand = h.getHand();
        Hand bestHand = new Hand();
        int bestValue = 0;
        int n = currentHand.size();
        ArrayList<Card> toTest = new ArrayList<>();
        // Iterate through all possible combinations
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        toTest.add(currentHand.get(i));
                        toTest.add(currentHand.get(j));
                        toTest.add(currentHand.get(k));
                        toTest.add(currentHand.get(l));
                        int testPoints = points(toTest);
                        if(testPoints > bestValue){
                            bestValue = testPoints;
                            bestHand.setHand(toTest);
                        }
                        // Clear arraylist for next combination
                        for(int x = toTest.size() - 1; x >= 0; x--){
                            toTest.remove(0);
                        }
                    }
                }
            }
        }

    }
    public static int points(ArrayList<Card> hand){
        int points = 0;
        boolean runof5 = false;
        boolean runof4 = false;
        boolean firstandsecond = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        for(Card card: hand){
            numbers.add(card.getValue());
            System.out.println(card.getValue());
        }
        Collections.sort(numbers);
        System.out.println(numbers.get(0) + ", " + numbers.get(1));
        if(numbers.get(0) == numbers.get(1) - 1) {
            firstandsecond = true;
            System.out.println("True");
        }
        if(numbers.get(0) == numbers.get(2) - 2){
            if(numbers.get(0) == numbers.get(3) - 3){
                if(numbers.get(0) == numbers.get(4) - 4 && firstandsecond){
                    runof5 = true;
                }
                else if(firstandsecond){
                    runof4 = true;
                    }
                else if(numbers.get(0) == numbers.get(4) - 4){
                    runof4 = true;
                    }
                }
            }
        if(runof5){
            System.out.println("Run of 5");
            return 0;
        }
        if(runof4){
            System.out.println("Run of 4");
            return 0;
        }

        // Do with every possible flip card, add them all up then average out:

        /** Must iterate through groups of 2, 3, 4, 5
         * Groups of 2: Check pairs and 15s
         * Groups of 3: Check 15s and runs of 3
         * Groups of 4: Check 15s, runs of 4, flushes
         * Groups of 5: Check 15s, runs of 5, flushes
         *
         */
        // Check pairs COMPLETE
        // Check 15s
        // Check runs
        for(int i = 0; i < hand.size(); i++){
            for(int j = i + 1; j < hand.size(); j++){
                //System.out.println(hand.get(i).getValue() + ", " + hand.get(j).getValue());
                if(hand.get(i).getValue() == hand.get(j).getValue()){
                    points += 2;
                }
            }
        }

        return points;
    }
}
