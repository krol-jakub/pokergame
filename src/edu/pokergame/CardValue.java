package edu.pokergame;

public enum CardValue {
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("T", 10),
	JACK("J", 11),
	QUEEN("Q", 12),
	KING("K",13),
	ACE("A", 14);
	
	private String cardValue;
	private int cardNumber;
	
	private CardValue(String value, int number) {
		this.cardValue = value;
		this.cardNumber = number;
	}
	
	public String getCardValue() {
		return cardValue;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setCardSymbol(String s) {
		cardValue = s;
	}
	
	public void setCardNumber(int number) {
		cardNumber = number;
	}
	
	public static CardValue fromString(String text) {
	    if (text != null) {
	      for (CardValue current : CardValue.values()) {
	        if (text.equalsIgnoreCase(current.getCardValue())) {
	        	return current;
	        }
	      }
	    }
	    return null;
	}
}
