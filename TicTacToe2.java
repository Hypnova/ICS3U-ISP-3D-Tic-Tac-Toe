import java.awt.*;
import hsa.*;
import javax.swing.*;
public class TicTacToe2
{
    static Console c;
    private static char[] [] [] grid;
    private int winningScore, height, width, board;
    private final char PLAYER1 = 'X';
    private final char PLAYER2 = 'O';
    private final char EMPTY = '-';
    private final int PLAYER1_WIN = 1;
    private final int PLAYER2_WIN = -1;
    private final int DRAW = 0;
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
    private int row, col, bo, xPos, yPos, turn;
    private char moveChar;
    private String player1Name, player2Name;
    // public static void main (String[] args)
    // {
    //     TicTacToe ttt = new TicTacToe (c);
    //     ttt.fullGame ();
    // }


    public void getPlayerNames ()
    {
	player1Name = JOptionPane.showInputDialog ("Player one what is your name?", "Player One");
	if (player1Name == null)
	return;
	player2Name = JOptionPane.showInputDialog ("Player two what is your name?", "Player Two");
	if (player2Name == null)
	return;
	
    }


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


    public void fullGame ()
    {
	turn = 0;
	Image picture;
	int picWidth, picHeight;
	String PICTURE_PATH = "gameBoard.png";
	picture = Toolkit.getDefaultToolkit ().getImage (PICTURE_PATH);
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (picture, 0);
	picWidth = picture.getWidth (null);
	picHeight = picture.getHeight (null);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	c.drawImage (picture, 0, 0, null);
	c.setColor (Color.cyan);
	drawGrid (215, 185);
	drawGrid (365, 285);
	drawGrid (515, 385);
	getPlayerNames ();
	do
	{
	    drawNames ();
	    turn++;
	    if (turn % 2 != 0)
	    {

		while (true)
		{
		    selectCell (row, col);
		    moveChar = c.getChar ();
		    deselectCell (row, col);
		    moveCursor (moveChar, 3, 0);

		    if (moveChar == '\n' && grid [bo] [col] [row] == EMPTY)
		    {
			writeMove (bo, row, col, 'X');
			addPlayer1 (bo, row, col);
			break;
		    }
		}
	    }
	    else
	    {
		while (true)
		{
		    selectCell (row, col);
		    moveChar = c.getChar ();
		    deselectCell (row, col);
		    moveCursor (moveChar, 3, 0);

		    if (moveChar == '\n' && grid [bo] [col] [row] == EMPTY)
		    {
			writeMove (bo, row, col, 'O');
			addPlayer2 (bo, row, col);
			break;
		    }
		}
	    }
	}
	while (whoWon () == 0 && !isFull ());
	if (whoWon () == 1)
	    JOptionPane.showMessageDialog (null, player1Name + " is the winner!");
	else if (whoWon () == -1)
	    JOptionPane.showMessageDialog (null, player2Name + " is the winner!");
	else
	    JOptionPane.showMessageDialog (null, "The game has ended in the draw, well played!");
    }


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


    private void writeMove (int bo, int row, int col, char piece)
    {
	c.drawString (piece + "", (xPos + 30) + col * 50, (yPos + 30) + row * 50 + 50);
    }


    public TicTacToe2 (int z, int x, int y, int score)
    {
	height = y;
	width = x;
	board = z;
	winningScore = score;
	grid = new char [y] [x] [z];

	for (int zPos = 0 ; zPos < board ; zPos++)
	{
	    for (int yPos = 0 ; yPos < height ; yPos++)
	    {
		for (int xPos = 0 ; xPos < width ; xPos++)
		{
		    grid [zPos] [yPos] [xPos] = EMPTY;
		}
	    }
	}
    }


    public TicTacToe2 (Console con)
    {
	this (3, 3, 3, 3);
	con = c;
    }


    public void addPlayer1 (int z, int x, int y)
    {

	grid [z] [y] [x] = PLAYER1;
    }


    public void addPlayer2 (int z, int x, int y)
    {

	grid [z] [y] [x] = PLAYER2;
    }


    public char get (int z, int x, int y)
    {
	return grid [z] [y] [x];
    }


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


    public int whoWon ()
    {
	for (int z = 0 ; z < board ; z++)
	{
	    for (int y = 0 ; y < height ; y++)
	    {
		for (int x = 0 ; x < width ; x++)
		{
		    if (countCollinears (z, x, y, RIGHT) == winningScore ||
			    countCollinears (z, x, y, DOWN) == winningScore ||
			    countCollinears (z, x, y, LEFT_DOWN) == winningScore ||
			    countCollinears (z, x, y, RIGHT_DOWN) == winningScore ||
			    countCollinears (z, x, y, DOWN_BOARD) == winningScore ||
			    countCollinears (z, x, y, DOWN_DIAG_BOARD) == winningScore ||
			    countCollinears (z, x, y, DOWN_DIAG_BOARD2) == winningScore ||
			    countCollinears (z, x, y, LEFT_BOARD) == winningScore ||
			    countCollinears (z, x, y, RIGHT_BOARD) == winningScore ||
			    countCollinears (z, x, y, LEFT_TOP_DIAG) == winningScore ||
			    countCollinears (z, x, y, RIGHT_TOP_DIAG) == winningScore ||
			    countCollinears (z, x, y, LEFT_BOT_DIAG) == winningScore ||
			    countCollinears (z, x, y, RIGHT_BOT_DIAG) == winningScore)
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
}


