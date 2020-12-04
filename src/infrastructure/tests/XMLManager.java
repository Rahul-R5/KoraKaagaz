package infrastructure.tests;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;


public class XMLManager {
	
	private Document doc;
	private HashMap <String, String> originalMap;
	private HashMap <String, String> desiredMap;
	
	public XMLManager(String xmlFilePath, HashMap <String, String> desiredMap) {		
		this.doc = getLoggerXML(xmlFilePath);
		this.originalMap = getOriginalMap(doc);
		this.desiredMap  = desiredMap;
		updateLoggerXML(doc, this.desiredMap);
	}
	public static Document getLoggerXML(String filePath) {
	        File xmlFile = new File(filePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        Document doc = null;
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();
	            doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            
	        } catch (SAXException | ParserConfigurationException | IOException e1) {
	            e1.printStackTrace();
	        }
	        return doc;
	        
	    }
		public static void writeLoggerXML(Document doc, String filePath) {
	        
	        try {
	            //write the updated document to file or console
	            doc.getDocumentElement().normalize();
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(filePath));
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.transform(source, result);
	            
	        } catch (TransformerException e1) {
	            e1.printStackTrace();
	        }
	        
	    }
		
		
	    public static void updateLoggerXML(Document doc, HashMap<String, String> dict) {
	    	
	    	Element testModeNode = (Element) doc.getElementsByTagName("testMode").item(0);
	        String nodeName = testModeNode.getTagName();
	        String nodeValue = dict.get(nodeName);
	        testModeNode.setTextContent(nodeValue);
	        
	        Element loggerOptions = (Element) doc.getElementsByTagName("loggerOptions").item(0);
	        NodeList loggerOption = loggerOptions.getElementsByTagName("loggerOption");
	        for(int i=0; i<loggerOption.getLength();i++){
	            Element lo = (Element) loggerOption.item(i);
	            nodeName = lo.getAttributes().getNamedItem("LoggerName").getNodeValue();
	            nodeValue = dict.get(nodeName);
	            lo.setTextContent(nodeValue);
	            
	        }
	        
	        Element logLevels = (Element) doc.getElementsByTagName("logLevels").item(0);
	        NodeList logLevel = logLevels.getElementsByTagName("logLevel");
	        for(int i=0; i<logLevel.getLength();i++){
	            Element ll = (Element) logLevel.item(i);
	            nodeName = ll.getAttributes().getNamedItem("level").getNodeValue();
	            nodeValue = dict.get(nodeName);
	            ll.setTextContent(nodeValue);
	            
	        }
	        writeLoggerXML(doc, "resources/infrastructure_logger.xml");
	    }
	    public void resetLoggerXML() {
	    	
	    	Document doc = this.doc;
	    	HashMap <String, String> dict = this.originalMap; 
	        Element testModeNode = (Element) doc.getElementsByTagName("testMode").item(0);
	        String nodeName = testModeNode.getTagName();
	        String nodeValue = dict.get(nodeName);
	        testModeNode.setTextContent(nodeValue);
	        
	        Element loggerOptions = (Element) doc.getElementsByTagName("loggerOptions").item(0);
	        NodeList loggerOption = loggerOptions.getElementsByTagName("loggerOption");
	        for(int i=0; i<loggerOption.getLength();i++){
	            Element lo = (Element) loggerOption.item(i);
	            nodeName = lo.getAttributes().getNamedItem("LoggerName").getNodeValue();
	            nodeValue = dict.get(nodeName);
	            lo.setTextContent(nodeValue);
	            
	        }
	        
	        Element logLevels = (Element) doc.getElementsByTagName("logLevels").item(0);
	        NodeList logLevel = logLevels.getElementsByTagName("logLevel");
	        for(int i=0; i<logLevel.getLength();i++){
	            Element ll = (Element) logLevel.item(i);
	            nodeName = ll.getAttributes().getNamedItem("level").getNodeValue();
	            nodeValue = dict.get(nodeName);
	            ll.setTextContent(nodeValue);
	            
	        }
	        writeLoggerXML(doc, "resources/infrastructure_logger.xml");
	    }
	    public static HashMap <String, String> getOriginalMap(Document doc) {
	    	HashMap <String, String> dict = new HashMap<String, String>();
	    	Element testModeNode = (Element) doc.getElementsByTagName("testMode").item(0);
	        String nodeName = testModeNode.getTagName();
	        String nodeValue = testModeNode.getFirstChild().getNodeValue();
	        dict.put(nodeName, nodeValue);
	        
	        Element loggerOptions = (Element) doc.getElementsByTagName("loggerOptions").item(0);
	        NodeList loggerOption = loggerOptions.getElementsByTagName("loggerOption");
	        for(int i=0; i<loggerOption.getLength();i++){
	            Element lo = (Element) loggerOption.item(i);
	            nodeName = lo.getAttributes().getNamedItem("LoggerName").getNodeValue();
	            nodeValue = lo.getFirstChild().getNodeValue();
	            dict.put(nodeName, nodeValue);
	            
	        }
	        
	        Element logLevels = (Element) doc.getElementsByTagName("logLevels").item(0);
	        NodeList logLevel = logLevels.getElementsByTagName("logLevel");
	        for(int i=0; i<logLevel.getLength();i++){
	            Element ll = (Element) logLevel.item(i);
	            nodeName = ll.getAttributes().getNamedItem("level").getNodeValue();
	            nodeValue = ll.getFirstChild().getNodeValue();
	            dict.put(nodeName, nodeValue);
	            
	        }
	        return dict;
	    }
	    

}