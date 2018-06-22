package server;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {

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
					+ "target_type=search&q="+ text +"&start=1&advanced=n";//&type1=word&pos=1";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// root tag 
			Element root = doc.getDocumentElement();

			NodeList nList = root.getElementsByTagName("item");

			if(nList.getLength()>0) {
				return true;
			}
			return false;

		} catch (Exception e){	
			e.printStackTrace();
			return false;
		}
	}
}