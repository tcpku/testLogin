package com.example.testlogin;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class WriteFile {
	Document doc;
	public WriteFile(){
		readDoc();
		addDoc();
		doc2XmlFile(doc,"history3.xml");
		
		
	}
	public void readDoc() {
		  try {
	       
	       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	       DocumentBuilder builder = factory.newDocumentBuilder();
	       doc = builder.newDocument();
	      }
	      catch (FactoryConfigurationError fce){
		  System.err.println("Could not create DocumentBuilderFactory");
	      }
	      catch (ParserConfigurationException pce) { 
	          System.out.println("Could not locate a JAXP parser"); 
	      }
	      /*catch (SAXException se) {
	          System.out.println("XML file is not well-formed.");
	      }
	      catch (IOException ioe) { 
	          System.out.println(
	          "Due to an IOException, the parser could not read the XML file"
	          ); 
	      }*/
	    
	    }
	public void addDoc(){
		Element root = doc.getDocumentElement();
		/*Element user = doc.createElement("user");
		Element addr = doc.createElement("address");

		   user.appendChild(addr);
		   root.appendChild(user);
		   
		   user.setAttribute("name", userName);
		   user.setAttribute("password", passWord);
		   addr.setAttribute("address", address);
		   addr.setAttribute("time", time);
		   addr.setAttribute("latitude", latitude);
		   addr.setAttribute("longtitude", longtitude);
		   return doc;*/
		
	}
	public Document removeDoc(Document doc){
		Element root=doc.getDocumentElement();
		doc.removeChild(root);
		Element root1=doc.createElement("recipe");
		doc.appendChild(root1);
		return doc;
	}






public boolean doc2XmlFile(Document document, String filename) {
	   boolean flag = true;
	   try {
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    /** ±àÂë */
	    // transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
	    DOMSource source = new DOMSource(document);
	    StreamResult result = new StreamResult(new File(filename));
	    transformer.transform(source, result);
	   } catch (Exception ex) {
	    flag = false;
	    ex.printStackTrace();
	   }
	   return flag;
	}

}
