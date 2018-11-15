package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PolynomTest {

	@Test
	public void testArea() {
		Polynom_able p1= new Polynom("0.2x^4+-1.5x^3+3x^2+-1x^1+-5");
		double actual= p1.area(-0.941, 4.831, 0.01);
		double expected= 25.18286464440871;
		assertEquals(expected, actual);
	}
	@Test
	public void testF() {
		Polynom_able p= new Polynom("0.2x^4+-1.5x^3+3x^2+-1x^1+-5");
		double actual= p.f(1);
		double expected= -4.3;
		assertEquals(expected, actual);
	}
	@Test
	public void testSubstract(){
		Polynom_able p1= new Polynom("5x^5+3x^3+-2");
		Polynom_able p2= new Polynom("8x^4+-1x^3+2x^2+-11x+7");
		p1.substract(p2);
		assertEquals(p1.toString()," +5.0x^5 +-8.0x^4 +4.0x^3 +-2.0x^2 +11.0x +-9.0");
		assertNotEquals(p1.toString(), "8x^4+-1x^3+2x^2+-11x+7");		
	}
		@Test
		public void testAddPolynom(){
			Polynom_able p1= new Polynom("5x^5+3x^3+-2");
			Polynom_able p2= new Polynom("8x^4+-1x^3+2x^2+-11x+7");
			p1.add(p2);
			assertEquals(p1.toString()," +5.0x^5 +8.0x^4 +2.0x^3 +2.0x^2 +-11.0x +5.0");
			assertNotEquals(p1.toString(), " +5.0x^5 +-8.0x^4 +4.0x^3 +-2.0x^2 +11.0x +-9.0");			
	}
		@Test
		public void testAddMono(){
			Polynom_able p1= new Polynom("5x^5+3x^3+-2");
			Monom m= new Monom(4,4);
			p1.add(m);
			assertEquals(p1.toString()," +5.0x^5 +4.0x^4 +3.0x^3 +-2.0");
			assertNotEquals(p1.toString(), " +5.0x^5 +-8.0x^4 +4.0x^3 +-2.0x^2 +11.0x +-9.0");			
	}
		@Test
		public void testMultiply(){
			Polynom_able p1= new Polynom("5x^5+3x^3+-2");
			Polynom_able p2= new Polynom("8x^4+-1x^3+2x^2+-11x+7");
			p1.multiply(p2);;
			assertEquals(p1.toString()," +40.0x^9 +-5.0x^8 +34.0x^7 +-58.0x^6 +41.0x^5 +-49.0x^4 +23.0x^3 +-4.0x^2 +22.0x +-14.0");
			assertNotEquals(p1.toString(), " +5.0x^5 +-8.0x^4 +4.0x^3 +-2.0x^2 +11.0x +-9.0");			
	}
		@Test
		public void testIsZero() {
			Polynom_able p1= new Polynom("5x^5+3x^3+-2");
			Polynom_able p3= new Polynom();
			boolean actual1= p1.isZero();
			boolean actual2= p3.isZero();
			assertFalse(actual1);
			assertTrue(actual2);
		}
		@Test
		public void testRoot() {
			Polynom_able p1= new Polynom("4x^3+2x^2+11x+23");
			double actual= p1.root(-2, 0, 0.01);
			double expected= -1.41796875;
			assertEquals(expected, actual);
		}
		@Test
		public void testDerivative(){
			Polynom_able p= new Polynom("8x^4+-1x^3+2x^2+-11x+7");
			p.derivative();
			assertEquals(p.toString()," +32.0x^3 +-3.0x^2 +4.0x +-11.0 +0.0");
			assertNotEquals(p.toString(), "8x^4+-1x^3+2x^2+-11x+7");		
		}
		
}
