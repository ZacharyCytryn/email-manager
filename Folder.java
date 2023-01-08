/**
 * Zachary Cytryn
 * ID: 114283379
 * Email: zachary.cytryn@stonybrook.edu
 * Homework #5
 * CSE 214 Recitation 30
 */

package emailManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * This class represents a folder of emails and includes methods to manipulate the folders and what's inside of them
 * 
 * @author zacharycytryn
 */
public class Folder implements java.io.Serializable {
	
	private ArrayList<Email> emails;
	private String name;
	private String currentSortingMethod;
	
	/* Sorting method key:
	*"DD": Date Descending (default)
	*"DA": Date Ascending
	*"SD": Subject Descending
	*"SA": Subject Ascending */
	
	/**
	 * Constructor for Folder. Also initalizes ArrayList for emails and sets the current sorting method to date
	 * descending ("DD") by default.
	 * 
	 * @param name
	 * name of folder
	 */
	public Folder(String name) {
		emails = new ArrayList<Email>();
		this.name = name;
		currentSortingMethod = "DD";
	}
	
	/**
	 * Getter method for emails inside the folder
	 * 
	 * @return
	 * emails
	 */
	public ArrayList<Email> getEmails() {
		return emails;
	}
	
	/**
	 * Getter method for name of folder
	 * 
	 * @return
	 * name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for current sorting method
	 * 
	 * @return
	 * currentSortingMethod
	 */
	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}
	
	/**
	 * Setter method for emails in folder
	 * 
	 * @param emails
	 * Arraylist of emails
	 */
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
	
	/**
	 * Setter method for name of the folder
	 * 
	 * @param name
	 * name of folder
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter method for currentSortingMethod
	 * "DD" = Date Descending, "DA" = Date Ascending, "SA" = Subject Ascending, "SD" = Subject Descending
	 * 
	 * @param currentSortingMethod
	 * currentSortingMethod using guide above
	 */
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}
	
	/**
	 * This method adds an email to the arraylist of emails in a folder
	 * 
	 * @param email
	 * email being added to the folder
	 */
	public void addEmail(Email email) {
		emails.add(email);
	}
	
	/**
	 * This method remove an email from the arraylist of emails in a folder
	 * 
	 * @param index
	 * index of email
	 * 
	 * @return
	 * email that has been removed
	 */
	public Email removeEmail(int index) {
		index--;
		Email email = emails.get(index);
		emails.remove(index);
		return email;
	}
	
	/**
	 * This method uses SubjectComparator to sort the arraylist of emails in a folder by subject ascending
	 */
	public void sortBySubjectAscending() {
		Collections.sort(emails, new SubjectComparator().reversed());
	}
	
	/**
	 * This method uses SubjectComparator to sort the arraylist of emails in a folder by subject descending
	 */
	public void sortBySubjectDescending() {
		Collections.sort(emails, new SubjectComparator());
	}
	
	/**
	 * This method uses DateComparator to sort the arraylist of emails in a folder by date ascending
	 */
	public void sortByDateAscending() {
		Collections.sort(emails, new DateComparator().reversed());
	}
	
	/**
	 * This method uses DateComparator to sort the arraylist of emails in a folder by date descending
	 */
	public void sortByDateDescending() {
		Collections.sort(emails, new DateComparator());
	}
	
	/**
	 * This is the toString method for a folder, which properly formats it for printing.
	 * 
	 * @return
	 * printFolder
	 */
	public String toString() {
		String[] amOrPm = {"AM", "PM"};
		String printFolder = "\n" + name + "\n\n";
		if (emails.size() == 0) {
			printFolder += name + " is empty.\n";
		}
		else {
			printFolder += String.format("%-15s %-11s %-9s", "Index |", "Time", "| Subject");
			printFolder += "\n-------------------------------------\n";
			int correctHour = 0;
			for (int i = 0; i < emails.size(); i++) {
				correctHour = emails.get(i).getTimestamp().get(Calendar.HOUR);
				if (correctHour == 0) {
					correctHour = 12;
				}
				printFolder += String.format("%-1s %-3s %-2s %-2s %-10s %-1s %-1s"," ", (i + 1), "|", 
						correctHour + ":"
						+ emails.get(i).getTimestamp().get(Calendar.MINUTE)
						+ amOrPm[emails.get(i).getTimestamp().get(Calendar.AM_PM)],
						emails.get(i).getTimestamp().get(Calendar.MONTH) + 1 + "/"
						+emails.get(i).getTimestamp().get(Calendar.DAY_OF_MONTH)
						+ "/" + emails.get(i).getTimestamp().get(Calendar.YEAR),
						"|", emails.get(i).getSubject());
				printFolder += "\n";
			}
			printFolder += "\n";
		}
		return printFolder;
	}
}