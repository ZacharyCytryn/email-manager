/**
 * Zachary Cytryn
 * ID: 114283379
 * Email: zachary.cytryn@stonybrook.edu
 * Homework #5
 * CSE 214 Recitation 30
 */

package emailManager;
import java.util.GregorianCalendar;

/**
 * This class represents an email with to, cc, bcc, subject, and body.
 * 
 * @author zacharycytryn
 */
public class Email implements java.io.Serializable {

	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	private GregorianCalendar timestamp;
	
	//Constructor
	/**
	 * This is the constructor for an email. Includes timestamp (date and time) of when Email is initialized.
	 * 
	 * @param to
	 * who the email is going to
	 * @param cc
	 * carbon copy
	 * @param bcc
	 * blind carbon copy
	 * @param subject
	 * subject of the email
	 * @param body
	 * body of the email
	 */
	public Email(String to, String cc, String bcc, String subject, String body) {
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
		timestamp = new GregorianCalendar();
	}
	
	//Getters
	/**
	 * Getter method for to
	 * 
	 * @return
	 * to
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * Getter method for CC
	 * 
	 * @return
	 * cc
	 */
	public String getCC() {
		return cc;
	}
	
	/**
	 * Getter method for BCC
	 * 
	 * @return
	 * bcc
	 */
	public String getBCC() {
		return bcc;
	}
	
	/**
	 * Getter method for subject
	 * 
	 * @return
	 * subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Getter method for body
	 * 
	 * @return
	 * body
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * Getter method for timestamp
	 * 
	 * @return
	 * timestamp
	 */
	public GregorianCalendar getTimestamp() {
		return timestamp;
	}
	
	//Setters
	/**
	 * Setter method for to
	 * 
	 * @param to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * Setter method for CC
	 * 
	 * @param cc
	 */
	public void setCC(String cc) {
		this.cc = cc;
	}
	
	/**
	 * Setter method for BCC
	 * 
	 * @param bcc
	 */
	public void setBCC(String bcc) {
		this.bcc = bcc;
	}
	
	/**
	 * Setter method for subject
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Setter method for body
	 * 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Setter method for timestamp
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(GregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * toString method for email, which properly formats the email
	 * 
	 * @return
	 * printString
	 */
	public String toString() {
		String printString = "To: " + to
				+"\nCC: " + cc
				+"\nBCC: " + bcc
				+"\nSubject: " + subject
				+"\n" + body + "\n";
		return printString;
	}
}
