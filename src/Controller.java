import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener{
    Hand hand;

    public Controller(Hand hand){
        this.hand = hand;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        if (event.equals("enter")) {
           // hand.playDraw(); do stuff
            System.out.println("Confirm Hand button pressed");
            if(hand.getHand().size() == 6) {
                ArrayList<Object> bestHand = Hand.calculateBestHand(hand);
                hand.printBestHand((Hand) bestHand.get(0), (String) bestHand.get(1));
            }
        }
        else if (event.equals("delete")) {
            System.out.println("Delete button pressed");
            if(hand.getHand().size() > 0) {
                hand.removeCard(hand.getHand().size() - 1);
            }
            hand.updateView();
        }
        else {
            // System.out.println("Card pressed");
            // Indicates card has been pressed
            Card card;
            String[] parse = event.split(",");
            String suit = parse[0].trim();
            int value = Integer.parseInt(parse[1].trim()) - 1;
            if(suit.equalsIgnoreCase("hearts")){
                card = new Card(0, value);
                //System.out.println(card.toString());
            }
            else if(suit.equalsIgnoreCase("diamonds")){
                card = new Card(1, value);
                //System.out.println(card.toString());
            }
            else if(suit.equalsIgnoreCase("clubs")){
                card = new Card(2, value);
                //System.out.println(card.toString());
            }
            else if(suit.equalsIgnoreCase("spades")){
                card = new Card(3, value);
                //System.out.println(card.toString());
            }
            else{
                card = new Card(0, 0);
            }
            if(hand.getHand().size() >= 6){
                return;
            }
            hand.addCard(card);
            // Update viewed player hand in view
            hand.updateView();
        }
    }
}
