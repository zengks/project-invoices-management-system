/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */
public class InvalidInvoiceNumberException extends Exception {

	/**
	 * customized exception for invalid invoice number
	 * 
	 * @param m
	 */
	public InvalidInvoiceNumberException(String m) {
		super(m);
	}
}
