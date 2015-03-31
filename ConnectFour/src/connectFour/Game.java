package connectFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Game {
	public static Piece currentPlayer, nextPlayer;
	public static Piece firstPlayer;
	public static boolean illegalMove = false;
	public static int moveCount = 0;
	public static boolean isTie = false;
	private static int best_Column;
	
	//adds the AI disk to the board
	public static void addDiskAI(Board c, BoardArray brd){
		//essentially same as addDisk, except it adds the best_column
		if (!legalMove(best_Column, brd)) displayIllegalMove(c);
		else{
			brd.addPiece(best_Column, Piece.RED);
		}
		currentPlayer = Piece.BLUE;
		if (checkWin.checkMove(brd) != Piece.EMPTY){
			checkWin.displayWinner(checkWin.checkMove(brd), c);
		}
		c.repaint();
	}
	
	//add player piece to the board
	public static void addDisk(int col, Board c, BoardArray brd){
		//no move if game ended
		if (Board.endGame) return;
		//if no player is chosen, display error
		if (currentPlayer != Piece.BLUE && currentPlayer != Piece.RED) displayStartError(c);

		//add the current player piece to the board if the move is legal
		if (!legalMove(col-1, brd)) displayIllegalMove(c);
		else { 
			brd.addPiece(col-1, currentPlayer);  
			if (checkWin.checkMove(brd) != Piece.EMPTY){
				checkWin.displayWinner(checkWin.checkMove(brd), c);
			}
			if (c.getMode().equals("AI") && currentPlayer == Piece.BLUE){
//        		addDiskAI(makeAIMove(brd, c)-1, c, brd);
				makeAIMove(brd, c);
				addDiskAI(c, brd);
        		return;
			}
		}
		if (c.getMode()!="AI"){
			//in turn player changing
			if (currentPlayer == Piece.BLUE){
				currentPlayer = Piece.RED;
				//TODO new update method for title change
				if (!Board.endGame) c.changeTitle("Connect Four - Red's Turn - Game in Progress");
			}else{
				currentPlayer = Piece.BLUE;
				if (!Board.endGame) c.changeTitle("Connect Four - Blue's Turn - Game in Progress");


			}
		}
        c.repaint();
	}
	
	private static boolean legalMove(int col, BoardArray brd){
		if (brd.getPiece(0, col) != Piece.EMPTY) return false;
		return true;
	}
	//make move for AI
	//AI is always RED and the player is always BLUE
	private static int makeAIMove(BoardArray brd, Board b){
		Value(brd, 0, Piece.BLUE);
//		System.out.println(best_Column);
		return best_Column;
	}
	
	private static int Value(BoardArray brd, int depth, Piece player){
		//if the game has not ended, check which player is winning
		if (checkWin.checkMove(brd) != Piece.EMPTY ){
			if (checkWin.checkMove(brd) == Piece.BLUE)
				//max player is winning
				return 255 - depth;
			//min player is winning
			else return 0 + depth; 
		}
		//depth of search will be 4, so evaluate value of move when depth = 4
		if (depth == 4 || isTie){
			return brd.evaluate();
		}
		++depth;
		//find the best valued move and set the best_Column to the column with highest value
		if (player == Piece.BLUE){
			int max = Integer.MIN_VALUE;
			int column = 0;
			for (int i = 0; i < brd.getCol(); i++){ 
				if (legalMove(i, brd)) {
					brd.addPiece(i, Piece.BLUE);
					int value = Value(brd, depth, Piece.RED);
					if (max < value) {
						max = value;
						column = i;
					}
					brd.removePiece(i);
//					printBoard(brd);
				}
			}
//			System.out.println("best column" + best_Column);
			best_Column = column;
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < brd.getCol(); i++) {
				if (legalMove(i, brd)) {
					brd.addPiece(i, Piece.RED);
					int value = Value(brd, depth, Piece.BLUE);
					if (min > value)
						min = value;
					brd.removePiece(i);
				}
			}
			return min;
		}
	}
	
	//determines who goes first
	public static void findFirst(Board b){
		Random rng = new Random();
		int randomInt = rng.nextInt(100);
		//if the number generated is 0<=x<50, blue is first, else red is first
		if (randomInt < 50){
			firstPlayer = Piece.BLUE;
			currentPlayer = Piece.BLUE;
			b.setTitle("Connect Four - Blue's Turn - Game in Progress");
		}else{
			firstPlayer = Piece.RED;
			currentPlayer = Piece.RED;
			b.setTitle("Connect Four - Red's Turn - Game in Progress");
		}
	}

	//displays the first player
	private static void displayFirst(Board b){
		if (b.getMode().equals("AI")){
			if (Game.firstPlayer == Piece.RED){
				JOptionPane.showMessageDialog(b, "AI goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(b, "You go first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			if (Game.currentPlayer == Piece.RED){
				JOptionPane.showMessageDialog(b, "Red goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(b, "Blue goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	//displays an error when inserting into a game which has not started yet
	private static void displayStartError(Board b){
		JOptionPane.showMessageDialog(b, "Press 'New Game' to start the game");
	}
	
	//starts a 2 player game
	public static void start(Board b){
		Board.endGame = false;
		moveCount = 0;
		findFirst(b);
		if (b.getMode().equals("AI") && firstPlayer == Piece.RED){
			best_Column = 3;
			addDiskAI(b, b.getBoardArray());
		}
		displayFirst(b);
	}
	
	//saves the game to a file, only one save state is allowed
	//save is permanent until overwritten with another "save"
	public static void save(BoardArray brd, Board b) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(new File("save/save.txt"));
		String temp = "";
		//first line stores current player
		if (currentPlayer == Piece.RED) temp = "RED";
		else if (currentPlayer == Piece.BLUE) temp = "BLUE"; 
		out.println(temp);
		
		//second line stores movecount
		out.println(moveCount);
		
		//second line stores the game pieces
		for (int row = 0; row<brd.getRow(); row++){
			for (int col = 0; col<brd.getCol(); col++){
				
					switch (brd.getPiece(row, col)){
						case RED: out.print("RED,"); break;
						case BLUE: out.print("BLUE,"); break;
						case EMPTY: out.print("EMPTY,"); break;
					}
			}
		}
		JOptionPane.showMessageDialog(b, "Game saved");
		out.close();
	}
	
	//resumes the game from the permanent save
	public static void resume(BoardArray brd, Board b) throws FileNotFoundException{
		Scanner in = new Scanner(new File("save/save.txt"));
		in.useDelimiter(",");
		
		//reads the first player
		String pl = in.nextLine();
		int movCount = Integer.parseInt(in.nextLine());
		moveCount = movCount;
		
		int row = 0;
		int col = 0;
		
		//reads the second line for how the board pieces are setup
		while(in.hasNext()){
			String t = in.next();
//			System.out.println(t);
			if (t.equals("RED")) brd.setPiece(row, col, Piece.RED); 
			else if (t.equals("BLUE")) brd.setPiece(row, col, Piece.BLUE);
			else if (t.equals("EMPTY")) brd.setPiece(row, col, Piece.EMPTY);

			if (col == 6){
				row ++;
				col = 0;
				continue;
			}
			col++;
		}
		
		//sets current player
		if (pl.equals("RED")) currentPlayer = Piece.RED;
		else if (pl.equals("BLUE")) currentPlayer = Piece.BLUE; 
		
		JOptionPane.showMessageDialog(b, "Game resumed.");
		in.close();
	}
	
	//displays an error when making illegal moves
	private static void displayIllegalMove(Board b){
		JOptionPane.showMessageDialog(b, "Illegal Move, try again");
	}
	
	//debug method, prints the BoardArray in console
	private static void printBoard(BoardArray brd){
		for (int row = 0; row<brd.getRow(); row++){
			for (int col = 0; col<brd.getCol(); col++){
				System.out.print(brd.getPiece(row,col) + "\t");
			}
			System.out.println();
		}
	}
	
	//unit testing
	public static void main(String args[]){
		BoardArray a= new BoardArray(6,7);
		Value(a, 0, Piece.BLUE);
	}
}
