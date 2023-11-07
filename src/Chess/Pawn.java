package Chess;
/**
 * This class helps to create and check the move of Pawn if it is valid or not.
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev */
public class Pawn extends Pieces
{
    /** Constructor of this class will call superclass constructor to initialize color of Pawn
     * @param color    color of Pawn, it can be either white or black*/
    public Pawn(String color)
    {
        super(color);
    }

    /** Overrides the getPieces method from super class
     *  @return String name of Pawn. if the color of the Pawn is white
     *  then it will return wP, if the color is black it will return bP.*/
    public String getPieces()
    {
        return color.equalsIgnoreCase("White")? "wP ": "bP ";
    }

//  ****************************************************** START OF ISVALID METHOD ********************************************************************************

    /** isValid boolean method is overridden from the super class.
     *  It checks if the given move from user is valid or not.
     *  @param curr1             index of current Pawn's column.
     *  @param curr2             index of current Pawn's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    if the move is valid then return true, if the move is invalid then returns false.*/

    @Override
    public boolean isValid(int curr1, int curr2, int dest1, int dest2)
    {
// ********************************************************** IF THE PAWN IS WHITE ******************************************************************
        if(color.equals("White"))
        {

            //--------------------------------------------------------- PAWN FIRST MOVE --------------------------------------------

            if(curr1 == 6 && dest2 == curr2 && curr1-2 == dest1 && curr1>dest1|| curr1 == 6 && dest2 == curr2 && curr1-1 == dest1 && curr1>dest1)
            {
                if(Board.pieces[dest1][dest2] == null)
                {
                    return true;
                }
            }

            //---------------------------------------------------------- PAWN SINGLE MOVE -----------------------------------------
            else if(dest2 == curr2 && curr1-1 == dest1 && curr1 > dest1)
            {
                if(Board.pieces[dest1][dest2] == null&&dest1 == 0)
                {
                    return promotion(curr1, curr2, dest1, dest2)? true : false;
                }
                else if(Board.pieces[dest1][dest2] == null)
                {
                    return true;
                }
            }
            //------------------------------------------------------------ CAPTURING THE PAWN ---------------------------------------

            else if(curr2!=0 &&curr2-1 == dest2 && curr1-1 == dest1 || curr2!=7 && curr2+1 == dest2 && curr1-1 == dest1)
            {
                if(isPassant(curr1, curr2, dest1, dest2))
                {
                    return true;
                }
                else if(Board.pieces[dest1][dest2]!= null && (Board.pieces[dest1][dest2].color).equals("Black"))
                {
                    if(dest1 == 0 && curr1-1 == dest1 && !Board.pieces[dest1][dest2].getPieces().equals("bK ") )
                    {
                        return promotion(curr1, curr2, dest1, dest2)? true : false;
                    }
                    else
                    {
                        return true;
                    }
                }

            }


        }
// ********************************************************* IF THE PAWN IS BLACK ***********************************************************************


        else if(color.equals("Black"))
        {
            //--------------------------------------------------------- PAWN FIRST MOVE ------------------------------------------------

            if(curr1 == 1 && dest2 == curr2 && curr1+2 == dest1 || curr1 == 1 && dest2 == curr2 && curr1+1 == dest1)
            {
                if(Board.pieces[dest1][dest2] == null)
                {
                    return true;
                }
            }
            //-------------------------------------------------------- PAWN SINGLE MOVE ------------------------------------------------
            else if(dest2 == curr2 && curr1+1 == dest1 && curr1 < dest1)
            {
                if(Board.pieces[dest1][dest2] == null&&dest1 == 7)
                {
                    return promotion(curr1, curr2, dest1, dest2)? true : false;
                }

                else if(Board.pieces[dest1][dest2] == null)
                {
                    return true;
                }
            }
            //-------------------------------------------------------- CAPTURING/EN PASSENT THE PAWN ------------------------------------

            else if(curr2 != 0 &&curr2-1 == dest2 && curr1+1 == dest1 || curr2 != 7 && curr2+1 == dest2 && curr1+1 == dest1)
            {
                if(isPassant(curr1, curr2, dest1, dest2))
                {
                    return true;
                }
                else if(Board.pieces[dest1][dest2]!= null && (Board.pieces[dest1][dest2].color).equals("White"))
                {
                    if(dest1 == 7 && curr1+1 == dest1 && !Board.pieces[dest1][dest2].getPieces().equals("wK "))
                    {
                        return promotion(curr1, curr2, dest1, dest2)? true : false;
                    }
                    else
                    {
                        return true;
                    }

                }

            }

        }

        return false;

    }

//   ****************************************************** END OF ISVALID METHOD ********************************************************************************


    //------------------------------------------------------------------ EN PASSENT ---------------------------------------------------------------------------------------
    /** isPassent boolean method checks if the given move from user is
     *  valid for passant  or not. If it is passant then it will make the move and return true.
     *  @param curr1             index of current Pawn's column.
     *  @param curr2             index of current Pawn's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    If it is passant then it will make the move and return true, if not then returns false.*/
    public boolean isPassant(int curr1, int curr2, int dest1, int dest2)
    {

        if(!Chess.moves.isEmpty())
        {
            int arraylistSize = Chess.moves.size();
            String lastMove = Chess.moves.get(arraylistSize-1);
            int prv1 =  8-(lastMove.charAt(4)-48);
            int prv2 =  lastMove.charAt(3)-97;

            if(color.equals("White"))
            {
                if(curr2-1 == prv2 && curr1 == prv1 && (Board.pieces[prv1][prv2].getPieces()).equals("bP ")
                        || curr2+1 == prv2 && curr1 == prv1 && (Board.pieces[prv1][prv2].getPieces()).equals("bP "))
                {
                    Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
                    Board.pieces[curr1][curr2] = null;
                    Board.pieces[prv1][prv2] = null;
                    Chess.moves.add(Chess.move);
                    return true;
                }
            }

            if(color.equals("Black"))
            {
                if(curr2 != 0 &&curr2-1 == prv2 && curr1 == prv1 && (Board.pieces[prv1][prv2].getPieces()).equals("wP ")
                        || curr2 != 7 && curr2+1 == prv2 && curr1 == prv1 && (Board.pieces[prv1][prv2].getPieces()).equals("wP "))
                {
                    Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
                    Board.pieces[curr1][curr2] = null;
                    Board.pieces[prv1][prv2] = null;
                    Chess.moves.add(Chess.move);
                    return true;
                }
            }
        }
        return false;
    }


//------------------------------------------------------------------ PROMOTION -----------------------------------------------------------------------------------------------
    /** promotion boolean method checks if the given move for promotion is valid then it will make the move.
     *  It will check if the user requested piece is valid to get promotion in form of other pieces or not.
     *  @param curr1             index of current Pawn's column.
     *  @param curr2             index of current Pawn's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    if promotion is valid then it will return true, else it will return false.*/
    public boolean promotion(int curr1, int curr2, int dest1, int dest2)
    {
        int count = 0;

        if(Chess.transform != null && Chess.transform.equalsIgnoreCase("N"))
        {
            Pieces obj = new Knight(color);
            return checkPieces(obj, curr1, curr2, dest1, dest2) ? true : false;

        }
        else if(Chess.transform != null && Chess.transform.equalsIgnoreCase("R"))
        {
            Pieces obj = new Rook(color);
            return checkPieces(obj, curr1, curr2, dest1, dest2) ? true : false;

        }
        else if(Chess.transform == null||  Chess.transform.equalsIgnoreCase("Q"))
        {
            Pieces obj = new Queen(color);
            for(int a = 0; a < Board.pieces.length; a++)
            {
                for(int b = 0; b < Board.pieces.length; b++)
                {
                    if(Board.pieces[a][b]!= null && Board.pieces[a][b].getPieces().equals(obj.getPieces()))
                    {
                        count++;
                    }
                }
            }

            if(count <= 8)
            {
                Board.pieces[dest1][dest2] = obj;
                Board.pieces[curr1][curr2] = null;
                Chess.moves.add(Chess.move);
                return true;
            }
            count = 0;
        }
        else if(Chess.transform != null && Chess.transform.equalsIgnoreCase("B"))
        {
            Pieces obj = new Bishop(color);
            return checkPieces(obj, curr1, curr2, dest1, dest2) ? true : false;
        }
        return false;
    }


    /** checkPieces boolean method checks if requested transform into piece is already on board or not.
     *  For example: pawn promotes and request it to be a Bishop, it will check if 2 Bishops are available
     *  on the Board or not, if one or none Bishops  are available then pawn will transform into Bishop.
     *  @param obj               object of piece to check if similar object is available or not.
     *  @param curr1             index of current Pawn's column.
     *  @param curr2             index of current Pawn's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    if transform into piece is not available then returns true, else returns false*/

    public boolean checkPieces(Pieces obj, int curr1, int curr2, int dest1, int dest2)
    {
        int count = 0;
        for(int a = 0; a < Board.pieces.length; a++)
        {
            for(int b = 0; b < Board.pieces.length; b++)
            {
                if(Board.pieces[a][b]!= null && Board.pieces[a][b].getPieces().equals(obj.getPieces()) )
                {
                    count++;
                }
            }
        }

        if(count <= 9)
        {
            Board.pieces[dest1][dest2] = obj;
            Board.pieces[curr1][curr2] = null;
            Chess.moves.add(Chess.move);
            return true;
        }
        return false;
    }

}
