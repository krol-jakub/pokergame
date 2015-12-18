package edu.pokergame;

/*
 *  Rezultaty sa uporzadkowane wzgledem wartosci figur w pokerze tak zeby dalo sie porownac 2 rezultaty w latwy sposob 
 *  patrzac na ich ordinal()
 */

public enum Result {
	HIGH(1),
	PAIR(2),
	TWO_PAIRS(3),
	THREE(4),
	STREET(5),
	COLOR(6),
	FULL(7),
	QUAD(8),
	STRAIGHT_FLUSH(9),
	ROYAL_FLUSH(10);
	
	private int handResult;

	private Result(int result) {
		handResult = result;
	}
	
	public int getHandResult() {
		return handResult;
	}
	
	public void setNewResult(Result high2) {
		handResult = high2.getHandResult();
	}
}
