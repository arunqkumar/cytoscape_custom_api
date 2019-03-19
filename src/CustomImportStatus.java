import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;


public class CustomImportStatus extends JDialog
{
  JPanel panel;
  JLabel label;


  // constructor
  CustomImportStatus()
  {

    setSize( 150, 100 );


    setLayout( new FlowLayout() );       // set the layout manager
    label = new JLabel("Downloading network data. Please wait...!");  // construct a JLabel
    add( label );                        // add the label to the JFrame

  }



}
