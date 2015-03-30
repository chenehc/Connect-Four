package connectFour;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener{
	private JButton vsPlayer, vsAI, preset;
	
	public Menu(){
		setTitle("Menu");
		buildMenu();
	}
	
	private void buildMenu(){
		setSize(400,600);
		vsPlayer = new JButton ("2 Player");
		vsPlayer.addActionListener(this);
		vsAI = new JButton ("VS AI");
		vsAI.addActionListener(this);
		preset = new JButton ("Make Preset");
		preset.addActionListener(this);
		
		add(vsPlayer, BorderLayout.NORTH);
		add(vsAI, BorderLayout.CENTER);
		add(preset, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == vsPlayer){
			setVisible(false);
			dispose();
			Board brd = new Board("player");
			brd.setVisible(true);
			brd.setLocationRelativeTo(null);
		}else if (e.getSource() == vsAI){
			Board brd = new Board("AI");
			brd.setVisible(true);
			brd.setLocationRelativeTo(null);
		}else if (e.getSource() == preset){
			Board brd = new Board("preset");
			brd.setVisible(true);
			brd.setLocationRelativeTo(null);
		}
	}
}
