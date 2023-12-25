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
        this.hand = new ArrayList<>(hand);
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
        double bestValue = 0;
        double testPoints;
        int n = currentHand.size();
        ArrayList<Card> toTest = new ArrayList<>();
        Deck deck = new Deck(currentHand);
        System.out.println("");
        // Iterate through all possible combinations
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        //System.out.println("New combination checking");
                        toTest.add(currentHand.get(i));
                        toTest.add(currentHand.get(j));
                        toTest.add(currentHand.get(k));
                        toTest.add(currentHand.get(l));
                        //Card card = new Card(3, 12);
                        //toTest.add(card);
                        testPoints = points(toTest, deck);
                        if(testPoints > bestValue){
                            //System.out.println("New best");
                            bestValue = testPoints;
                            bestHand.setHand(toTest);
                            //bestHand.printHand();
                            //System.out.println("Total points: " + bestValue);
                            //System.out.println("");
                        }
                        // Clear arraylist for next combination
                        for(int x = toTest.size() - 1; x >= 0; x--){
                            toTest.remove(x);
                        }
                    }
                }
            }
        }
        System.out.println("After calculating all possible flip cards, you should keep:");
        bestHand.printHand();
        String formattedValue = String.format("%.2f", bestValue);
        System.out.println("Resulting in an average of " + formattedValue + " points.");
    }
    public static double points(ArrayList<Card> hand, Deck deck){
        double points = 0.0;
        for(Card card: deck.getDeck()){

            hand.add(card);
            // Check runs
            int runStatus = 0;
            runStatus = checkRunOfFourOrFive(hand);
            switch(runStatus){
                case 0:
                    // Count values: 0 = no run, 1 = run, 2 = double run, 3 = triple run, 4 = quad run
                    int runStatus2 = checkRunOfThree(hand);
                    switch(runStatus2){
                        case 0:
                            //System.out.println("No runs");
                            break;
                        case 1:
                            //System.out.println("Run of three");
                            points += 3;
                            break;
                        case 2:
                            //System.out.println("Double run of three");
                            points += 6;
                            break;
                        case 3:
                            //System.out.println("Triple run of three");
                            points += 9;
                            break;
                        case 4:
                            //System.out.println("Quad run of three");
                            points += 12;
                            break;
                    }
                    break;
                case 1:
                    //System.out.println("Run of 4");
                    points += 4;
                    break;
                case 2:
                    //System.out.println("Run of 5");
                    points += 5;
                    break;
                case 3:
                    //System.out.println("Double run of 4");
                    points += 8;
                    break;
            }

            // Check pairs
            points += checkPairs(hand);

            // Check 15s
            points += check15s(hand);

            // Check flushes
            points += checkFlush(hand);

            hand.remove(4);
        }

        return points / (52 - 6);
    }

    public static int checkFlush(ArrayList<Card> hand){
        int points = 0;
        int suit;

        suit = hand.get(0).getSuit();
        if(hand.get(3).getSuit() == suit && hand.get(2).getSuit() == suit
                && hand.get(3).getSuit() == suit){
            points += 4;
            if(hand.get(4).getSuit() == suit)
                points += 1;
        }
        return points;
    }
    public static int check15s(ArrayList<Card> hand){
        int points = 0;
        ArrayList<Card> copy = new ArrayList<>();
        for(Card card: hand){
            int value = card.getValue();
            int suit = card.getSuit();
            Card copyCard = new Card(suit, value);
            copy.add(copyCard);
            if(copyCard.getValue() > 9)
                copyCard.setValue(9);
        }
        // All 5
        if(copy.get(0).getValue() + copy.get(1).getValue() +
                copy.get(2).getValue() + copy.get(3).getValue() +
                copy.get(4).getValue() + 5 == 15){
            points += 2;
        }
        // Using 4
        for(int i = 0; i < copy.size(); i++){
            ArrayList<Card> tempHand = new ArrayList<>(copy);
            tempHand.remove(i);
            if(tempHand.get(0).getValue() + tempHand.get(1).getValue() +
                    tempHand.get(2).getValue() + tempHand.get(3).getValue() + 4 == 15){
                points += 2;
            }
        }
        // Using 3
        for(int i = 0; i < copy.size(); i++){
            for(int j = i + 1; j < copy.size(); j++){
                for(int k = j + 1; k < copy.size(); k++){
                    if(copy.get(i).getValue() + copy.get(j).getValue() + copy.get(k).getValue() + 3 == 15){
                        points += 2;
                    }
                }
            }
        }
        // Using 2
        for(int i = 0; i < copy.size(); i++) {
            for (int j = i + 1; j < copy.size(); j++) {
                //System.out.println("Checking between " + copy.get(i).toString() + " and " + copy.get(j).toString());
                if(copy.get(i).getValue() + copy.get(j).getValue() + 2 == 15){
                    points += 2;
                }
                //System.out.println(copy.get(i).getValue() + copy.get(j).getValue() + 2);
            }
        }
        return points;
    }
    public static int checkRunOfFourOrFive(ArrayList<Card> hand){
        boolean runof4 = false;
        boolean runof5 = false;
        boolean firstandsecond = false;
        int count = 0; // Returns to function what run there is.

        ArrayList<Integer> numbers = new ArrayList<>();
        for(Card card: hand){
            numbers.add(card.getValue());
            //System.out.println(card.getValue());
        }
        Collections.sort(numbers);

        if(numbers.get(0) == numbers.get(1) - 1) {
            firstandsecond = true;
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
            count = 2;
        }
        if(runof4){
            count = 1;
        }
        if(checkPairs(hand) > 0 && count == 1){
            count = 3;
        }
        // 0 if no run, 1 if ro4, 2 if ro5, 3 if dro4
        return count;
    }
    public static int checkRunOfThree(ArrayList<Card> hand){
        ArrayList<Integer> completeHand = new ArrayList<>();
        int count = 0;
        for(Card card: hand){
            completeHand.add(card.getValue());
            //System.out.println(card.getValue());
        }
        Collections.sort(completeHand);
        int pairs = checkPairs(hand);
        if(pairs == 0){
            int[] tru = new int[]{0, 0, 0, 0, 0};
            // No double runs, continue normally
            for(int i = 0; i < completeHand.size() - 3; i++){
                if(completeHand.get(i) == (completeHand.get(i+1) - 1)
                        && completeHand.get(i) == (completeHand.get(i+2) - 2)){
                    count = 1;
                    break;
                }
            }
        }
        // May be double runs: check to see if there is a pair, two pairs, or a triple
        else if(pairs == 2){
            // One pair
            int index = -1;
            for(int i = 0; i < completeHand.size() - 1; i++){
                if(completeHand.get(i) == completeHand.get(i + 1)){
                    index = i;
                    completeHand.remove(i);
                    break;
                }
            }
            for(int i = 0; i < completeHand.size() - 3; i++) {
                if (completeHand.get(i) == (completeHand.get(i + 1) - 1)
                        && completeHand.get(i) == (completeHand.get(i + 2) - 2)) {
                    if(index == i || index == i + 1 || index == i + 2){
                        count = 2;
                    }
                    else{
                        count = 1;
                    }
                    break;
                }
            }
        }

        else if(pairs == 6) {
            // Triple
            int index = -1;
            for(int i = 0; i < completeHand.size() - 1; i++){
                if(completeHand.get(i) == completeHand.get(i + 1)){
                    index = i;
                    completeHand.remove(i + 2);
                    completeHand.remove(i + 1);
                    break;
                }
            }
            // Since removed two values, only need to check the remaining three
            if (completeHand.get(0) == (completeHand.get(1) - 1)
                    && completeHand.get(0) == (completeHand.get(2) - 2)) {
                count = 3;
            }
        }
        else if(pairs == 4){
            // Quad
            int i1 = -1;
            int i2 = -1;
            for(int i = 0; i < completeHand.size() - 1; i++){
                if(completeHand.get(i) == completeHand.get(i + 1)){
                    if(i1 == -1)
                        i1 = i;
                    else{
                        i2 = i;
                        break;
                    }
                }
            }
            completeHand.remove(i2);
            completeHand.remove(i1);
            // Since removed two values, only need to check the remaining three
            if (completeHand.get(0) == (completeHand.get(1) - 1)
                    && completeHand.get(0) == (completeHand.get(2) - 2)) {
                count = 4;
            }
        }
        // Count values: 0 = no run, 1 = run, 2 = double run, 3 = triple run, 4 = quad run
        return count;
    }
    public static int checkPairs(ArrayList<Card> hand){
        int points = 0;
        for(int i = 0; i < hand.size(); i++){
            for(int j = i + 1; j < hand.size(); j++){
                if(hand.get(i).getValue() == hand.get(j).getValue()){
                    points += 2;
                }
            }
        }
        return points;
    }
}
