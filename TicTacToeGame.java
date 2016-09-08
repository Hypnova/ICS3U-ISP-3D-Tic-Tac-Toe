/*
Siavash Samiei
3D Tic Tac Toe Game
01/13/2015
This is the main class of the 3D Tic Tac Toe game, where program flow is controlled, the screens are created.
The first screen seen by the user is the splash screen made by the splashScreen() method and the Animation
thread. The screen after is the main menu, created in mainMenu(). From here the user decides whether they wo
uld like to see the instructions of the game, view the highscores, or player the game. The user will be ret
urned to the main menu at any point that they wish from the other screens. If the user plays a full game they
will alos be returned to the main menu. The user can choose to exit the program by selecting the Exit option
which calls the goodBye() method which closes the program when the user wishes.
*/
import hsa.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
/*
|-----------------------|-----------------------------------------|
|  Variable Dictionary  |                                         |
|----------|------------|-----------------------------------------|
|   Name   |    Type    |               Description               |
|----------|------------|-----------------------------------------|
|     c    | Reference  | c is a reference variable used to refer |
|----------|------------| to the Console class and to access meth |
|                       | ods within it. This makes the program p |
|                       | oint to the Console class when the vari |
|                       | able c is called.                       |
|----------|------------|-----------------------------------------|
|     t    | Reference  | t is a reference variable used to refer |
|----------|------------| to the TicTacToe class and to access me |
|                       | thods within it. This makes the program |
|                       | point to the Tic Tac Toe class when the |
|                       | variable t is called.                   |
|-----------------------|-----------------------------------------|
|  choice  |   String   | choice is the string variable that is g |
|----------|------------| iven a value by the user. Depending on t|
|                       | he value of choice, the program determin|
|                       | es which method to call and therefore w |
|                       | hich screen to show.                    |
|-----------------------|-----------------------------------------|

*/

public class TicTacToeGame
{
    Console c;
    TicTacToe t;
    String choice;
    /*
    image Method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The purpose of this method is to import and image with a specific file name. The method gets the file name thr|
    |--------------| ough the fileName parameter. The method then uses the fileName to import the chosen picture and displays it on|
    |              | the screen.                                                                                                   |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |  Try Block   | The purpose of the try block is to incase the tracker.waitForAll() method and catch an InterruptedException wh|
    |              | en it is thrown.                                                                                                 |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    | fileName |   String   | This variable is a parameter of the ima |
    |----------|------------| ge method and it is used as the file na |
    |                       | me of the image that is being imported. |
    |-----------------------|-----------------------------------------|
    | pciture  |    Image   | This variable is an Image which is giv  |
    |----------|------------| en the value of the image that is being |
    |                       | imported.                               |
    |-----------------------|-----------------------------------------|
    | tracker  | Reference  | tracker is a reference variable used to |
    |----------|------------| refer to the MediaTracker class and to  |
    |                       | acc ess methods within it. This makes t |
    |                       | he program point to the MediaTracker cla|
    |                       | ss when the variable tracker is called. |
    |-----------------------|-----------------------------------------|
    |     e    | Interrupte | Used to catch InterruptedException throw|
    |          | dExceotion | n by the waitForAll().                  |
    |          | var        |                                         |
    |----------|------------|-----------------------------------------|
    */
    private void image (String fileName)
    {
	c.clear ();
	Image picture;
	picture = Toolkit.getDefaultToolkit ().getImage (fileName);
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (picture, 0);
	c.drawImage (picture, 0, 0, null);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	c.drawImage (picture, 0, 0, null);
    }


    /*
    pauseProgram Method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The purpose of this method is to display a string then ask for a character then continue when a character val |
    |--------------| ue is given. This essentialy "pauses" the program, maintaining the current screen until a character value is r|
    |              | ecieved.                                                                                                      |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    |    Msg   |   String   | This variable is a parameter of the pau |
    |----------|------------| seProgram method and is given a string v|
    |                       | alue to be displyed when the method is c|
    |                       | alled.                                  |
    |-----------------------|-----------------------------------------|
    */
    private void pauseProgram (String msg)
    {
	c.setTextColor (Color.white);
	c.println (msg);
	c.getChar ();
    }


    /*
	instrucions method
	|--------------|---------------------------------------------------------------------------------------------------------------|
	|    Purpose   | The purpose of this method is to call the image() method with the fileName "instructions.jpg" which includes t|
	|--------------| he pre-written instrcutions. The method then calls pauseProgram, keeping the screen displayed until the user i|
	|              | nputs a character. It then returns to the main menu.                                                                                                      |
	|--------------|---------------------------------------------------------------------------------------------------------------|
    */
    private void instructions ()
    {
	image ("instructions.jpg");
	c.setCursor (35, 1);
	pauseProgram ("Press any key to return to the main menu...");
    }


    /*
	goodBye method
	|--------------|---------------------------------------------------------------------------------------------------------------|
	|    Purpose   | The purpose of this method is to call the image() method with the fileName "goodbye.jpg" which includes the go|
	|--------------| odbye screen. The method then asks for a character input and closes the program when one was recieved.        |
	|--------------|---------------------------------------------------------------------------------------------------------------|
    */
    private void goodBye ()
    {
	image ("goodbye.jpg");
	c.getChar ();
	c.close ();
    }


    /*
	mainMenu method
	|--------------|---------------------------------------------------------------------------------------------------------------|
	|    Purpose   | The purpose of this method is to call the image() method with the fileName "mainmenu.jpg" which includes the  |
	|--------------| main menu screen. The method then asks for a string input and assigns that string to choice and depending on  |
	|              | the value, the method proceeds to a different screen.                                                               |
	|--------------|---------------------------------------------------------------------------------------------------------------|
    */
    public void mainMenu ()
    {
	image ("mainmenu.jpg");
	while (true)
	{
	    c.setTextColor (Color.black);
	    c.setTextBackgroundColor (new Color (30, 170, 195));
	    c.setCursor (37, 1);
	    c.println ();
	    c.setCursor (37, 50);
	    choice = c.readLine ();
	    if (choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4"))
		break;
	    t.message ("Please enter a valid choice (1,2,3,4).");
	}

    }


    /*
    highScores Method
	|--------------|--------------------------------------------------------------------------------------------------------------------|
	|    Purpose   | The purpose of this method is to call the image method with the fileName "highscores.jpg" to display the high      |
	|--------------| score background image. The program reads the highscores file with the saved highscores in the "highScores.saiv"   |
	|              | file created by this program. The method then error traps the file, as well as the data in the file and if there   |
	|              | no errors, the method displays the highscores in an organized fashion.                                             |
	|--------------|--------------------------------------------------------------------------------------------------------------------|
	|    Loops     | The first loop in this method is used to read the data from the file, then print the data in an organized manner.  |
	|              |                                                                                                                    |
	|              | The second loop only prints the data without reading from file.                                                    |
	|--------------|--------------------------------------------------------------------------------------------------------------------|
	| Conditionals |  The first if statement checks if the file has been opened before, if not it reads from file, otherwise it doesn't.|
	|              |                                                                                                                    |
	|              |  The second if statement in this method is for the purpose of error trapping the file being read from. If the fi   |
	|--------------|  rst line of the file is not the unique header that a file made with this program has, then the program will gi    |
	|              |  ve and error and not continue with the rest of the highScores() method, going back to the main menu.              |
	|              |                                                                                                                    |
	|              |  The third if statement in this method is also for the purpose of error trapping the file being read from. If th   |
	|              |  e second line of the file or later is null, then the highscore file is either invalid or empty, so the program    |
	|              |  gives an error message and returns to the main menu.                                                              |
	|--------------|--------------------------------------------------------------------------------------------------------------------|
	|  Try Block   |  The try blocks in this method are used to catch exceptions created by the file io in the method. These are caused |
	|              |  by the FileReader classes.                                                                                        |
	|--------------|--------------------------------------------------------------------------------------------------------------------|
	|-----------------------|-----------------------------------------|
	|   VariableDictionary  |                                         |
	|----------|------------|-----------------------------------------|
	|   Name   |    Type    |               Description               |
	|----------|------------|-----------------------------------------|
	|     i    |     int    | Used in reading from file/printing to sc|
	|          |            | reen for loops.                         |
	|----------|------------|-----------------------------------------|
	|     e    | Exception  | Used to catch NumberFormatException,    |
	|          | var        | FileNotFoundException and IOExcpetion th|
	|          |            | rown by FileReader().                   |
	|----------|------------|-----------------------------------------|
	*/
    public void highScores ()
    {
	boolean fileRead = false;
	image ("highscores.jpg");
	c.setCursor (4, 1);
	c.setTextColor (new Color (30, 170, 195));
	c.setTextBackgroundColor (Color.black);
	if (!fileRead)
	{
	    try
	    {
		BufferedReader br = new BufferedReader (new FileReader ("highScores.siav"));
		if (br.readLine ().equals (t.HEADER))
		{
		    for (int i = 0 ; i < t.GAMES_LIMIT ; i++)
		    {
			t.names [i] = br.readLine ();
			if (t.names [0] == null)
			{
			    t.message ("There are no highscores to display.");
			    return;
			}
			t.outcome [i] = br.readLine ();
			t.moves [i] = Integer.parseInt (br.readLine ());
			printHighScore (i);
			fileRead = true;
		    }
		}
		else
		{
		    t.message ("High Score file is invalid.");
		    return;
		}
	    }
	    catch (FileNotFoundException e)
	    {
		t.message ("There are no highscores to display.");
		return;
	    }
	    catch (NumberFormatException e)
	    {
	    }
	    catch (IOException e)
	    {
	    }
	}
	else
	{
	    for (int i = 0 ; i < t.GAMES_LIMIT ; i++)
	    {
		printHighScore (i);
	    }
	}
	pauseProgram ("Press any key to go back to the main menu...");
    }


    /*
    printHighScore Method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The purpose of this method is to print the highscores when it is called with an integer value and it will prin|
    |--------------| the values stored at the different high score arrays at location loop.                                        |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    |   loop   |    int     | The loop integer is a parameter of the  |
    |----------|------------| printHighScor method. This variable dete|
    |                       | rmines the position in the array of the |
    |                       | data that will be displayed.            |
    |-----------------------|-----------------------------------------|
    */
    private void printHighScore (int loop)
    {
	c.println ((loop + 1) + " Names:  " + t.names [loop]);
	c.println ("  Winner: " + t.outcome [loop]);
	c.println ("  Moves:  " + t.moves [loop]);
    }


    /*
    twoPlayerGame method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The purpose of this method is to call the image with the fileName "gameBoard.jpg". It then calls the getPlayer|
    |--------------| Names method from the TicTacToe class. Then, if the playerName input is valid, it then calls the fullGame() me|
    |              | thod fromt he TicTacToe class.                                                                                |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Loops     | The purpose of the loop is to continue asking for player name until a valid input is given (Error trapping).  |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    | Conditionals | The if statement in this method is to make sure that player1Name and player2Name are not null before fullGame |
    |              | is called.                                                                                                    |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    */
    public void twoPlayerGame ()
    {
	image ("gameBoard.png");
	while (true)
	{
	    if (t.getPlayerNames ())
		return;
	    if (t.player1Name != null && t.player2Name != null)
	    {
		break;
	    }
	}
	t.fullGame ();
    }


    /*
    splashScreen method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The purpose of this method is to call the image with the fileName "splashscreen.jpg". The program then initia |
    |--------------| lizes the Animation thread and starts the thread, displaying the animation until it is completed, at which po |
    |              | int the program continues to the main menu.                                                                   |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |   Try block  | The try block in this method catches the Exception thrown by Thread.sleep().                                  |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    |     e    | Exception  | Used to catch the general exception thro|
    |          | var        | wn by Thread.sleep().                   |
    |----------|------------|-----------------------------------------|
    */
    public void splashScreen ()
    {
	try
	{
	    image ("splashscreen.jpg");
	    Animation a = new Animation (c);
	    a.start ();
	    Thread.sleep (4000);
	}
	catch (Exception e)
	{
	}

    }


    /*
	constructor
	|--------------|---------------------------------------------------------------------------------------------------------------------|
	|    Purpose   | The purpose of this method is to construct an object of the Console class by instantiating c with a Console object. |
	|--------------| This method also constructs an object of the TicTacToe class by intantiating t with the TicTacToe object.           |                                                                   |
	|--------------|---------------------------------------------------------------------------------------------------------------------|

    */
    public TicTacToeGame ()
    {
	c = new Console (40, 100, "Tic Tac Toe");
	t = new TicTacToe (c);
    }


    /*
	main Method
	|--------------|-----------------------------------------------------------------------------------------------------------------------------|
	|    Purpose   |The purpose of this method is to call the methods in this program and give them program flow, executing the program in order.|
	|--------------|-----------------------------------------------------------------------------------------------------------------------------|
	|              |                                                                                                                             |
	|--------------|-----------------------------------------------------------------------------------------------------------------------------|
	| Conditionals |The purpose of the if statment with the else if and else is to decide what method to run based on the user input of choice,  |
	|              |which tells the program what the user wants to do.                                                                           |
	|--------------|-----------------------------------------------------------------------------------------------------------------------------|
	|              |                                                                                                                             |
	|--------------|-----------------------------------------------------------------------------------------------------------------------------|
	|     Loops    |The purpose of the while loop is to call methods based on the input for option until the option is 4 which is "Exit" to break|
	|              |the loop and runs the goodBye() method to close the program.                                                                 |                                                           |
	|--------------|-----------------------------------------------------------------------------------------------------------------------------|
	|-----------------------|-------------------------------------------|
	|  Variable Dictionary  |                                           |
	|----------|------------|-------------------------------------------|
	|   Name   |    Type    |               Description                 |
	|----------|------------|-------------------------------------------|
	|    ttt   | Reference  | ttt is a reference variable used to refer |
	|----------|------------| to the TicTacToeGame class and to access  |
	|                       | meth ods within it. This makes the program|
	|                       | p oint to the TicTacToeGame class when the|
	|                       | vari able c is called.                    |
	|----------|------------|-------------------------------------------|

    */
    public static void main (String[] args)
    {
	TicTacToeGame ttt = new TicTacToeGame ();
	ttt.splashScreen ();
	while (true)
	{
	    ttt.mainMenu ();
	    if (ttt.choice.equals ("1"))
		ttt.twoPlayerGame ();
	    else if (ttt.choice.equals ("2"))
		ttt.instructions ();
	    else if (ttt.choice.equals ("3"))
		ttt.highScores ();
	    else if (ttt.choice.equals ("4"))
		break;
	}
	ttt.goodBye ();
    }
}


