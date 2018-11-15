package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MonomTest {
	@Test
	public void testMultiply(){
		Monom m1= new Monom (4,2);
		Monom m2= new Monom (5,3);
		m1.multiply(m2);;
		assertEquals(m1.toString()," +20.0x^5");
		assertNotEquals(m1.toString(), " +5.0x^5");			
	}
	@Test
	public void testF() {
		Monom m1= new Monom (4,2);	
		double actual= m1.f(2.5);
		double expected= 25.0;
		assertEquals(expected, actual);
	}
	@Test
	public void testAddo(){
		Monom m1= new Monom(4,4);
		Monom m2= new Monom(11,4);
		m1.add(m2);
		assertEquals(m1.toString()," +15.0x^4");
		assertNotEquals(m1.toString(), " -8.0x^4");			
	}
	@Test
	public void testDerivative(){
		Monom m1= new Monom(4,4);
		m1.derivative();
		assertEquals(m1.toString()," +16.0x^3");
		assertNotEquals(m1.toString(), "8x^4");		
	}		
}
