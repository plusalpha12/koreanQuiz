package server;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {
	static ArrayList<String> word = new ArrayList<String>();

	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if(nValue == null) 
			return null;
		return nValue.getNodeValue();
	}

	public static boolean xmlParsing(String text) {
		try{
			String url = "https://opendict.korean.go.kr/api/search?certkey_no=258&key=EEBCB032558B51D1EF57A360DD33D85D&"
					+ "target_type=search&sort=popular&q="+ text +"&start=1&advanced=y&pos=1&type1=word&type2=native,chinese";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// root tag 
			Element root = doc.getDocumentElement();

			NodeList nList = root.getElementsByTagName("item");
			
			if(nList.getLength()>0) {
				Node n = nList.item(0);
				if(n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					word.add(getTagValue("definition", e));
				}
				return true;
			}
			return false;

		} catch (Exception e){	
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<String> getdefinition(){
		return word;
	}
}