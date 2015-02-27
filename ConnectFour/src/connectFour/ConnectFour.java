package connectFour;

import javax.swing.JFrame;

public class ConnectFour{
	public static void main(String args[]){
		Board board = new Board();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
		board.setLocationRelativeTo(null);
		board.setResizable(false);
	}
}

