import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.net.URL;
public class View extends JFrame{
    private Controller controller;
    private Hand hand;
    private JPanel board, south, north, northEast, northCenter, diamonds, hearts, clubs, spades,
            dcontainer, hcontainer, ccontainer, scontainer, handcontainer;
    private JScrollPane dscroll, hscroll, cscroll, sscroll, currentscroll;
    private JButton enterButton, deleteButton, selectionCardButton, currentSelectedCardButton;
    public View(){
        super("Crib hand calculator");
        hand = new Hand();
        hand.addViews(this);
        controller = new Controller(hand);

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new JPanel(new BorderLayout());
        board.setPreferredSize(new Dimension(800,600));
        //pGameBoard.setBackground(Color.LIGHT_GRAY);
        this.add(board);

        south = new JPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        south.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        south.setPreferredSize(new Dimension(800, 400));

        // Set up slide panels to select cards
        diamonds = new JPanel(new BorderLayout());
        diamonds.setBorder(BorderFactory.createLineBorder(Color.blue,2));
        diamonds.setPreferredSize(new Dimension(800, 100));
        hearts = new JPanel(new BorderLayout());
        hearts.setBorder(BorderFactory.createLineBorder(Color.red,2));
        hearts.setPreferredSize(new Dimension(800, 100));
        spades = new JPanel(new BorderLayout());
        spades.setBorder(BorderFactory.createLineBorder(Color.black,2));
        spades.setPreferredSize(new Dimension(800, 100));
        clubs = new JPanel(new BorderLayout());
        clubs.setBorder(BorderFactory.createLineBorder(Color.green,2));
        clubs.setPreferredSize(new Dimension(800, 100));

        dcontainer = new JPanel(new FlowLayout());
        dscroll = new JScrollPane(dcontainer);
        diamonds.add(dscroll,BorderLayout.CENTER);
        dscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hcontainer = new JPanel(new FlowLayout());
        hscroll = new JScrollPane(hcontainer);
        hearts.add(hscroll,BorderLayout.CENTER);
        hscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scontainer = new JPanel(new FlowLayout());
        sscroll = new JScrollPane(scontainer);
        spades.add(sscroll,BorderLayout.CENTER);
        sscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ccontainer = new JPanel(new FlowLayout());
        cscroll = new JScrollPane(ccontainer);
        clubs.add(cscroll,BorderLayout.CENTER);
        cscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add cards
        setupButtons(dcontainer, 0);
        setupButtons(hcontainer, 1);
        setupButtons(scontainer, 2);
        setupButtons(ccontainer, 3);

        // Add these to the south panel
        south.add(spades);
        south.add(clubs);
        south.add(diamonds);
        south.add(hearts);
        board.add(south,BorderLayout.SOUTH);

        north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        north.setPreferredSize(new Dimension(700, 200));
        northEast = new JPanel();
        northEast.setLayout(new BoxLayout(northEast, BoxLayout.Y_AXIS));
        northEast.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        northEast.setBackground(Color.white);
        northEast.setPreferredSize(new Dimension(100, 200));
        northCenter = new JPanel();
        northCenter.setLayout(new BoxLayout(northCenter, BoxLayout.Y_AXIS));
        northCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        northCenter.setPreferredSize(new Dimension(700, 200));

        handcontainer = new JPanel(new FlowLayout());
        handcontainer.setBackground(Color.white);
        currentscroll = new JScrollPane(handcontainer);
        currentscroll.setBackground(Color.white);
        northCenter.add(currentscroll,BorderLayout.CENTER);
        currentscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        enterButton = new JButton("<html><div style='text-align: " +
                "center; vertical-align: middle;'>Confirm<br>Hand</div></html>");
        enterButton.setBackground(Color.WHITE);
        enterButton.setPreferredSize(new Dimension(100, 100));
        enterButton.addActionListener(controller);
        enterButton.setActionCommand("enter");
        enterButton.setEnabled(true);

        deleteButton = new JButton("<html><div style='text-align: " +
                "center; vertical-align: middle;'>Delete<br>Last Card</div></html>");
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setPreferredSize(new Dimension(100, 100));
        deleteButton.addActionListener(controller);
        deleteButton.setActionCommand("delete");
        deleteButton.setEnabled(true);

        northEast.add(enterButton,BorderLayout.NORTH);
        northEast.add(deleteButton,BorderLayout.SOUTH);

        board.add(north,BorderLayout.CENTER);
        board.add(northEast,BorderLayout.EAST);
        board.add(northCenter,BorderLayout.WEST);

        this.setVisible(true);
    }
    public void calculatedHand(ArrayList<Card> handToKeep, String avgPoints){
        JFrame frame = new JFrame("Calculated hand");
        frame.setSize(600, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel points = new JLabel("Average points: " + avgPoints);
        points.setHorizontalAlignment(JLabel.CENTER);

        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));
        cards.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        cards.setPreferredSize(new Dimension(700, 200));

        JPanel cardsContainer = new JPanel(new FlowLayout());
        cardsContainer.setBackground(Color.white);
        JScrollPane cardsScrollPane = new JScrollPane(cardsContainer);
        cardsScrollPane.setBackground(Color.white);
        cards.add(cardsScrollPane,BorderLayout.CENTER);
        cardsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] nums = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(Card card: handToKeep){
            JButton selectionCardButton = new JButton();
            // Set card icon
            String filepath = "./PNG-cards-1.3/" + nums[card.getValue()] + "_of_" + suits[card.getSuit()] + ".png";
            if (getClass().getResource(filepath) != null) {
                ImageIcon cardIcon = new ImageIcon(getClass().getResource(filepath));
                Image cardImage = cardIcon.getImage();
                cardImage = cardImage.getScaledInstance(70, 120, java.awt.Image.SCALE_SMOOTH);
                cardIcon = new ImageIcon(cardImage);
                selectionCardButton.setIcon(cardIcon);
            }
            selectionCardButton.setVerticalTextPosition(SwingConstants.TOP);
            selectionCardButton.setHorizontalTextPosition(SwingConstants.CENTER);
            selectionCardButton.setOpaque(true);
            selectionCardButton.setContentAreaFilled(false);
            selectionCardButton.setBorderPainted(false);
            selectionCardButton.setEnabled(false);
            cardsContainer.add(selectionCardButton);
        }
        cardsContainer.revalidate();
        cardsContainer.repaint();

        panel.add(cards);
        panel.add(points);
        frame.add(panel);
        frame.setVisible(true);
    }
    public void setupButtons(JPanel container, int x){
        // Add cards
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] nums = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(int i = 0; i < 13; i++){
            selectionCardButton = new JButton();

            // Set card icon
            String filepath = "./PNG-cards-1.3/" + nums[i] + "_of_" + suits[x] + ".png";
            if (getClass().getResource(filepath) != null) {
                ImageIcon cardIcon = new ImageIcon(getClass().getResource(filepath));
                Image cardImage = cardIcon.getImage();
                cardImage = cardImage.getScaledInstance(33, 65, java.awt.Image.SCALE_SMOOTH);
                cardIcon = new ImageIcon(cardImage);
                selectionCardButton.setIcon(cardIcon);
            }
            selectionCardButton.setVerticalTextPosition(SwingConstants.TOP);
            selectionCardButton.setHorizontalTextPosition(SwingConstants.CENTER);

            selectionCardButton.setOpaque(true);
            selectionCardButton.setContentAreaFilled(false);
            selectionCardButton.setBorderPainted(false);
            selectionCardButton.addActionListener(controller);
            selectionCardButton.setActionCommand(suits[x] + "," + values[i]);
            selectionCardButton.setEnabled(true);
            container.add(selectionCardButton);
        }

    }

    public void updatePlayerHand(){
        System.out.println("here");
        handcontainer.removeAll();
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] nums = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(Card card: hand.getHand()){
            selectionCardButton = new JButton();
            // Set card icon
            String filepath = "./PNG-cards-1.3/" + nums[card.getValue()] + "_of_" + suits[card.getSuit()] + ".png";
            if (getClass().getResource(filepath) != null) {
                ImageIcon cardIcon = new ImageIcon(getClass().getResource(filepath));
                Image cardImage = cardIcon.getImage();
                cardImage = cardImage.getScaledInstance(70, 120, java.awt.Image.SCALE_SMOOTH);
                cardIcon = new ImageIcon(cardImage);
                selectionCardButton.setIcon(cardIcon);
            }
            selectionCardButton.setVerticalTextPosition(SwingConstants.TOP);
            selectionCardButton.setHorizontalTextPosition(SwingConstants.CENTER);
            selectionCardButton.setOpaque(true);
            selectionCardButton.setContentAreaFilled(false);
            selectionCardButton.setBorderPainted(false);
            selectionCardButton.addActionListener(controller);
            selectionCardButton.setEnabled(false);
            handcontainer.add(selectionCardButton);
        }
        handcontainer.revalidate();
        handcontainer.repaint();
    }
    public static void main(String[] args){
        new View();
    }
}