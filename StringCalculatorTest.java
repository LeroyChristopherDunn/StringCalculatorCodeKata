import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leroy.dunn on 2017/03/15.
 */
public class StringCalculatorTest {

    @Test
    public void givenAnEmptyStringShouldReturnZero() throws Exception {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void givenSingleNumberShouldReturnNumber() throws Exception {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void givenTwoNumbersShouldReturnSumOfNumbersLessThan1000() throws Exception {
        assertEquals(2, StringCalculator.add("1,1"));
    }

    @Test
    public void givenUnknownNumberOfNumbersShouldReturnSumExceptNumbersLargerThan1000() throws Exception {
        assertEquals(8, StringCalculator.add("1,1\n2,2\n2,1001"));
    }

    @Test
    public void givenCustomDelimiterShouldExceptNumbersLargerThan1000() throws Exception {
        assertEquals(3, StringCalculator.add("//;\n1;2;1001"));
    }

    @Test
    public void givenNegativeNumbersShouldThrowExceptionWithNegativeNumbersInExceptionMessage() {
        try {
            assertEquals(-3, StringCalculator.add("//;\n1;-1;-3;1001"));
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("-1,-3"));
        }
    }

    @Test
    public void givenLongCustomDelimiterShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void givenMultipleSingleCustomDelimitersShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void givenMultipleLongCustomDelimitersShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//[**][%%]\n1**2%%3"));
    }

}