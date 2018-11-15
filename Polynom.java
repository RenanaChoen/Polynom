package myMath;

import java.util.ArrayList;
import java.util.Iterator;

import myMath.Monom;
/**
 * This class represents a general Polynom: f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n,
 * where: a_1, a_2 ... a_n are real numbers and b_1<b_2..<b_n >=0 are none negative integers (naturals)
 * Functions in this class:
 * 1. add Monom, 
 * 2. add Polynom
 * 3. subtract polynom
 * 4. multiply polynoms
 * 5. Riemann's Integral
 * 6. Finding a numerical value between two values (currently support root only f(x)=0).
 * 7. Derivative
 * 
 * @author Renana
 *
 */
public class Polynom implements Polynom_able{
	/**
	 * Object of an array list of monom
	 */
	private ArrayList<Monom> poly;
	/**
	 * Constructor of an empty polynom
	 */
	public Polynom() {
		this.poly= new ArrayList<Monom>();
	}
	/**
	 *Constructor of a polynom that receives a polynom form string 
	 *@param s
	 *@param m monom 
	 *@param spl array of string
	 *@param c string of coefficient in the monom
	 *@param p string of power in the monom
	 *@return array list of monom
	 */
	public Polynom (String s) {
		poly=new ArrayList<Monom>();
		if(s.length()==0) {
			throw new RuntimeException("Polynom can't be empty");
		}
		else {
			Monom m;
			String spl[]= s.split("\\+");
			for (int i=0; i<spl.length; i++) {
				if (spl[i].indexOf("x")!=-1 && spl[i].indexOf("^")!=-1) {
					String c= spl[i].substring(0, spl[i].indexOf("x"));
					double _coefficient= Double.valueOf(c);
					String p= spl[i].substring(spl[i].indexOf("^")+1);
					int _power=Integer.valueOf(p);
					m= new Monom(_coefficient,_power);
					poly.add(m);
				}
				else if (spl[i].indexOf("x")!=-1 && spl[i].indexOf("^")==-1) {
					String c= spl[i].substring(0, spl[i].indexOf("x"));
					double _coefficient= Double.valueOf(c);
					m= new Monom(_coefficient,1);
					poly.add(m);
				}
				else {
					String c= spl[i];
					double _coefficient= Double.valueOf(c);
					m= new Monom(_coefficient,0);
					poly.add(m);
				}
			}
		}
	}
	/**
	 * print the polynom
	 */
	public String toString() {
		String s="";
		Iterator<Monom>iterM= this.poly.iterator();
		while (iterM.hasNext()){
			Monom m= iterM.next();
			s= s + m.toString();
		}
		return s;
	}
	/**
	 * @param x
	 * @return this Polynom value at p(x)
	 */
	@Override
	public double f(double x) {
		double d=0;
		Iterator<Monom>iterM= this.poly.iterator();
		while (iterM.hasNext()){
			Monom m= iterM.next();
			d= d+m.f(x);
		}
		return d;
	}
	/**
	 * Add p1 to this Polynom
	 * @param p1 Polynom
	 * @param m_p1 Monom in array list p1
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom>iterM1 = p1.iteretor();
		while(iterM1.hasNext())
		{
			Monom m_p1=new Monom(iterM1.next());
			this.add(m_p1);	
		}
	}
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 * @param m Monom in array list poly
	 * @param found_power found if have in the poly, Monom whit same power
	 */
	@Override
	public void add(Monom m1) {
		Iterator<Monom>iterM= this.poly.iterator();
		boolean found_power= false;
		while (iterM.hasNext()){
			Monom m= iterM.next();
			if (m.get_power()==m1.get_power()) {
				m.add(m1);
				found_power=true;
			}
		}
		if (found_power==false)
		{
			this.poly.add(m1);
		}
		Monom_Comperator sortpoly= new Monom_Comperator();
		this.poly.sort(sortpoly);
	}
	/**
	 * Subtract p1 from this Polynom
	 * @param p1
	 * @param m_p1 Monom in array list p1
	 * @param neg Monom whit negative coefficient
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom>iterM1 = p1.iteretor();
		while(iterM1.hasNext())
		{
			Monom m_p1=new Monom(iterM1.next());
			Monom neg=new Monom(-1,0);
			m_p1.multiply(neg);
			this.add(m_p1);	
		}
	}
	@Override
	/**
	 * Multiply this Polynom by p1
	 * @param temp this polynom= poly*p1
	 * @param m_p Monom in array list poly
	 * @param m Monom
	 * @param p1
	 */
	public void multiply(Polynom_able p1) {
		Iterator<Monom>iterM = this.iteretor();
		Polynom temp=new Polynom();
		while(iterM.hasNext())
		{
			Monom m_p=new Monom(iterM.next());
			Iterator<Monom>iterM1 = p1.iteretor();
			while(iterM1.hasNext())
			{
				Monom m=new Monom(m_p);
				m.multiply(iterM1.next());
				temp.add(m);
			}
		}
		poly=((Polynom)temp.copy()).poly;
	}
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1
	 * @return true iff this polynom represents the same function ans p1
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		boolean flag=true;
		Iterator<Monom>iterM = this.iteretor();
		Iterator<Monom>iterM1 = p1.iteretor();
		while(iterM.hasNext()&&iterM1.hasNext())
		{
			if(!iterM.next().equals(iterM1.next()))
			{
				flag=false;
				break;
			}
		}
		if(iterM.hasNext()||iterM1.hasNext())
		{
			flag=false;
		}
		return flag;
	}
	/**
	 * Test if this is the Zero Polynom
	 * @return
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom>iterM = this.iteretor();
		while(iterM.hasNext())
		{
			if(iterM.next().get_coefficient()!=0)
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(mid) such that:
	 * *	(i) x0<=mid<=x2 && (ii) f(mid)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @param mid middle point
	 * @return point
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double point=((x1+x0)/2);
		double mid;
		if (f(x0)*f(x1)>0 || x0>x1 || eps<0)
		{
			throw new RuntimeException("There is no cutting point with the X axis in the field you selected"
					+ "or you need that x1>xo or eps must must be positive");
		}
		while(Math.abs(x1-x0)>eps)
		{
			if(f(x0)*f(x1)<0) {
				mid= ((x0+x1)/2);
				if(f(x0)*f(mid)<0) {
					x1=mid;
				}
				else {
					x0=mid;
				}
				point= ((x1+x0)/2);
			}
		}
		return point;
	}
	/**
	 * create a deep copy of this Polynum
	 * @param m Monom in array list poly
	 * @return 
	 */
	@Override
	public Polynom_able copy() {
		Iterator<Monom>iterM =poly.iterator();
		Polynom_able copy= new Polynom();
		while (iterM.hasNext()){
			Monom m= new Monom(iterM.next());
			copy.add(m);
		}
		return copy;
	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return
	 */
	@Override
	public Polynom_able derivative() {
		Iterator<Monom>iterM =this.iteretor();
		while (iterM.hasNext()){
			iterM.next().derivative();
		}
		return null;
	}
	/**
	 * Compute Riemann's Integral over this Polynom the
	 * approximated area only above/ beneath the x-axis below this Polynom and between the [x0,x1] range.,
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return sum
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double sum=0;
		double y=0;
		if(x1<x0)
		{
			throw new RuntimeException("you need that x1>x0");	
		}
		if (f(x0)*f(x1)<0) {
			throw new RuntimeException("The value of f(x0) and f(x1) is the same positive/ negative");	

		}
		
		while(x0<x1)
		{
			if (f(x0)>0)
			{
				y=((f(x0)+f(x0+eps))/2);
				sum+=y*eps;
			}	
			else if (f(x0)<0)
			{
				y=((f(x0)+f(x0+eps))/2);
				sum= sum +Math.abs(y)*eps;
			}
			x0= x0+eps;
		}
		
		return sum;
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return this.poly.iterator();
	}
}
