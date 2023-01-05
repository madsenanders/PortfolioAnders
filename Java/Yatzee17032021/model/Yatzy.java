package term1.yatzy.model;

import java.util.Random;

public class Yatzy {
	// Face values of the 5 dice.
	// 1 <= values[i] <= 6.
	private int[] values = new int[5];

	// Number of times the 5 dice have been thrown.
	// 0 <= throwCount <= 3.
	private int throwCount = 0;

	// Random number generator.
	private Random random = new Random();

	public Yatzy() {
		//
	}

	/**
	 * Returns the 5 face values of the dice.
	 */
	public int[] getValues() {
		return this.values;
	}

	/**
	 * Sets the 5 face values of the dice. Pre: values contains 5 face values in
	 * [1..6]. Note: This method is only meant to be used for test, and therefore
	 * has package visibility.
	 */
	void setValues(int[] values) {
		this.values = values;
	}

	/**
	 * Returns the number of times the 5 dice has been thrown.
	 */
	public int getThrowCount() {
		return this.throwCount;
	}

	/**
	 * Resets the throw count.
	 */
	public void resetThrowCount() {
		this.throwCount = 0;
	}

	/**
	 * Rolls the 5 dice. Only roll dice that are not hold. Pre: holds contain 5
	 * boolean values.
	 */
	public void throwDice(boolean[] holds) {
		throwCount++;
		for (int i = 0; i < this.values.length; i++) {
			if (holds[i] == false) {
				this.values[i] = random.nextInt(6) + 1;
			}
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * Returns all results possible with the current face values. The order of the
	 * results is the same as on the score board. Note: This is an optional method.
	 * Comment this method out, if you don't want use it.
	 */
	public int[] getResults() {
		int[] results = new int[15];
		for (int i = 0; i <= 5; i++) {
			results[i] = this.sameValuePoints(i + 1);
		}
		results[6] = this.onePairPoints();
		results[7] = this.twoPairPoints();
		results[8] = this.threeSamePoints();
		results[9] = this.fourSamePoints();
		results[10] = this.fullHousePoints();
		results[11] = this.smallStraightPoints();
		results[12] = this.largeStraightPoints();
		results[13] = this.chancePoints();
		results[14] = this.yatzyPoints();

		return results;
	}

	// -------------------------------------------------------------------------

	// Returns an int[7] containing the frequency of face values.
	// Frequency at index v is the number of dice with the face value v, 1 <= v
	// <= 6.
	// Index 0 is not used.
	private int[] calcCounts() {
		int[] faceValues = new int[7];

		for (int value : this.values) {
			faceValues[value]++;
		}

		return faceValues;
	}

	/**
	 * Returns same-value points for the given face value. Returns 0, if no dice has
	 * the given face value. Pre: 1 <= value <= 6;
	 */
	public int sameValuePoints(int value) {
		int sum = 0;
		if (value >= 1 && value <= 6) {
			for (int faceValue : this.values) {
				if (faceValue == value) {
					sum++;
				}
			}
		}
		return (sum * value);
	}

	/**
	 * Returns points for one pair (for the face value giving highest points).
	 * Returns 0, if there aren't 2 dice with the same face value.
	 */
	public int onePairPoints() {
		int[] faceValueCount = this.calcCounts();
		int highest = 0;

		for (int i = 1; i < faceValueCount.length; i++) {
			if (faceValueCount[i] >= 2) {
				highest = i;
			}
		}
		return highest * 2;
	}

	/**
	 * Returns points for two pairs (for the 2 face values giving highest points).
	 * Returns 0, if there aren't 2 dice with one face value and 2 dice with a
	 * different face value.
	 */
	public int twoPairPoints() {
		int[] faceValueCount = this.calcCounts();
		int pair1 = 0;
		int pair2 = 0;

		for (int i = 1; i < faceValueCount.length; i++) {
			if (faceValueCount[i] >= 2) {
				if (pair1 != 0) {
					pair2 = i;
				} else {
					pair1 = i;
				}
			}
		}
		if (pair1 == 0 || pair2 == 0) {
			return 0;
		} else {
			return pair1 * 2 + pair2 * 2;
		}
	}

	/**
	 * Returns points for 3 of a kind. Returns 0, if there aren't 3 dice with the
	 * same face value.
	 */
	public int threeSamePoints() {
		int[] faceValueCount = this.calcCounts();
		int highest = 0;

		for (int i = 1; i < faceValueCount.length; i++) {
			if (faceValueCount[i] >= 3) {
				highest = i;
			}
		}
		return highest * 3;
	}

	/**
	 * Returns points for 4 of a kind. Returns 0, if there aren't 4 dice with the
	 * same face value.
	 */
	public int fourSamePoints() {
		int[] faceValueCount = this.calcCounts();
		int highest = 0;

		for (int i = 1; i < faceValueCount.length; i++) {
			if (faceValueCount[i] >= 4) {
				highest = i;
			}
		}
		return highest * 4;
	}

	/**
	 * Returns points for full house. Returns 0, if there aren't 3 dice with one
	 * face value and 2 dice a different face value.
	 */
	public int fullHousePoints() {
		int[] faceValueCount = this.calcCounts();
		int threes = 0;
		int twos = 0;

		for (int i = 1; i < faceValueCount.length; i++) {
			if (faceValueCount[i] == 3) {
				threes = i;
			} else if (faceValueCount[i] == 2) {
				twos = i;
			}
		}
		if (threes == 0 || twos == 0) {
			return 0;
		} else {
			return threes * 3 + twos * 2;
		}
	}

	/**
	 * Returns points for small straight. Returns 0, if the dice are not showing
	 * 1,2,3,4,5.
	 */
	public int smallStraightPoints() {
		int[] faceValueCount = this.calcCounts();

		for (int i = 1; i < faceValueCount.length - 1; i++) {
			if (faceValueCount[i] != 1) {
				return 0;
			}
		}
		return 15;
	}

	/**
	 * Returns points for large straight. Returns 0, if the dice is not showing
	 * 2,3,4,5,6.
	 */
	public int largeStraightPoints() {
		int[] faceValueCount = this.calcCounts();

		for (int i = 2; i < faceValueCount.length; i++) {
			if (faceValueCount[i] != 1) {
				return 0;
			}
		}
		return 20;
	}

	/**
	 * Returns points for chance.
	 */
	public int chancePoints() {
		int sum = 0;
		for (int value : this.values) {
			sum += value;
		}
		return sum;
	}

	/**
	 * Returns points for yatzy. Returns 0, if there aren't 5 dice with the same
	 * face value.
	 */
	public int yatzyPoints() {
		int target = this.values[0];
		for (int value : this.values) {
			if (value != target || value == 0) {
				return 0;
			}
		}
		return 50;
	}

	public void resetFaceValues() {
		for (int i = 0; i < this.values.length; i++) {
			values[i] = 0;
		}
	}

}
