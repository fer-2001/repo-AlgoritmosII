package classifiermachine;

import static org.junit.Assert.*;

import org.junit.Test;


public class MyMachineTest {
	
	MyMachine machine;
	int[] sample = {5, 7, 9, 11, 11, 5, 0, 11, 9, 100, 9};

	@Test
	public void testMyMachine() {
		machine = new MyMachine(sample);		
		assertTrue(machine.getOperationCount(MachineOperations.MOVEOUTSIDE) == 0);
		assertTrue(machine.getOperationCount(MachineOperations.MOVEINSIDE) == 0);
		assertTrue(machine.getOperationCount(MachineOperations.PRESSBUTTON) == 0);
	}

	@Test
	public void testCountMoveInside() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < sample.length; i++) {
			machine.moveInside(i);
		}
		assertTrue(machine.getOperationCount(MachineOperations.MOVEINSIDE) == sample.length);
	}
	
	@Test
	public void testCountMoveInside2() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < sample.length; i++) {
			machine.moveInside(i);
			machine.moveInside(i);
		}
		assertTrue(machine.getOperationCount(MachineOperations.MOVEINSIDE) == sample.length*2);
	}
	
	@Test
	public void testMoveInsideSameHasNoEffect() throws MachineException {
		machine = new MyMachine(sample);
		machine.moveInside(sample.length-1);
		assertTrue(machine.pressButton() == 1);
		machine.moveInside(sample.length-1);
		assertTrue(machine.pressButton() == 1);
	}

	@Test
	public void testMoveOutside() throws MachineException {
		machine = new MyMachine(sample);
		machine.moveOutside(sample.length-1);
		assertTrue(machine.getOperationCount(MachineOperations.MOVEOUTSIDE) == 1);
	}

	@Test
	public void testMoveOutside2() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < sample.length; i++) {
			machine.moveOutside(i);			
		}
		assertTrue(machine.getOperationCount(MachineOperations.MOVEOUTSIDE) == sample.length);
	}
	
	@Test
	public void testMoveOutsideUndoMoveInside() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < sample.length; i++) {
			machine.moveInside(i);
			assertTrue(machine.pressButton() == 1); 
			machine.moveOutside(i);
			assertTrue(machine.pressButton() == 0);
		}
	}
	
	@Test
	public void testPressButton() throws MachineException {
		machine = new MyMachine(sample);
		int prevFrequency = 0;
		int newFrequency = 0;
		for (int i = 0; i < sample.length; i++) {
			machine.moveInside(i);
			newFrequency = machine.pressButton();
			assertTrue( prevFrequency <= newFrequency); 
			prevFrequency = newFrequency;
		}
	}
	
	@Test
	public void testPressButton2() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < sample.length; i++) {
			machine.moveInside(i);
		}
		int prevFrequency = machine.pressButton();
		for (int i = sample.length -1; i >= 0; i--) {
			machine.moveOutside(i);
			int newFrequency = machine.pressButton();
			assertTrue(newFrequency <= prevFrequency); 
			prevFrequency = newFrequency;
		}
	}

	@Test(timeout = 5)
	public void pressButtonEfficiencyTest() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < 40000; i++) {
			machine.moveInside(i%sample.length);
		}
		machine.pressButton();
	}
	
	@Test(expected = MachineException.class)
	public void moveInsideCallsViolationTest() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < 40001; i++) {
			machine.moveInside(i%sample.length);
		}
	}
	
	@Test(expected = MachineException.class)
	public void moveOutsideCallsViolationTest() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < 40001; i++) {
			machine.moveOutside(i%sample.length);
		}
	}
	
	@Test(expected = MachineException.class)
	public void pressButtonCallsViolationTest() throws MachineException {
		machine = new MyMachine(sample);
		for (int i = 0; i < 40001; i++) {
			machine.pressButton();
		}
	}

}
