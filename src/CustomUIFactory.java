import java.awt.GridLayout;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.util.*;

class CustomSearchInfo{
	private String nodeList;
	private String species;

	public void setNodeList(String nodeList){
		this.nodeList = nodeList;
	}

	public String getNodeList(){
		return nodeList;
	}

	public void setSpecies(String species){
		this.species = species;
	}

	public String getSpecies(){
		return species;
	}
}

class CustomSearchPanel extends JPanel{
	private JLabel lblNodeList;
	private JTextArea taNodeList;
	private JLabel lblSpecies;
	private JComboBox<String> cbSpecies;
	private JLabel lblEdgeOptions;
	private JCheckBox chkInteractionTypes;
	private JCheckBox chkEvidenceTypes;

	public CustomSearchPanel(){
		super(new GridBagLayout());
		constructUI();
	}

	private void constructUI(){

		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex){
			//Ignore exception. Let JRE use its default look and feel.
		}

		GridBagConstraints cs = new GridBagConstraints();

	    cs.fill = GridBagConstraints.HORIZONTAL;

	    JPanel parentPanel = new JPanel();
	    parentPanel.setLayout(new GridBagLayout());

	    JPanel nodePanel = new JPanel();
	    nodePanel.setLayout(new GridBagLayout());

	    lblNodeList = new JLabel("Node list: ");
	    cs.gridx = 0;
	    cs.gridy = 0;
	    cs.gridwidth = 1;
	    cs.insets = new Insets(10,5,0,5);
	    cs.anchor = GridBagConstraints.NORTH;
	    nodePanel.add(lblNodeList, cs);

	    taNodeList = new JTextArea(5, 20);
	    cs.gridx = 1;
	    cs.gridy = 0;
	    cs.gridwidth = 2;
	    cs.insets = new Insets(10,5,0,5);

	    nodePanel.add(taNodeList, cs);

	    lblSpecies = new JLabel("Species: ");
	    cs.gridx = 0;
	    cs.gridy = 1;
	    cs.gridwidth = 1;
	    cs.insets = new Insets(10,5,10,5);
	    nodePanel.add(lblSpecies, cs);


	    loadSpecies();
	    cs.gridx = 1;
	    cs.gridy = 1;
	    cs.gridwidth = 2;
	    cs.insets = new Insets(10,5,10,5);
	    nodePanel.add(cbSpecies, cs);
	    //panel.setBorder(new LineBorder(Color.GRAY));

	    cs.gridx = 0;
	    cs.gridy = 0;
	    cs.gridwidth = 1;

	    nodePanel.setBorder(BorderFactory.createTitledBorder("Starting node list"));
	    parentPanel.add(nodePanel, cs);

	    JPanel edgeOptionsPanel = new JPanel();

	    lblEdgeOptions = new JLabel("Restrict edge search by");
	    edgeOptionsPanel.setLayout(new GridBagLayout());
	    cs.gridx = 0;
	    cs.gridy = 0;
	    cs.weighty = 1;
	    cs.weightx = 1;
	    cs.insets = new Insets(5,5,0,0);

	    cs.gridwidth = 1;
	    edgeOptionsPanel.add(lblEdgeOptions, cs);

	    chkInteractionTypes = new JCheckBox("Interaction types(e.g Regulation)");
	    cs.gridx = 0;
	    cs.gridy = 1;
	    cs.insets = new Insets(5,5,0,0);
	    //cs.anchor = GridBagConstraints.FIRST_LINE_START;
	    cs.gridwidth = 1;
	    edgeOptionsPanel.add(chkInteractionTypes, cs);

	    chkEvidenceTypes = new JCheckBox("Evidence types(e.g Experiment)");
	    cs.gridx = 0;
	    cs.gridy = 2;
	    cs.insets = new Insets(1,5,0,0);
	    //cs.anchor = GridBagConstraints.WEST;
	    cs.gridwidth = 1;
	    edgeOptionsPanel.add(chkEvidenceTypes, cs);


	    cs.gridx = 0;
	    cs.gridy = 1;
	    cs.gridwidth = 1;
	    edgeOptionsPanel.setBorder(BorderFactory.createTitledBorder("Edge options"));
	    parentPanel.add(edgeOptionsPanel, cs);

	    JPanel dataSourcePanel = new JPanel();
	    dataSourcePanel.setLayout(new GridBagLayout());
        //tree.setRootVisible(false);
        JTree dataSourceTree = getTree("DataSource");
        CheckTreeManager dataSourceTreeManager = new CheckTreeManager(dataSourceTree, false, true);
        JScrollPane dataSourcePane = new JScrollPane(dataSourceTree);
        dataSourcePane.setPreferredSize(new Dimension(200, 300));
        dataSourcePanel.add(dataSourcePane);


	    cs.gridx = 1;
	    cs.gridy = 0;
	    cs.gridwidth = 1;
	    cs.gridheight = 2;
	    dataSourcePanel.setBorder(BorderFactory.createTitledBorder("Data source"));
	    parentPanel.add(dataSourcePanel, cs);


	    JPanel moleculeTypesPanel = new JPanel();
	    moleculeTypesPanel.setLayout(new GridBagLayout());
	    //create the root node
        JTree moleculeTypeTree = getTree("MoleculeType");
        CheckTreeManager moleculeTreeManager = new CheckTreeManager(moleculeTypeTree, false, true);
        JScrollPane moleculeTypePane = new JScrollPane(moleculeTypeTree);
        moleculeTypePane.setPreferredSize(new Dimension(200, 300));


        moleculeTypesPanel.add(moleculeTypePane);


	    cs.gridx = 2;
	    cs.gridy = 0;
	    cs.gridwidth = 1;
	    cs.gridheight = 2;
	    moleculeTypesPanel.setBorder(BorderFactory.createTitledBorder("Molecule type"));
	    parentPanel.add(moleculeTypesPanel, cs);


	    JPanel interactionTypesPanel = new JPanel();
	    interactionTypesPanel.setLayout(new GridBagLayout());
        //tree.setRootVisible(false);
        JTree interactionTypeTree = getTree("InteractionType");
        CheckTreeManager interactionTypeTreeManager = new CheckTreeManager(interactionTypeTree, false, true);
        JScrollPane interactionTypePane = new JScrollPane(interactionTypeTree);
        interactionTypePane.setPreferredSize(new Dimension(200, 300));
        interactionTypesPanel.add(interactionTypePane);


	    cs.gridx = 3;
	    cs.gridy = 0;
	    cs.gridwidth = 1;
	    cs.gridheight = 2;
	    interactionTypesPanel.setBorder(BorderFactory.createTitledBorder("Interaction type"));
	    parentPanel.add(interactionTypesPanel, cs);


	    JPanel evidenceTypesPanel = new JPanel();
	    evidenceTypesPanel.setLayout(new GridBagLayout());
        //tree.setRootVisible(false);
        JTree evidenceTypeTree = getTree("EvidenceType");
        CheckTreeManager treeManager = new CheckTreeManager(evidenceTypeTree, false, true);
        JScrollPane evidenceTypePane = new JScrollPane(evidenceTypeTree);
        evidenceTypePane.setPreferredSize(new Dimension(200, 300));
        evidenceTypesPanel.add(evidenceTypePane);


	    cs.gridx = 4;
	    cs.gridy = 0;
	    cs.gridwidth = 1;
	    cs.gridheight = 2;
	    evidenceTypesPanel.setBorder(BorderFactory.createTitledBorder("Evidence type"));
	    parentPanel.add(evidenceTypesPanel, cs);


		this.add(parentPanel);
	}
	public CustomSearchInfo getSearchInfo(){
		CustomSearchInfo searchParameters = new CustomSearchInfo();
		searchParameters.setNodeList(taNodeList.getText());
		searchParameters.setSpecies(cbSpecies.getSelectedItem().toString());
		return searchParameters;
	}

	private JTree getTree(String ontologyType){

		XmlUtil xmlUtil = new XmlUtil();
		String xmlString;

	    //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Any");

		if(ontologyType.equals("MoleculeType")){
			try{
			xmlString =  RESTUtil.httpGet(CustomAppConfiguration.baseWebserviceURL+"/api/GetMoleculeTypeOntology");
			xmlUtil.loadXmlString(xmlString);
			ArrayList<String> interactorTypeList = xmlUtil.getNodeValues("InteractorType");
			ArrayList<String> interactorTypeParentList = xmlUtil.getNodeValues("InteractorTypeParent");

			for(int i=0;i<interactorTypeParentList.size();++i){
				if(interactorTypeParentList.get(i).equals("'NULL'")){
					DefaultMutableTreeNode n = new DefaultMutableTreeNode(interactorTypeList.get(i));
					root.add(n);
				}
			}
			}
			catch(Exception ex){
				System.out.println(ex.getStackTrace());
			}
		}


		if(ontologyType.equals("EvidenceType")){
			try{
			xmlString =  RESTUtil.httpGet(CustomAppConfiguration.baseWebserviceURL+"/api/GetEvidenceTypeOntology");
			xmlUtil.loadXmlString(xmlString);

			ArrayList<String> evidenceTypeList = xmlUtil.getNodeValues("EvidenceType");
			ArrayList<String> evidenceTypeParentList = xmlUtil.getNodeValues("EvidenceTypeParent");

			for(int i=0;i<evidenceTypeParentList.size();++i){
				if(evidenceTypeParentList.get(i).equals("'NULL'")){
					DefaultMutableTreeNode n = new DefaultMutableTreeNode(evidenceTypeList.get(i));
					root.add(n);
				}
			}

			for(int i=0;i<evidenceTypeParentList.size();++i){
				int childCount = root.getChildCount();
				if(!evidenceTypeParentList.get(i).equals("'NULL'")){
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(evidenceTypeList.get(i));

					Enumeration en = root.depthFirstEnumeration();
					while (en.hasMoreElements()) {

					  DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
					  if(node.toString().equals(evidenceTypeParentList.get(i))){
						  node.add(newNode);
					  }
					}

				}
			}


			}
			catch(Exception ex){
				System.out.println(ex.getStackTrace());
			}
		}

		if(ontologyType.equals("DataSource")){
			try{
			xmlString =  RESTUtil.httpGet(CustomAppConfiguration.baseWebserviceURL+"/api/GetDataSourceOntology");
			xmlUtil.loadXmlString(xmlString);

			ArrayList<String> dataSourceList = xmlUtil.getNodeValues("DataSource");
			ArrayList<String> dataSourceParentList = xmlUtil.getNodeValues("DataSourceParent");

			String previousParent, currentParent;
			previousParent = null;
			DefaultMutableTreeNode parentNode = null;

			for(int i=0;i<dataSourceParentList.size();++i){
				currentParent = dataSourceParentList.get(i);
				if(!currentParent.equals(previousParent)){
					parentNode = new DefaultMutableTreeNode(currentParent);
					root.add(parentNode);
				}

				parentNode.add(new DefaultMutableTreeNode(dataSourceList.get(i)));

				previousParent = currentParent;
			}


			}
			catch(Exception ex){
				System.out.println(ex.getStackTrace());
			}
		}

		if(ontologyType.equals("InteractionType")){
			try{
			xmlString =  RESTUtil.httpGet(CustomAppConfiguration.baseWebserviceURL+"/api/GetInteractionTypeOntology");
			xmlUtil.loadXmlString(xmlString);

			ArrayList<String> interactionTypeList = xmlUtil.getNodeValues("InteractionType");
			ArrayList<String> interactionTypeParentList = xmlUtil.getNodeValues("InteractionTypeParent");

			DefaultMutableTreeNode parentNode = null;
			ArrayList<String> addedParents = new ArrayList<String>();
			String parent;
			for(int i=0;i<interactionTypeParentList.size();++i){
				parent = interactionTypeParentList.get(i);
				if(!addedParents.contains(parent)){
					parentNode = new DefaultMutableTreeNode(parent);
					root.add(parentNode);
					addedParents.add(parent);
				}

				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(interactionTypeList.get(i));
				Enumeration en = root.depthFirstEnumeration();
				while (en.hasMoreElements()) {

				  DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
				  if(node.toString().equals(parent)){
					  node.add(newNode);
				  }
				}

			}


			}
			catch(Exception ex){
				System.out.println(ex.getStackTrace());
			}
		}




        JTree tree = new JTree(root);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

		return tree;
	}

	private void loadSpecies(){

		;
		try{
			String speciesXmlString = RESTUtil.httpGet(CustomAppConfiguration.baseWebserviceURL+"/api/GetSpecies");
			//System.out.println(speciesXml);
			XmlUtil xmlUtil = new XmlUtil();
			xmlUtil.loadXmlString(speciesXmlString);
			ArrayList<String> speciesList = xmlUtil.getNodeValues("simpleName");
			String[] species = {};
			species = speciesList.toArray(species);
			cbSpecies = new JComboBox<>(species);
		}
		catch(Exception ex){
			System.out.println(ex.toString());
			String[] species = {"Human","Mouse","Dog"};
		    cbSpecies = new JComboBox<>(species);
		}


	}
}


public class CustomUIFactory{

	public CustomSearchPanel getSearchPanel(){
		return new CustomSearchPanel();
	}
}
