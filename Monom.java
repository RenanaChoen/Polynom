
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Renana
 *
 */
public class Monom implements function{
	/**
	 * Objects of coefficient and power in Monom
	 */
	private double _coefficient; 
	private int _power;
	/**
	 * constructor of Monom
	 * @param a
	 * @param b
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
		if(b<0)
		{
			throw new RuntimeException("b<0 ");	
		}
	}
	/**
	 * create a deep copy of this Monom
	 * @param other
	 */
	public Monom(Monom other) {
		this(other.get_coefficient(), other.get_power());
	}
	/**
     * Test if this Monom is logically equals to m1.
	 * @param m1
	 * @return true iff this Monom represents the same function ans m1
	 */
	public boolean equals(Monom m1)
	{		
		return this._coefficient==m1.get_coefficient()&&this._power==m1.get_power();
	}
	/**
	 * Multiply this Monom by other
	 * @param m1
	 */
	public void multiply(Monom m1) {
		this._coefficient*=m1._coefficient;
		this._power+=m1._power;
	}
	/**
	 * add other to this Monom
	 * @param m1
	 */
	public void add(Monom m1) {
		if (this._power==m1._power) {
			this._coefficient+=m1._coefficient;
		}
		else  	
			throw new RuntimeException("You can't connect 2 Monoms whit diffrent power");
	}
	/**
	 * Compute a new Monom which is the derivative of this Monom
	 */
	public void derivative() {
		if (this._power==0)
		{
			this._coefficient=0;
			this._power=0;	
		}
		else
		{
		this._coefficient*=this.get_power();
		this._power=this.get_power()-1;
		}
	}
	/**
	 * print this Monom
	 */
	public String toString() {
		if (_power==1)
			return " +" + _coefficient + "x";
		else if (_power==0)
			return " +" + _coefficient;
		else
			return " +" + _coefficient +"x"+"^"+_power;
	}
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/**
	 * return this Monom value at f(x)
	 */
	@Override
	public double f(double x) {
		return get_coefficient()*Math.pow(x, get_power());
	} 
}
