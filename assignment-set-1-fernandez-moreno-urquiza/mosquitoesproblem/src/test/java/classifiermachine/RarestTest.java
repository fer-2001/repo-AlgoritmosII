package classifiermachine;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;


/**
 * Checks functionality of rarest algorithm.
 * It is implemented using jUnit's parameterized tests.
 *
 */

@RunWith(Parameterized.class)
public class RarestTest {

    private int[] sample;
    private int expectedRarest;
    private static int[] longInput = new int[2000];


    /**
    * Default constructor for parameterized tests.
    * Sets elements from Parameters into the variables for input.
    * @param sample is the source sequence. 
    * @param expectedRarest is minimum frequency expected.
    */
    public RarestTest(int[] sample, int expectedRarest){
        this.sample = sample;
        this.expectedRarest = expectedRarest;
    }

    @BeforeClass
    public static void setUp(){
      for (int i = 0; i < 1500; i++) {
        if( i % 2 == 0){
          longInput[i] = 2;
        } else {
          longInput[i] = i;
        }
      }
    }

    /**
     * Contains the sequence values to use for tests.
     * This method contains the inputs to be used for testing.
     * New tests are added by adding new inputs here.
     * See test methods in this class, for actual properties
     * being checked.
     * @return an array of int arrays (sample posible values) and one integer value (expected result), used as
     * parameters for tests.
     */
    @Parameters
    public static Collection<Object[]> parameters(){
        return Arrays.asList(new Object[][] {
            { new int[] {5, 8, 9, 5, 9, 9}, 1},
            { new int[] {0,0}, 2},
            { new int[] {1, 0}, 1},
            { new int[] {2, 1, 1, 1, 1, 1, 2, 0}, 1},
            { new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 199},
            { new int[] {1, 0, 2, 1, 0, 0, 2, 0, 2, 3, 3, 3, 1, 2, 0, 0, 0, 3, 2, 3, 0, 2, 4, 3, 2, 1, 1, 3, 3, 1, 2, 2, 3, 3, 1, 2, 2, 3, 2, 3, 1, 1, 3, 2, 1, 2, 1, 0, 3, 2, 1, 2, 2, 1, 0, 3, 1, 1, 3, 1, 2, 2, 1, 3, 0, 0, 3, 0, 0, 2, 1, 2, 0, 2, 1, 3, 3, 1, 3, 2, 3, 0, 1, 1, 1, 0, 1, 3, 2, 0, 0, 3, 1, 3, 2, 0, 3, 2, 0, 0, 1, 0, 2, 0, 1, 2, 3, 0, 3, 1, 2, 1, 1, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 2, 3, 0, 0, 2, 1, 3, 1, 0, 1, 0, 2, 1, 2, 2, 0, 0, 0, 3, 2, 1, 2, 0, 0, 2, 3, 0, 0, 0, 3, 0, 3, 1, 1, 1, 1, 3, 3, 2, 2, 2, 2, 2, 3, 0, 3, 3, 1, 1, 3, 0, 3, 0, 2, 3, 0, 3, 1, 3, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2, 3, 1}, 2},
            { longInput, 1},
        });
    }

    /**
    * Checks  rarest implementation.
    */
    @Test
    public void resultRarestTest(){
    	int actualRarest = App.rarest(this.sample);
        assertEquals("result value",expectedRarest, actualRarest);
    }

    /**
    * Checks  rarest input requirements.
    */
    @Test(expected = IllegalArgumentException.class)
    public void RarestInvalidInputTest1(){
      int[] sampleInput = new int[] {5};
      int rarest = App.rarest(sampleInput);
    }

    /**
    * Checks  rarest input requirements.
    */
    @Test(expected = IllegalArgumentException.class)
    public void RarestInvalidInputTest2(){
      int[] sampleInput = new int[2001];
      for(int i = 0; i < 2001; i++){
        sampleInput[i] = i;
      }
      int rarest = App.rarest(sampleInput);
    }
    
    /**
     * Checks time efficiency of rarest implementation.
     */
     @Test(timeout = 500)
     public void resultRaresTimeTest(){
    	 App.rarest(this.sample);
     }


}
