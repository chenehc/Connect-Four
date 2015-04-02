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
	private JPanel topPanel, leftPanel;
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
		if (mode.equals("player")) build2PlayerBoard();
		else if (mode.equals("AI")) buildAIBoard();
		else if (mode.equals("setup")) buildPresetBoard();
	}
	
	public String getMode(){
		return mode;
	}
	
	public void setMode(String mode){
		this.mode = mode;
	}
	
	public BoardArray getBoardArray(){
		return brd;
	}
	
	//builds the board for a 2 player game
	private void build2PlayerBoard(){
		topPanel = new JPanel();
		leftPanel = new JPanel();
		
		JPanel center = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1024, 678);
            }
        };
        
		JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
		JPanel east = new JPanel(new GridBagLayout());
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
		
		JLabel spacer = new JLabel();
		buttonPanel.add(spacer);
		
		JTextField n = new JTextField();
		n.setPreferredSize(new Dimension(30, 45));
		n.setEditable(false);
		buttonPanel.add(n);
		
		spacer = new JLabel();
		buttonPanel.add(spacer);
		
		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(this);
		buttonPanel.add(newGameBtn);

		storeGameBtn = new JButton("Save");
		storeGameBtn.addActionListener(this);
		buttonPanel.add(storeGameBtn);

		loadGameBtn = new JButton("Load");
		loadGameBtn.addActionListener(this);
		buttonPanel.add(loadGameBtn);
		
		menuBtn =  new JButton("Menu");
		menuBtn.addActionListener(this);
		buttonPanel.add(menuBtn);

		//make an empty board;
		brd = new BoardArray(ROW,COLUMN);

		//add the components to the frame
		add(east, BorderLayout.EAST);
		center.add(leftPanel, BorderLayout.WEST);
		center.add(topPanel, BorderLayout.NORTH);
//		add(leftPanel,BorderLayout.WEST);
//		add(topPanel, BorderLayout.NORTH);
		add(center);
		pack();
		
		brd.newGame();
		Game.start(this);
		repaint();
	}
	
	//builds the board components for playing against AI
	private void buildAIBoard(){
		topPanel = new JPanel();
		leftPanel = new JPanel();

		JPanel center = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1024, 678);
            }
        };
        
		JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
		JPanel east = new JPanel(new GridBagLayout());
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
		
		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(this);
		buttonPanel.add(newGameBtn);

		storeGameBtn = new JButton("Save");
		storeGameBtn.addActionListener(this);
		buttonPanel.add(storeGameBtn);

		loadGameBtn = new JButton("Load");
		loadGameBtn.addActionListener(this);
		buttonPanel.add(loadGameBtn);
		
		menuBtn =  new JButton("Menu");
		menuBtn.addActionListener(this);
		buttonPanel.add(menuBtn);

		//make an empty board;
		brd = new BoardArray(ROW,COLUMN);
		
		//add the components to the frame
		add(east, BorderLayout.EAST);
		center.add(leftPanel, BorderLayout.WEST);
		center.add(topPanel, BorderLayout.NORTH);
//		add(leftPanel,BorderLayout.WEST);
//		add(topPanel, BorderLayout.NORTH);
		add(center);
		pack();
		
		brd.newGame();
		Game.start(this);
		repaint();
	}
	
	//builds the board for manual board set up
		private void buildPresetBoard(){
			topPanel = new JPanel();
			leftPanel = new JPanel();
		
			JPanel center = new JPanel(){
	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(1024, 678);
	            }
	        };
	        
			JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
			JPanel east = new JPanel(new GridBagLayout());
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
			
			newGameBtn = new JButton("New Game");
			newGameBtn.addActionListener(this);
			buttonPanel.add(newGameBtn);
			
			blueTurn = new JButton("Blue");
			blueTurn.addActionListener(this);
			buttonPanel.add(blueTurn);
			
			redTurn = new JButton("Red");
			redTurn.addActionListener(this);
			buttonPanel.add(redTurn);
			
			endStateBtn = new JButton("End State");
			endStateBtn.addActionListener(this);
			buttonPanel.add(endStateBtn);
			
			storeGameBtn = new JButton("Save");
			storeGameBtn.addActionListener(this);
			buttonPanel.add(storeGameBtn);
			
			loadGameBtn = new JButton("Load");
			loadGameBtn.addActionListener(this);
			buttonPanel.add(loadGameBtn);

			menuBtn =  new JButton("Menu");
			menuBtn.addActionListener(this);
			buttonPanel.add(menuBtn);
			
			//make an empty board;
			brd = new BoardArray(ROW,COLUMN);
			
			//add the components to the frame
			add(east, BorderLayout.EAST);
			center.add(leftPanel, BorderLayout.WEST);
			center.add(topPanel, BorderLayout.NORTH);
//			add(leftPanel,BorderLayout.WEST);
//			add(topPanel, BorderLayout.NORTH);
			add(center);
			pack();
			
			brd.newGame();
			Game.start(this);
			repaint();
		}
	
	public void changeTitle(String s){
		setTitle(s);
	}
	
	public void paint(Graphics g){
		super.paint(g);
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
	
	public static void endGame(){
		endGame=true;
	}

	//this message plays when the user presses the 'End State' button
	public static void displayEndState(Board b){
		JOptionPane.showMessageDialog(b, "Game Over, press 'New Game' to play again");
	}
	
	//sets action depending on which button is pressed
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
			if (!endGame) setTitle("Connect Four - Red's Turn - Game in Progress");
		}else if (e.getSource() == blueTurn){
			//change current player and change title
			Game.currentPlayer = Piece.BLUE;
			if (!endGame) setTitle("Connect Four - Blue's Turn - Game in Progress");
		}else if (e.getSource() == endStateBtn){
			//end the game and check the board
			endGame = true;
			checkWin.checkState(brd, this);
			displayEndState(this);
			setTitle("Connect Four - Game Ended");
		}else if (e.getSource() == storeGameBtn){
			try {
				Game.save(brd, this);
				//TODO add mode to the save file
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == loadGameBtn){
			try {
				Game.resume(brd, this);
				//TODO add mode to read file
				repaint();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
}
	
