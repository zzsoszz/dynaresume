package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.webapp;

import org.eclipse.jst.server.jetty.core.internal.xml.XMLElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WebAppContext extends XMLElement {

	public void setContextPath(String contextPath) {
		Element element = super.findElement("Set", "contextPath");
		element.setTextContent("/" + contextPath);

	}

	public void setWar(String war) {
		Element element = super.findElement("Set", "war");
		element.setTextContent(war);
		Document document = element.getOwnerDocument();
		Element systemProperty = document.createElement("SystemProperty");
		systemProperty.setAttribute("name", "jetty.home");
		systemProperty.setAttribute("default", ".");
		Node firstChild = element.getFirstChild();
		element.insertBefore(systemProperty, firstChild);
	}

	public String getContextPath() {
		Element element = super.findElement("Set", "contextPath");
		return element.getTextContent();
	}

}
