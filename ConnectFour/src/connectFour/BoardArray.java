package connectFour;

/********************************************************************************************
 * this class creates a board object which holds the current board placements and allows
 * manipulation of the board. 
 *******************************************************************************************/
public class BoardArray {
	private Piece[][] boardArray;
	private int ROW,COLUMN;
	private static int[][] valueTable = {{3, 4, 5, 7, 5, 4, 3}, 
			  							{4, 6, 8, 10, 8, 6, 4},
			  							{5, 8, 11, 13, 11, 8, 5}, 
			  							{5, 8, 11, 13, 11, 8, 5},
			  							{4, 6, 8, 10, 8, 6, 4},
			  							{3, 4, 5, 7, 5, 4, 3}};
	
	/**
	 * This method evaluates the value of the move based on the valueTable above
	 * @return the calculated value
	 */
	public int evaluate() {
		int base = 128;
		int sum = 0;
		for (int row = 0; row < ROW; row++)
			for (int col = 0; col <COLUMN; col++)
				if (boardArray[row][col] == Piece.BLUE)
					sum += valueTable[row][col];
				else if (boardArray[row][col] == Piece.RED)
					sum -= valueTable[row][col];
		return base + sum;
	}
	
	//constructor
	public BoardArray(int ROW, int COLUMN){
		this.ROW = ROW;
		this.COLUMN = COLUMN;
		this.boardArray = new Piece[ROW][COLUMN];
		for (int row=0; row<ROW; row++)
            for (int col=0; col<COLUMN; col++)
                    boardArray[row][col]= Piece.EMPTY;
	}
	
	/**
	 * check whether the element at the slot is empty
	 * @param row  	- The row of the piece that needs checking
	 * @param col	- The column of the piece that needs checking
	 * @return true if empty, else false
	 */
	public boolean isEmpty(int row, int col){
		return (this.boardArray[row][col] == Piece.EMPTY);
	}
	
	/**
	 * check whether the element at the slot is red
	 * @param row  	- The row of the piece that needs checking
	 * @param col	- The column of the piece that needs checking
	 * @return true if empty, else false
	 */
	public boolean isRed(int row, int col){
		return (this.boardArray[row][col] == Piece.RED);
	}
	
	/**
	 * check whether the element at the slot is blue
	 * @param row  	- The row of the piece that needs checking
	 * @param col	- The column of the piece that needs checking
	 * @return true if empty, else false
	 */
	public boolean isBlue(int row, int col){
		return (this.boardArray[row][col] == Piece.BLUE);
	}
	
	/**
	 * @return number of rows on the board
	 */
	public int getRow(){
		return this.ROW;
	}
	
	/**
	 * @return number of columns on the board
	 */
	public int getCol(){
		return this.COLUMN;
	}
	
	//clone this object
	public void clone(BoardArray from){
		for (int row =0; row<ROW; row++){
			for (int col =0; col<COLUMN; col++){
				setPiece(row, col, from.getPiece(row, col));
			}
		}
	}
	
	/**
	 * This method finds the piece located at [row][column] 
	 * and returns it
	 * @param row - The row in which the piece is located
	 * @param col - The column in which the piece is located
	 * @return the Piece at the index
	 */
	public Piece getPiece(int row, int col){
		return this.boardArray[row][col];
	}
	
	/**
	 * Set a Piece on a specific index
	 * @param row - The row that the piece will be placed in
	 * @param col - The column that the piece will be placed in
	 * @param p	  - The Piece that will be placed
	 */
	public void setPiece(int row, int col, Piece p){
		this.boardArray[row][col] = p;
	}
	
	/**
	 * This method counts the number of red pieces on the board
	 * @return the number of red pieces
	 */
	public int countRed(){
		int count = 0;
		for (int row=0; row<this.ROW; row++)
      for (int col=0; col<this.COLUMN; col++)
        if (boardArray[row][col] == Piece.RED) count++;
    return count;
	}
	
	/**
	 * This method counts the number of blue pieces on the board
	 * @return the number of blue pieces
	 */
	public int countBlue(){
		int count = 0;
		for (int row=0; row<this.ROW; row++)
      for (int col=0; col<this.COLUMN; col++)
      	if (boardArray[row][col] == Piece.BLUE) count++;
		return count;
	}
	
	/**
	 * This method adds a specific piece to the board at a specific column
	 * @param col - The column that the piece will be inserted in 
	 * @param p   - The piece that will be inserted
	 */
	public void addPiece(int col, Piece p){
		int row;
		//counts to the row from which a piece can be placed, row = 0 being the top row
		for (row=0; row<ROW; row++)
            if (!isEmpty(row, col)) break;
		if (row>0){
			boardArray[--row][col] = p;
		}else{
			Game.illegalMove = true;
		}
	}
	
	/**
	 * This method removes the most upper piece that is not EMPTY
	 * in a column
	 * @param col - Column that the piece will be removed from
	 */
	public void removePiece(int col){
		for (int row=0; row<ROW; row++)
			if (!isEmpty(row, col)){
				boardArray[row][col] = Piece.EMPTY;
				break;
			}

		Game.illegalMove = false;
	}

	/**
	 * This method checks if the game is a tie
	 * @return true if tie, else false
	 */
	public boolean isTie(){
		for (int j = 0; j < COLUMN; j++)
			if (boardArray[0][j] == Piece.EMPTY)
				return false;
		return true;
	}
	
	/**
	 * This method resets the board, setting all pieces to EMPTY
	 */
	public void newGame(){
		for (int row=0; row<ROW; row++)
            for (int col=0; col<COLUMN; col++)
                    boardArray[row][col]= Piece.EMPTY;
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
		
	public static void main(String args[]){
		BoardArray a = new BoardArray(6, 7);
		a.setPiece( 1, 2, Piece.BLUE);
		a.setPiece(2,3,Piece.RED);
		BoardArray b = new BoardArray(6, 7);
//		b = a.clone();
		printBoard(a);
		System.out.println();
		printBoard(b);
	}
}
