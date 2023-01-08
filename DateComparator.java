package emailManager;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * This class uses Comparator for sorting emails by date
 * 
 * @author zacharycytryn
 */
public class DateComparator implements Comparator<Email> {
	
	/**
	 * This method compares two emails and determines the order based on the timestamp
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
		if (email1.getTimestamp().get(Calendar.YEAR) < email2.getTimestamp().get(Calendar.YEAR)) {
			return 1;
		}
		else if (email1.getTimestamp().get(Calendar.YEAR) > email2.getTimestamp().get(Calendar.YEAR)) {
			return -1;
		}
		else {
			if (email1.getTimestamp().get(Calendar.MONTH) < email2.getTimestamp().get(Calendar.MONTH)) {
				return 1;
			}
			else if (email1.getTimestamp().get(Calendar.MONTH) > email2.getTimestamp().get(Calendar.MONTH)) {
				return -1;
			}
			else {
				if (email1.getTimestamp().get(Calendar.DAY_OF_MONTH) < email2.getTimestamp().get(Calendar.DAY_OF_MONTH)) {
					return 1;
				}
				else if (email1.getTimestamp().get(Calendar.DAY_OF_MONTH) > email2.getTimestamp().get(Calendar.DAY_OF_MONTH)) {
					return -1;
				}
				else {
					if (email1.getTimestamp().get(Calendar.HOUR_OF_DAY) < email2.getTimestamp().get(Calendar.HOUR_OF_DAY)) {
						return 1;
					}
					else if (email1.getTimestamp().get(Calendar.HOUR_OF_DAY) > email2.getTimestamp().get(Calendar.HOUR_OF_DAY)) {
						return -1;
					}
					else {
						if (email1.getTimestamp().get(Calendar.MINUTE) < email2.getTimestamp().get(Calendar.MINUTE)) {
							return 1;
						}
						else if (email1.getTimestamp().get(Calendar.MINUTE) > email2.getTimestamp().get(Calendar.MINUTE)) {
							return -1;
						}
						else {
							return 0;
						}
					}
				}
			}
		}
	}
}
