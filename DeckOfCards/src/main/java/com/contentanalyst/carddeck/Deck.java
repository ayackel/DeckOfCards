package com.contentanalyst.carddeck;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/**
 * @author Alan
 *
 */
public class Deck {

	private static final int CARDS_IN_FULL_DECK = 52;
	private List<Card> cards = Collections.emptyList();

	private static final Random RNG = new SecureRandom();

	public Deck() {
		cards = createNewDeck();
	}

	List<Card> createNewDeck() {
		List<Card> freshDeck = new ArrayList<Card>(CARDS_IN_FULL_DECK);
		for (Rank rank : EnumSet.allOf(Rank.class)) {
			for (Suit suit : EnumSet.allOf(Suit.class)) {
				freshDeck.add(new Card(rank, suit));
			}
		}
		return freshDeck;
	}

	/**
	 * Resets the deck and shuffles it
	 */
	public void shuffle() {
		//Shuffles twice because the shuffle algorithm left "holes" where certain cards 
		//could never be in certain locations in the deck.  This has to do with starting 
		//from an order deck.  Second shuffle doesn't have this problem.
		cards = doShuffle(createNewDeck());
		cards = doShuffle(cards);
	}

	/**
	 * Randomizes the deck.  Uses "inside-out" Fisher–Yates shuffle. 
	 */
	private List<Card> doShuffle(List<Card> freshDeck) {
		List<Card> shuffledDeck = new ArrayList<Card>(CARDS_IN_FULL_DECK);
		shuffledDeck.addAll(freshDeck);
		for (int i = 1; i < CARDS_IN_FULL_DECK; i++) {
			int j = RNG.nextInt(i);
			shuffledDeck.set(i, shuffledDeck.get(j));
			shuffledDeck.set(j, freshDeck.get(i));
		}

		return shuffledDeck;
	}

	/**
	 * Deals the top card from the deck. 
	 * If the deck is empty a EmptyStackException is thrown.
	 * @return top Card
	 */
	public Card dealsOneCard() {
		if(hasCards())
			return cards.remove(cards.size() - 1);
		throw new EmptyStackException();
	}

	public boolean hasCards() {
		return !cards.isEmpty();
	}

	int cardsInDeck() {
		return cards.size();
	}

	void printDeck() {
		for (Card card : cards) {
			System.out.println(card);
		}
	}
}
