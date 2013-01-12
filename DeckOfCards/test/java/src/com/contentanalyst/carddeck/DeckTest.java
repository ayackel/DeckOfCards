package com.contentanalyst.carddeck;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {

	Deck deck;
	
	@Before
	public void setup() {
		deck = new Deck();
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

}
