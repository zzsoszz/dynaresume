package org.eclipse.jst.server.jetty.core.internal.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.eclipse.jst.server.jetty.core.internal.util.IOUtils;

public class WebdefaultXMLConfig {

	public static InputStream getInputStream(File webdefaultXMLFile)
			throws IOException {
		InputStream stream = new FileInputStream(webdefaultXMLFile);
		try {
			boolean useFileMappedBuffer = false;
			InputStreamReader input = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(input);
			StringWriter newContent = new StringWriter();
			for (String line = reader.readLine(); line != null; line = reader
					.readLine()) {
				if (useFileMappedBuffer) {
					line = "<param-value>false</param-value>";
					useFileMappedBuffer = false;
				}
				if (line.indexOf("<param-name>useFileMappedBuffer</param-name>") != -1) {
					useFileMappedBuffer = true;
				}
				newContent.append(line);
				newContent.append('\n');
			}			
			return IOUtils.toInputStream(newContent.toString(), "UTF-8");
		} finally {
			stream.close();
		}
	}
}
