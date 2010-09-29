package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Connector {

	private Element portElement;
	
	public Connector(Element portElement) {
		this.portElement = portElement;
	}
	
	public String getPort() {
		Node firstChild = portElement.getFirstChild();
		if (firstChild.getNodeType() == Node.ELEMENT_NODE) {
			// SystemProperty default=""
			return ((Element)firstChild).getAttribute("default");
			
		}
		else {
			return portElement.getTextContent();
		}
	}
	
	public void setPort(String port) {		
		portElement.setTextContent(port);		
	}

}
