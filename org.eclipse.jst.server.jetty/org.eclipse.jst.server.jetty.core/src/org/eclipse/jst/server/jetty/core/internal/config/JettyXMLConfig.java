package org.eclipse.jst.server.jetty.core.internal.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.eclipse.jst.server.jetty.core.internal.util.IOUtils;

public class JettyXMLConfig {

	public static InputStream getInputStream(File jettyXMLFile)
			throws IOException {
		InputStream stream = new FileInputStream(jettyXMLFile);
		try {
			InputStreamReader input = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(input);
			StringWriter newContent = new StringWriter();
			for (String line = reader.readLine(); line != null; line = reader
					.readLine()) {
				newContent.append(line);
				newContent.append('\n');
			}
			String s = newContent.toString().replaceAll("<Property", "<SystemProperty");		
			return IOUtils.toInputStream(s, "UTF-8");
		}
		finally {
			stream.close();
		}	
	}
}
