package edu.pokergame;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hand {
	private SortedSet<Map.Entry<String, Integer>> listOfSortedCards = new TreeSet<Map.Entry<String, Integer>>(
            new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    if(e1.getValue() > e2.getValue()) {
                    	return (-1);
                    }
                    if(e1.getValue() < e2.getValue()) {
                    	return 1;
                    }
                    if(CardValue.fromString(e1.getKey()).getCardNumber() > CardValue.fromString(e2.getKey()).getCardNumber()) {
                    	return (-1);
                    }
                    if(CardValue.fromString(e1.getKey()).getCardNumber() < CardValue.fromString(e2.getKey()).getCardNumber()) {
                    	return 1;
                    }
                    return 0;
                }
            });
	// HashMap dla karty Key - wartosc karty (jej figura), Value - ilosc wystapien karty w secie
	private HashMap<String,Integer> listOfHandCards = new HashMap<String,Integer>();
	private HashMap<String,Integer> listOfCardSuites = new HashMap<String,Integer>();
	private int highestFigureInHand = 0;
	private int distanceHighToLow = 0;
	private boolean areAllCardSameSuit = false;
	private Result handResult = Result.HIGH;
	private int countTwo = 0;
	private int countThree = 0;
	private int countFour = 0;
	
	public Hand() {
		listOfHandCards.clear();
		listOfCardSuites.clear();
		listOfSortedCards.clear();
	}
	
	public void putCardToTheCardList(String card) {
		if(listOfHandCards.containsKey(card)) {
			int val = listOfHandCards.get(card);
			listOfHandCards.put(card, (val+1));
			return;
		}
		listOfHandCards.put(card, 1);
	}
	
	public void putCardSuiteToMap(String card) {
		if(listOfCardSuites.containsKey(card)) {
			int val = listOfCardSuites.get(card);
			listOfCardSuites.put(card, (val+1));
			return;
		}
		listOfCardSuites.put(card, 1);
	}
	
	public void countSameCardsInHands() {
		for(final Iterator<Entry<String, Integer>> it = listOfSortedCards.iterator() ; it.hasNext() ; ){
			Entry<String, Integer> tmp = it.next();
			if(tmp.getValue() == 2) {
				setCountTwo(getCountTwo() + 1);
			}
			if(tmp.getValue() == 3) {
				setCountThree(getCountThree() + 1);
			}
			if(tmp.getValue() == 4) {
				setCountFour(getCountFour() + 1);
			}
		}
	}
	
	public void hashToTreeMapCards() {
		listOfSortedCards.addAll((Collection<? extends Entry<String, Integer>>) extracted());
	}

	private Collection<? extends Entry<String, Double>> extracted() {
		return (Collection<? extends Entry<String, Double>>) listOfHandCards.entrySet();
	}
	
	public SortedSet<Map.Entry<String, Integer>> getSortedSet() {
		return listOfSortedCards;
	}
	
	public void setHighestHandFigure(int highest) {
		highestFigureInHand = highest;
	}
	
	public int getHighestHandFigure() {
		return highestFigureInHand;
	}
	
	public boolean getAreAllCardSameSuit() {
		return areAllCardSameSuit;
	}

	public void setAreAllCardSameSuit(boolean areAllCardSameSuit) {
		this.areAllCardSameSuit = areAllCardSameSuit;
	}
	
	public void areAllCardsTheSameSuit() {
		if(listOfCardSuites.size() == 1) {
			setAreAllCardSameSuit(true);			
		}
	}

	public int getDistanceHighToLow() {
		return distanceHighToLow;
	}

	public void setDistanceHighToLow() {
		int distance = CardValue.fromString(listOfSortedCards.first().getKey()).getCardNumber() - 
				CardValue.fromString(listOfSortedCards.last().getKey()).getCardNumber();
		this.distanceHighToLow = Math.abs(distance);
	}
	
	public void resetHighestCard(Hand hand) {
		final Iterator<Entry<String, Integer>> it = hand.getSortedSet().iterator(); 
		final Iterator<Entry<String, Integer>> it2 = this.getSortedSet().iterator(); 
		while(it.hasNext() && it2.hasNext()) {
			Entry<String, Integer> tmp = it.next();
			Entry<String, Integer> tmp2 = it2.next();
			if(!tmp.getKey().equals(tmp2.getKey())) {
				hand.setHighestHandFigure(CardValue.fromString(tmp.getKey()).getCardNumber());
				this.setHighestHandFigure(CardValue.fromString(tmp2.getKey()).getCardNumber());
				return;
			}
		}
	}
	
	public void setResult(Result result) {
		if(handResult.getHandResult() < result.getHandResult()) {
			handResult = result;			
		}
		highestFigureInHand = CardValue.fromString(listOfSortedCards.first().getKey()).getCardNumber();
	}
	
	public int getActuallyFoundResult() {
		return handResult.getHandResult();
	}
	
	public Result getFinalResult() {
		return handResult;
	}

	public int getCountTwo() {
		return countTwo;
	}

	public void setCountTwo(int countTwo) {
		this.countTwo = countTwo;
	}

	public int getCountThree() {
		return countThree;
	}

	public void setCountThree(int countThree) {
		this.countThree = countThree;
	}

	public int getCountFour() {
		return countFour;
	}

	public void setCountFour(int countFour) {
		this.countFour = countFour;
	}
}
