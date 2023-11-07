package Chess;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class containing main method will have user prompts for chess.
 *
 * @author Aditi Patel
 * @author Aakaash Prakash Hemdev */

public class Chess
{
    static int curr1, curr2, dest1, dest2, kingcurr1, kingcurr2;
    static String transform, move;
    static ArrayList<String> moves = new ArrayList<>();

    public static void main(String [] argus)
    {
        int size = 0, i = 0;
        String isDraw, color;
        boolean win = false, draw = false, resign = true;
        Scanner input = new Scanner(System.in);
        Board bd = new Board();

//------------------------------------------------------

        bd.board();
        bd.populateBoard();
        Board.display(Board.pieces);

        do
        {
            if(i%2 == 0)
            {
                color = "White";

                if(isCheckMate(color))
                {
                    System.out.println("Checkmate");
                    System.out.println("Black Wins");
                    break;
                }

                if(isKingUnderCheck(color))
                {
                    System.out.println("White King is under check.");
                }

                System.out.print("White's Move: ");
                move = input.nextLine().trim().toLowerCase();

                if(move.contains("resign"))
                {
                    System.out.println("Black Wins");
                    resign = true;
                    win = true;
                    draw = true;
                }
                else if(move.contains("draw?") || move.contains("draw ?"))
                {
                    System.out.print("Black's response: ");
                    isDraw = input.nextLine().trim().toLowerCase();
                    if(isDraw.contains("draw"))
                    {
                        resign = true;
                        win = true;
                        draw = true;
                    }
                }
                else
                {
                    getTheMove(move);
                    if(move.length() == 7)
                    {
                        transform = ""+move.charAt(6);
                    }
                    if(Board.pieces[curr1][curr2] != null)
                    {
                        if(Board.pieces[dest1][dest2] != null && Board.pieces[dest1][dest2].getPieces().equals("wK ")
                                || Board.pieces[dest1][dest2] != null && Board.pieces[dest1][dest2].getPieces().equals("bK ") )
                        {
                            System.out.println("Illegal move, try again");
                        }
                        else if(Board.pieces[curr1][curr2].color.equalsIgnoreCase("Black"))
                        {
                            System.out.println("Illegal move, try again");
                        }
                        else
                        {
                            size = moves.size();
                            if(Board.pieces[curr1][curr2].isValid(curr1, curr2, dest1, dest2))
                            {
                                if(size == moves.size())
                                {
                                    setMove(curr1, curr2, dest1, dest2);
                                    bd.board();
                                    Board.display(Board.pieces);
                                    transform = null;
                                }
                                else
                                {
                                    bd.board();
                                    Board.display(Board.pieces);
                                    transform = null;
                                }
                                i++;
                            }
                            else
                            {
                                System.out.println("Illegal move, try again");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Illegal move, try again");
                    }
                }

            }
            else if(i%2 != 0)
            {
                color = "Black";
                if(isCheckMate(color))
                {
                    System.out.println("Checkmate");
                    System.out.println("White Wins");
                    break;
                }

                if(isKingUnderCheck(color))
                {
                    System.out.println("Black King is under check.");
                }

                System.out.print("Black's Move: ");
                move = input.nextLine().trim().toLowerCase();

                if(move.contains("resign"))
                {
                    System.out.println("White Wins");
                    resign = true;
                    win = true;
                    draw = true;
                }
                else if(move.contains("draw?") || move.contains("draw ?"))
                {
                    System.out.print("White's response: ");
                    isDraw = input.nextLine().trim().toLowerCase();
                    if(isDraw.contains("draw"))
                    {
                        resign = true;
                        win = true;
                        draw = true;
                    }
                }
                else
                {
                    getTheMove(move);
                    if(move.length() == 7)
                    {
                        transform = ""+move.charAt(6);
                    }
                    if(Board.pieces[curr1][curr2] != null)
                    {
                        if(Board.pieces[dest1][dest2] != null && Board.pieces[dest1][dest2].getPieces().equals("wK ")
                                || Board.pieces[dest1][dest2] != null && Board.pieces[dest1][dest2].getPieces().equals("bK ") )
                        {
                            System.out.println("Illegal move, try again");
                        }
                        else if(Board.pieces[curr1][curr2].color.equalsIgnoreCase("White"))
                        {
                            System.out.println("Illegal move, try again");
                        }
                        else
                        {
                            size = moves.size();
                            if(Board.pieces[curr1][curr2].isValid(curr1, curr2, dest1, dest2))
                            {
                                if(size == moves.size())
                                {
                                    setMove(curr1, curr2, dest1, dest2);
                                    bd.board();
                                    Board.display(Board.pieces);
                                    transform = null;
                                }
                                else
                                {
                                    bd.board();
                                    Board.display(Board.pieces);
                                    transform = null;
                                }
                                i++;
                            }
                            else
                            {
                                System.out.println("Illegal move, try again");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Illegal move, try again");
                    }
                }
            }

        }while(win == false || draw == false || resign == false);
        input.close();
    }

//--------------------------------------------------------- GETS THE MOVE -----------------------------------------------------

    public static void getTheMove(String move)
    {
        curr1 = 8-(move.charAt(1)-48);
        curr2 = move.charAt(0)-97;
        dest1 = 8-(move.charAt(4)-48);
        dest2 =  move.charAt(3)-97;
    }
    //--------------------------------------------------------- SET THE MOVE -----------------------------------------------------

    /**
     * moves piece from current position to required destination
     *  @param curr1             index of current Rook's column.
     *  @param curr2             index of current Rook's row.
     *  @param dest1             index of user given destination of column.
     *  @param dest2             index of user given destination of row.
     */
    public static void setMove(int curr1, int curr2, int dest1, int dest2)
    {
        Board.pieces[dest1][dest2] = Board.pieces[curr1][curr2];
        Board.pieces[curr1][curr2] = null;
        moves.add(move);
    }
//-------------------------------------------------------- IS KING UNDER CHECK --------------------------------------------------
    /** This boolean method checks if the king is under a check or not.
     *  It will take the current king's position as index and will check
     *  if the opponent piece is threatening to king or not.
     *  it will return false if the piece is not threatening.
     *
     *  @param color             color of current player's king
     *  @return boolean value    true/false */
    public static boolean isKingUnderCheck(String color)
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

        getkingCurrPlace(color);

        for(int a = 0; a < 8; a++)
        {
            for(int b = 0; b < 8; b++)
            {
                if(Board.pieces[a][b]!= null && !Board.pieces[a][b].color.equalsIgnoreCase(color))
                {
                    if(Board.pieces[a][b]!= null && !Board.pieces[a][b].getPieces().contains("K")&&
                            Board.pieces[a][b].isValid(a, b, kingcurr1, kingcurr2))
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

    //--------------------------------------------------------- IS IT CHECK MATE? ---------------------------------------------------
    public static boolean isCheckMate(String color)
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

        getkingCurrPlace(color);

        if(!isKingUnderCheck(color))
        {
            return false;
        }
        else
        {
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    if(Board.pieces[kingcurr1][kingcurr2].isValid(kingcurr1, kingcurr2, i, j))
                    {
                        Board.pieces = newPieces;
                        return false;
                    }

                }
            }
        }
        Board.pieces = newPieces;
        return true;

    }

    //------------------------------------------------------ GETS KING'S CURRENT PLACE ----------------------------------------------
    public static void getkingCurrPlace(String color)
    {
        for(int  i = 0; i < 8; i++)
        {
            for (int k = 0; k <8; k++)
            {
                if(Board.pieces[i][k] != null &&
                        Board.pieces[i][k].getPieces().contains("K") && Board.pieces[i][k].color.equalsIgnoreCase(color))
                {
                    kingcurr1 = i;
                    kingcurr2 = k;
                }
            }
        }
    }
}

