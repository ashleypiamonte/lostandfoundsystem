/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lostandfoundsystem;


import java.awt.Component;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class LostAndFound {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] users = new String[5];
        String[] passwords = new String[5];
        int userCount = 0;
        String[][] items = new String[5][5];
        int itemCount = 0;
        boolean loggedIn = false;

        label225:
        while(true) {
            String mainChoice = JOptionPane.showInputDialog("LOST & FOUND SYSTEM\n[1] SIGN UP\n[2] LOGIN\n[3] EXIT\nChoose:");
            if (mainChoice == null) {
                return;
            }

            switch (mainChoice) {
                case "1":
                    if (userCount >= 5) {
                        JOptionPane.showMessageDialog((Component)null, "Maximum users reached!");
                    } else {
                        System.out.print("Enter username: ");
                        String newUser = sc.nextLine().trim();
                        boolean exists = false;

                        for(int i = 0; i < userCount; ++i) {
                            if (users[i].equalsIgnoreCase(newUser)) {
                                exists = true;
                                break;
                            }
                        }

                        if (exists) {
                            JOptionPane.showMessageDialog((Component)null, "Username already exists!");
                        } else {
                            System.out.print("Enter password: ");
                            String newPass = sc.nextLine().trim();
                            if (!newUser.isEmpty() && !newPass.isEmpty()) {
                                users[userCount] = newUser;
                                passwords[userCount] = newPass;
                                ++userCount;
                                JOptionPane.showMessageDialog((Component)null, "Account created!");
                            } else {
                                JOptionPane.showMessageDialog((Component)null, "Fields cannot be empty!");
                            }
                        }
                    }
                    break;
                case "2":
                    String u = JOptionPane.showInputDialog("Enter Username:");
                    String p = JOptionPane.showInputDialog("Enter Password:");
                    boolean found = false;
                    int currentUser = -1;

                    for(int i = 0; i < userCount; ++i) {
                        if (users[i].equals(u) && passwords[i].equals(p)) {
                            found = true;
                            loggedIn = true;
                            break;
                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog((Component)null, "INVALID CREDENTIALS!");
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "LOGIN SUCCESSFUL!");

                        while(loggedIn) {
                            String menuChoice = JOptionPane.showInputDialog("===== MAIN MENU =====\n[1] ADD ITEM\n[2] VIEW ITEMS\n[3] UPDATE ITEM (CLAIM)\n[4] DELETE ITEM\n[5] LOGOUT\nChoose:");
                            if (menuChoice == null) {
                                continue label225;
                            }

                            switch (menuChoice) {
                                case "1":
                                    if (itemCount >= 5) {
                                        JOptionPane.showMessageDialog((Component)null, "Cannot add more items!");
                                    } else {
                                        System.out.print("Item Name: ");
                                        String name = sc.nextLine().trim();
                                        System.out.print("Description (Location and Time): ");
                                        String desc = sc.nextLine().trim();
                                        System.out.print("Contact Info: ");
                                        String contact = sc.nextLine().trim();
                                        if (!name.isEmpty() && !desc.isEmpty() && !contact.isEmpty()) {
                                            String type;
                                            do {
                                                type = JOptionPane.showInputDialog("Type (Lost/Found):");
                                                if (type == null) {
                                                    break;
                                                }

                                                if (!type.equals("Lost") && !type.equals("Found")) {
                                                    JOptionPane.showMessageDialog((Component)null, "Invalid type!");
                                                    type = null;
                                                }
                                            } while(type == null);

                                            items[itemCount][0] = name;
                                            items[itemCount][1] = desc;
                                            items[itemCount][2] = contact;
                                            items[itemCount][3] = type;
                                            items[itemCount][4] = "Unclaimed";
                                            ++itemCount;
                                            JOptionPane.showMessageDialog((Component)null, "Item added successfully!");
                                            continue;
                                        }

                                        JOptionPane.showMessageDialog((Component)null, "Fields cannot be empty!");
                                    }
                                    break;
                                case "2":
                                    if (itemCount == 0) {
                                        JOptionPane.showMessageDialog((Component)null, "No items recorded.");
                                        break;
                                    }

                                    String list = "======== ITEMS LIST ========\n\n";

                                    for(int i = 0; i < itemCount; ++i) {
                                        list = list + "----------------- ITEM # " + (i + 1) + "-----------------\nName: " + items[i][0] + "\nDescription: " + items[i][1] + "\nContact: " + items[i][2] + "\nType: " + items[i][3] + "\nStatus: " + items[i][4] + "\n\n";
                                    }

                                    JOptionPane.showMessageDialog((Component)null, list);
                                    break;
                                case "3":
                                    if (itemCount == 0) {
                                        JOptionPane.showMessageDialog((Component)null, "No items to update.");
                                    } else {
                                        String updateItem = JOptionPane.showInputDialog("Enter item number to claim:");
                                        if (updateItem != null && !updateItem.isBlank()) {
                                            int up;
                                            try {
                                                up = Integer.parseInt(updateItem);
                                            } catch (NumberFormatException var32) {
                                                JOptionPane.showMessageDialog((Component)null, "Invalid input! Please enter a NUMBER.");
                                                continue;
                                            }

                                            if (up >= -1 && up <= itemCount) {
                                                if (items[up - 1][4].equals("Claimed")) {
                                                    JOptionPane.showMessageDialog((Component)null, "Already claimed.");
                                                } else {
                                                    items[up - 1][4] = "Claimed";
                                                    JOptionPane.showMessageDialog((Component)null, "Item marked as CLAIMED.");
                                                }
                                                continue;
                                            }

                                            JOptionPane.showMessageDialog((Component)null, "Invalid item number.");
                                            continue;
                                        }

                                        JOptionPane.showMessageDialog((Component)null, "Input cannot be empty!");
                                    }
                                    break;
                                case "4":
                                    if (itemCount == 0) {
                                        JOptionPane.showMessageDialog((Component)null, "No items to delete.");
                                    } else {
                                        String deleteItem = JOptionPane.showInputDialog("Enter item number to delete:");
                                        if (deleteItem != null && !deleteItem.isBlank()) {
                                            int del;
                                            try {
                                                del = Integer.parseInt(deleteItem);
                                            } catch (NumberFormatException var31) {
                                                JOptionPane.showMessageDialog((Component)null, "Invalid input! Please enter a NUMBER.");
                                                continue;
                                            }

                                            if (del >= 1 && del <= itemCount) {
                                                for(int i = del - 1; i < itemCount - 1; ++i) {
                                                    for(int j = 0; j < 5; ++j) {
                                                        items[i][j] = items[i + 1][j];
                                                    }
                                                }

                                                --itemCount;
                                                JOptionPane.showMessageDialog((Component)null, "Item deleted.");
                                                continue;
                                            }

                                            JOptionPane.showMessageDialog((Component)null, "Invalid item number.");
                                            continue;
                                        }

                                        JOptionPane.showMessageDialog((Component)null, "Input cannot be empty!");
                                    }
                                    break;
                                case "5":
                                    loggedIn = false;
                                    JOptionPane.showMessageDialog((Component)null, "Logged out.");
                                    break;
                                default:
                                    JOptionPane.showInputDialog((Component)null, "Invalid option.");
                            }
                        }
                    }
                    break;
                case "3":
                    JOptionPane.showMessageDialog((Component)null, "Exiting Program!");
                    sc.close();
                    return;
                default:
                    JOptionPane.showMessageDialog((Component)null, "Invalid choice.");
            }
        }
    }
}
