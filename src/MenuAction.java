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
import org.cytoscape.task.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.ArrayList;

import org.cytoscape.io.read.CyNetworkReaderManager;
import org.cytoscape.io.util.StreamUtil;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



class CustomImportObserver implements TaskObserver {
    public void allFinished(FinishStatus status) {
    }

    public void taskFinished(ObservableTask task) {
    }
}


public class MenuAction extends AbstractCyAction {
    public CySwingAppAdapter adapter;

	public MenuAction(CySwingAppAdapter adapter){
		super("Custom Extension");
	    this.adapter = adapter;
	    setPreferredMenu("File");

	}




    public void actionPerformed(ActionEvent e) {
    	try{

    		String symbols;
    		ImageIcon icon = null;


    	    try{
                 #provide url to a custom icon image here
    	    	 icon = new ImageIcon(new URL(customAppConfiguration.baseWebserviceURL+"/Images/"));
    	    }
    	    catch(Exception ex){
    	    	JOptionPane.showMessageDialog(null,ex.getStackTrace());
    	    }

    	   CustomUIFactory uiFactory = new CustomUIFactory();

    	   CustomSearchPanel searchPanel = uiFactory.getSearchPanel();
    	   int result = JOptionPane.showConfirmDialog(null, searchPanel, "Network node search",
    	        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);

    	    if (result == JOptionPane.OK_OPTION) {
    	    	CustomSearchInfo searchParameters = searchPanel.getSearchInfo();
    	        symbols = searchParameters.getNodeList();
    	    } else {
    	        return;
    	    }


    	    URL u = new URL(CustomAppConfiguration.baseWebserviceURL+"/api/GrowNetwork?Symbols="+symbols+);

    	    final TaskIterator ti = adapter.get_LoadNetworkURLTaskFactory().loadCyNetworks(u);
    	    CustomImportObserver to = new CustomImportObserver(); //dummy observer - implement if required later
    	    //adapter.getDialogTaskManager().execute(ti, to);
    	    adapter.getTaskManager().execute(ti);

    	    SwingWorker<Void, Void> CustomWorker = new SwingWorker<Void, Void>(){
    	         @Override
    	         protected Void doInBackground() throws Exception {

    	            // long running process goes here


    	            return null;
    	         }
    	      };


    			try{
    				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    			}
    			catch(Exception ex){
                    //pass
    			}


    	      final JDialog dialog = new JDialog(adapter.getCySwingApplication().getJFrame(), "Dialog", Dialog.ModalityType.APPLICATION_MODAL);
		      JProgressBar progressBar = new JProgressBar();
		      progressBar.setIndeterminate(true);
		      JPanel panel = new JPanel();
		      panel.setLayout(new GridBagLayout());
		      GridBagConstraints cs = new GridBagConstraints();

			  cs.gridx = 0;
			  cs.gridy = 0;
			  cs.gridwidth = 1;
			  cs.insets = new Insets(10,10,10,10);
		      panel.add(new JLabel("Importing network data. Please wait..."), cs);

			  cs.fill = GridBagConstraints.HORIZONTAL;
			  cs.gridx = 0;
			  cs.gridy = 1;
			  cs.gridwidth = 1;
			  cs.insets = new Insets(5,10,10,10);
			  //cs.anchor = GridBagConstraints.NORTH;


		      panel.add(progressBar, cs);
		      dialog.add(panel);
		      dialog.pack();
		      dialog.setLocationRelativeTo(adapter.getCySwingApplication().getJFrame());
		      dialog.setVisible(true);
    	      CustomWorker.addPropertyChangeListener(new PropertyChangeListener() {
    	         @Override
    	         public void propertyChange(PropertyChangeEvent evt) {
    	            if (evt.getPropertyName().equals("state")) {
    	               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
    	                  dialog.dispose();
    	               }
    	            }
    	         }
    	      });
    	      CustomWorker.execute();
    	}
    	catch(MalformedURLException muex){
    		JOptionPane.showMessageDialog(null,muex.toString());
    	}
    	catch(Exception ex){
    		JOptionPane.showMessageDialog(null,ex.getStackTrace());
    	}

    }


}
