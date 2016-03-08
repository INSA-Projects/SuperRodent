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
		
		
		
		
		
		
		
		Initializator init = new Initializator();
		
		// init of the board
		Board board = new Board(23);
		init.initBoard(board);
		
		// init rat controler
		RatControler ratControl = new RatControler(board);		
		typingArea.addKeyListener(ratControl); // we don't care for sequence diagram
		init.addRatToBoard(ratControl, board);
		
		// init cat controler
		CatControler catControl = new CatControler(board);
		init.addCatToBoard(catControl, board);
		
		// show the board
		System.out.println(""+board.toString());
	}

}
