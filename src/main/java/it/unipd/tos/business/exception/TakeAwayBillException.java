////////////////////////////////////////////////////////////////////
// [Marco] [Dalla Libera] [1170634]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception {

    private static final long serialVersionUID = 1L;

    public TakeAwayBillException(String errorMessage) {
        super(errorMessage);
    }
}
