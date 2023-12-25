import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Hand hand = new Hand();
        Card card1 = new Card(0, 4);
        Card card2 = new Card(1, 1);
        Card card3 = new Card(2, 12);
        Card card4 = new Card(3, 2);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(0, 12);

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        hand.addCard(card4);
        hand.addCard(card5);
        hand.addCard(card6);

        System.out.println("Current hand, must get rid of two:");
        hand.printHand();
        ArrayList<Card> cards = hand.getHand();
        System.out.println("");
        //int points = Hand.points(cards);
        Hand.calculateBestHand(hand);
        //System.out.println("");
        //System.out.println("Total points in hand: " + points);
    }
}