package com.contentanalyst.carddeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;


public class Deck {

	private static final int CARDS_IN_FULL_DECK = 52;
  private List<Card> cards = Collections.emptyList();

  private static final Random RNG = new Random();

  public Deck() {
    cards = createNewDeck();
  }

  List<Card> createNewDeck() {
    List<Card> freshDeck = new ArrayList<Card>(CARDS_IN_FULL_DECK);
    for(Rank rank : EnumSet.allOf(Rank.class)) {
      for(Suit suit : EnumSet.allOf(Suit.class)) {
        freshDeck.add(new Card(rank, suit));
      }
    }
    return freshDeck;
  }

  public void shuffle() {
    List<Card> freshDeck = createNewDeck();
    cards = doShuffle(freshDeck);
  }

	/**
	 * Fisher–Yates shuffle.  Randomizes the deck;
	 */
	private List<Card> doShuffle(List<Card> freshDeck) {
	  List<Card> shuffledDeck = new ArrayList<Card>();
	  shuffledDeck.addAll(freshDeck);
	  for(int i=1; i < CARDS_IN_FULL_DECK; i++) {
	    int j = RNG.nextInt(i);
	    shuffledDeck.set(i, shuffledDeck.get(j));
	    shuffledDeck.set(j, freshDeck.get(i));
	  }

	  return shuffledDeck;
  }

  public Card dealsOneCard() {
		return cards.remove(cards.size() - 1);
	}

  int cardsInDeck() {
    return cards.size();
  }

  void printDeck() {
    for(Card card : cards) {
      System.out.println(card);
    }
  }
}
