package connectFour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*******************************************************************************
 * This class holds a Menu object which is a frame that shows the 3 play
 * options available in the game and makes Board objects based on 
 * the option selected by the user
 ******************************************************************************/

public class Menu extends JFrame implements ActionListener{
	
	private JButton vsPlayer, vsAI, setup;
	
	//constructor
	public Menu(){
		setTitle("Menu");
		buildMenu();
	}
	
	/**
	 * This method builds the frame components used in menu
	 */
	private void buildMenu(){
		setSize(200,300);
		JPanel pane = new JPanel(new GridLayout(3,1));
		pane.setVisible(true);
		
		vsPlayer = new JButton ("2 Player");
		vsPlayer.addActionListener(this);
		vsPlayer.setPreferredSize(new Dimension (200, 50));
		pane.add(vsPlayer);
		
		vsAI = new JButton ("VS AI");
		vsAI.addActionListener(this);
		pane.add(vsAI);
		
		setup = new JButton ("Set up board");
		setup.addActionListener(this);
		pane.add(setup);
		
		add(pane, BorderLayout.CENTER);
		
	}
	
	/**
	 * This method detects the buttons pressed and 
	 * opens a board that corresponds to the option selected
	 */
	public void actionPerformed(ActionEvent e){
		//opens a different board based on the option chosen
		if (e.getSource() == vsPlayer){
			setVisible(false);
			dispose();
			Board brd = new Board("player");
			brd.setVisible(true);
			brd.setLocationRelativeTo(null);
		}else if (e.getSource() == vsAI){
			setVisible(false);
			dispose();
			Board brd = new Board("AI");
			brd.setVisible(true);
			brd.setLocationRelativeTo(null);
		}else if (e.getSource() == setup){
			setVisible(false);
			dispose();
			Board brd = new Board("setup");
			brd.setVisible(true);
			brd.setLocationRelativeTo(null);
		}
	}
}
