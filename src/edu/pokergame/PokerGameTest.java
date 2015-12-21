package edu.pokergame;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PokerGameTest {
	// komentarz
	
	private PokerGame poker;
	
	@Before
	public void setUp() {
		poker = new PokerGame();	
	}
	
	@Test
	public void shouldHandleOnePair() {
		// given
		
		// when
		poker.doTheSplit("2H 2D 5D 6D AS");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("PAIR", result);
	}
	
	@Test
	public void shouldHandleTwoPairs() {
		// given
		
		// when
		poker.doTheSplit("2H 2D 5D 5S AS");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("TWO_PAIRS", result);
	}
	
	@Test
	public void shouldHandleThree() {
		// given
		
		// when
		poker.doTheSplit("2H 2D 6D 5S 2C");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("THREE", result);
	}
	
	@Test
	public void shouldHandleFull() {
		// given
		
		// when
		poker.doTheSplit("2H 2D 5D 5S 5C");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("FULL", result);
	}
	
	@Test
	public void shouldHandleQuads() {
		// given
		
		// when
		poker.doTheSplit("5H 2D 5D 5S 5C");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("QUAD", result);
	}
	
	@Test
	public void shouldHandleColor() {
		// given
		
		// when
		poker.doTheSplit("2H 4H 5H 7H AH");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("COLOR", result);
	}
	
	@Test
	public void shouldHandleStreet() {
		// given
		
		// when
		poker.doTheSplit("2H 3D 4D 5H 6S");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("STREET", result);
	}
	
	@Test
	public void shouldHandleDistanceFourButNotStreet() {
		// given
		
		// when
		poker.doTheSplit("2H 2D 4D 5H 6S");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("PAIR", result);
	}
	
	@Test
	public void shouldHandlePoker() {
		// given
		
		// when
		poker.doTheSplit("7D 8D 9D TD JD");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("STRAIGHT_FLUSH", result);
	}
	
	@Test
	public void shouldHandleRoyalPoker() {
		// given
		
		// when
		poker.doTheSplit("TH JH QH KH AH");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("ROYAL_FLUSH", result);
	}
	
	@Test
	public void shouldHandleHighCard() {
		// given
		
		// when
		poker.doTheSplit("TH 2C 3D KH 5S");
		String result = poker.playRound(poker.returnPlayerOne());
		
		// then
		Assert.assertEquals("HIGH", result);
	}
	
	@Test
	public void shouldReadAllLines() throws FileNotFoundException {
		// given
		
		// when
		poker.readFromFile();
		
		// then
		Assert.assertEquals(1000, poker.getPokerGamesCount());
	}
	
	@Test
	public void shouldHandleTwoPlayerGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 2D 5D 5S 5C TH 2C 3D KH 5S");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(false, whoWon);
	}
	
	@Test
	public void shouldHandleAnotherTwoPlayerGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 2D 3S 4S 5D 5H 5D AS QH KC");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleTwoPlayerFullGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 2D 3S 3C 3H 2S 2C AS AH AC");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleTwoPlayerPairGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 2D 5S 5C AH 2S 2C 7S 7H 4C");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleAnotherTwoPlayerPairGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 2D TS TC AH 2S 2C QS QH 4C");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleYetAnotherTwoPlayerPairGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 2D TS TC 4H 2S 2C TD TH AC");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleAnotherTwoPlayerFullGame() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("AH AD TS TC TH QS QC JS JH JC");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleAnotherTwoPlayerGameWithHighs() throws FileNotFoundException {
		// given
		
		// when
		poker.doTheSplit("2H 3D 7S 8C JH 4S 2C 6S JC QC");
		poker.playRound(poker.returnPlayerOne());
		poker.playRound(poker.returnPlayerTwo());
		boolean whoWon = poker.whoWonTheRound();
		
		// then
		Assert.assertEquals(true, whoWon);
	}
	
	@Test
	public void shouldHandleAllTextfileGames() throws FileNotFoundException {
		// given
		int playerOneVictoryCount = 0;
		int playerTwoVictoryCount = 0;
		
		// when
		poker.readFromFile();
		for(String currentGame : poker.getAllPokerGames())
		{
			poker.doTheSplit(currentGame);
			poker.playRound(poker.returnPlayerOne());
			poker.playRound(poker.returnPlayerTwo());
			if(!poker.whoWonTheRound())
			{
				++playerOneVictoryCount;
			}
			if(poker.whoWonTheRound()) {
				++playerTwoVictoryCount;
			}
		}
		System.out.println(String.format("P1: %1$d\nP2: %2$d",playerOneVictoryCount,playerTwoVictoryCount));
		
		// then
		Assert.assertEquals(376, playerOneVictoryCount);
	}

}
