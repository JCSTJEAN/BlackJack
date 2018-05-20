import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        //Create a dealer and a player
        Dealer Dealer = new Dealer();
        Player Player = new Player();

        //deal cards
        Dealer.DealerHand.hit(Dealer.DealerDeck.DealCard());
        Player.PlayerHand.hit(Dealer.DealerDeck.DealCard());
        Dealer.DealerHand.hit(Dealer.DealerDeck.DealCard());
        Player.PlayerHand.hit(Dealer.DealerDeck.DealCard());

        
        int a = 0;
        Scanner s = new Scanner(System.in);
        
        while (a != -1)
        {
            Dealer.DealerHand.peekHand();
            Player.PlayerHand.showHand();

            //request to either hit or stand.
            Dealer.promptToHitOrStand();
            
            a = s.nextInt();
            

            switch (a)
            {
                case 1://if hit
                    Player.PlayerHand.hit(Dealer.DealerDeck.DealCard());
                    if (Player.PlayerHand.isHandOver()) //check to see if hand is over
                    {
                        a = -1;
                    }
                    System.out.println("================================================================");
                    break;
                case 2://if stand
                    a = -1;
                    break;
            }
        }

        System.out.println("================================================================");
        Dealer.DealerHand.showHand("DEALER_OVERLOADED_HAND");

        Player.PlayerHand.showHand();

        if (Player.PlayerHand.isHandOver())
        {
            System.out.println("YOU LOST cause your hand is over 21 \n\n");
        }
        else if (Dealer.DealerHand.totalOfHand() > Player.PlayerHand.totalOfHand())
        {
            System.out.println("YOU LOST because the dealer hand is greater than yours \n\n");
        }
        else if (Dealer.DealerHand.totalOfHand() < Player.PlayerHand.totalOfHand())
        {
            System.out.println("YOU WIN because your hand is greater than the dealers\n\n");
        }
        else
            System.out.println("A DRAW because both your hands are the same\n\n");
        
		
	}

}

class Deck
{
	private Stack<Cards> cardStack = new Stack<Cards>();
	
    //for shuffling use this 
    /*
     * Collections.shuffle(list) to shuffle a list with the standard Java library 
     * or Collections.shuffle(Arrays.asList(a)) to shuffle the entries in an array.
     */
	
    public Cards DealCard()
    {
        return cardStack.pop();         
    }
    
    public Deck()
    {
        //initialize a default set of 52 cards
        //create suit running through loop
        for (int i = 1; i <= 4; i++)
        {
            String tempSuit;
            int tempValue;
            int tempID = 1;

            //suit classification. 1 == spades, 2 = hearts; 3 = clubs; 4 = diamond;
            if (i == 1) { tempSuit = "Spades"; }
            else if (i == 2) { tempSuit = "Hearts"; }
            else if (i == 3) { tempSuit = "Clubs"; }
            else { tempSuit = "Diamonds"; }

            //create card values running through loop
            for (int j = 1; j <= 13; j++)
            {
                tempValue = j; //value of iteration

                if(tempValue > 10) { tempValue = 10; }


                Cards a = new Cards(tempID, tempSuit, tempValue);
               cardStack.push(a);
               tempID++;
            }
        }
        
        Collections.shuffle(cardStack);
    }
        public Deck(boolean emptyDeck){ if (emptyDeck == true) {/*initialize empty Deck/Stack*/}
    }
        }

	

        //uses a card class to create 52 cards by default
          class Cards
        {
            protected  String suitName;
            protected  int cardValue;
            protected  int uniqueID;

            public Cards(){ }
            //take the values requested by the deck and create a card based on the request.
            public Cards(int uID, String sName, int cValue)
            {
                uniqueID = uID; suitName = sName; cardValue = cValue;
            }  

            public String getSuitName() { return suitName; }
            public int getCardValue() { return cardValue; }
            public int getUniqueID() { return uniqueID; }
        }

        class Hand
        {
            private int totalInHand = 0; 
            private Stack<Cards> cardStack = new Stack<Cards>();
            private Queue<Cards> cardQueue = new LinkedList<Cards>();
            

            public void hit(Cards a)
            {
                totalInHand += a.getCardValue();
                cardStack.push(a);
            }

            public void stand() { /*do nothing cause they are standing*/}

            public int totalOfHand() { return totalInHand; }
            public boolean isHandOver() { if (totalInHand > 21) return true; else return false; }

            public void peekHand()
            {
                System.out.println("This is the dealer hand\n" + cardStack.peek().getCardValue() + " " + cardStack.peek().getSuitName()); //DOUBLE CHECK TO SEE IF THIS EVEN WORKS. CHECKING ON THE TOP CARD
                System.out.println("a hidden card");
                System.out.println("");
            }

            public void showHand() //1 for dealer 2 for player
            {

                System.out.println("\n\n This is your hand\n\n");
                while (cardStack.empty() != true)
                {
                    Cards a = cardStack.pop();              
                    System.out.println(a.getCardValue() + " " + a.getSuitName()); //return the value of the card and the suit name
                      //show the card suit
                    cardQueue.add(a);              
                }

                while(cardQueue.isEmpty() != true)
                {
                    Cards a = cardQueue.remove();
                    cardStack.push(a);
                }
            }

            public void showHand(String OVERLOADED_STATE_FOR_DEALER) //1 for dealer 2 for player
            {
                System.out.println("\n\n This is the Dealers Hand\n\n");
                while (cardStack.empty() != true)
                {
                    Cards a = cardStack.pop();
                    System.out.println(a.getCardValue() + " " + a.getSuitName()); //return the value of the card and the suit name
                                                                                 //show the card suit
                    cardQueue.add(a);
                }

                while (cardQueue.isEmpty() != true)
                {
                    Cards a = cardQueue.remove();
                    cardStack.push(a);
                }
            }
        }
    
