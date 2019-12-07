package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TakeAwayBillExceptionTest {

    TakeAwayBillException exception = null;
    
    private String input;
    private String output;
    
    public TakeAwayBillExceptionTest(String input, String output) {
        this.input = input;
        this.output = output;
    }
    
    @Parameters
    public static Collection<String[]> testConditions() {
        String expectedOutputs[][] = {
                { "test", "test" },
                { null, null } };
        return Arrays.asList(expectedOutputs);
    }
    
    @Test
    public void constructor_ErrorMessageMatch() {
        exception = new TakeAwayBillException(input);
        assertEquals(output, exception.getMessage());
    }
}
