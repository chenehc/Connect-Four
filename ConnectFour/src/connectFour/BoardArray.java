package connectFour;

public class BoardArray {
	private Piece[][] boardArray;
	private int ROW,COLUMN;
	
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
	
	//returns the piece at the given index
	public Piece getPiece(int row, int col){
		return this.boardArray[row][col];
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
	public void addPiece(int col){
		int row;
		for (row=0; row<ROW; row++)
            if (!isEmpty(row, col)) break;
		if (row>0) {
           boardArray[--row][col] = Game.currentPlayer;
		}
		Game.moveCount++;
	}
	
	//removes the player piece in a column
	public void removePiece(int col){
		int row;
		for (row=0; row<ROW; row++)
            if (!isEmpty(row, col)) break;
		if (row>0) {
           boardArray[row][col] = Piece.EMPTY;
		}
		Game.illegalMove = false;
		Game.moveCount--;
	}
	
	//resets the board, set all pieces to EMPTY
	public void newGame(){
		for (int row=0; row<ROW; row++)
            for (int col=0; col<COLUMN; col++)
                    boardArray[row][col]= Piece.EMPTY;
	}
}
