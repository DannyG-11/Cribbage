public class Card {
    public enum Suit {heart, diamond, club, spade}
    public enum Value {one, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king}
    private int suit;
    private int value;

    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }
    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
    @Override
    public String toString(){
        String s = new String();
        s += (Value.values()[value]) + " of " + Suit.values()[suit] + "s";
        return s;
    }
}
