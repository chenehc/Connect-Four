package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*****************************************************************************************************
 * This class holds the frame component of the game
 * 
 * 
 *****************************************************************************************************/

public class Board extends JFrame implements ActionListener{
	private static final int COLUMN = 7;
	private static final int ROW = 6;
	private JButton col1, col2, col3, col4, col5, col6, col7;
	private JButton newGameBtn, endStateBtn, storeGameBtn, loadGameBtn, menuBtn;
	private JPanel topPanel, bottomPanel, leftPanel;
	private JButton blueTurn, redTurn;
	private String space = "                ";
	private String mode;
	
	public static boolean endGame = false;
	private BoardArray brd;
	
	//Instantiates the Board object
	public Board(String mode){
		this.mode = mode;
		setTitle ("Connect Four");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		if (mode.equals("player")) build2PlayerBoard();
		else if (mode.equals("AI")) buildAIBoard();
		else if (mode.equals("preset")) buildPresetBoard();
	}
	
	public String getMode(){
		return mode;
	}
	
	public BoardArray getBoardArray(){
		return brd;
	}
	
	//builds the board for a 2 player game
	private void build2PlayerBoard(){
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		leftPanel = new JPanel();

		setSize(1024, 700);
		
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
		bottomPanel.add(newGameBtn);

		storeGameBtn = new JButton("Save");
		storeGameBtn.addActionListener(this);
		bottomPanel.add(storeGameBtn);

		loadGameBtn = new JButton("Load");
		loadGameBtn.addActionListener(this);
		bottomPanel.add(loadGameBtn);
		
		menuBtn =  new JButton("Menu");
		menuBtn.addActionListener(this);
		bottomPanel.add(menuBtn);

		//make an empty board;
		brd = new BoardArray(ROW,COLUMN);

		//add the components to the frame
		add(bottomPanel, BorderLayout.SOUTH);
		add(leftPanel,BorderLayout.WEST);
		add(topPanel, BorderLayout.NORTH);
		
		brd.newGame();
		Game.start(this);
		repaint();
	}
	
	//builds the board components for playing against AI
	private void buildAIBoard(){
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		leftPanel = new JPanel();

		setSize(1024, 700);

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
		bottomPanel.add(newGameBtn);

		storeGameBtn = new JButton("Save");
		storeGameBtn.addActionListener(this);
		bottomPanel.add(storeGameBtn);

		loadGameBtn = new JButton("Load");
		loadGameBtn.addActionListener(this);
		bottomPanel.add(loadGameBtn);
		
		menuBtn =  new JButton("Menu");
		menuBtn.addActionListener(this);
		bottomPanel.add(menuBtn);

		//make an empty board;
		brd = new BoardArray(ROW,COLUMN);
		
		//add the components to the frame
		add(bottomPanel, BorderLayout.SOUTH);
		add(leftPanel,BorderLayout.WEST);
		add(topPanel, BorderLayout.NORTH);
		
		brd.newGame();
		Game.start(this);
		repaint();
	}
	
	//builds the board for handicapped game (pregame state)
		private void buildPresetBoard(){
			topPanel = new JPanel();
			bottomPanel = new JPanel();
			leftPanel = new JPanel();
		
			setSize(1024, 700);
			
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
			
			menuBtn =  new JButton("Menu");
			menuBtn.addActionListener(this);
			bottomPanel.add(menuBtn);
			
			newGameBtn = new JButton("New Game");
			newGameBtn.addActionListener(this);
			bottomPanel.add(newGameBtn);
			
			blueTurn = new JButton("Blue");
			blueTurn.addActionListener(this);
			bottomPanel.add(blueTurn);
			
			redTurn = new JButton("Red");
			redTurn.addActionListener(this);
			bottomPanel.add(redTurn);
			
			endStateBtn = new JButton("End State");
			endStateBtn.addActionListener(this);
			bottomPanel.add(endStateBtn);
			
			storeGameBtn = new JButton("Save");
			storeGameBtn.addActionListener(this);
			bottomPanel.add(storeGameBtn);
			
			loadGameBtn = new JButton("Load");
			loadGameBtn.addActionListener(this);
			bottomPanel.add(loadGameBtn);

			menuBtn =  new JButton("Menu");
			menuBtn.addActionListener(this);
			bottomPanel.add(menuBtn);
			
			//make an empty board;
			brd = new BoardArray(ROW,COLUMN);
			
			//add the components to the frame
			add(bottomPanel, BorderLayout.SOUTH);
			add(leftPanel,BorderLayout.WEST);
			add(topPanel, BorderLayout.NORTH);
			
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
        g.drawRect(145, 65, 100 * COLUMN + 30, 95 * ROW + 30);
        
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
                        g.fillOval(165+100*col, 70+100*row, 90, 90);
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
	
