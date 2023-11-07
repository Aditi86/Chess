package Chess;

/**
 * This class creates and displays the chess Board.
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev
 */

public class Board
{
    /** String 2D array of size 9 to store chess board. */
    static String[][] board = new String[9][9];
    /** Pieces 2d object array to store chess pieces as objects
     * 	We will use this array in back-end to get the location of the pieces in board.
     *  When user plays their move, we will change the place of the piece in this array
     *  to the destination given by user*/
    static Pieces[][] pieces = new Pieces[8][8];


    // -------------------------------------------NORMAL BOARD-----------------------------------------------
    /** public void board() method creates string chess board, and stores it into
     *  2D array of String named board.*/
    public void board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0 && j % 2 == 0 || i % 2 == 0 && j % 2 != 0) {
                    board[i][j] = "## ";
                } else {
                    board[i][j] = "   ";
                }

            }
            board[i][8] = "" + (8 - i);
            board[8][8] = "";

        }
        for (int k = 0; k < 8; k++) {
            board[8][k] = " " + ((char) (k + 97)) + " ";
        }

    }

    // --------------------------------------POPULATING THE BOARD ----------------------------
    /** public void populateBoard() will store chess pieces object in 2D array of pieces named pieces.
     *  This method will store pieces on a index, where each chess piece is located on the chess board.
     *  */
    public void populateBoard()
    {
        //initialize black piece board
        pieces[0][0] = new Rook("Black");
        pieces[0][1] = new Knight("Black");
        pieces[0][2] = new Bishop("Black");
        pieces[0][3] = new Queen("Black");
        pieces[0][4] = new King("Black");
        pieces[0][5] = new Bishop("Black");
        pieces[0][6] = new Knight("Black");
        pieces[0][7] = new Rook("Black");
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn("Black");
        }

        // initialize for white pieces
        pieces[7][0] = new Rook("White");
        pieces[7][1] = new Knight("White");
        pieces[7][2] = new Bishop("White");
        pieces[7][3] = new Queen("White");
        pieces[7][4] = new King("White");
        pieces[7][5] = new Bishop("White");
        pieces[7][6] = new Knight("White");
        pieces[7][7] = new Rook("White");
        for (int i = 0; i < 8; i++) {
            pieces[6][i] = new Pawn("White");
        }
    }

    // ------------------------------------------DISPLAY THE BOARD ---------------------------------------------

    /**
     * This method displays the board for user. In whichever place of 2D array pieces is null
     * this method will print 2D String array named Board. Whenever the 2D array pieces is not null
     * this method will call the String getPieces() method in each object stored in 2D pieces array
     * and store the name of that chess piece in a string board 2d array such as wp or bp.
     *
     * @param pieces      2D array of Pieces, will store chess pieces as object.
     *
     *  */
    public static void display(Pieces[][] pieces) {
        System.out.println("");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i <= 7 && j <= 7) {
                    if (pieces[i][j] != null) {
                        board[i][j] = pieces[i][j].getPieces();
                    }

                }
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
