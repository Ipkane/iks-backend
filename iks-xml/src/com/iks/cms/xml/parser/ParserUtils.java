package com.iks.cms.xml.parser;

import org.w3c.dom.*;

import java.io.*;

import javax.xml.parsers.*;

/**
 * @author Igor Kaynov
 */
public class ParserUtils {
  public static Document parseFile( String fileName ) throws Exception {
    File fXmlFile = new File( fileName );
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse( fXmlFile );
    doc.getDocumentElement().normalize();
    return doc;
  }
}
