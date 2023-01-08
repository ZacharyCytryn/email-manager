package emailManager;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * This class represents a mailbox, containing all folders with emails in them. It includes
 * methods to manipulate the mailbox based on user input. Contains the main method.
 * 
 * @author Zachary Cytryn
 */
public class Mailbox implements java.io.Serializable {
	
	private Folder inbox;
	private Folder trash;
	private ArrayList<Folder> folders;
	
	/**
	 * Constructor for mailbox. Initializes Inbox folder and Trash folder, along with an arraylist of folders
	 */
	public Mailbox() {
		inbox = new Folder("Inbox");
		trash = new Folder("Trash");
		folders = new ArrayList<Folder>();
	}
	
	public static Mailbox mailbox;
	
	/**
	 * Getter method for the arraylist of folders in the mailbox
	 * 
	 * @return
	 * folders
	 */
	public ArrayList<Folder> getFolderList() {
		return folders;
	}
	
	/**
	 * Getter method for inbox
	 * 
	 * @return
	 * inbox
	 */
	public Folder getInbox() {
		return inbox;
	}
	
	/**
	 * Getter method for trash folder
	 * 
	 * @return
	 * trash
	 */
	public Folder getTrash() {
		return trash;
	}
	
	/**
	 * This method adds a folder to the arraylist of folders in mailbox
	 * 
	 * @param folder
	 * folder to be added to mailbox
	 */
	public void addFolder(Folder folder) {
		folders.add(folder);
	}
	
	/**
	 * This method deletes a folder from the arraylist of folders in mailbox
	 * 
	 * @param name
	 * name of folder to be deleted
	 * @throws FolderNotFoundException
	 * thrown if folder name does not exist in mailbox
	 */
	public void deleteFolder(String name) throws FolderNotFoundException {
		int counter = 0;
		for (int i = 0; i < folders.size(); i++) {
			if(folders.get(i).getName().equals(name)) {
				folders.remove(i);
				counter++;
				break;
			}
		}
		if (counter == 0) {
			throw new FolderNotFoundException(name + " does not exist.");
		}
	}
	
	/**
	 * This method composes an email based on user input and adds it to the inbox.
	 * Also calls a sorting method to sort the inbox after email is added based on current sorting method
	 * 
	 * @param to
	 * who the email is to
	 * @param cc
	 * carbon copy recipients
	 * @param bcc
	 * blind carbon copy recipients
	 * @param subject
	 * subject of email
	 * @param body
	 * body of email
	 */
	public void composeEmail(String to, String cc, String bcc, String subject, String body) {
		inbox.addEmail(new Email(to, cc, bcc, subject, body));
		sortFolder(inbox);
		System.out.println("\nEmail successfully added to Inbox.");
	}
	
	/**
	 * This method deletes an email by taking the requested email and moving it to the trash folder
	 * 
	 * @param email
	 * email to be deleted
	 */
	public void deleteEmail(Email email) {
		trash.addEmail(email);
		sortFolder(trash);
	}
	
	/**
	 * This method permanently deletes all of the emails in the trash folder
	 */
	public void clearTrash() {
		int counter = 0;
		for(int i = trash.getEmails().size(); i > 0; i--) {
			trash.removeEmail(i);
			counter++;
		}
		if (counter == 0) {
			System.out.println("Your trash is already empty!");
		}
		else {
			System.out.println(counter + " item(s) successfully deleted.");
		}
	}
	
	/**
	 * This method moves an email from one folder to another
	 * 
	 * @param email
	 * email to be moved
	 * @param target
	 * the folder that the email is being moved to
	 */
	public void moveEmail(Email email, Folder target) {
		target.addEmail(email);
		sortFolder(target);
	}
	
	/**
	 * Getter method for a specific folder (a user-made folder) [CASE SENSITIVE]
	 * 
	 * @param name
	 * name of folder
	 * @return
	 * folder
	 * @throws FolderNotFoundException
	 * thrown if requested folder does not exist
	 */
	public Folder getFolder(String name) throws FolderNotFoundException {
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				return folders.get(i);
			}
		}
		throw new FolderNotFoundException(name + " does not exist!");
	}
	
	/**
	 * This method sorts a folder based on currentSortingMethod
	 * 
	 * @param folder
	 * folder to be sorted
	 */
	public static void sortFolder(Folder folder) {
		if (folder.getCurrentSortingMethod().equals("DA")) {
			folder.sortByDateAscending();
		}
		else if (folder.getCurrentSortingMethod().equals("DD")) {
			folder.sortByDateDescending();
		}
		else if (folder.getCurrentSortingMethod().equals("SA")) {
			folder.sortBySubjectAscending();
		}
		else if (folder.getCurrentSortingMethod().equals("SD")) {
			folder.sortBySubjectDescending();
		}
	}
	
	/**
	 * This is the main method where mailbox is initialized, unless save is found. Also 
	 * allows user to manipulate and save current mailbox.
	 * 
	 * @param args
	 * command line arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		mailbox = new Mailbox();
		try {
			FileInputStream file = new FileInputStream("mailbox.obj");
			ObjectInputStream in = new ObjectInputStream(file);
			mailbox = (Mailbox) in.readObject();
			file.close();
			in.close();
			System.out.println("Previous save of mailbox opened.");
		} catch (IOException e) {
			System.out.println("Previous save not found, starting with an empty mailbox.");
		} catch (ClassNotFoundException e) {
			System.out.println("Previous save not found, starting with an empty mailbox.");
		}
		
		String firstAns = "";
		String secondAns = "";
		Folder currentFolder;
		
		while(!firstAns.toUpperCase().equals("Q")) {
			System.out.println("\nMailbox:"
					+ "\n--------"
					+ "\nInbox"
					+ "\nTrash");
			
			for (int i = 0; i < mailbox.getFolderList().size() ; i++) {
				System.out.print(mailbox.getFolderList().get(i).getName() + "\n");
			}
			System.out.println();
			
			System.out.println("A - Add folder"
					+ "\nR - Remove folder"
					+ "\nC - Compose email"
					+ "\nF - Open folder"
					+ "\nI - Open Inbox"
					+ "\nT - Open Trash"
					+ "\nE - Empty Trash"
					+ "\nQ - Quit"
					+ "\n"
					+ "\nEnter a user option: ");
			
			firstAns = sc.nextLine();
			System.out.println();
			
			switch(firstAns.toUpperCase()) {
			case "A":
				System.out.println("Enter folder name: ");
				String name = sc.nextLine();
				if (name.equals("Inbox")) {
					System.out.println("You already have an inbox!");
				}
				else if (name.equals("Trash")) {
					System.out.println("You already have a trash folder!");
				}
				else {
					mailbox.addFolder(new Folder(name));
				}
				break;
			
			case "R":
				System.out.println("Enter folder name: ");
				String removalName = sc.nextLine();
				if (removalName.equals("Inbox")) {
					System.out.println("You cannot remove your inbox!");
				}
				else if (removalName.equals("Trash")) {
					System.out.println("You cannot remove your trash folder!");
				}
				else {
					try {
						mailbox.deleteFolder(removalName);
						System.out.println(removalName + " successfully removed");
					} catch(FolderNotFoundException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
				
			case "C":
				System.out.println("Enter recipient (To): ");
				String to = sc.nextLine();
				System.out.println("Enter carbon copy recipients (CC): ");
				String cc = sc.nextLine();
				System.out.println("Enter blind carbon copy recipients (BCC): ");
				String bcc = sc.nextLine();
				System.out.println("Enter subject line: ");
				String subject = sc.nextLine();
				System.out.println("Enter body: ");
				String body = sc.nextLine();
				mailbox.composeEmail(to, cc, bcc, subject, body);
				break;
				
			case "F":
				System.out.println("Enter folder name: ");
				String folderName = sc.nextLine();
				try {
					currentFolder = mailbox.getFolder(folderName);
					while(!secondAns.toUpperCase().equals("R")) {
						System.out.println(currentFolder.toString());
						System.out.println("M - Move email"
								+ "\nD - Delete email"
								+ "\nV - View email contents"
								+ "\nSA - Sort by subject line in ascending order"
								+ "\nSD - Sort by subject line in descending order"
								+ "\nDA - Sort by date in ascending order"
								+ "\nDD - Sort by date in descending order"
								+ "\nR - Return to mailbox"
								+ "\n\nEnter a user option: ");
						
						secondAns = sc.nextLine();
						System.out.println();
						
						switch(secondAns.toUpperCase()) {
						case "M":
							System.out.println("Enter the index of the email to move: ");
							try {
								int moveIndex = Integer.parseInt(sc.nextLine());
								if (currentFolder.getEmails().size() == 0) {
									System.out.println(currentFolder.getName() + " is empty!");
								}
								else if (moveIndex < 1 || moveIndex > currentFolder.getEmails().size()) {
									System.out.println("Please enter a valid index.");
								}
								else {
									System.out.println("Folders:"
											+ "\nInbox"
											+ "\nTrash");
									for (int i = 0; i < mailbox.getFolderList().size(); i++) {
										System.out.println(mailbox.getFolderList().get(i).getName());
									}
									System.out.println("\nSelect a folder to move \"" + currentFolder.getEmails().get(moveIndex - 1).getSubject() + "\" to: ");
									String targetFolderName = sc.nextLine();
									if (targetFolderName.equals("Inbox")) {
										Email movedEmail = currentFolder.getEmails().get(moveIndex - 1);
										mailbox.moveEmail(currentFolder.removeEmail(moveIndex), mailbox.getInbox());
										System.out.println("\n\"" + movedEmail.getSubject() + "\" successfully moved to Inbox");
									}
									else {
										try {
											Folder targetFolder = mailbox.getFolder(targetFolderName);
											Email movedEmail = currentFolder.getEmails().get(moveIndex - 1);
											mailbox.moveEmail(currentFolder.removeEmail(moveIndex), targetFolder);
											System.out.println("\n\"" + movedEmail.getSubject() + "\" successfully moved to " + targetFolderName);
										} catch (FolderNotFoundException e) {
											System.out.println(e.getMessage());
										}
									}
								}
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid index.");
							}
							break;
							
						case "D":
							System.out.println("Enter email index: ");
							try {
								int deleteIndex = Integer.parseInt(sc.nextLine());
								if (deleteIndex < 1 || deleteIndex > currentFolder.getEmails().size()) {
									System.out.println("Invalid index.");
								}
								else {
									Email deletedEmail = currentFolder.getEmails().get(deleteIndex - 1);
									mailbox.deleteEmail(currentFolder.removeEmail(deleteIndex));
									System.out.println("\n\"" + deletedEmail.getSubject() + "\" has successfully been moved to the trash.");
								}
							} catch(NumberFormatException e) {
								System.out.println("Please enter a valid index");
							}
							break;
							
						case "V":
							System.out.println("Enter email index: ");
							try {
								int emailIndex = Integer.parseInt(sc.nextLine());
								if (currentFolder.getEmails().size() == 0) {
									System.out.println(currentFolder.getName() + " is empty!");
								}
								else if (emailIndex < 1 || emailIndex > currentFolder.getEmails().size()) {
									System.out.println("Please enter a valid index.");
								}
								else {
									System.out.println(currentFolder.getEmails().get(emailIndex - 1).toString());
								}
							} catch(NumberFormatException e) {
								System.out.println("Please enter a valid index.");
							}
							break;
							
						case "SA":
							currentFolder.setCurrentSortingMethod("SA");
							currentFolder.sortBySubjectAscending();
							break;
							
						case "SD":
							currentFolder.setCurrentSortingMethod("SD");
							currentFolder.sortBySubjectDescending();
							break;
							
						case "DA":
							currentFolder.setCurrentSortingMethod("DA");
							currentFolder.sortByDateAscending();
							break;
							
						case "DD":
							currentFolder.setCurrentSortingMethod("DD");
							currentFolder.sortByDateDescending();
							break;
							
						case "R":
							break;
							
						default:
							System.out.println("Please enter a valid option.");
							break;
						}
					}
					secondAns = "";
				} catch(FolderNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case "I":
				while(!secondAns.toUpperCase().equals("R")) {
					System.out.println(mailbox.getInbox().toString());
					System.out.println("M - Move email"
							+ "\nD - Delete email"
							+ "\nV - View email contents"
							+ "\nSA - Sort by subject line in ascending order"
							+ "\nSD - Sort by subject line in descending order"
							+ "\nDA - Sort by date in ascending order"
							+ "\nDD - Sort by date in descending order"
							+ "\nR - Return to mailbox"
							+ "\n\nEnter a user option: ");
					
					secondAns = sc.nextLine();
					System.out.println();
					
					switch(secondAns.toUpperCase()) {
					case "M":
						System.out.println("Enter the index of the email to move: ");
						try {
							int moveIndex = Integer.parseInt(sc.nextLine());
							if (mailbox.getInbox().getEmails().size() == 0) {
								System.out.println("Inbox is empty!");
							}
							else if (moveIndex < 1 || moveIndex > mailbox.getInbox().getEmails().size()) {
								System.out.println("Please enter a valid index.");
							}
							else {
								System.out.println("Folders:"
										+ "\nInbox"
										+ "\nTrash");
								for (int i = 0; i < mailbox.getFolderList().size(); i++) {
									System.out.println(mailbox.getFolderList().get(i).getName());
								}
								System.out.println("\nSelect a folder to move \"" + mailbox.getInbox().getEmails().get(moveIndex - 1).getSubject() + "\" to: ");
								String targetFolderName = sc.nextLine();
								try {
									Folder targetFolder = mailbox.getFolder(targetFolderName);
									Email movedEmail = mailbox.getInbox().getEmails().get(moveIndex - 1);
									mailbox.moveEmail(mailbox.getInbox().removeEmail(moveIndex), targetFolder);
									System.out.println("\n\"" + movedEmail.getSubject() + "\" successfully moved to " + targetFolderName);
								} catch (FolderNotFoundException e) {
									System.out.println(e.getMessage());
								}
							}
						} catch(NumberFormatException e) {
							System.out.println("Please enter a valid index.");
						}
						break;
						
					case "D":
						System.out.println("Enter email index: ");
						try {
							int deleteIndex = Integer.parseInt(sc.nextLine());
							if (deleteIndex < 1 || deleteIndex > mailbox.getInbox().getEmails().size()) {
								System.out.println("Invalid index.");
							}
							else {
								Email deletedEmail = mailbox.getInbox().getEmails().get(deleteIndex - 1);
								mailbox.deleteEmail(mailbox.getInbox().removeEmail(deleteIndex));
								System.out.println("\n\"" + deletedEmail.getSubject() + "\" has successfully been moved to the trash.");
							}
						} catch(NumberFormatException e) {
							System.out.println("Please enter a valid index.");
						}
						break;
						
					case "V":
						System.out.println("Enter email index: ");
						try {
							int emailIndex = Integer.parseInt(sc.nextLine());
							if (mailbox.getInbox().getEmails().size() == 0) {
								System.out.println("Inbox is empty!");
							}
							else if (emailIndex < 1 || emailIndex > mailbox.getInbox().getEmails().size()) {
								System.out.println("Please enter a valid index.");
							}
							else {
								System.out.println(mailbox.getInbox().getEmails().get(emailIndex - 1).toString());
							}
						} catch (NumberFormatException e) {
							System.out.println("Please enter a valid index.");
						}
						break;
						
					case "SA":
						mailbox.getInbox().setCurrentSortingMethod("SA");
						mailbox.getInbox().sortBySubjectAscending();
						break;
						
					case "SD":
						mailbox.getInbox().setCurrentSortingMethod("SD");
						mailbox.getInbox().sortBySubjectDescending();
						break;
						
					case "DA":
						mailbox.getInbox().setCurrentSortingMethod("DA");
						mailbox.getInbox().sortByDateAscending();
						break;
						
					case "DD":
						mailbox.getInbox().setCurrentSortingMethod("DD");
						mailbox.getInbox().sortByDateDescending();
						break;
						
					case "R":
						break;
						
					default:
						System.out.println("Please enter a valid option.");
						break;
					}
				}
				secondAns = "";
				break;
			
			case "T":
				while(!secondAns.toUpperCase().equals("R")) {
					System.out.println(mailbox.getTrash().toString());
					System.out.println("M - Move email"
							+ "\nV - View email contents"
							+ "\nSA - Sort by subject line in ascending order"
							+ "\nSD - Sort by subject line in descending order"
							+ "\nDA - Sort by date in ascending order"
							+ "\nDD - Sort by date in descending order"
							+ "\nR - Return to mailbox"
							+ "\n\nEnter a user option: ");
					
					secondAns = sc.nextLine();
					System.out.println();
					
					switch(secondAns.toUpperCase()) {
					case "M":
						System.out.println("Enter the index of the email to move: ");
						try {
							int moveIndex = Integer.parseInt(sc.nextLine());
							if (mailbox.getTrash().getEmails().size() == 0) {
								System.out.println("Trash is empty!");
							}
							else if (moveIndex < 1 || moveIndex > mailbox.getTrash().getEmails().size()) {
								System.out.println("Please enter a valid index.");
							}
							else {
								System.out.println("Folders:"
										+ "\nInbox"
										+ "\nTrash");
								for (int i = 0; i < mailbox.getFolderList().size(); i++) {
									System.out.println(mailbox.getFolderList().get(i).getName());
								}
								System.out.println("\nSelect a folder to move \"" + mailbox.getTrash().getEmails().get(moveIndex - 1).getSubject() + "\" to: ");
								String targetFolderName = sc.nextLine();
								if (targetFolderName.equals("Inbox")) {
									Email movedEmail = mailbox.getTrash().getEmails().get(moveIndex - 1);
									mailbox.moveEmail(mailbox.getTrash().removeEmail(moveIndex), mailbox.getInbox());
									System.out.println("\n\"" + movedEmail.getSubject() + "\" successfully moved to Inbox");
								}
								else {
									try {
										Folder targetFolder = mailbox.getFolder(targetFolderName);
										Email movedEmail = mailbox.getTrash().getEmails().get(moveIndex - 1);
										mailbox.moveEmail(mailbox.getTrash().removeEmail(moveIndex), targetFolder);
										System.out.println("\n\"" + movedEmail.getSubject() + "\" successfully moved to " + targetFolderName);
									} catch (FolderNotFoundException e) {
										System.out.println(e.getMessage());
									}
								}
							}
						} catch (NumberFormatException e) {
							System.out.println("Please enter a valid index.");
						}
						break;
						
					case "V":
						System.out.println("Enter email index: ");
						try {
							int emailIndex = Integer.parseInt(sc.nextLine());
							if (mailbox.getTrash().getEmails().size() == 0) {
								System.out.println(mailbox.getTrash().getName() + " is empty!");
							}
							else if (emailIndex < 1 || emailIndex > mailbox.getTrash().getEmails().size()) {
								System.out.println("Please enter a valid index.");
							}
							else {
								System.out.println(mailbox.getTrash().getEmails().get(emailIndex - 1).toString());
							}
						} catch(NumberFormatException e) {
							System.out.println("Please enter a valid index.");
						}
						break;
						
					case "SA":
						mailbox.getTrash().setCurrentSortingMethod("SA");
						mailbox.getTrash().sortBySubjectAscending();
						break;
						
					case "SD":
						mailbox.getTrash().setCurrentSortingMethod("SD");
						mailbox.getTrash().sortBySubjectDescending();
						break;
						
					case "DA":
						mailbox.getTrash().setCurrentSortingMethod("DA");
						mailbox.getTrash().sortByDateAscending();
						break;
						
					case "DD":
						mailbox.getTrash().setCurrentSortingMethod("DD");
						mailbox.getTrash().sortByDateDescending();
						break;
						
					case "R":
						break;
						
					default:
						System.out.println("Please enter a valid option.");
						break;
					}
				}
				secondAns = "";
				break;
				
			case "E":
				mailbox.clearTrash();
				break;
				
			case "Q":
				try {
					FileOutputStream file = new FileOutputStream("mailbox.obj");
					ObjectOutputStream out = new ObjectOutputStream(file);
					out.writeObject(mailbox);
					out.close();
					file.close();
					System.out.println("Program successfully exited and mailbox saved.");
				} catch(IOException e) {
					System.out.println("Error saving mailbox.");
				}
				break;
				
			default:
				System.out.println("Please enter a valid option.");
				break;
			
			}
		}
		sc.close();
	}
}
