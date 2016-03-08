import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class Program 
{
	public static void main(String args[])
	{
		// init of the frame (we don't care for now)
		JFrame frame = new JFrame("SuperRodent");
		frame.setSize(100, 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextField typingArea = new JTextField(20);
		frame.getContentPane().add(typingArea, BorderLayout.CENTER);
		frame.setVisible(true);
		
		
		
				
		// board
		Board board = new Board(23);
		
		// rat controler
		RatControler ratControl = new RatControler(board);		
		typingArea.addKeyListener(ratControl); // we don't care for sequence diagram

		// initializator
		Initializator init = new Initializator(board, ratControl);
		init.resetBoard();
		
		// show the board
		System.out.println(board.toString());
	}

}
