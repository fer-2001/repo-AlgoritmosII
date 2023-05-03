package classifiermachine;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class LargeMyMachineTest {
	
	static MyMachine machine;
	static int[] largeSample;
	
	@BeforeClass
	public static void setUpLarge(){
		largeSample = new int [40000];
		machine = new MyMachine(largeSample);
		for (int i = 0; i < largeSample.length; i++) {
			largeSample[i] = i*i;
			machine.moveInside(i);
		}
	}
	
	@Test(timeout = 1)
	public void pressButtonEfficiencyTest() throws MachineException {
		assertTrue(machine.pressButton()==1);
		
	}

}