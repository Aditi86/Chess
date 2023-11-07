package Chess;
/**
 * This class helps to create and check the move of king, if it  is valid or not.
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev */

public class King extends Pieces
{
    /** Constructor of this class will call superclass constructor to initialize color of King
     * @param color    color of king, it can be either white or black*/
    public King(String color)
    {
        super(color);
    }

    /** Overrides the getPieces method from super class
     *  @return String name of King. if the color of King is white
     *  then it will return wK, if the color is black it will return bK.*/
    @Override
    public String getPieces()
    {
        return color.equalsIgnoreCase("White")? "wK ": "bK ";
    }

//************************************************************ ISVALID METHOD START ************************************************************************
    /** isValid boolean method is overridden from the super class.
     *  It checks if the given move from user is valid or not.
     *  @param curr1             index of current King's column.
     *  @param curr2             index of current King's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     *  @return boolean value    if the move is valid then return true, if the move is invalid then returns false.*/
    @Override
    public boolean isValid(int curr1, int curr2, int dest1, int dest2)
    {
        if(color.equals("White"))
        {
//--------------------------------------------------------- NORTMAL MOVE OF WHITE KING ------------------------------------------------

            if(curr2 != 7 && curr2+1 == dest2 || curr2 == dest2 || curr2 != 0 && curr2-1 == dest2)
            {
                if(curr1 != 7 && curr1+1 == dest1 || curr1 == dest1 || curr1 != 0 && curr1-1 == dest1)
                {
                    if(Board.pieces[dest1][dest2] != null &&!Board.pieces[dest1][dest2].color.equals("White"))
                    {
                        Pieces dest = Board.pieces[dest1][dest2];
                        Pieces curr = Board.pieces[curr1][curr2];
                        Board.pieces[dest1][dest2] =  Board.pieces[curr1][curr2];
                        if(!isKingUnderCheck(dest1, dest2))
                        {
                            Board.pieces[dest1][dest2] = dest;
                            Board.pieces[curr1][curr2] = curr;
                            return true;
                        }
                        Board.pieces[dest1][dest2] = dest;
                        Board.pieces[curr1][curr2] = curr;
                    }
                    else if(Board.pieces[dest1][dest2] == null)
                    {
                        Pieces curr = Board.pieces[curr1][curr2];
                        Board.pieces[dest1][dest2] =  Board.pieces[curr1][curr2];
                        if(!isKingUnderCheck(dest1, dest2))
                        {
                            Board.pieces[curr1][curr2] = curr;
                            return true;
                        }
                        Board.pieces[curr1][curr2] = curr;
                    }
                }
            }
//-------------------------------------------------------- CASTLING ------------------------------------------------------------------
            else if(curr2 == 4 && curr1==7 && curr2+2 == dest2 || curr2 == 4 && curr1==7 && curr2-2 == dest2)
            {

                String check;
                for(int i = 0; i < Chess.moves.size(); i++)
                {
                    check = ""+Chess.moves.get(i).charAt(0) + Chess.moves.get(i).charAt(1);

                    if(check.equals("e1") ||check.equals("a1") && dest2 < 4 || check.equals("h1") && dest2 > 4 || isKingUnderCheck(curr1, curr2))
                    {
                        return false;
                    }
                }

                if(dest2 < 4 && Board.pieces[7][3] == null && Board.pieces[7][2] == null && Board.pieces[7][1] == null && Board.pieces[7][0]!= null)
                {
                    Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
                    Board.pieces[dest1][dest2+1] = Board.pieces[7][0];
                    Board.pieces[7][0] = null;
                    Board.pieces[curr1][curr2] = null;
                    Chess.moves.add(Chess.move);

                    return true;

                }
                else if(dest2 > 4 && Board.pieces[7][5] == null && Board.pieces[7][6] == null&& Board.pieces[7][7]!= null)
                {
                    Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
                    Board.pieces[dest1][dest2-1] = Board.pieces[7][7];
                    Board.pieces[7][7] = null;
                    Board.pieces[curr1][curr2] = null;
                    Chess.moves.add(Chess.move);

                    return true;

                }
            }



        }

        else if(color.equals("Black"))
        {
//--------------------------------------------------------- NORTMAL MOVE OF BLACK KING ------------------------------------------------

            if(curr2 != 7 && curr2+1 == dest2 || curr2 == dest2 || curr2 != 0 && curr2-1 == dest2)
            {
                if(curr1 != 7 && curr1+1 == dest1 || curr1 == dest1 || curr1 != 0 && curr1-1 == dest1)
                {
                    if(Board.pieces[dest1][dest2] != null &&!Board.pieces[dest1][dest2].color.equals("Black"))
                    {
                        Pieces dest = Board.pieces[dest1][dest2];
                        Pieces curr = Board.pieces[curr1][curr2];
                        Board.pieces[dest1][dest2] =  Board.pieces[curr1][curr2];
                        if(!isKingUnderCheck(dest1, dest2))
                        {
                            Board.pieces[dest1][dest2] = dest;
                            Board.pieces[curr1][curr2] = curr;
                            return true;
                        }
                        Board.pieces[dest1][dest2] = dest;
                        Board.pieces[curr1][curr2] = curr;
                    }
                    else if(Board.pieces[dest1][dest2] == null)
                    {
                        Pieces curr = Board.pieces[curr1][curr2];
                        Board.pieces[dest1][dest2] =  Board.pieces[curr1][curr2];
                        if(!isKingUnderCheck(dest1, dest2))
                        {
                            Board.pieces[curr1][curr2] = curr;
                            return true;
                        }
                        Board.pieces[curr1][curr2] = curr;
                    }
                }
            }
//-------------------------------------------------------- CASTLING ------------------------------------------------------------------
            else if(curr1==0 && curr2+2 == dest2 ||curr1==0 && curr2-2 == dest2)
            {
                String check;
                for(int i = 0; i < Chess.moves.size(); i++)
                {
                    check = ""+Chess.moves.get(i).charAt(0) + Chess.moves.get(i).charAt(1);

                    if(check.equals("e8") ||check.equals("a8") && dest2 < 4 || check.equals("h8") && dest2 > 4 || isKingUnderCheck(curr1, curr2))
                    {
                        return false;
                    }
                }

                if(dest2 < 4 && Board.pieces[0][3] == null && Board.pieces[0][2] == null && Board.pieces[0][1] == null && Board.pieces[0][0]!= null)
                {
                    Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
                    Board.pieces[dest1][dest2+1] = Board.pieces[0][0];
                    Board.pieces[0][0] = null;
                    Board.pieces[curr1][curr2] = null;
                    Chess.moves.add(Chess.move);

                    return true;

                }
                else if(dest2 > 4 && Board.pieces[0][5] == null && Board.pieces[0][6] == null&& Board.pieces[0][7]!= null)
                {
                    Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
                    Board.pieces[dest1][dest2-1] = Board.pieces[0][7];
                    Board.pieces[0][7] = null;
                    Board.pieces[curr1][curr2] = null;
                    Chess.moves.add(Chess.move);

                    return true;

                }
            }



        }

        return false;
    }
//************************************************************ ISVALID METHOD END ************************************************************************



//************************************************************ CHECK IF THE KIND IS UNDER CHECK ************************************************************

    /** This boolean method checks if the king is under a check or not.
     *  It will take the current king's position as index and will check
     *  if the opponent piece is threatening to king or not.
     *  it will return false if the piece is not threatening.
     *
     *  @param dest1             index of current King's column.
     *  @param dest2             index of current King's row.
     *  @return boolean value    true/false */
    public boolean isKingUnderCheck(int dest1, int dest2)
    {
        Pieces [][] newPieces = new Pieces [8 ][8];

        for(int i = 0; i < Board.pieces.length; i++)
        {
            for(int j = 0; j < Board.pieces.length; j++)
            {
                if(Board.pieces[i][j] != null)
                {
                    newPieces [i][j] = Board.pieces[i][j];
                }
            }
        }

        for(int a = 0; a < 8; a++)
        {
            for(int b = 0; b < 8; b++)
            {
                if(Board.pieces[a][b]!= null && !Board.pieces[a][b].color.equals(color))
                {
                    if(Board.pieces[a][b].isValid(a, b, dest1, dest2))
                    {
                        Board.pieces = newPieces;
                        return true;
                    }
                }
            }
        }
        Board.pieces = newPieces;
        return false;

    }
}
