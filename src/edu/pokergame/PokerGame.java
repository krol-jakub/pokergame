package edu.pokergame;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class PokerGame {
	private Hand cardsInPlayer1Hand = null;
	private Hand cardsInPlayer2Hand = null;
	private ArrayList<String> allPokerGames = new ArrayList<String>();
	
	public void readFromFile() throws FileNotFoundException {
		String line = null;
		BufferedReader lineReader = new BufferedReader(new FileReader("poker.txt"));
		
		try {
			while((line = lineReader.readLine()) != null) {
				allPokerGames.add(line);
			}
			lineReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doTheSplit(String input) {
		cardsInPlayer1Hand = new Hand();
		cardsInPlayer2Hand = new Hand();
		String[] getCardsFromInput = input.split("\\s+");
		for(int i = 0 ; i < getCardsFromInput.length ; i++) {
			String[] splitCardToChars = getCardsFromInput[i].split("(?!^)");
			if(i < 5) {
				cardsInPlayer1Hand.putCardToTheCardList(splitCardToChars[0]);
				cardsInPlayer1Hand.putCardSuiteToMap(splitCardToChars[1]);				
			}
			if(i >= 5) {
				cardsInPlayer2Hand.putCardToTheCardList(splitCardToChars[0]);
				cardsInPlayer2Hand.putCardSuiteToMap(splitCardToChars[1]);
			}
		}
		if(getCardsFromInput.length > 5) {
			cardsInPlayer2Hand.areAllCardsTheSameSuit();			
			cardsInPlayer2Hand.hashToTreeMapCards();
		}
		cardsInPlayer1Hand.areAllCardsTheSameSuit();
		cardsInPlayer1Hand.hashToTreeMapCards();
	}
	
	public String playRound(Hand cardsInPlayerHand) {
		cardsInPlayerHand.setDistanceHighToLow();
		cardsInPlayerHand.countSameCardsInHands();
		cardsInPlayerHand.setResult(Result.HIGH);
		if(cardsInPlayerHand.getCountTwo() == 1) {
			if(cardsInPlayerHand.getCountThree() == 1) {
				cardsInPlayerHand.setResult(Result.FULL);
			}
			cardsInPlayerHand.setResult(Result.PAIR);
		}
		if(cardsInPlayerHand.getCountTwo() == 2) {
			cardsInPlayerHand.setResult(Result.TWO_PAIRS);
		}
		if(cardsInPlayerHand.getCountThree() == 1) {
			cardsInPlayerHand.setResult(Result.THREE);
		}
		if(cardsInPlayerHand.getCountFour() == 1) {
			cardsInPlayerHand.setResult(Result.QUAD);
		}
		if(cardsInPlayerHand.getAreAllCardSameSuit()) {
			cardsInPlayerHand.setResult(Result.COLOR);
		}
		if(cardsInPlayerHand.getDistanceHighToLow() == 4) {
			if(cardsInPlayerHand.getAreAllCardSameSuit()) {
				if(CardValue.fromString(cardsInPlayerHand.getSortedSet().first().getKey()).equals(CardValue.ACE)) {
					cardsInPlayerHand.setResult(Result.ROYAL_FLUSH);
				}
				cardsInPlayerHand.setResult(Result.STRAIGHT_FLUSH);
			}
		}
		if(cardsInPlayerHand.getSortedSet().first().getValue() == 1) {
			if(cardsInPlayerHand.getDistanceHighToLow() == 4) {
				cardsInPlayerHand.setResult(Result.STREET);				
			}
		}
		return cardsInPlayerHand.getFinalResult().name();
	}
	
	public boolean whoWonTheRound() {
		// method returns true if Player 2 wins, false if Player 1 wins. There are no draws.
		if(cardsInPlayer1Hand.getFinalResult().getHandResult() > cardsInPlayer2Hand.getFinalResult().getHandResult()) {
			return false;			
		}
		if(cardsInPlayer1Hand.getFinalResult().getHandResult()< cardsInPlayer2Hand.getFinalResult().getHandResult()) {
			return true;			
		}
		if(cardsInPlayer1Hand.getHighestHandFigure() == cardsInPlayer2Hand.getHighestHandFigure()) {
			cardsInPlayer1Hand.resetHighestCard(cardsInPlayer2Hand);			
		}
		return cardsInPlayer1Hand.getHighestHandFigure() < cardsInPlayer2Hand.getHighestHandFigure();
	}
	
	public int getPokerGamesCount() {
		return allPokerGames.size();
	}
	
	public ArrayList<String> getAllPokerGames() {
		return allPokerGames;
	}
	
	public Hand returnPlayerOne() {
		return cardsInPlayer1Hand;
	}
	
	public Hand returnPlayerTwo() {
		return cardsInPlayer2Hand;
	}
}
