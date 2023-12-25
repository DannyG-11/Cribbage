import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Hand hand = new Hand();
        Card card1 = new Card(0, 1);
        Card card2 = new Card(1, 2);
        Card card3 = new Card(2, 3);
        Card card4 = new Card(3, 5);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(0, 11);
        /**hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        hand.addCard(card4);
        hand.addCard(card5);
        hand.addCard(card6);
        hand.printHand();
         **/
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        //cards.add(card6);
        int points = Hand.points(cards);
        System.out.println("Total points from pairs: " + points);
    }
}