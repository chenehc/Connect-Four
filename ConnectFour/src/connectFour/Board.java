package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

class Board extends JFrame implements ActionListener{
	private static final int COLUMN = 7;
	private static final int ROW = 6;
	private JButton col1, col2, col3, col4, col5, col6, col7;
	private JButton newGameBtn, endStateBtn;
	private JPanel topPanel, bottomPanel, leftPanel;
	private JButton blueTurn, redTurn;
	private String space = "                ";
	
	public static boolean endGame = false;
	public BoardArray brd;
	
	//Instantiates the Board object
	public Board(){
		setTitle ("Connect Four");
		buildBoardGUI();
	}
	
	//builds the board components, being panel, buttons
	private void buildBoardGUI(){
		JOptionPane.showMessageDialog(this, "Hover mouse over the top and bottom of the GUI for the buttons to show, we can't fix it" );
		
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		leftPanel = new JPanel();
	
		setSize(1024, 700);
		
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
		
		blueTurn = new JButton("Blue");
		blueTurn.addActionListener(this);
		bottomPanel.add(blueTurn);
		
		redTurn = new JButton("Red");
		redTurn.addActionListener(this);
		bottomPanel.add(redTurn);
		
		endStateBtn = new JButton("End State");
		endStateBtn.addActionListener(this);
		bottomPanel.add(endStateBtn);
		
		brd = new BoardArray(ROW,COLUMN);
		
		add(bottomPanel, BorderLayout.SOUTH);
		add(leftPanel,BorderLayout.WEST);
		add(topPanel, BorderLayout.NORTH);
		
	}
	
	public void paint(Graphics g){
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
		}else if(e.getSource() == newGameBtn){
			brd.newGame();
			Game.start(this);
			repaint();
		}else if (e.getSource() == redTurn){
			Game.currentPlayer = Piece.RED;
			setTitle("Connect Four - Red's Turn - Game in Progress");
		}else if (e.getSource() == blueTurn){
			Game.currentPlayer = Piece.BLUE;
			setTitle("Connect Four - Blue's Turn - Game in Progress");
		}else if (e.getSource() == endStateBtn){
			endGame = true;
			checkWin.check(brd, this);
			displayMsg.displayEndState(this);
			setTitle("Connect Four - Game Ended");
		}
	}
}
	