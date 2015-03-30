package connectFour;

import javax.swing.JFrame;

public class ConnectFour{
	public static void main(String args[]){
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setResizable(false);
	}
}

