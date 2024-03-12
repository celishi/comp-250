package assignment2;

import java.util.Random;

public class Deck {
	public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
	public static Random gen = new Random();

	public int numOfCards; // contains the total number of cards in the deck
	public Card head; // contains a pointer to the card on the top of the deck

	/* 
	 * TODO: Initializes a Deck object using the inputs provided
	 */
	public Deck(int numOfCardsPerSuit, int numOfSuits) {
		boolean setHead = false;
		Card placeHolder = null;

		if ( !(1 <= numOfCardsPerSuit && numOfCardsPerSuit <= 13) || 
			!(1 <= numOfSuits && numOfSuits <= suitsInOrder.length)) {
			throw new IllegalArgumentException("wrong number for cards and/or suit");
		}
		else {
			for(int i=0; i<numOfSuits;i++) {
				String suit = suitsInOrder[i];
				for(int j=1;j<=numOfCardsPerSuit;j++) {
					if (!setHead){
						this.head = new PlayingCard(suit, j);
						setHead = true;
						placeHolder = this.head;
						this.numOfCards++;
						// System.out.println(this.head);
						continue;
					}
					Card newCard = new PlayingCard(suit, j);
					placeHolder.next = newCard;
					newCard.prev = placeHolder;
					// System.out.println(newCard);
					placeHolder = newCard;
					this.numOfCards++;
				}
			}
			Card redJ = new Joker("red");
			Card blackJ = new Joker("black");
			placeHolder.next = redJ;
			redJ.next = blackJ;
			redJ.prev = placeHolder;
			blackJ.next = this.head;
			blackJ.prev = redJ;
			this.head.prev = blackJ;
			// System.out.println(placeHolder.next);
			// System.out.println(placeHolder.next.next);
			// System.out.println(placeHolder.next.next.next);
			this.numOfCards += 2;
		}
	}

	/* 
	 * TODO: Implements a copy constructor for Deck using Card.getCopy().
	 * This method runs in O(n), where n is the number of cards in d.
	 */
	public Deck(Deck d) {
		Card temp = d.head.getCopy();
		this.numOfCards = d.numOfCards;
		this.head = temp;
		Card current = d.head;
		// System.out.println("head of list is: " + temp.toString());
		// System.out.println("size of list is: " + this.numOfCards);

		for(int i=0; i<d.numOfCards - 1;i++){
			temp.next = current.next.getCopy();
			temp.next.prev = temp;
			temp = temp.next;
			current = current.next;
			// System.out.println("next in list is: " + temp.toString());
			// System.out.println(temp == current);
		}

		temp.next = this.head;
		this.head.prev = temp;
	}

	/*
	 * For testing purposes we need a default constructor.
	 */
	public Deck() {
		this.head = null;
		this.numOfCards = 0;
	}

	public void print(){
		Card temp = this.head;
		for(int i=0; i<this.numOfCards;i++){
			System.out.println(temp);
			temp = temp.next;
		}
	}

	/* 
	 * TODO: Adds the specified card at the bottom of the deck. This 
	 * method runs in $O(1)$. 
	 */
	public void addCard(Card c) {
		if(this.head == null){
			this.head = c;
			this.head.prev = this.head;
			this.head.next = this.head;
		}
		else {
			Card oldPrev = this.head.prev;
			oldPrev.next = c;
			this.head.prev = c;
			c.prev = oldPrev;
			c.next = this.head;
		}
		this.numOfCards++;
	}
	
	/*
	 * TODO: Shuffles the deck using the algorithm described in the pdf. 
	 * This method runs in O(n) and uses O(n) space, where n is the total 
	 * number of cards in the deck.
	 */
	public void shuffle() {
		Card[] copy = new Card[this.numOfCards];
		Card temp = this.head;

		for(int i=0; i<this.numOfCards;i++){
			copy[i] = temp;
			temp = temp.next;
		}

		for(int i=copy.length - 1; i>0; i--) {
			int num = gen.nextInt(i+1);
			Card placeHolder = copy[i];
			copy[i] = copy[num];
			copy[num] = placeHolder;
		}

		this.head = null;
		this.numOfCards = 0;

		for(int i=0; i<copy.length;i++){
			this.addCard(copy[i]);
		}
	}

	/*
	 * TODO: Returns a reference to the joker with the specified color in 
	 * the deck. This method runs in O(n), where n is the total number of 
	 * cards in the deck. 
	 */
	public Joker locateJoker(String color) {
		Card current = this.head;

		for(int i=0; i<this.numOfCards;i++){
			if(current instanceof Joker && ((Joker) current).getColor() == color ){
				return (Joker) current;
			}
			current = current.next;
		}
		return null;
	}

	/*
	 * TODO: Moved the specified Card, p positions down the deck. You can 
	 * assume that the input Card does belong to the deck (hence the deck is
	 * not empty). This method runs in O(p).
	 */
	public void moveCard(Card c, int p) {
		Card card = c;
		Card cardNext = c.next;
		Card cardPrev = c.prev;

		for(int i=0; i<p; i++){
			if(card.next == c){
				i--;
			}
			card = card.next;
		}
		
		Card next = card.next;
		if(next != c){
			cardNext.prev = cardPrev;
			cardPrev.next = cardNext;
			card.next = c;
			c.prev = card;
			c.next = next;
			next.prev = c;
		}
		else{

		}
	}

	/*
	 * TODO: Performs a triple cut on the deck using the two input cards. You 
	 * can assume that the input cards belong to the deck and the first one is 
	 * nearest to the top of the deck. This method runs in O(1)
	 */
	public void tripleCut(Card firstCard, Card secondCard) {
		Card first = this.head;
		Card last = this.head.prev;
		Card newTail = firstCard.prev;
		if (firstCard == this.head && secondCard == this.head.prev){
			//do nothing
		}
		else if (secondCard == this.head.prev){
			this.head = firstCard;
		}
		else if (firstCard == this.head){
			this.head = secondCard.next;

		}         
		else {
			this.head = secondCard.next;
			this.head.prev = newTail;
			secondCard.next = first;
			first.prev = secondCard;
			last.next = firstCard;
			firstCard.prev = last;
			newTail.next = this.head;			
		}
	}

	/*
	 * TODO: Performs a count cut on the deck. Note that if the value of the 
	 * bottom card is equal to a multiple of the number of cards in the deck, 
	 * then the method should not do anything. This method runs in O(n).
	 */
	public void countCut() {
		int count = this.head.prev.getValue() % this.numOfCards;
		Card first = this.head;
		Card tail = this.head.prev;

		if (count != 0){
			Card last = this.head;
			for(int i=1; i<count; i++){
				last = last.next;
			}

			first.prev = tail.prev;
			tail.prev.next = first;
			this.head = last.next;
			last.next = tail;
			this.head.prev = tail;
			tail.next = this.head;
			tail.prev = last;
		}
	}

	/*
	 * TODO: Returns the card that can be found by looking at the value of the 
	 * card on the top of the deck, and counting down that many cards. If the 
	 * card found is a Joker, then the method returns null, otherwise it returns
	 * the Card found. This method runs in O(n).
	 */
	public Card lookUpCard() {
		int value = this.head.getValue() % this.numOfCards;
		// System.out.println("value of last card is " + this.head.getValue());
		// System.out.println("count is " + value);
		Card card = this.head;
		for (int i=0; i<value; i++){
			card = card.next;
		}
		if(card instanceof Joker){
			return null;
		}
		else{
			return card;
		}
	}

	/*
	 * TODO: Uses the Solitaire algorithm to generate one value for the keystream 
	 * using this deck. This method runs in O(n).
	 */
	public int generateNextKeystreamValue() {
		Card lookedUp = null;

		while(lookedUp == null){
			// System.out.println("locating red joker");
			Card redJ = this.locateJoker("red");
			this.moveCard(redJ, 1);
			// this.print();
			// System.out.println("locating black joker");
			Card blackJ = this.locateJoker("black");
			this.moveCard(blackJ, 2);
			// this.print();
	
			// System.out.println("performing triple cut");
			Card temp = this.head;
			for(int i=0; i<this.numOfCards;i++){
				if(temp instanceof Joker){
					if(((Joker) temp).getColor() == "red"){
						// System.out.println("red first");
						this.tripleCut(redJ, blackJ);
						break;
					}
					else if(((Joker) temp).getColor() == "black"){
						// System.out.println("black first");
						this.tripleCut(blackJ, redJ);
						break;
					}
				}
				temp = temp.next;
			}
			// this.print();
	
			// System.out.println("performing countcut");
			this.countCut();
			// this.print();
			lookedUp = this.lookUpCard();
		}

		return lookedUp.getValue();
	}

	public abstract class Card { 
		public Card next;
		public Card prev;

		public abstract Card getCopy();
		public abstract int getValue();

	}

	public class PlayingCard extends Card {
		public String suit;
		public int rank;

		public PlayingCard(String s, int r) {
			this.suit = s.toLowerCase();
			this.rank = r;
		}

		public String toString() {
			String info = "";
			if (this.rank == 1) {
				//info += "Ace";
				info += "A";
			} else if (this.rank > 10) {
				String[] cards = {"Jack", "Queen", "King"};
				//info += cards[this.rank - 11];
				info += cards[this.rank - 11].charAt(0);
			} else {
				info += this.rank;
			}
			//info += " of " + this.suit;
			info = (info + this.suit.charAt(0)).toUpperCase();
			return info;
		}

		public PlayingCard getCopy() {
			return new PlayingCard(this.suit, this.rank);   
		}

		public int getValue() {
			int i;
			for (i = 0; i < suitsInOrder.length; i++) {
				if (this.suit.equals(suitsInOrder[i]))
					break;
			}

			return this.rank + 13*i;
		}

	}

	public class Joker extends Card{
		public String redOrBlack;

		public Joker(String c) {
			if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black")) 
				throw new IllegalArgumentException("Jokers can only be red or black"); 

			this.redOrBlack = c.toLowerCase();
		}

		public String toString() {
			//return this.redOrBlack + " Joker";
			return (this.redOrBlack.charAt(0) + "J").toUpperCase();
		}

		public Joker getCopy() {
			return new Joker(this.redOrBlack);
		}

		public int getValue() {
			return numOfCards - 1;
		}

		public String getColor() {
			return this.redOrBlack;
		}
	}

}
