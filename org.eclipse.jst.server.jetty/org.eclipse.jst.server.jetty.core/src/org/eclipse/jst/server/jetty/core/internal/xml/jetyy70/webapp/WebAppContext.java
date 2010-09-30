package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.webapp;

import java.io.IOException;

import org.eclipse.jst.server.jetty.core.internal.xml.XMLElement;
import org.eclipse.jst.server.jetty.core.internal.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WebAppContext extends XMLElement {

	private String fileName;

	public void setContextPath(String contextPath) {
		Element element = super.findElement("Set", "contextPath");
		if (contextPath.startsWith("/")) {
			element.setTextContent(contextPath);
		} else {
			element.setTextContent("/" + contextPath);
		}

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

	public String getWar() {
		Element element = super.findElement("Set", "war");
		return element.getTextContent();
	}

	public void save() throws IOException {
		XMLUtil.save(fileName, getElementNode().getOwnerDocument());
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
