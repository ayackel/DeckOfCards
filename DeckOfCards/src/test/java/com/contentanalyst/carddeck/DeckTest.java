package com.contentanalyst.carddeck;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
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

		lookForDuplicateCards();
	}

	@Test
	public void testShuffleRandomness() {
		long[][] counts = new long[4][CARDS_IN_DECK];

		//init counts
		for(int i=0; i<4; i++) {
			for(int j=0; j<CARDS_IN_DECK; j++) {
				counts[i][j] = 0;
			}
		}
		
		//check random distribution of first four cards
		for(int run=0; run<10000; run++) {
			deck.shuffle();
			for(int i=0; i<4; i++) {
				Card card = deck.dealsOneCard();
				int index = card.getSuit().ordinal() * Rank.values().length;
				index += card.getRank().ordinal();
				counts[i][index]++;
			}
		}
		
		//If the chi-square value is less than 196 then it passes 99% randomness threshold.  
		//The value 196 comes from a chi-squared table for 153 degrees of freedom ( (52-1)*(4-1) = 153).
		ChiSquareTest chiSquareTest = new ChiSquareTest();
 		double chiSquare = chiSquareTest.chiSquare(counts);
 		assertTrue(chiSquare < 196.62);
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
