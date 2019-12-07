package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;

public class BillMakerTest {
    
    BillMaker billMaker = new BillMaker();
    private static final double DELTA = 0.001;

    @Test
    public void getOrderPrice_StateUnderTest_ExpectedBehavior() {
        try {
            assertEquals(0, billMaker.getOrderPrice(null), DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }

}
