/**
 * Zachary Cytryn
 * ID: 114283379
 * Email: zachary.cytryn@stonybrook.edu
 * Homework #5
 * CSE 214 Recitation 30
 */

package emailManager;
import java.util.Comparator;

/**
 * This class uses Comparator for sorting emails by subject of email
 * 
 * @author zacharycytryn
 */
public class SubjectComparator implements Comparator<Email> {

	/**
	 * This method compares two emails and determines the order based on the subject
	 * 
	 * @Override
	 * @param email1
	 * First email for comparison
	 * @param email2
	 * Second email for comparison
	 * @return
	 * int for sorting
	 */
	public int compare(Email email1, Email email2) {
		if (email1.getSubject().compareToIgnoreCase(email2.getSubject()) > 0) {
			return -1;
		}
		else if (email1.getSubject().compareToIgnoreCase(email2.getSubject()) < 0) {
			return 1;
		}
		else {
			if (email1.getSubject().compareTo(email2.getSubject()) < 0) {
				return -1;
			}
			else if (email1.getSubject().compareTo(email2.getSubject()) > 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}
