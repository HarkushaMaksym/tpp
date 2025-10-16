package hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void testCorrectExpression() {
        assertEquals(2, Calculator.evaluate("1+1"));
    }

   // @Test
    //void testFailExpression() {
   //     assertEquals(2, Calculator.evaluate("5-1"));
   // }
}
