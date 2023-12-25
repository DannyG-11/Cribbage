import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private ArrayList<Card> deck;
    public Deck(ArrayList<Card> hand){
        deck = new ArrayList<>();
        buildDeck(hand);
    }
    public ArrayList<Card> getDeck() {
        return deck;
    }
    private void buildDeck(ArrayList<Card> hand){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Card card = new Card(i, j);
                if(hand.contains(card)){
                    //System.out.println("Hand contains " + card.toString() + " , not in deck");
                }
                else {
                    deck.add(card);
                }
            }
        }
    }
}
