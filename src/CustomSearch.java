import java.awt.event.ActionEvent;
import org.cytoscape.app.swing.*;
import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.work.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;


class CustomSearch extends JFrame
{
  JPanel panel;
  JLabel label;
  CySwingAppAdapter adapter;

  // constructor
  CustomSearch(CySwingAppAdapter adapter)
  {
    super( "Custom Search" );                      // invoke the JFrame constructor
    setSize( 150, 100 );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    setLayout( new FlowLayout() );       // set the layout manager
    label = new JLabel("Hello Swing!");  // construct a JLabel
    add( label );                        // add the label to the JFrame
  }



}
