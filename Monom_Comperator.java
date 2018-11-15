package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	public int compare(Monom m1, Monom m2) {
		return m2.get_power()-m1.get_power();
	}
}