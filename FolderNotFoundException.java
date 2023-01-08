/**
 * Zachary Cytryn
 * ID: 114283379
 * Email: zachary.cytryn@stonybrook.edu
 * Homework #5
 * CSE 214 Recitation 30
 */

package emailManager;

/**
 * This class is an exception for if a specific folder is not found in mailbox
 * 
 * @author zacharycytryn
 */
public class FolderNotFoundException extends Exception {
	
	/**
	 * Constructor for exception
	 * 
	 * @param message
	 * Message if exception is thrown
	 */
	public FolderNotFoundException(String message) {
		super(message);
	}
}
