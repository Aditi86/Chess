package Chess;
/**
 * This class helps to check the move of Knight if it is valid or not.
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev */
public class Knight extends Pieces
{
    /** Constructor of this class will call superclass constructor to initialize color of Knight
     * @param color    color of Knight, it can be either white or black*/
    public Knight(String color) {
        super(color);
    }

    /** Overrides the getPieces method from super class
     *  @return String name of Knight. if the color of Knight is white
     *  then it will return wN, if the color is black it will return bN.*/
    public String getPieces() {
        return color.equalsIgnoreCase("White") ? "wN " : "bN ";
    }

    /** isValid boolean method is overridden from the super class.
     *  It checks if the given move from user is valid or not.
     *  @param curr1             index of current Knight's column.
     *  @param curr2             index of current Knight's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    if the move is valid then return true, if the move is invalid then returns false.*/
    @Override
    public boolean isValid(int curr1, int curr2, int dest1, int dest2)
    {
        int diff1 = Math.abs(curr1 - dest1);
        int diff2 = Math.abs(curr2 - dest2);

        if (curr1 < 0 || curr1 > 7 || curr2 < 0 || curr2 > 7 || dest1 < 0 || dest1 > 7 || dest2 < 0 || dest2 > 7)
        {
            return false;
        }

        else if (diff1 == 1 && diff2 == 2 || diff1 == 2 && diff2 == 1)
        {
            if (Board.pieces[dest1][dest2] != null && !Board.pieces[dest1][dest2].color.equals(color))
            {
                return true;
            }
            else if (Board.pieces[dest1][dest2] == null)
            {
                return true;
            }

        }

        return false;

    }
}
