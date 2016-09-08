// The "MiscellaneousMethods" class.
import java.awt.*;
import hsa.*;

public class NonLogicMethods
{
    Console c;           // The output console

    private void title ()
    {
	c.clear ();
	c.setColor (new Color (180, 0, 0));
	c.setFont (new Font ("Old English Text MT", 1, 50));
	c.drawString ("3D Tic Tac Toe", 50, 70);
    }


    private void splashScreen ()
    {
	Image picture;
	int picWidth, picHeight;
	final String PICTURE_PATH = "splashScreen.jpg";
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	picture = Toolkit.getDefaultToolkit ().getImage (PICTURE_PATH);
	MediaTracker tracker = new MediaTracker (new Frame ());

	tracker.addImage (picture, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}

	if (tracker.isErrorAny ())
	{
	    c.println ("Couldn't load " + PICTURE_PATH);
	    return;
	}
	picWidth = picture.getWidth (null);
	picHeight = picture.getHeight (null);
	c.drawImage (picture, 75, 90, null);
	c.setColor (new Color (180, 0, 0));
	c.setFont (new Font ("Old English Text MT", 1, 70));
	c.drawString ("3D Tic Tac Toe", 50, 70);

    }


    private void pauseProgram (String msg)
    {
	c.println (msg);
	if (c.getChar () == ('b'))
	    return;
	else
	{
	}

    }


    private void goodBye ()
    {
	pauseProgram ("Thank you for playing the 3D Tic Tac Toe game, hope you enjoyed it. Please press b to go back to the main menu or any other key to exit the program.");
	c.close ();
    }


    public NonLogicMethods ()
    {
	c = new Console ();
    }


    public static void main (String[] args)
    {
	NonLogicMethods nlm = new NonLogicMethods ();
	nlm.splashScreen ();

	// Place your program here.  'c' is the output console
    } // main method
} // MiscellaneousMethods class


