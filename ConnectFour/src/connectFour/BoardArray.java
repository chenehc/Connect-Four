package connectFour;

public class BoardArray {
	private Piece[][] boardArray;
	private int ROW,COLUMN;
	private static int[][] valueTable = {{3, 4, 5, 7, 5, 4, 3}, 
			  							{4, 6, 8, 10, 8, 6, 4},
			  							{5, 8, 11, 13, 11, 8, 5}, 
			  							{5, 8, 11, 13, 11, 8, 5},
			  							{4, 6, 8, 10, 8, 6, 4},
			  							{3, 4, 5, 7, 5, 4, 3}};
	
	//evaluates the value of the move based on the valueTable above
	public int evaluate() {
		int base = 128;
		int sum = 0;
		for (int row = 0; row < ROW; row++)
			for (int col = 0; col <COLUMN; col++)
				if (boardArray[row][col] == Piece.BLUE)
					sum += valueTable[row][col];
				else if (boardArray[row][col] == Piece.RED)
					sum -= valueTable[row][col];
//		System.out.println("evaluation" +(base + sum));
		return base + sum;
	}
	
	//initialize the array with empty pieces
	public BoardArray(int ROW, int COLUMN){
		this.ROW = ROW;
		this.COLUMN = COLUMN;
		this.boardArray = new Piece[ROW][COLUMN];
		for (int row=0; row<ROW; row++)
            for (int col=0; col<COLUMN; col++)
                    boardArray[row][col]= Piece.EMPTY;
	}
	
	//check whether the element at the slot is empty
	public boolean isEmpty(int row, int col){
		return (this.boardArray[row][col] == Piece.EMPTY);
	}
	
	//checks whether the element at the index is empty
	public boolean isRed(int row, int col){
		return (this.boardArray[row][col] == Piece.RED);
	}
	
	//checks if the element at the given index is empty
	public boolean isBlue(int row, int col){
		return (this.boardArray[row][col] == Piece.BLUE);
	}
	
	public int getRow(){
		return this.ROW;
	}
	
	public int getCol(){
		return this.COLUMN;
	}
	
//	//clone this object
//	public BoardArray clone(){
//		BoardArray temp = new BoardArray(ROW, COLUMN);
//		for (int row =0; row<ROW; row++){
//			for (int col =0; col<COLUMN; col++){
//				temp.setPiece(row, col, getPiece(row, col));
//			}
//		}
//		return temp;
//	}
	
	//returns the piece at the given index
	public Piece getPiece(int row, int col){
		return this.boardArray[row][col];
	}

	public void setPiece(int row, int col, Piece p){
		this.boardArray[row][col] = p;
	}
	//counts the number of red pieces on the board
	public int countRed(){
		int count = 0;
		for (int row=0; row<this.ROW; row++)
      for (int col=0; col<this.COLUMN; col++)
        if (boardArray[row][col] == Piece.RED) count++;
    return count;
	}
	
	//counts the number of blue pieces on the board
	public int countBlue(){
		int count = 0;
		for (int row=0; row<this.ROW; row++)
      for (int col=0; col<this.COLUMN; col++)
      	if (boardArray[row][col] == Piece.BLUE) count++;
		return count;
	}
	
	//add the current player piece to the board
	public void addPiece(int col, Piece p){
		int row;
		//counts to the row from which a piece can be placed, row = 0 being the top row
		for (row=0; row<ROW; row++)
            if (!isEmpty(row, col)) break;
		if (row>0){
			boardArray[--row][col] = p;
			Game.moveCount++;
		}else{
			Game.illegalMove = true;
		}
	}
	
//	//removes the player piece in a column
//	public void removePiece(int col){
//		int row;
//		//similar to add, except in reverse
//		for (row=0; row<ROW; row++)
//            if (!isEmpty(row, col)) break;
//		if (row>0) {
//           boardArray[row][col] = Piece.EMPTY;
//		}
//		Game.illegalMove = false;
//		Game.moveCount--;
//	}
	
	//removes the player piece in a column
		public void removePiece(int col){
			for (int row=0; row<ROW; row++)
	            if (!isEmpty(row, col)){
	            	boardArray[row][col] = Piece.EMPTY;
	            	break;
	            }

			Game.illegalMove = false;
			Game.moveCount--;
		}
	
	public boolean isTie(){
		for (int j = 0; j < COLUMN; j++)
			if (boardArray[0][j] == Piece.EMPTY)
				return false;
	return true;
	}
	//resets the board, set all pieces to EMPTY
	public void newGame(){
		for (int row=0; row<ROW; row++)
            for (int col=0; col<COLUMN; col++)
                    boardArray[row][col]= Piece.EMPTY;
	}
	
}
