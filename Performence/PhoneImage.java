// Import of API classes

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

 

//A first MIDlet with simple text and a few commands.

public class PhoneImage extends MIDlet 

               implements CommandListener, ItemStateListener {

 

//The commands

private Command exitCommand;

//The display for this MIDlet

private Display display;

// Display items e.g. Form and Image


Form displayForm;
Image image;


public PhoneImage() {

            display = Display.getDisplay(this);
               exitCommand = new Command("Exit", Command.SCREEN, 1);

               try
                 {
                   image = Image.createImage("/img/JavaPowered-8.png");
                    
               }
               catch (java.io.IOException ioExc)
               {
                    ioExc.printStackTrace();
               }
 

 

 }

 

 // Start the MIDlet by creating the Form and 

 // associating the exit command and listener.

  public void startApp() {

                  displayForm = new Form("Image");
                     displayForm. append(
                new ImageItem("Default Layout",
                                image,
                                ImageItem.LAYOUT_DEFAULT,
                                "Image Cannot be shown"));

                    displayForm.addCommand(exitCommand);

                     displayForm.setCommandListener(this);

                     displayForm.setItemStateListener(this);

                     display.setCurrent(displayForm);

  }

  

  public  void itemStateChanged(Item item)

  {

 

  }

  

  // Pause is a no-op when there is no   background

  // activities or record stores to be closed.

  public void pauseApp() { }

 

  // Destroy must cleanup everything not handled 

  // by the garbage collector.

  public void destroyApp (boolean unconditional) { }

 

  // Respond to commands. Here we are only implementing

  // the exit command. In the exit command, cleanup and

  // notify that the MIDlet has been destroyed.

  public void commandAction (

  Command c, Displayable s) {

    if (c == exitCommand) {

            destroyApp(false);

            notifyDestroyed();

    }

   }

 }
