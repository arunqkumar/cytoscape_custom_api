import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

class XmlUtil{
	
	
	private Document doc;
	
	public XmlUtil(){

	}
	
	private String getCharacterDataFromElement(Element e) {
		    Node child = e.getFirstChild();
		    if (child instanceof CharacterData) {
		      CharacterData cd = (CharacterData) child;
		      return cd.getData();
		    }
		    return "";
	  }
	  
	public void loadXmlString(String xmlString) throws SAXException, IOException, ParserConfigurationException{
	  DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	  InputSource is = new InputSource();
	  is.setCharacterStream(new StringReader(xmlString));
	  doc = db.parse(is);
	}
	
	public ArrayList<String> getNodeValues(String nodeName){
		ArrayList<String> list = new ArrayList<String>();
		
		NodeList nodes = doc.getElementsByTagName("Table");

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element element = (Element) nodes.item(i);

	      NodeList name = element.getElementsByTagName(nodeName);
	      Element line = (Element) name.item(0);
	      list.add(getCharacterDataFromElement(line));	
	    }

		
		return list;
	}
	
}
