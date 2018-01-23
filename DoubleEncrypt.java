/*************************************************
  * Authors: Diego Villatoro and John Ventura
  * CS112 Intro to Programming I
  * GS@IP Fall 2017
  * 
  * Programming Assignment 7
  * 
  * Description: A program that double encrypts user inputted strings with the Caesar Cipher encription mechanism
  *
  *
  * Honor Code: Unless permission to do so is granted by the instructor, you (or your group, if a group assignment) may not
  *             give a copy of your work in any form to another student. You must report any violations that you become aware of.
  * 
  * **********************************************/

// import statements
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics; 

public class DoubleEncryption extends JFrame{ 



   //making the pop-ups
   

   // method: encryptWord
   public static String encryptWord(String word, int key) {
      String encryptedWord = "";
         for (int i = 0; i < word.length(); ++i) {
            char ch = (char)(word.charAt(i) + key);         //char plus int casted as a char so ascii value is incremented by key value and stored as a char
            if (ch > 'z') {
               encryptedWord += (char)(word.charAt(i) - (26 - key));       //char minus an int casted as a char so proper ascii value is stored as a char
            }
            else {
               encryptedWord += ch;       //ascii value incrementation is applied to the word's character at its location in the loop
            }
         }
      return encryptedWord;
   }
   
   // method: decryptWord
   public static String decryptWord(String word, int key) {
      String decryptedWord = "";
         for (int i = 0; i < word.length(); ++i) {
            char ch = (char)(word.charAt(i) - key);         //char minus int casted as a char so ascii value is decremented by key value and stored as a char
            if (ch < 'a') {
               decryptedWord += (char)(word.charAt(i) + (26 - key));       //char plus an int casted as a char so proper ascii value is stored as a char
            }
            else {
               decryptedWord += ch;       //ascii value decrementation is applied to the word's character at its location in the loop
            }
         }
      return decryptedWord;
   }
   
   // method: encryptArray
   public static String[] encryptArray(String[] array, int key) {
      for (int i = 0; i < key; ++i) {        //loops amount of times given by the key
         String temp = array[array.length - 1];       //element in final index is stored into a temporary variable
            for (int j = array.length - 1; j > 0; --j) {
               array[j] = array[j - 1];         //loop which shifts elements to the right by 1 (performed as many times as key loop is performed)
            }
         array[0] = temp;        //element at index zero is replaced by temporary variable
      }
      return array;
   }
   
   // method: decryptArray
   public static String[] decryptArray(String[] array, int key) {
      for (int i = 0; i < key; ++i) {        //loops amount of times given by the key
         String temp = array[0];       //element at index zero is stored into a temporary variable
            for (int j = 0; j < array.length - 1; ++j) {
               array[j] = array[j + 1];         //loop which shifts elements to the left by 1 (performed as many times as key loop is performed)
            }
         array[array.length - 1] = temp;        //element in final index is replaced by temporary variable
      }
      return array;
   }
   
   // method: printArray
   public static String printArray(String[] stringArray) {
      return Arrays.toString(stringArray);
   }
   
   // method: stringCheck
   public static boolean stringCheck(String word) {
      boolean returnValue = true;         //declares and initializes return value
         for (int i = 0; i < word.length(); ++i) {
            if ((int)word.charAt(i) >= 97 && (int)word.charAt(i) <= 122) {       //makes sure ascii value is between 97 and 122
               returnValue = true;
            }
            else {
               returnValue = false;
            }
         }
      return returnValue;
   }
   
   
   
   //method: AL
   public static class AL extends DoubleEncryption implements ActionListener {
   
   public void actionPerformed(ActionEvent a){          
   JPasswordField field = (JPasswordField) a.getSource();  //creates a JPasswordField called field
   char[] pass1 = field.getPassword();                  //creates a char array called pass1
   String p = new String(pass1);                        //creates a string called p which takes in the characters
      
   if (p.equals(password)){                                //if p is equal to the password                             
         correct = true;                                   //true
   }
   else{                                                   //else
         JOptionPane.showMessageDialog(null,"Wrong Password. Try Again");   //print "Wrong Password. Try Again" 
         correct = false;     
   }
  }    
 }
 
   private static String password = "password";           //creat a private static string called password
   
   // method: main
   public static boolean correct = false;

   public static void main(String[] args){ 
      
      JFrame frame = new JFrame("Secret Password");       //creates a JFrame titled "Secret Password
      frame.pack();
      frame.setVisible(true);
      frame.setSize(400,100);
     
      
      JLabel label = new JLabel("Enter Top Secret Password"); //creates a JLabel that says "Enter Top Secre Password"
      label.setForeground(Color.white);                       //changes text color to white
      JPanel panel = new JPanel();                            //creates a new JPanel
      frame.add(panel);
      panel.setBackground(Color.BLACK);                       //changes the panel color to black

      JPasswordField pass = new JPasswordField(10);           //creates a new password field
      pass.setEchoChar('*');                                  //sets the password characters to '*'
      pass.addActionListener(new AL());
      panel.add(label, BorderLayout.WEST);
      panel.add(pass, BorderLayout.EAST);

      pass.addActionListener(new java.awt.event.ActionListener() {
      @Override
      
      public void actionPerformed(java.awt.event.ActionEvent evt) {
      if(correct){                                                  //if true it runs through the following code
      UIManager.put("OptionPane.messageForeground", Color.white);   //changes text to white
      UIManager.put("OptionPane.background", Color.black);          //changes background to black
      UIManager.put("Panel.background", Color.black);               //changes panel to black
      UIManager.put("Button.background", Color.white);              //changes button to white
      
      String arraySizePane = JOptionPane.showInputDialog("Enter the size of the array:");        //asks user for array size
      int arraySize = Integer.parseInt(arraySizePane);
      String[] userArray = new String[arraySize];                   //creates new string array
      
      
         for (int i = 0; i < userArray.length; ++i) {
            String arrayElementPane = JOptionPane.showInputDialog("Enter array element:");       //asks user for element to be stored in that specific index      
            userArray[i] = arrayElementPane.toLowerCase();         //converts user input to lowercase
            if (stringCheck(userArray[i]) != true) {               //passes elements in string array through stringCheck method
               String alphabetError = JOptionPane.showInputDialog("Please input letters between a-z or A-Z");
               userArray[i] = alphabetError.toLowerCase();
            }
         }
      String key1Pane = JOptionPane.showInputDialog("Enter key #1:");       //asks user for key values
      int key1 = Integer.parseInt(key1Pane);
      String key2Pane = JOptionPane.showInputDialog("Enter key #2:");
      int key2 = Integer.parseInt(key2Pane);
      int encryptDecrypt = 0;
      String EnDe_cryptPane = JOptionPane.showInputDialog("Press 0 to Encrypt or 1 to decrypt:");       //asks user for encryption or decription
      encryptDecrypt = Integer.parseInt(EnDe_cryptPane);
         while (encryptDecrypt != 0 && encryptDecrypt != 1) {        //makes sure user input is 0 or 1
            String EnDe_cryptPaneError = JOptionPane.showInputDialog("ERROR: Press 0 to Encrypt or 1 to decrypt:");
            encryptDecrypt = Integer.parseInt(EnDe_cryptPaneError); 
         }
      JOptionPane.showMessageDialog(null, "Initial Array:" + printArray(userArray), "Initial Array", JOptionPane.PLAIN_MESSAGE);   //prints array elements stored in the created array by passing it through the printArray method
      String[] encryptedUserArray = new String[arraySize];        //creates new string array to use for encription/decription
      if (encryptDecrypt == 0) {
         for (int i = 0; i < userArray.length; ++i) {
            encryptedUserArray[i] = encryptWord(userArray[i], key1);       //passes strings in userArray through encryptWord method
         }                                                                 //and each encrypted string is stored in specific index of the new string array
         JOptionPane.showMessageDialog(null, "Encrypted Array:" + printArray(encryptArray(encryptedUserArray, key2)), "Encrypted Array", JOptionPane.PLAIN_MESSAGE);   //passes new string array through encryptArray method which is then printed by printArray method
      }
      else {
         for (int i = 0; i < userArray.length; ++i) {
            encryptedUserArray[i] = decryptWord(userArray[i], key1);       //passes strings in userArray through decryptWord method
         }                                                                 //and each encrypted string is stored in specific index of the new string array
         JOptionPane.showMessageDialog(null, "Decrypted Array:" + printArray(decryptArray(encryptedUserArray, key2)), "Decrypted Array" , JOptionPane.PLAIN_MESSAGE);  //passes new string array through decryptArray method which is then printed by printArray method
       }
      }
     }
      
  });
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}
