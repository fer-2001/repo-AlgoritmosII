package classifiermachine;

public interface Machine {
  /**
   * Set sample to be analyzed for the Machine.
   * @param sample mosquitoes sample to be analyzed.
   */
	void setSample(int[] sample);

	/**
	 * Insert the ith element into the machine.
	 * @param i the mosquito's index to be added.
	 * @exception MachineException if the number of calls to this procedure exceeds 40000.
	 * @exception IllegalArgumentException if i < 0 or i >= length of sample in the Machine.
	 */
	void moveInside(int i);

	/**
	 * Removes the ith element into the machine.
	 * @param i mosquito's index to be eliminated.
	 * @exception MachineException if the number of calls to this procedure exceeds 40000.
	 * @exception IllegalArgumentException if i < 0 or i >= length of sample in the Machine.
	 */
	void moveOutside(int i);

	/**
	 * Return the cardinality of the most frequent mosquito type.
	 * considering only mosquitoes inside the machine.
	 * @return the cardinality of the most frequent mosquito type.
	 * @exception MachineException if the number of calls to this procedure exceeds 40000.
	 */
	int pressButton();


	/**
	 * Return the number of times the {@code operation} was called.
   * @param operation operation in which  calculate the number of called times.
	 * @return the number of times the {@code operation} was called.
	 */
	int getOperationCount(MachineOperations operation);

}
