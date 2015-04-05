package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*****************************************************************************************************
 * This class holds the Board component of the game, dealing with the actual board and updates the 
 * frame when a change has been made to the BoardArray
 *****************************************************************************************************/

public class Board extends JFrame implements ActionListener{
	private static final int COLUMN = 7;
	private static final int ROW = 6;
	private JButton col1, col2, col3, col4, col5, col6, col7;
	private JButton newGameBtn, endStateBtn, storeGameBtn, loadGameBtn, menuBtn;
	private JPanel topPanel, leftPanel, east, buttonPanel;
	private JTextField playerColour;
	private JButton blueTurn, redTurn;
	private String space = "                ";
	private String mode;
	
	public static boolean endGame = false;
	private BoardArray brd;
	
	//Constructor
	public Board(String mode){
		this.mode = mode;
		setTitle ("Connect Four");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		brd = new BoardArray(ROW, COLUMN);
		buildBoard();
	}
	
	/**
	 * This method gets the mode for the game
	 * @return mode - game mode that it's currently in
	 */
	public String getMode(){
		return mode;
	}
	
	/**
	 * This method sets the mode for the game
	 * Only used by save function
	 * @param mode - the mode that the game will be
	 */
	public void setMode(String mode){
		this.mode = mode;
	}
	
	/**
	 * This method gets the current BoardArray
	 * @return brd - BoardArray used for the board
	 */
	public BoardArray getBoardArray(){
		return brd;
	}
	
	/**
	 * This method builds the frame components for the game
	 */
	private void buildBoard(){
		topPanel = new JPanel();
		leftPanel = new JPanel();
		
		JPanel center = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1024, 678);
            }
        };
        
		buttonPanel = new JPanel(new GridLayout(10, 1));
		east = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        east.add(buttonPanel, gbc);
        
		//set up the buttons
		col1 = new JButton("V");
		col1.addActionListener(this);
		topPanel.add(col1);
		JLabel spaceLbl = new JLabel(space);
		topPanel.add(spaceLbl);

		col2 = new JButton("V");
		col2.addActionListener(this);
		topPanel.add(col2);
		spaceLbl = new JLabel(space);
		topPanel.add(spaceLbl);

		col3 = new JButton("V");
		col3.addActionListener(this);
		topPanel.add(col3);
		spaceLbl = new JLabel(space);
		topPanel.add(spaceLbl);

		col4 = new JButton("V");
		col4.addActionListener(this);
		topPanel.add(col4);
		spaceLbl = new JLabel(space);
		topPanel.add(spaceLbl);

		col5 = new JButton("V");
		col5.addActionListener(this);
		topPanel.add(col5);
		spaceLbl = new JLabel(space);
		topPanel.add(spaceLbl);

		col6 = new JButton("V");
		col6.addActionListener(this);
		topPanel.add(col6);
		spaceLbl = new JLabel(space);
		topPanel.add(spaceLbl);

		col7 = new JButton("V");
		col7.addActionListener(this);
		topPanel.add(col7);
		
		JLabel spacer = new JLabel("Current Player: ");
		buttonPanel.add(spacer);
		
		playerColour = new JTextField();
		playerColour.setPreferredSize(new Dimension(30, 45));
		playerColour.setEditable(false);
		buttonPanel.add(playerColour);
		
		spacer = new JLabel();
		buttonPanel.add(spacer);
		
		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(this);
		buttonPanel.add(newGameBtn);

		if (mode == "setup"){
			blueTurn = new JButton("Blue");
			blueTurn.addActionListener(this);
			buttonPanel.add(blueTurn);
			
			redTurn = new JButton("Red");
			redTurn.addActionListener(this);
			buttonPanel.add(redTurn);
			
			endStateBtn = new JButton("End State");
			endStateBtn.addActionListener(this);
			buttonPanel.add(endStateBtn);
		}
		
		if (mode != "setup"){
		storeGameBtn = new JButton("Save");
		storeGameBtn.addActionListener(this);
		buttonPanel.add(storeGameBtn);

		loadGameBtn = new JButton("Load");
		loadGameBtn.addActionListener(this);
		buttonPanel.add(loadGameBtn);
		}
		
		menuBtn =  new JButton("Menu");
		menuBtn.addActionListener(this);
		buttonPanel.add(menuBtn);

		//add the components to the frame
		add(east, BorderLayout.EAST);
		center.add(leftPanel, BorderLayout.WEST);
		center.add(topPanel, BorderLayout.NORTH);
		add(center);
		pack();
		
//		brd.newGame();
		Game.start(this);
		repaint();
	}
	
	/**
	 * This method deals will all the shapes and colouring on the frame, 
	 * also used for updating pieces on the board
	 */
	public void paint(Graphics g){
		super.paint(g);
		
		//just a gimmick, not really necessary, the title displays the current game status
		if (Game.currentPlayer == Piece.BLUE) playerColour.setBackground(Color.BLUE);
		else if (Game.currentPlayer == Piece.RED) playerColour.setBackground(Color.RED);
		
		//draw rectangular frame
		g.setColor(Color.BLACK);
        g.drawRect(150, 75, 100 * COLUMN + 30, 95 * ROW + 30);
        
        //draw player piece holder
        g.setColor(Color.DARK_GRAY);
        g.fillRect(30, 200, 90, 90);
        g.fillRect(900, 200, 90, 90);
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
        g.fillOval(30, 150, 90, 90);
        g.setColor(Color.RED);
        g.fillOval(900, 150, 90, 90);
        
        for (int row=0; row<ROW; row++)
                for (int col=0; col<COLUMN; col++) {
                        if (brd.isEmpty(row, col)) g.setColor(Color.WHITE);
                        if (brd.isRed(row, col)) g.setColor(Color.RED);
                        if (brd.isBlue(row, col)) g.setColor(Color.BLUE);
                        g.fillOval(175+100*col, 80+100*row, 90, 90);
                }
	}
	
	/**
	 * This method updates the title 
	 * @param s - The new title
	 */
	public void changeTitle(String s){
		setTitle(s);
	}
	
	/**
	 * This method sets endGame to true, ends the game
	 */
	public void endGame(){
		endGame=true;
	}
	
	/**
	 * This method updates the buttonPanel when the End State button is pressed
	 */
	public void updatePanelAfterSetup(){	
		//remove components not needed, and add the components for playing a normal game
		buttonPanel.remove(blueTurn);
		buttonPanel.remove(redTurn);
		buttonPanel.remove(endStateBtn);
		buttonPanel.remove(menuBtn);
		
		storeGameBtn = new JButton("Save");
		storeGameBtn.addActionListener(this);
		buttonPanel.add(storeGameBtn);

		loadGameBtn = new JButton("Load");
		loadGameBtn.addActionListener(this);
		buttonPanel.add(loadGameBtn);
		
		menuBtn =  new JButton("Menu");
		menuBtn.addActionListener(this);
		buttonPanel.add(menuBtn);
		
		revalidate();
		repaint();
	}
	
	/**
	 * This message displays a message when the user attemps to play the game after it ended
	 */
	public void displayEndGame(){
		JOptionPane.showMessageDialog(this, "Game Over, press 'New Game' to play again");
	}
	
	/**
	 * This method sets actions based on which button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == col1){
			Game.addDisk(1, this, brd);
		}else if(e.getSource() == col2){
			Game.addDisk(2, this, brd);
		}else if (e.getSource() == col3){
			Game.addDisk(3, this, brd);
		}else if (e.getSource() == col4){
			Game.addDisk(4, this, brd);
		}else if (e.getSource() == col5){
			Game.addDisk(5, this, brd);
		}else if (e.getSource() == col6){
			Game.addDisk(6, this, brd);
		}else if (e.getSource() == col7){
			Game.addDisk(7, this, brd);
		}else if(e.getSource() == menuBtn){
			//close current board and open the menu
			setVisible(false);
			dispose();
			Menu menu = new Menu();
			menu.setVisible(true);
			menu.setLocationRelativeTo(null);
		}else if(e.getSource() == newGameBtn){
			//start a new game
			brd.newGame();
			Game.start(this);
			repaint();
		}else if (e.getSource() == redTurn){
			//change current player and change title
			Game.currentPlayer = Piece.RED;
			repaint();
			if (!endGame) setTitle("Connect Four - Red's Turn - Setup Mode");
		}else if (e.getSource() == blueTurn){
			//change current player and change title
			Game.currentPlayer = Piece.BLUE;
			repaint();
			if (!endGame) setTitle("Connect Four - Blue's Turn - Setup Mode");
		}else if (e.getSource() == endStateBtn){
			//check the board
			//assuming setup is for 2 player only
			Game.checkState(brd, this);
		}else if (e.getSource() == storeGameBtn){
			if (endGame) displayEndGame();
			else{
				try {
					Game.save(brd, this);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}else if (e.getSource() == loadGameBtn){
				try {
					Game.resume(brd, this);
					repaint();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		}
	}
}
