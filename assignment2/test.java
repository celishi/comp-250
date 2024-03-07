package assignment2;

import assignment2.Deck.Card;
import assignment2.Deck.PlayingCard;

public class test {
	public static void main(String[] args) {
		Deck x = new Deck(3, 2);
		x.shuffle();
		// x.print();

		SolitaireCipher test = new SolitaireCipher(x);
		String msg = test.encode("Is that you, Bob?");
		System.out.println(msg);

		SolitaireCipher coded = new SolitaireCipher(x);
		String out = coded.decode(msg);
		System.out.println(out);

		// System.err.println(x.head.prev);
		// Deck y = new Deck(x);
		// System.err.println("tail of y is " + y.head.prev);
		// x.addCard(y.head.prev);
		// System.out.println("new card added");
		// y.addCard(x.head.next.next.next);
		// Card card = y.lookUpCard();
		// System.out.println("looked up card is " + card);
		// Card jokerR = y.locateJoker("red");
		// Card jokerB = y.locateJoker("black");
		// Card jokerR = y.head;
		// Card jokerB = y.head.prev.prev.prev;

		// System.out.println("doing count up");
		// y.countCut();
		// System.out.println("generating");
		// int value = x.generateNextKeystreamValue();
		// System.out.println("value of keystream is " + value);
		// System.out.println("tail of y is " + y.head.prev);
		// Joker locatedJoker = y.locateJoker("red");
		// System.out.println(locatedJoker.toString());
		// System.out.println("next to joker is: " + locatedJoker.next.toString());
		// System.out.println("prev to joker is: " + locatedJoker.prev.toString());
	}
}
