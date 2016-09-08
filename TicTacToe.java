/*
 Siavash Samiei
 01/13/2015
 3D Tic Tac Toe Logic
 This class is the main game logic class of the 3D Tic Tac Toe game. This class holds the logic used to play the 3D tic tac toe game.
 This class creates the three dimensional array that the data from the 3d tic tac toe game is stored in. When a move is made, the
 position of the move is saved in the array, the array is then checked for a winning sequence of the same peice. This class also
 creates the graphical disply in which the game is played on by the user. The visual data seen by the user is stored in the array for
 the logic to be done by the writeMove() method. This class creates the UI used by the player to place peices and move around the board.
 */
import hsa.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
/*
 |----------------------------|--------------------------------------------------------------------------------------------------------------|
 |    Variable Dictionary     |                                                                                                              |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |   Name        |    Type    |                                                    Description                                               |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |    c          | Reference  | c is a reference variable used to refer to the Console class and to access methods within it. This makes the |
 |               |            | program point to the Console class when the variable c is c alled.                                           |
 |----------------------------|--------------------------------------------------------------------------------------------------------------|
 |   grid        | char [][][]| grid is a three dimensional array that stores all moves played, the way it works is that it stores 3 two dime|
 |               |            | nsional arrays that each store a one dimensional array. This array is used for most of the logic of the game.|
 |               |            | The board starts out being full with the EMPTY char at every point, meaning no pieces are on the board. When |
 |               |            | a move is made by any player, that move is then stored in grid with either the PLAYER1 or PLAYER2 char value.|
 |               |            | This array is then checked for winning sequences of the char values. If there are no EMPTY values in the arra|
 |               |            | y and there are no winning arrangements, then the board is full and isFull() returns a value of true and the |
 |               |            | game ends in a draw and whoWon() returns the int value DRAW. At the start of the game, grid is reset to store|
 |               |            | a full array of EMPTY so that the game can be played as many times as the user likes.                        |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 | PLAYER1       |    char    | This is the variable that assigns what character will be entered for player 1 in grid [][][].                |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 | PLAYER2       |            | This is the variable that assigns what character will be entered for player 2 in grid [][][].                |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |  EMPTY        |            | This is that variable that is in grid [] [] [] in the positions where no move has been made.                 |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 | moveChar      |            | This is a variable that is used for the movement of the cursor in the grid UI, it gets a user input and deter|
 |               |            | mines what move to make in the game.                                                                         |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |   row         |     int    | This variable is used throughout the program to keep track of the row in grid[][][] the moves being made on  |
 |               |            | the graphic UI.                                                                                              |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   col         |            | This variable is used throughout the program to keep track of the col in grid[][][] the moves being made on  |
 |               |            | the graphic UI.                                                                                              |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   bo          |            | This variable is used throughout the program to keep track of the board in grid[][][] the moves being made on|
 |               |            | the graphic UI.                                                                                              |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   xPos        |            | This variable is used to determine the x position of the graphics throughout the game board.                 |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   yPos        |            |  This variable is used to determine the y position of the graphics throughout the game board.                |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   turn        |            | This variable is used to keep track of how many moves in total have been made and determine whos turn it is. |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 | WIN_SCORE     |            | WIN_SCORE is an int that holds the value of what the winning score of the game, and is checked in the whoWon |
 |               |            | method to see if the counterCollinears method returns the same value of WIN_SCORE. If it does, then the metho|
 |               |            | d determines who won the game.                                                                               |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |PLAYER1_WIN    |            | This variable is the value that is returned when the winning sequence in grid [][][] is 3 PLAYER1 chars.     |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |PLAYER2_WIN    |            | This variable is the value that is returned when the winning sequence in grid [][][] is 3 PLAYER2 chars.     |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   DRAW        |            | This variable is the value that is returned when neither PLAYER1 or PLAYER2 are in a winning sequence in grid|
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   RIGHT       |            | This is the value of a horizontal winning sequence on one board.                                             |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |   DOWN        |            | This is a value of a vertical winning sequence on one board.                                                 |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 | LEFT_DOWN     |            | This is a value of a diagonal winning sequence on one board going from the top right corner down to the botto|
 |               |            | m left corner.                                                                                               |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |RIGHT_DOWN     |            | This is a value of a diagonal winning sequence on one board going from the top left corner down to the bottom|
 |               |            | right corner.                                                                                                |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |DOWN_BOARD     |            | This is a value of a winning sequence on all 3 boards from the same cell on each board.                      |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |DOWN_DIAG_BOARD|            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in the |
 |               |            | same column but each in different rows (starting from the first row) and different boards.                   |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 DOWN_DIAG_BOARD2|            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in the |
 |               |            | same columns but each in different rows (starting from the last row) and different boards.                   |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |LEFT_BOARD     |            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in the |
 |               |            | same rows but each in different columns (starting from the third column) and different boards.               |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |RIGHT_BOARD    |            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in the |
 |               |            | same rows but each in different columns (starting from the first column) and different boards.               |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |LEFT_TOP_DIAG  |            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in diff|
 |               |            | erent rows, different columns and different boards(starting from the top left cell in the top board).        |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |RIGHT_TOP_DIAG |            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in diff|
 |               |            | erent rows, different columns and different boards(starting from the top right cell in the top board).       |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |LEFT_BOT_DIAG  |            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in diff|
 |               |            | erent rows, different columns and different boards(starting from the bottom left cell in the top board).     |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |RIGHT_BOT_DIAG |            | This is a value of a winning sequence on all 3 boards, starting on the top board, the three cells are in diff|
 |               |            | erent rows, different columns and different boards(starting from the bottom right cell in the top board).    |
 |               |            |--------------------------------------------------------------------------------------------------------------|
 |GAMES_LIMIT    |            | This is the value of the maximum number of highscores that can be read in/written to file.                   |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |   movesNum    |   double   | This is the variable with the value of how many moves the winning player took to win the game.               |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |  player1Name  |   String   | This is the String that stores the name of the first player.                                                 |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |  player2Name  |            | This is the String that stores the name of the second player.                                                |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |     HEADER    |            | This is the string that stores the header line of all files written by this program.                         |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |     names     |  String[]  | This is the array that stores the names for both players for the highscores.                                 |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |    outcome    |            | This is the array that stores the name of the winner of each game for the highscores.                        |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 |     moves     |    int[]   | This is the array that stores the number of moves it took the winner of the game to win for the highscores.  |
 |---------------|------------|--------------------------------------------------------------------------------------------------------------|
 */

public class TicTacToe
{
  static Console c;
  private static char[] [] [] grid = new char [3] [3] [3];
  private final char PLAYER1 = 'X', PLAYER2 = 'O', EMPTY = '-';
  private char moveChar;
  private int row, col, bo, xPos, yPos, turn;
  private final int WIN_SCORE = 3, PLAYER1_WIN = 1, PLAYER2_WIN = -1, DRAW = 0;
  private final int RIGHT = 11;
  private final int DOWN = 12;
  private final int LEFT_DOWN = 13;
  private final int RIGHT_DOWN = 14;
  private final int DOWN_BOARD = 15;
  private final int DOWN_DIAG_BOARD = 16;
  private final int DOWN_DIAG_BOARD2 = 17;
  private final int LEFT_BOARD = 18;
  private final int RIGHT_BOARD = 19;
  private final int LEFT_TOP_DIAG = 20;
  private final int RIGHT_TOP_DIAG = 21;
  private final int LEFT_BOT_DIAG = 22;
  private final int RIGHT_BOT_DIAG = 23;
  final int GAMES_LIMIT = 10;
  private double movesNum;
  public String player1Name, player2Name;
  public final String HEADER = ("3D Tic Tac Toe By Siavash Samiei");
  public String[] names = new String [GAMES_LIMIT];
  public String[] outcome = new String [GAMES_LIMIT];
  public int[] moves = new int [GAMES_LIMIT];
  
  
  /*
   message Method
   |--------------|---------------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to create a JOptionPane message dialog with a user specified message.           |
   |--------------|---------------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |    msg   |   String   | This is the variable that is assigned a |
   |----------|------------| designated string to be displayed as the|
   |                       | message displayed by the message dialog.|
   |-----------------------|-----------------------------------------|
   */
  public void message (String msg)
  {
    JOptionPane.showMessageDialog (null, msg);
  }
  
  
  /*
   message Method
   |--------------|---------------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to create a JOptionPane confirm dialog with a user specified message.           |
   |--------------|---------------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |    msg   |   String   | This is the variable that is assigned a |
   |----------|------------| designated string to be displayed as the|
   |                       | message displayed by the confirm dialog.|
   |-----------------------|-----------------------------------------|
   */
  public int confirm (String msg)
  {
    int result = JOptionPane.showConfirmDialog (null, msg, "Warning", JOptionPane.YES_NO_OPTION);
    return result;
  }
  
  
  /*
   nameInput Method
   |--------------|---------------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to create a JOptionPane input dialog to get a player name.                      |
   |--------------|---------------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |  player  |   String   | This is the variable that is assigned ei|
   |----------|------------| ther "Player One" or "Player Two" to be |
   |                       | displayed in the prompt when the dialog |
   |                       | is shown. It is also set as the defult  |
   |                       | string for the input.                   |
   |-----------------------|-----------------------------------------|
   */
  public String nameInput (String player)
  {
    String nameOfPlayer;
    nameOfPlayer = JOptionPane.showInputDialog (player + " what is your name? Must be less than 10 characters.", player);
    return nameOfPlayer;
  }
  
  
  /*
   getPlayerName Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to get the name of each player using the JOptionPane input dialog and error|
   |              | trapping the input.                                                                                      |
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if statement is to check if the user has already entered a name, if so the user is asked if the|
   |              | y would like to change it, otherwise the game asks for the na,es.                                        |
   |              |                                                                                                          |
   |              | The second if statement in this method is used to determine which player's name to ask for.              |
   |              |                                                                                                          |
   |              | The third if statement is to check if the user would like to change their names or not.                  |
   |              |                                                                                                          |
   |              | The fourth if statement is for errortrapping player1Name and player2Name, if any of the two are null, the|
   |              | method ends and returns to main menu because null is the value assigned when the user presses cancel on t|
   |              | he JOptionPane input dialog. Otherwise, if either of the names are greater than 10 characters in length  |
   |              | an error message is shown and then the user is asked to re-enter a valid name.                           |
   |              |                                                                                                          |
   |              | The final if statement is to determine when to exit the method and that is when there is two valid inputs|
   |--------------|----------------------------------------------------------------------------------------------------------|
   |     loops    | The purpose of this loop to errortrap player1Name and player2Name and continue until the names are valid.|
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |  counter |     int    | This is the variable that is used to det|
   |----------|------------| ermines which players name to ask for wh|
   |                       | en the loop is happening. This variable |
   |                       | is incremented when a valid input is giv|
   |                       | en.                                     |
   |-----------------------|-----------------------------------------|
   */
  public boolean getPlayerNames ()
  {
    int counter = 0;
    boolean longer = false;
    while (true)
    {
      if (player1Name == null || player2Name == null || longer)
      {
        if (counter == 0)
          player1Name = nameInput ("Player One");
        else
        {
          if (counter == 1)
            player2Name = nameInput ("Player Two");
        }
      }
      else
      {
        if (confirm ("Do you want to change the names of the players?") == JOptionPane.YES_OPTION)
        {
          player1Name = null;
          player2Name = null;
          return false;
        }
        else
          break;
      }
      if ((counter == 0 && player1Name == null) || (counter == 1 && player2Name == null))
      {
        return true;
      }
      else if ((counter == 0 && player1Name.length () > 10) || (counter == 1 && player2Name.length () > 10))
      {
        message ("Name must be less than 10 characters.");
        longer = true;
      }
      else
      {
        counter++;
        if (counter == 2)
          break;
      }
    }
    return false;
  }
  
  
  /*
   drawName Method
   |--------------|-------------------------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to draw a the names of each player with a color coded box around it when it is that player|
   |              | s turn. When one rectangle is drawn, the other is erased.                                                               |
   |--------------|-------------------------------------------------------------------------------------------------------------------------|
   | Conditionals | The purpose of this if statement is to determine where to draw the box around the names and the color based on the turn.|
   |--------------|-------------------------------------------------------------------------------------------------------------------------|
   */
  public void drawNames ()
  {
    c.setFont (new Font ("Impact", 0, 20));
    c.drawString (player1Name, 20, 130);
    c.drawString (player2Name, 20, 170);
    if (turn % 2 == 0)
    {
      c.setColor (Color.green);
      c.drawRect (16, 105, 92, 38);
      c.setColor (Color.black);
      c.drawRect (16, 145, 92, 38);
    }
    else
    {
      c.setColor (Color.red);
      c.drawRect (16, 145, 92, 38);
      c.setColor (Color.black);
      c.drawRect (16, 105, 92, 38);
    }
  }
  
  
  /*
   playerInput Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to get the user input for the main gameplay. The user can move around the  |
   |              | board and make a move or choose to back to the main menu. This method is a boolean return method so that |
   |              | when it returns true, the fullGame() method where this method is called is ended and returns to the main |
   |              | menu.                                                                                                    |
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if/else statement is for checking if the player wants to make a move and if the cell they want |
   |              | to move in is empty, if not then the player cannot place their piece there. This conditional also checks |
   |              | if the user would like to return to the main menu by pressing b.                                         |
   |              |                                                                                                          |
   |              | The second if/else statement is to determine which player is moving so that the move is added with the co|
   |              | rrect piece.                                                                                             |
   |              |                                                                                                          |
   |              | The third if/else statement is to confirm if the user wants to return to the main menu with a confirm dia|
   |              | log. If they press yes, the method will return true, else it will continue with the method.              |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |     loops    | The purpose of this loop to errortrap the player input. The loop stops when the user has successfully mad|
   |              | e a move or if the user would like to go back to the main menu.                                          |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |  player  |     int    | This is the variable that is used to det|
   |----------|------------| ermines which player is taking the turn |
   |                       | so that the correct char can be added to|
   |                       | grid[][][].                             |
   |-----------------------|-----------------------------------------|
   */
  public boolean playerInput (int player)
  {
    while (true)
    {
      selectCell (row, col);
      moveChar = c.getChar ();
      deselectCell (row, col);
      moveCursor (moveChar, 3, 0);
      if (moveChar == '\n' && grid [bo] [col] [row] == EMPTY)
      {
        if (player == 1)
          addMove ('X', 1);
        else
          addMove ('O', 2);
        break;
      }
      else if (moveChar == 'b')
      {
        if (confirm ("Are you sure you want to go back to the main Menu?") == JOptionPane.YES_OPTION)
          return true;
        else
          continue;
      }
    }
    return false;
  }
  
  
  /*
   fullGame Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to draw the game board and the UI for the game, get the players move, and  |
   |              | to check who won. The method starts by setting the grid to be full with EMPTY, turn to 0, and the board  |
   |              | to 0 so that the w,a,s,d,q,e movement of the UI can have the correct limits. The method then draws the g |
   |              | ameboard and the UI. The method then calls the methods that get user input, and checks if the user is wa |
   |              | nting to return to the main menu. The method continously checks the board to see if it is full or if it  |
   |              | has a winning sequence. If so, it breaks from the loop and checks who won the game and in how many turns.|
   |              | The method then tells the user who won and calls the saveHighScore method.                               |
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if/else statement is for checking whos turn it is so that the program can ask for user input f |
   |              | rom the right player.                                                                                    |
   |              |                                                                                                          |
   |              | The second if/else statement is to determine which player has won or if the game is a draw and to display|
   |              | the proper message dialog based on the outcome of the game.                                              |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |     loops    | The purpose of the do while loop is to continuously ask for the user input and update the UI until the ga|
   |              | me is either won or ends in a draw.                                                                      |
   |--------------|----------------------------------------------------------------------------------------------------------|
   
   */
  public void fullGame ()
  {
    resetGrid ();
    turn = 0;
    bo = 0;
    c.setColor (Color.cyan);
    drawGrid (215, 185);
    drawGrid (365, 285);
    drawGrid (515, 385);
    do
    {
      drawNames ();
      turn++;
      if (turn % 2 != 0)
      {
        if (playerInput (1) == true)
          return;
      }
      if (turn % 2 == 0)
      {
        if (playerInput (2) == true)
          return;
        
      }
    }
    while (whoWon () == 0 && !isFull ());
    if (whoWon () == 1)
    {
      movesNum = (turn / 2.0) + 0.5;
      message (player1Name + " is the winner!");
    }
    else if (whoWon () == -1)
    {
      movesNum = (turn / 2.0);
      message (player2Name + " is the winner!");
    }
    else
      message ("The game has ended in the draw, well played!");
    saveHighScore ();
    
  }
  
  
  /*fullGame Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to save the score values gotten from the game to a file so that it can be  |
   |              | displayed when the user would like to see them. This method also sorts the scores in order from least to |
   |              | greatest based on the number of moves it took the player to win.                                         |
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if/else statement is to check if the file with the saved highscores is blank with no scores. If|
   |              | this is true, the method exits the loop that is reading from file and continues with the rest of the code|
   |              |                                                                                                          |
   |              | The second if/else statement is used to sort the scores based on the number of moves the winning player m|
   |              | ade to win. If this number is less than any of the scores saved in the file, the program moves the lower |
   |              | score farther down the array.                                                                            |
   |              |                                                                                                          |
   |              | The third if/else statement is to determine what outcome to write to the file based on the result of the |
   |              | game.                                                                                                    |
   |              |                                                                                                          |
   |              | The final if/else statement is to break when the final score set has been written to the file by checking|
   |              | the value of name in the array.                                                                          |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |     loops    | The first for loop in this method is used to read a maximum of 10 highscores from the highscores file. Th|
   |              | is loop also stores the lines read to each of the arrays at position i.                                  |
   |              |                                                                                                          |
   |              | The second for loop is used to sort the values saved in the highscores array based on the player moves.  |
   |              | This is repeated the same number of times as the values read from file, the maximum being 10. This loop  |
   |              | is used to add the current score to the array in the correct position as well.                           |
   |              |                                                                                                          |
   |              | The third loop (the while loop) is used to actually rearrange the highscores arrays in the right oder. Th|
   |              | is loop repeats as long as y is greater than the maximum number of scores that had been read from the fil|
   |              | e so that it keeps sorting until it has sorted all of the scores in the correct order.                   |
   |              |                                                                                                          |
   |              | The final for loop in this method is used to print highscores to the hihgscores file a maximum of 10 time|
   |              | s. It does this by printing the values in the highscore arrays at position j which starts and 0 and can  |
   |              | have a value up to GAMES_LIMIT.                                                                          |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |  Try blocks  | The try blocks in this method are used to catch exceptions created by the file io in the method. These ar|
   |              | e caused by the FileReader and the FileWriter classes.                                                   |
   |--------------|----------------------------------------------------------------------------------------------------------|
   
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   | maxRead  |     int    | This is a variable used to count the nu |
   |----------|------------| mber of scores read from the original hi|
   |                       | ghscores.siav file.                     |
   |-----------------------|-----------------------------------------|
   |    y     |     int    | This is a variable starting equivalent t|
   |          |            | o the number of highscores read and then|
   |          |            | is set to the position of each score in |
   |          |            | its respective array so that it can be u|
   |          |            | sed to sort the arrays by changing the p|
   |          |            | ositions of the values.                 |
   |-----------------------|-----------------------------------------|
   |     i    |     int    | Used in a for loop.                     |
   |-----------------------|-----------------------------------------|
   |     q    |     int    | Used in a for loop.                     |
   |-----------------------|-----------------------------------------|
   |     j   |     int     | Used in a for loop.                     |
   |-----------------------|-----------------------------------------|
   |     e    | Exception  | Used to catch NullPointerExcpeption     |
   |          | var        | and IOExcpetion  thrown by FileReader() |
   |          |            | and FileWriter().                       |
   |----------|------------|-----------------------------------------|
   |    ex    | Exception  | Used to catch IOExcpeption thrown by    |
   |          | var        | FileReader().                           |
   |----------|------------|-----------------------------------------|
   */
  public void saveHighScore ()
  {
    int maxRead = 0;
    try
    {
      BufferedReader br = new BufferedReader (new FileReader ("highScores.siav"));
      br.readLine ();
      for (int i = 0 ; i < 9 ; i++)
      {
        names [i] = br.readLine ();
        if (names [i] == null)
          break;
        outcome [i] = br.readLine ();
        moves [i] = Integer.parseInt (br.readLine ());
        maxRead++;
      }
      for (int q = 0 ; q <= maxRead ; q++)
      {
        if (movesNum < moves [q] || moves [q] == 0)
        {
          int y = maxRead;
          while (y > q && maxRead != 0)
          {
            names [y] = names [y - 1];
            outcome [y] = outcome [y - 1];
            moves [y] = moves [y - 1];
            y--;
          }
          
          names [y] = (player1Name + " & " + player2Name);
          if (whoWon () == 1)
            outcome [y] = player1Name;
          else if (whoWon () == -1)
            outcome [y] = player2Name;
          else
            outcome [y] = "Draw";
          moves [y] = (int) movesNum;
          break;
        }
      }
      PrintWriter pw = new PrintWriter (new FileWriter ("highScores.siav"));
      pw.println (HEADER);
      for (int j = 0 ; j < GAMES_LIMIT ; j++)
      {
        if (names [j] == null)
          break;
        pw.println (names [j]);
        pw.println (outcome [j]);
        pw.println (moves [j]);
      }
      pw.close ();
    }
    catch (IOException e)
    {
      try
      {
        PrintWriter pw = new PrintWriter (new FileWriter ("highScores.siav"));
        pw.println (HEADER);
        pw.close ();
        saveHighScore ();
      }
      catch (IOException ex)
      {
      }
    }
    catch (NullPointerException e)
    {
    }
  }
  
  
  /*
   addMove Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to call the writeMove method with the right char so that it draws the righ |
   |              | t piece on the visual gameBoard and it calls either the addPlayer1 or addPlayer2 based on which player is|
   |              | making the move so that the piece coressponding to which player is making a move is added to grid [][][].|
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if/else statement is for checking if the player wants to make a move and if the cell they want |
   |              | to move in is empty, if not then the player cannot place their piece there. This conditional also checks |
   |              | if the user would like to return to the main menu by pressing b.                                         |
   |              |                                                                                                          |
   |              | The second if/else statement is to determine which player is moving so that the move is added with the co|
   |              | rrect piece.                                                                                             |
   |              |                                                                                                          |
   |              | The third if/else statement is to confirm if the user wants to return to the main menu with a confirm dia|
   |              | log. If they press yes, the method will return true, else it will continue with the method.              |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |  player  |     int    | This is the variable that is used to det|
   |----------|------------| ermines which method to call so that th |
   |                       | e correct piece is add to grid[][][].   |
   |-----------------------|-----------------------------------------|
   |  piece   |    char    | This is the variable that is assigned a |
   |----------|------------| char variable that will be drawn by the |
   |                       | writeMove method.                       |
   |-----------------------|-----------------------------------------|
   */
  
  private void addMove (char piece, int player)
  {
    writeMove (bo, row, col, piece);
    if (player == 1)
      addPlayer1 (bo, row, col);
    else
      addPlayer2 (bo, row, col);
  }
  
  
  /*
   moveCursor Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to get a character from the user and move the cursor around the board so t |
   |              | hat the user is able to see where they are making their move on the board.                               |
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if/else statement is to decide what to do based on what character is entered by the user.      |
   |              |                                                                                                          |
   |              | The if statements within the other if statements are to check if the current position of the cursor is w |
   |              | ithin the limits so that it can make sure no movement moves the cursor outside of the board.             |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |    ch    |    char    | The purpose of this variable is to check|
   |----------|------------| what dimension to increment based on wha|
   |                       | t character ch is.                      |
   |-----------------------|-----------------------------------------|
   |   int    |   maxVal   | This variable is the maximum value that |
   |----------|------------| row and col and bo can have setting limi|
   |                       | ts for where the cursor can move to.    |
   |-----------------------|-----------------------------------------|
   |   int    |   minVal   | This variable is the minimum value that |
   |----------|------------| row and col and bo can have setting limi|
   |                       | ts for where the cursor can move to.    |
   |-----------------------|-----------------------------------------|
   */
  
  private void moveCursor (char ch, int maxVal, int minVal)
  {
    if (ch == 'w')
    {
      if (row > minVal)
        row--;
    }
    else if (ch == 's')
    {
      if (row < maxVal - 1)
        row++;
    }
    else if (ch == 'a')
    {
      if (col > minVal)
        col--;
    }
    else if (ch == 'd')
    {
      if (col < maxVal - 1)
        col++;
    }
    else if (ch == 'q')
    {
      if (bo < maxVal - 1)
      {
        yPos -= 100;
        xPos -= 150;
        bo++;
      }
    }
    else
    {
      if (ch == 'e')
      {
        if (bo > minVal)
        {
          yPos += 100;
          xPos += 150;
          bo--;
        }
      }
    }
  }
  
  
  /*
   drawGrid Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to draw the 3 3x3 grids in which the game is played on. This gives the user|
   |              | a visual representation of the 3d array that is storing the data so that they can choose their move posit|
   |              | ions in an easy and user friendly way.                                                                   |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |     Loops    |  The first for loop in this method used to draw the the vertical and horizontal lines 3 times to create  |
   |              |  the 3x3 grid.                                                                                           |
   |              |                                                                                                          |
   |              |  The second for loop in this method is used to draw the same 3x3 grid in different positions making a thi|
   |              |  cker and more visible grid. This loop is incremented so that it is 3 of each line drawn side by side to |
   |              |  make thicker lines.                                                                                     |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |   row    |    int     | This variable is used to determine the x|
   |----------|------------| position of where the game board is draw|
   |                       | n.                                      |
   |-----------------------|-----------------------------------------|
   |   col    |    int     | This variable is used to determine the y|
   |----------|------------| position of where the game board is draw|
   |          |            | n.                                      |
   |          |            |-----------------------------------------|
   |   i      |    int     | Used in a for loop.                     |
   |          |            |                                         |
   |   j      |    int     | Used in a for loop.                     |
   |-----------------------|-----------------------------------------|
   */
  private void drawGrid (int row, int col)
  {
    c.setColor (Color.cyan);
    for (int i = 0 ; i <= 3 ; i++)
    {
      for (int j = -1 ; j <= 1 ; j++)
      {
        c.drawLine (row, i * 50 + col + j + 50, (row + 150), i * 50 + col + j + 50);
        c.drawLine (row + 50 * i + j, col + 50, row + 50 * i + j, (col + 150) + 50);
      }
    }
    xPos = row;
    yPos = col;
  }
  
  
  /*
   selectCell Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Purpose   | The purpose of this method is to highlight the cell selected by user so that the user can see where they |
   |              | are making their move.                                                                                   |
   |--------------|----------------------------------------------------------------------------------------------------------|
   | Conditionals | The if/else statement in this method is used to determine the color of the cursor based on the turn.     |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |     Loops    | The loop in this method used to draw the rectangle that is highlighting the cells.                       |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |   row    |     int    | This variable is used to determine the x|
   |----------|------------| position of where the cell is drawn.    |
   |-----------------------|-----------------------------------------|
   |   col    |   int      | This variable is used to determine the y|
   |          |            | position of where the cell  is drawn.   |
   |----------|------------|-----------------------------------------|
   |   i      |    int     | Used in a for loop.                     |
   |-----------------------|-----------------------------------------|
   */
  private void selectCell (int row, int col)
  {
    if (turn % 2 != 0)
    {
      c.setColor (Color.green);
    }
    else
      c.setColor (Color.red);
    for (int i = -1 ; i <= 1 ; i++)
      c.drawRect (50 * col + xPos + i, 50 * row + yPos + i + 50, 50 - i * 2, 50 - i * 2);
    c.setColour (Color.BLACK);
  }
  
  
  /*
   deselectCellMethod
   |--------------|----------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is to redraw the highlight the cell selected by user when it is not selected  |
   |              | in the same color as the board so that it is no longer highlighted.                                      |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |    Loops     | The loop in this method used to draw the rectangle that is highlighting the cells in the same color as th|
   |              | e grid so that it is no longer highlighted.                                                              |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |   VariableDictionary  |                                         |
   |----------|------------|-----------------------------------------|
   |   Name   |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |   row    |     int    | This variable is used to determine the x|
   |----------|------------| position of where the cell is drawn.    |
   |-----------------------|-----------------------------------------|
   |   col    |    int     | This variable is used to determine the y|
   |----------|------------| position of where the cell  is drawn.   |
   |-----------------------|-----------------------------------------|
   */
  private void deselectCell (int row, int col)
  {
    c.setColour (Color.cyan);
    for (int i = -1 ; i <= 1 ; i++)
      c.drawRect (50 * col + xPos + i, 50 * row + yPos + i + 50, 50 - i * 2, 50 - i * 2);
    c.setColour (Color.cyan);
    c.drawLine (50 * col + xPos - 1, 50 * row + yPos + 50, 50 * col + xPos + 51, 50 * row + yPos + 50);
    c.drawLine (50 * col + xPos - 1, 50 * row + yPos + 50 + 50, 50 * col + xPos + 51, 50 * row + yPos + 50 + 50);
    c.drawLine (50 * col + xPos, 50 * row + yPos - 1 + 50, 50 * col + xPos, 50 * row + yPos + 51 + 50);
    c.drawLine (50 * col + xPos + 50, 50 * row + yPos - 1 + 50, 50 * col + xPos + 50, 50 * row + yPos + 51 + 50);
  }
  
  
  /*
   writeMove lMethod
   |--------------|----------------------------------------------------------------------------------------------------------|
   |   Purpose    | The puprose of this method is to draw either and X or O on the board where the user would like to make   |
   |              | their move so that they are able to see what is going on and where they previous moves have been made.   |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|-----------------------------------------|
   |  VariableDictionary   |                                         |
   |----------|------------|-----------------------------------------|
   |  Name    |    Type    |               Description               |
   |----------|------------|-----------------------------------------|
   |  int     |     row    | This variable is used to determine the x|
   |----------|------------| position of where the X or O is drawn.  |
   |-----------------------|-----------------------------------------|
   |  int     |    col     | This variable is used to determine the y|
   |----------|------------| position of where the X or O is drawn.  |
   |-----------------------|-----------------------------------------|
   |  int     |    col     | This variable is used to determine the  |
   |----------|------------| board in which the X or O is drawn.     |
   |          |            |-----------------------------------------|
   |   i      |    int     | Used in a for loop.                     |
   |-----------------------|-----------------------------------------|
   */
  
  private void writeMove (int bo, int row, int col, char piece)
  {
    c.drawString (piece + "", (xPos + 30) + col * 50, (yPos + 30) + row * 50 + 50);
  }
  
  
  /*
   addPlayer1 Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is too add the token of player1 (X) to the specified location in grid[][][].  |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     z      | This variable is the position of the thi |
   |----------|------------| rd dimension of the grid (which board).  |
   |-----------------------|------------------------------------------|
   |  int     |     x      | This variable is the position of the sec |
   |----------|------------| ond dimension of the grid (which column).|
   |-----------------------|------------------------------------------|
   |  int     |     y      | This variable is the position of the fir |
   |----------|------------| st dimension of the grid (which row).    |
   |-----------------------|------------------------------------------|
   */
  public void addPlayer1 (int z, int x, int y)
  {
    grid [z] [y] [x] = PLAYER1;
  }
  
  
  /*
   addPlayer2 Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is too add the token of player2 (O) to the specified location in grid[][][].  |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     z      | This variable is the position of the thi |
   |----------|------------| rd dimension of the grid (which board).  |
   |-----------------------|------------------------------------------|
   |  int     |     x      | This variable is the position of the sec |
   |----------|------------| ond dimension of the grid (which column).|
   |-----------------------|------------------------------------------|
   |  int     |     y      | This variable is the position of the fir |
   |----------|------------| st dimension of the grid (which row).    |
   |-----------------------|------------------------------------------|
   */
  public void addPlayer2 (int z, int x, int y)
  {
    grid [z] [y] [x] = PLAYER2;
  }
  
  
  /*
   get Method
   |--------------|----------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is get what character (X, O, or -)at the specified location in grid[][][].    |
   |--------------|----------------------------------------------------------------------------------------------------------|
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     z      | This variable is the position of the thi |
   |----------|------------| rd dimension of the grid (which board).  |
   |-----------------------|------------------------------------------|
   |  int     |     x      | This variable is the position of the sec |
   |----------|------------| ond dimension of the grid (which column).|
   |-----------------------|------------------------------------------|
   |  int     |     y      | This variable is the position of the fir |
   |----------|------------| st dimension of the grid (which row).    |
   |-----------------------|------------------------------------------|
   */
  public char get (int z, int x, int y)
  {
    return grid [z] [y] [x];
  }
  
  
  /*
   whoWon Method
   |--------------|------------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is to check the positions of the char values in grid, and see if any of them ar |
   |              | e aligned in a winning combination. If so, the method will return a value specific to that winning sequence|
   |              | otherwise, it will return 1.                                                                               |
   |--------------|------------------------------------------------------------------------------------------------------------|
   | Conditionals |  All the conditionals in this method are used to first check if the direction passed in matches one of the |
   |              |  global direction values (RIGHT,DOWN, etc.) and if a value at a point in grid[][][] is the same as another |
   |              |  point  in a cell that is going in direction. If the direction and the cells being checked correlate, the m|
   |              |  ethod returns the value 1 plus the value of the direction. If no winning combinations are found, meaning n|
   |              |  o values in grid[][][] are arranged in the winning formations, the method returns 1.                      |
   |--------------|------------------------------------------------------------------------------------------------------------|
   |  Try Blocks  | The purpose of this try block is to catch any outOfBoundsExceptions created when checking the value of a p |
   |              | eice added to grid[][][] that would go out of bounds if it was to go past the limits of grid[][][] when the|
   |              | collinears in a specific direction are checked. E.g. direction==RIGHT and a value was placed at z=1,x=3,and|
   |              | y=1. For RIGHT x is checked by add 1 to x but in this situation that would be out of bounds so the exceptio|
   |              | n and the program continues.                                                                               |
   |--------------|------------------------------------------------------------------------------------------------------------|
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     z      | This variable starts with a value of zero|
   |          |            | and increases to the length of the first |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the first grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     x      | This variable starts with a value of zero|
   |          |            | and increases to the length of the second|
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the second grid[][][] |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     y      | This variable starts with a value of zero|
   |          |            | and increases to the length of the third |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the third grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     | directions | This variable holds a value of a directio|
   |          |            | n of a possible winning sequence and is u|
   |          |            | sed to check the values in grid with the |
   |          |            | same value in the direction.             |
   |-----------------------|------------------------------------------|
   */
  private int countCollinears (int z, int x, int y, int direction)
  {
    try
    {
      if (direction == RIGHT && get (z, x + 1, y) == get (z, x, y))
        return 1 + countCollinears (z, x + 1, y, direction);
      if (direction == DOWN && get (z, x, y + 1) == get (z, x, y))
        return 1 + countCollinears (z, x, y + 1, direction);
      if (direction == LEFT_DOWN && get (z, x - 1, y + 1) == get (z, x, y))
        return 1 + countCollinears (z, x - 1, y + 1, direction);
      if (direction == RIGHT_DOWN && get (z, x + 1, y + 1) == get (z, x, y))
        return 1 + countCollinears (z, x + 1, y + 1, direction);
      if (direction == DOWN_BOARD && get (z + 1, x, y) == get (z, x, y))
        return 1 + countCollinears (z + 1, x, y, direction);
      if (direction == DOWN_DIAG_BOARD && get (z + 1, x, y - 1) == get (z, x, y))
        return 1 + countCollinears (z + 1, x, y - 1, direction);
      if (direction == DOWN_DIAG_BOARD2 && get (z + 1, x, y + 1) == get (z, x, y))
        return 1 + countCollinears (z + 1, x, y + 1, direction);
      if (direction == LEFT_BOARD && get (z + 1, x + 1, y) == get (z, x, y))
        return 1 + countCollinears (z + 1, x + 1, y, direction);
      if (direction == RIGHT_BOARD && get (z + 1, x - 1, y) == get (z, x, y))
        return 1 + countCollinears (z + 1, x - 1, y, direction);
      if (direction == LEFT_TOP_DIAG && get (z + 1, x + 1, y + 1) == get (z, x, y))
        return 1 + countCollinears (z + 1, x + 1, y + 1, direction);
      if (direction == RIGHT_TOP_DIAG && get (z + 1, x + 1, y - 1) == get (z, x, y))
        return 1 + countCollinears (z + 1, x + 1, y - 1, direction);
      if (direction == LEFT_BOT_DIAG && get (z + 1, x - 1, y - 1) == get (z, x, y))
        return 1 + countCollinears (z + 1, x - 1, y - 1, direction);
      if (direction == RIGHT_BOT_DIAG && get (z + 1, x - 1, y + 1) == get (z, x, y))
        return 1 + countCollinears (z + 1, x - 1, y + 1, direction);
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      return 1;
    }
    return 1;
  }
  
  
  /*
   whoWon Method
   |--------------|------------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is to check which player won by checking what char value the winning sequence f |
   |              | ound by the countCollinears method has. Based on this, the method will return the value of the result of th|
   |              | e game, either PLAYER1, PLAYER2, or DRAW.                                                                  |
   |--------------|------------------------------------------------------------------------------------------------------------|
   | Conditionals | The first if statement is check the grid[][][] array for any of the same values arranged in a winning sequ |
   |              | ence. If so, it then checks which player one, otherwise the method returns DRAW.                           |
   |              |                                                                                                            |
   |              | The second/third if statementis to check if the chars in the winning sequence have the value of PLAYER1 or |
   |              | PLAYER2 so that it can determine who won the game.                                                         |
   |--------------|------------------------------------------------------------------------------------------------------------|
   |    Loops     | The first for loop is used to cycle through and check all the values of the first dimension of grid[][][]  |
   |              |                                                                                                            |
   |              | The second for loop is used to cycle through and check all the values of the second dimension of grid[][][]|
   |              |                                                                                                            |
   |              | The third for loop is used to cycle through and check all the values of the third dimension of grid[][][]  |
   |--------------|------------------------------------------------------------------------------------------------------------|
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     z      | This variable starts with a value of zero|
   |          |            | and increases to the length of the first |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the first grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     x      | This variable starts with a value of zero|
   |          |            | and increases to the length of the second|
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the second grid[][][] |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     y      | This variable starts with a value of zero|
   |          |            | and increases to the length of the third |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the third grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   */
  public int whoWon ()
  {
    for (int z = 0 ; z < 3 ; z++)
    {
      for (int y = 0 ; y < 3 ; y++)
      {
        for (int x = 0 ; x < 3 ; x++)
        {
          if (countCollinears (z, x, y, RIGHT) == WIN_SCORE ||
              countCollinears (z, x, y, DOWN) == WIN_SCORE ||
              countCollinears (z, x, y, LEFT_DOWN) == WIN_SCORE ||
              countCollinears (z, x, y, RIGHT_DOWN) == WIN_SCORE ||
              countCollinears (z, x, y, DOWN_BOARD) == WIN_SCORE ||
              countCollinears (z, x, y, DOWN_DIAG_BOARD) == WIN_SCORE ||
              countCollinears (z, x, y, DOWN_DIAG_BOARD2) == WIN_SCORE ||
              countCollinears (z, x, y, LEFT_BOARD) == WIN_SCORE ||
              countCollinears (z, x, y, RIGHT_BOARD) == WIN_SCORE ||
              countCollinears (z, x, y, LEFT_TOP_DIAG) == WIN_SCORE ||
              countCollinears (z, x, y, RIGHT_TOP_DIAG) == WIN_SCORE ||
              countCollinears (z, x, y, LEFT_BOT_DIAG) == WIN_SCORE ||
              countCollinears (z, x, y, RIGHT_BOT_DIAG) == WIN_SCORE)
          {
            if (get (z, x, y) == PLAYER2)
              return PLAYER2_WIN;
            if (get (z, x, y) == PLAYER1)
              return PLAYER1_WIN;
          }
        }
      }
    }
    return DRAW;
  }
  
  
  /*
   isFull Method
   |--------------|------------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is get the value of each character in gird[][][] until one of them has a vlaue  |
   |              | of EMPTY, at which point the program returns false, if there is no character with a value of EMPTY, then   |
   |              | method returns true. This is used in processing to know when the board is full so that the game can stop   |
   |              | asking for user input.                                                                                     |
   |--------------|------------------------------------------------------------------------------------------------------------|
   |    Loops     | The first for loop is used to cycle through and check all the values of the first dimension of grid[][][]  |
   |              |                                                                                                            |
   |              | The second for loop is used to cycle through and check all the values of the second dimension of grid[][][]|
   |              |                                                                                                            |
   |              | The third for loop is used to cycle through and check all the values of the third dimension of grid[][][]  |
   |--------------|------------------------------------------------------------------------------------------------------------|
   
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     q      | This variable starts with a value of zero|
   |          |            | and increases to the length of the first |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the first grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     i      | This variable starts with a value of zero|
   |          |            | and increases to the length of the second|
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the second grid[][][] |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     c      | This variable starts with a value of zero|
   |          |            | and increases to the length of the third |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the third grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   */
  public boolean isFull ()
  {
    for (int q = 0 ; q < grid.length ; q++)
    {
      for (int i = 0 ; i < grid [q].length ; i++)
      {
        for (int c = 0 ; c < grid [q] [i].length ; c++)
          if (grid [q] [i] [c] == EMPTY)
          return false;
      }
    }
    return true;
  }
  
  
  /*
   resetGrid Method
   |--------------|---------------------------------------------------------------------------------------------------------------------|
   |   Purpose    | The purpose of this method is to set all the values in grid[][][] to EMPTY, resetting the board and leaving no left |
   |              | behind data so that a new game can be started.                                                                      |
   |--------------|---------------------------------------------------------------------------------------------------------------------|
   |    Loops     | The first for loop is used to cycle through and sets all the values of the first dimension of grid[][][] to EMPTY   |
   |              |                                                                                                                     |
   |              | The second for loop is used to cycle through and check all the values of the second dimension of grid[][][] to EMPTY|
   |              |                                                                                                                     |
   |              | The third for loop is used to cycle through and check all the values of the third dimension of grid[][][] to EMPTY  |
   |--------------|---------------------------------------------------------------------------------------------------------------------|
   
   |-----------------------|------------------------------------------|
   |  VariableDictionary   |                                          |
   |----------|------------|------------------------------------------|
   |  Name    |    Type    |               Description                |
   |----------|------------|------------------------------------------|
   |  int     |     q      | This variable starts with a value of zero|
   |          |            | and increases to the length of the first |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the first grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     i      | This variable starts with a value of zero|
   |          |            | and increases to the length of the second|
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the second grid[][][] |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   |  int     |     c      | This variable starts with a value of zero|
   |          |            | and increases to the length of the third |
   |          |            | dimension of grid[][][]. Used to cycle th|
   |          |            | rough all parts of the third grid[][][]  |
   |          |            | dimension.                               |
   |-----------------------|------------------------------------------|
   */
  public void resetGrid ()
  {
    for (int q = 0 ; q < grid.length ; q++)
    {
      for (int i = 0 ; i < grid [q].length ; i++)
      {
        for (int c = 0 ; c < grid [q] [i].length ; c++)
          grid [q] [i] [c] = EMPTY;
      }
    }
  }
  
  /*
   constructor
   
   Access console from the TicTacToeGame class. 
   
   |-----------------------|--------------------------------------------------------------------|
   |   VariableDictionary  |                                                                    |
   |----------|------------|--------------------------------------------------------------------|
   |   Name   |    Type    |               Description                                          |
   |----------|------------|--------------------------------------------------------------------|
   |    c     |  Console   | c is the variable that passes the console from the TicTacToeGame   |
   |----------|------------| class into the TicTacToe class to allow it access to methods from  |
   |                       | the console class.                                                 |  
   |-----------------------|--------------------------------------------------------------------|
   */
  public TicTacToe (Console con)
  {
    c = con;
  }
}


