import java.awt.event.ActionEvent;
import org.cytoscape.app.swing.*;
import org.cytoscape.application.swing.*;
import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.work.*;



public class CustomApp extends AbstractCySwingApp
{
    public CustomApp (CySwingAppAdapter adapter)
    {
        super(adapter);
        CySwingApplication application = adapter.getCySwingApplication();
        application.addAction(new MenuAction(adapter));
    }
}
