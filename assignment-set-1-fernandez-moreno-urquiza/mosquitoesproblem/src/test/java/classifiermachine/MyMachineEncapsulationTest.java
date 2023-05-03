package classifiermachine;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;


public class MyMachineEncapsulationTest {
	
	MyMachine machine;

	@Test
	public void myMachinePublicMethodsTest() {
		Method [] myMachineMethods = MyMachine.class.getDeclaredMethods();
		int publics = 0;
		for (Method m : myMachineMethods) {
			if (Modifier.isPublic(m.getModifiers())) {
				publics++;
			}
		}
		assertTrue("There are invalid public methods",publics == 5);				
	}
	
	@Test
	public void testMyMachineAttrib() {
		Field [] myMachineFields = MyMachine.class.getDeclaredFields();
		int publics = 0;
		for (Field f : myMachineFields) {
			if (Modifier.isPublic(f.getModifiers())) {
				publics++;
			}
		}
		assertTrue("There are public fields:"+publics,publics == 0);	
	}

}
