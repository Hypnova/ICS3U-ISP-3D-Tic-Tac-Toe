/*
Siavash Samiei
3D Tic Tac Toe Splash Screen Animation
01/13/2015
This is a thread used in the 3D Tic Tac Toe program which creates the original splash screen animation at the start of the program.
This thread contains an animation of 3 X shapes made of 2 lines which are created by the drawLine() method then called in the drawX
method, 2 O shapes made of arcs in the drawO method, and another line going over the 3 X shapes to signify the winning of the X side
created by the drawLine method again. There is only one screen for this thread, which is the scene of the drawing of the X and O sh-
apes and the final line. There is no program flow in this class, as the animation will play at the start of the program and continue
until it has finished.
*/
import java.awt.*;
import hsa.Console;
/*
|-----------------------|-----------------------------------------|
|  Variable Dictionary  |                                         |
|----------|------------|-----------------------------------------|
|   Name   |    Type    |               Description               |
|----------|------------|-----------------------------------------|
|     c    | Reference  | c is a reference variable used to refer |
|----------|------------| to the Console class and to access meth |
			| ods within it. This makes the program p |
			| oint to the Console class when the vari |
			| able c is c alled.                      |
			|-----------------------------------------|

*/


public class Animation extends Thread
{
    private Console c;
    /*
    drawLine Method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The Purpose of this method is to draw 20 lines using two for loops and two drawLine commands. This will first |
    |--------------| a normal diagonal line starting at (xPos,yPos) to (xPos+loopLength, yPos+loopLength), this line is then drawn |
    |              | 9 more times to create a thick line. The direction of the line is dependant on the direction variable. If the |
    |              | boolean is true, then the line goes diagonally from left to right, otherwise it goes right to left. When this |
    |              | method is called twice in the drawO method with different parameters, it creates an X shape. Using a delay cr |
    |              | eated by Thread.sleep(), the drawing of the lines is delayed, making it animated.                             |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Loops     | The first for loop in this method repeats 10 times increasing the loop variable by 10 each time. This loop is |
    |--------------| used to draw the two lines 10 times while al so reducing the xPos by 1 each time and increasing the yPos by 1 |
    |              | each time. This will make the draw the lines diagonally under eachother, making the image of a thick line.    |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    | Conditionals | The purpose of the if structure in this method is to use the boolean variable direction to check which direct |
    |--------------| ion to draw the line. It does this by checking whether or not direction is true. If it is, it calls the draw l|
    |              | ine method written with the parameters (xPos - j, yPos + j, (xPos + length) - j, (yPos + length) + j), which w|
    |              | ill draw the line from left to right by reducing the xPos variable, which determines the starting x position o|
    |              | f the line, by the length variable (determines the designated length) and increasing the yPos, which is the s |
    |              | tarting y position of the line, by length. If direction is false, it calls the draw line command with the par |
    |              | ameters (xPos + j, yPos + j, (xPos - length) + j, (yPos + length) + j), which will increase the xPos variable |
    |              | and reduce the yPos variable by the length, drawing the line from right to left.                              |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |  Try Block   | The purpose of the try block is to incase the Thread.sleep() method and catch any exception that occurs when  |
    |              | it is thrown.                                                                                                 |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    |   xPos   |     int    | This variable is a parameter in the dra |
    |----------|------------| wLine method and it is used as the star |
    |                       | ting x position of the first line that  |
    |                       | is drawn.                               |
    |-----------------------|-----------------------------------------|
    |   yPos   |     int    | This variable is a parameter in the dra |
    |----------|------------| wLine method and it is used as the star |
    |                       | ting y position of the first line that  |
    |                       | is drawn.                               |
    |-----------------------|-----------------------------------------|
    |  length  |     int    | This variable is a parameter in the dra |
    |----------|------------| wLine method and it is used to determin |
    |                       | e the designated length of the lines th |
    |                       | at ar drawn by the method.              |
    |-----------------------|-----------------------------------------|
    |direction |  boolean   | This variable is a parameter in the dra |
    |----------|------------| wLine method and it is used to determin |
    |                       | e whether or not the xPos and yPos will |
    |                       | be increased or descreased. Depending o |
    |                       | n the value of direction, the xPos and  |
    |                       | yPos will be incremented to make the li |
    |                       | nes in a certain direction.             |
    |-----------------------|-----------------------------------------|
    |   j      |    int     | Used in a for loop for drawing and anima|
    |          |            | tion.                                   |
    |-----------------------|-----------------------------------------|
    */
    public void drawLine (int xPos, int yPos, int length, boolean direction)
    {
	for (int j = 0 ; j < 10 ; j++)
	{
	    if (direction)
	    {
		c.drawLine (xPos - j, yPos + j, (xPos + length) - j, (yPos + length) + j);
	    }
	    else
	    {
		c.drawLine (xPos + j, yPos + j, (xPos - length) + j, (yPos + length) + j);
	    }
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    drawX Method
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Purpose   | The Purpose of this method is to call the drawLine method twice with different parameters to create an x shape|
    |--------------|---------------------------------------------------------------------------------------------------------------|

    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    |   xPos   |     int    | This variable is a parameter in the dra |
    |----------|------------| wX method and it is used as the startin |
    |                       | x position of the first line that is dra|
    |                       | wn by the first drawLine method called. | 
    |-----------------------|-----------------------------------------|
    |   yPos   |     int    | This variable is a parameter in the dra |
    |----------|------------| wX method and it is used as the starting|
    |                       | y position of the first line that is dra|
    |                       | n by the first drawLine method called.  |
    |-----------------------|-----------------------------------------|
    |  xPos2   |     int    |This variable is a parameter in the dra  |
    |----------|------------| wX method and it is used as the starting|
    |                       | x position of the first line that is dra|
    |                       | wn by the second drawLine method called.|
    |-----------------------|-----------------------------------------|
    |   yPos2  |     int    | This variable is a parameter in the dra |
    |----------|------------| wX method and it is used as the starting|
    |                       | y position of the first line that is dra|
    |                       | wn by the second drawLine method called.|
    |-----------------------|-----------------------------------------|

    */
    public void drawX (int xPos, int yPos, int xPos2, int yPos2)
    {
	c.setColor (new Color (45, 100, 180));
	drawLine (xPos, yPos, 75, true);
	drawLine (xPos2, yPos2, 75, false);
    }

    /*
    drawO Method
    |--------------|-------------------------------------------------------------------------------------------------------------- |
    |    Purpose   | The Purpose of this method is draw 10 arcs that slowly go around 360 degrees to create the image of an O shap |
    |--------------| e. The method draws one arc first, then 9 more with increasing x and y positions and deacreasing lengths to c |
    |              | reate a thic arc. The angles of these arcs start at 0 and go up to 360, creating a full circle and, in the end|
    |              | an 0 shape.                                                                                                   |
    |--------------|---------------------------------------------------------------------------------------------------------------|
    |    Loops     | The purpose of the first loop in the method is to draw the arc 10 times, incrementing the starting x, starting|
    |              | y psoitions and the length of the arc each time.                                                              |
    |              |                                                                                                               |
    |              | The second loop is used to increment the angles of the arc from 1-360, increasing by 1 each time. This makes t|
    |              | he drawing look animted when delayed.                                                                         |
    |--------------|---------------------------------------------------------------------------------------------------------------|

    |-----------------------|-----------------------------------------|
    |   VariableDictionary  |                                         |
    |----------|------------|-----------------------------------------|
    |   Name   |    Type    |               Description               |
    |----------|------------|-----------------------------------------|
    |   xPos   |     int    | This variable is a parameter in the dra |
    |----------|------------| wO method and it is used as the starting|
    |                       | x position of the first arc that is draw|
    |                       | n by the drawArc().                     | 
    |-----------------------|-----------------------------------------|
    |   yPos   |     int    | This variable is a parameter in the dra |
    |----------|------------| wO method and it is used as the starting|
    |                       | y position of the first arc that is draw|
    |                       | n by the drawArc().                     |
    |-----------------------|-----------------------------------------|
    |-----------------------|-----------------------------------------|
    |   j      |    int     | Used in a for loop for drawing and anima|
    |          |            | tion.                                   |
    |-----------------------|-----------------------------------------|
    |-----------------------|-----------------------------------------|
    |   i      |    int     | Used in a for loop for drawing and anima|
    |          |            | tion.                                   |
    |-----------------------|-----------------------------------------|

    */
    public void drawO (int xPos, int yPos)
    {
	c.setColor (new Color (180, 250, 255));
	for (int j = 0 ; j < 10 ; j++)
	{
	    for (int i = 0 ; i < 360 ; i++)
	    {
		c.drawArc (xPos + j, yPos + j, 90 - j * 2, 90 - j * 2, i, i);
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
    |----------|------------| class into the Animation class to allow it access to methods from  |
    |                       | the console class.                                                 |  
    |-----------------------|--------------------------------------------------------------------|
    */
    public Animation (Console con)
    {
	c = con;
    }

    /*
    drawO Method
    |--------------|-------------------------------------------------------------------------------------------------------------- |
    |    Purpose   | The Purpose of this method is call the drawX method 3 times, the drawO method twice, and the drawLine method  |
    |--------------| once. This is the method which calls all of these in order with specified parameters, drawing and animating t |
    |              | in the correct positions in the correct order.                                                                |
    |--------------|---------------------------------------------------------------------------------------------------------------|

    */
    public void run ()
    {
	drawX (220, 300, 290, 300);
	drawO (220, 470);
	drawX (380, 470, 450, 470);
	drawO (380, 640);
	drawX (540, 640, 610, 640);
	c.setColor (new Color (240, 240, 240));
	drawLine (200, 275, 400, true);
    }
}
