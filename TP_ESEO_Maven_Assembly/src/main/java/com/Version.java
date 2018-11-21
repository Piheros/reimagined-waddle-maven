package com;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class Version {

	
public synchronized static final String getVersion() {
    // Try to get version number from pom.xml
    try {
        String className = Version.class.getName();
        String classfileName = "/" + className.replace('.', '/') + ".class";
        URL classfileResource = Version.class.getResource(classfileName);
        if (classfileResource != null) {
            Path absolutePackagePath = Paths.get(classfileResource.toURI()).getParent();
            int packagePathSegments = className.length() - className.replace(".", "").length();
            Path path = absolutePackagePath;
            for (int i = 0, segmentsToRemove = packagePathSegments + 2; i < segmentsToRemove; i++) {
                path = path.getParent();
            }
            Path pom = path.resolve("pom.xml");
            try (InputStream is = Files.newInputStream(pom)) {
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                doc.getDocumentElement().normalize();
                String version = (String) XPathFactory.newInstance().newXPath().compile("/project/version").evaluate(doc, XPathConstants.STRING);
                if (version != null) {
                    version = version.trim();
                    System.out.println("\nVersion : " + version);
                    if (!version.isEmpty()) {
                        return version;
                    }
                }
            }
        }
    } catch (Exception e) {
    	e.printStackTrace();
    }

    String version = null;
    version = version == null ? "" : version.trim();
    return version.isEmpty() ? "unknown" : version;
}
}
