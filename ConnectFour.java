package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This program is the game Connect Four
 * @Author Hui Chen
 * @version 1.0
 * @since 2015-02-13
 */
public class ConnectFour{
	public static void main(String args[]){
		Board board = new Board();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
		board.setLocationRelativeTo(null);
		board.setResizable(false);
		
	}
}

class Board extends JFrame implements ActionListener{
	private static final int COLUMN = 7;
	private static final int ROW = 6;
	private JButton col1, col2, col3, col4, col5, col6, col7;
	private JButton playBtn, newGameBtn;
	private JPanel startPanel, mainPanel, bluePanel, redPanel;
	private JLabel blueTurn, redTurn;
	private boolean endGame = false;
	private static final char RED = 'r';
	private static final char BLUE = 'b';
	private static final char EMPTY = 'e';
	private String space = "                ";
	private char currentPlayer;
	
	
	
	public Board(){
		setTitle ("Connect Four");
//		buildStartGUI();
		findFirst();
		buildBoardGUI();
		
	}
	
//	private void buildStartGUI(){
//		setSize(350, 200);
//		startPanel = new JPanel
//		startPanel.setLayout(new GridLayout(1, 1));
//		add(startPanel);
//		playBtn = new JButton("Play");
//		playBtn.addActionListener(this);
//		startPanel.add(playButton);
//		
//	}
//	
	//builds the board components
	private void buildBoardGUI(){
		mainPanel = new JPanel();
		// bluePanel = new JPanel();
		// redPanel = new JPanel();
		
		// add(bluePanel, BorderLayout.WEST);
		// add(redPanel,BorderLayout.EAST);
		add(mainPanel, BorderLayout.NORTH);
		setSize(1024, 768);
		
		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(this);
		bluePanel.add(newGameBtn);
		
		col1 = new JButton("V");
		col1.addActionListener(this);
		mainPanel.add(col1);
		JLabel spaceLbl = new JLabel(space);
		mainPanel.add(spaceLbl);
		
		col2 = new JButton("V");
		col2.addActionListener(this);
		mainPanel.add(col2);
		spaceLbl = new JLabel(space);
		mainPanel.add(spaceLbl);
		
		col3 = new JButton("V");
		col3.addActionListener(this);
		mainPanel.add(col3);
		spaceLbl = new JLabel(space);
		mainPanel.add(spaceLbl);
		
		col4 = new JButton("V");
		col4.addActionListener(this);
		mainPanel.add(col4);
		spaceLbl = new JLabel(space);
		mainPanel.add(spaceLbl);
		
		col5 = new JButton("V");
		col5.addActionListener(this);
		mainPanel.add(col5);
		spaceLbl = new JLabel(space);
		mainPanel.add(spaceLbl);
		
		col6 = new JButton("V");
		col6.addActionListener(this);
		mainPanel.add(col6);
		spaceLbl = new JLabel(space);
		mainPanel.add(spaceLbl);
		
		col7 = new JButton("V");
		col7.addActionListener(this);
		mainPanel.add(col7);
		
		// blueTurn = new JLabel("Blue's turn");
		// redTurn = new JLabel("Red's turn");
		// bluePanel.add(blueTurn);
		// redPanel.add(redTurn);
		startPlay();
	}
	
	//starts the game
	public void startPlay(){
		boardArray = new char[ROW][COLUMN];
        for (int row=0; row<ROW; row++)
                for (int col=0; col<COLUMN; col++)
                        boardArray[row][col]=EMPTY;
        endGame=false;
	}
	
	//determines who goes first
	public void findFirst(){
		Random rng = new Random();
		int randomInt = rng.nextInt(100);
		//if the number generated is 0<=x<50, blue is first, else red is first
		if (randomInt < 50) currentPlayer = BLUE;
		else currentPlayer = RED;
	}
	
	public void paint(Graphics g){
		//draw rectangular frame
		g.setColor(Color.BLACK);
        g.drawRect(135, 80, 100 * COLUMN + 50, 100 * ROW + 50);
        
        //draw player piece holder
        g.setColor(Color.DARK_GRAY);
        g.fillRect(8, 200, 100, 100);
        g.fillRect(915, 200, 100, 100);
        g.setColor(Color.GREEN);
        int x1, x2, x3, y1, y2, y3;
        x1 = x2 = x3 = 30;
        y1 = y2 = y3 = 500;
        g.fillPolygon(new int[]{x1, x2, x3}, new int[] {y1,y2,y3}, 3);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
        
        //draw player pieces
        g.setColor(Color.BLUE);
        g.fillOval(8, 150, 100, 100);
        g.setColor(Color.RED);
        g.fillOval(915, 150, 100, 100);
        
        for (int row=0; row<ROW; row++)
                for (int col=0; col<COLUMN; col++) {
                        if (boardArray[row][col]==EMPTY) g.setColor(Color.WHITE);
                        g.fillOval(160+100*col, 100+100*row, 100, 100);
                }
        
	}
	
	//add player piece to the board
	public void addDisk(int col){
		if (endGame) return;
		
	}
	
	//shows the winner and ends the game
	public void displayWinner(int player){
		if (player == RED){
			JOptionPane.showMessageDialog(this, "Red wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "Blue wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}
		endGame = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == col1){
			addDisk(1);
		}else if(e.getSource() == col2){
			addDisk(2);
		}else if (e.getSource() == col3){
			addDisk(3);
		}else if (e.getSource() == col4){
			addDisk(4);
		}else if (e.getSource() == col5){
			addDisk(5);
		}else if (e.getSource() == col6){
			addDisk(6);
		}else if (e.getSource() == col7){
			addDisk(7);
		}else if(e.getSource() == newGameBtn){
			startPlay();
			super.repaint();
		}else if (e.getSource() == playBtn){
			buildBoardGUI();
		}
		
	}
}
