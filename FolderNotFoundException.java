package emailManager;

/**
 * This class is an exception for if a specific folder is not found in mailbox
 * 
 * @author Zachary Cytryn
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
