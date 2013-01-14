package com.contentanalyst.carddeck;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.contentanalyst.carddeck.Card;
import com.contentanalyst.carddeck.Deck;

public class DeckTest {

	private static final int CARDS_IN_DECK = 52;
	Deck deck;

	@Before
	public void setup() {
		deck = new Deck();
	}

	@Test
	public void testShuffle() {
		deck.shuffle();

		deck.printDeck();

		lookForDuplicateCards();
	}

	@Test
	public void testCreateNewDeck() {
		deck.createNewDeck();
		assertEquals(52, deck.cardsInDeck());

		lookForDuplicateCards();
	}

	private void lookForDuplicateCards() {
		Set<Card> cards = new HashSet<Card>();
		for (int i = 0; i < CARDS_IN_DECK; i++) {
			boolean unique = cards.add(deck.dealsOneCard());
			assertTrue(unique);
		}
	}

	@Test
	public void testDealsOneCard() {
		Card card = deck.dealsOneCard();
		assertNotNull(card);
	}

	@Test
	public void testDealsOneCard_Twice() {
		Card card = deck.dealsOneCard();
		assertNotNull(card);
		Card card2 = deck.dealsOneCard();
		assertNotNull(card2);
		assertThat(card, not(equalTo(card2)));
	}

	@Test(expected = EmptyStackException.class)
	public void testDealsOneCard_53times() {
		for (int i = 0; i < CARDS_IN_DECK; i++) {
			deck.dealsOneCard();
		}

		deck.dealsOneCard();
	}

}
