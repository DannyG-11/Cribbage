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
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString(){
        String s = new String();
        s += (Value.values()[value]) + " of " + Suit.values()[suit] + "s";
        return s;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value == card.value && suit == card.suit;
    }
}
