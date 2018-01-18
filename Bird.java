import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class FlappyBird {
   
   public static void main(String[] args) {
      
      // Construct the JFrame object
      JFrame appFrame = new JFrame();

      // Set the frame's width and height in pixels
      appFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      appFrame.getContentPane().setBackground(Color.BLUE);
      
      // Set the frame's title
      appFrame.setTitle("Flappy Bird");
      
      // Set the program to exit when the user
      // closes the frame
      appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Make the frame visible to the user
      appFrame.setVisible(true);
   }
}
