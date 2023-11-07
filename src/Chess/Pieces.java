package Chess;

/**
 * This abstract class will be used to provide common implementation to it's all subclasses.
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev*/

public abstract class Pieces
{
    /** String color will determine the color of a pieces, whether it's black or white*/
    String color;

    /** Constructor of class to initialize color of a chess piece.
     *  @param color      for color of the pieces*/
    public Pieces(String color)
    {
        this.color = color;
    }

    /** abstract string method which returns name of the pieces such as wp , bp
     *  @return String value of piece name. */
    public abstract String getPieces();

    /** abstract boolean isValid method which checks if the user move is valid or not.
     *  if it is valid then it will return true, if the move is not valid then it will return false.
     *  @param curr1             index of current piece's column.
     *  @param curr2             index of current piece's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value */
    public abstract boolean isValid(int curr1, int curr2, int dest1, int dest2);

}
