package Chess;
/**
 * This class helps to create and check the move of queen is valid or not.
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev */

public class Queen extends Pieces {

    /** Constructor of this class will call superclass constructor to initialize color of King
     * @param color    color of king, it can be either white or black*/
    public Queen(String color)
    {
        super(color);
    }

    /** Overrides the getPieces method from super class
     *  @return String name of King. if the color of King is white
     *  then it will return wK, if the color is black it will return bK.*/
    @Override
    public String getPieces()
    {
        return color.equalsIgnoreCase("White")? "wQ ": "bQ ";
    }


    /** isValid boolean method is overridden from the super class.
     *  It checks if the given move from user is valid or not.
     *  @param curr1             index of current Queen's column.
     *  @param curr2             index of current Queen's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    if the move is valid then return true, if the move is invalid then returns false.*/
    @Override
    public boolean isValid(int curr1, int curr2, int dest1, int dest2) {
        int xChange = Math.abs(dest1 - curr1);
        int yChange = Math.abs(dest2 - curr2);
        int vert = curr1 - dest1;
        int horiz = curr2 - dest2;

        //bishop-like movement
        if(xChange == yChange) {
            if(vert > 0 && horiz > 0) {
                return path(curr1, curr2, dest1, dest2, 1);
            }
            else if(vert > 0 && horiz < 0) {
                return path(curr1, curr2, dest1, dest2, 2);
            }
            else if(vert < 0 && horiz > 0) {
                return path(curr1, curr2, dest1, dest2, 3);
            }
            else if(vert < 0 && horiz < 0) {
                return path(curr1, curr2, dest1, dest2, 4);
            }
        }
        //rook-like movement
        else if(xChange != 0 && yChange == 0) {
            if(vert > 0) {
                return path(curr1, curr2, dest1, dest2, 5);
            }
            else {
                return path(curr1, curr2, dest1, dest2, 6);
            }
        }
        else if(xChange == 0 && yChange != 0) {
            if(horiz < 0) {
                return path(curr1, curr2, dest1, dest2, 7);
            }
            else {
                return path(curr1, curr2, dest1, dest2, 8);
            }
        }
        return false;
    }

    /**
     * Checks if path to destination is clear depending on direction
     * @param curr1             index of current Rook's column.
     * @param curr2             index of current Rook's row.
     * @param dest1             index of user given destination of column.
     * @param dest2             index of user given destination of row.
     * @param direction 		direction in which the piece wants to move
     * @return True if move is possible
     */
    public boolean path(int curr1, int curr2, int dest1, int dest2, int direction) {
        if(direction == 1) {
            while(curr1 != (dest1+1) && curr2 != (dest2+1)) {
                curr1--;
                curr2--;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 2) {
            while(curr1 != (dest1 + 1) && curr2 != (dest2 - 1)) {
                curr1--;
                curr2++;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 3) {
            while(curr1 != (dest1 - 1) && curr2 != (dest2 + 1)) {
                curr1++;
                curr2--;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 4) {
            while(curr1 != (dest1 - 1) && curr2 != (dest2 - 1)) {
                curr1++;
                curr2++;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 5) {
            while(curr1 != (dest1 + 1)) {
                curr1--;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 6) {
            while(curr1 != (dest1-1)) {
                curr1++;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 7) {
            while(curr2 != (dest2 - 1)) {
                curr2++;
                if(Board.pieces[curr1][curr2] != null) {
                    return false;
                }
            }
        }
        else if(direction == 8) {
            while(curr2 != (dest2 + 1)) {
                curr2--;
                if(Board.pieces[curr1][curr2] != null){
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
}