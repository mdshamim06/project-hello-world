package com.gmail.hidekishima.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Shows how to parse an xml into dom, and how to generate an xml from a dom.  
 * 
 * @author Hideki Shima
 *
 */
public class Dom {

  // Example docbook from wikipedia 
  public static String docbook = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
          + "<book xml:id=\"simple_book\" xmlns=\"http://docbook.org/ns/docbook\" version=\"5.0\">\n"
          + "  <title>Very simple book</title>\n" 
          + "  <chapter xml:id=\"chapter_1\">\n"
          + "    <title>Chapter 1</title>\n" 
          + "    <para>Hello world!</para>\n"
          + "    <para>I hope that your day is proceeding <emphasis>splendidly</emphasis>!</para>\n"
          + "  </chapter>\n" 
          + "  <chapter xml:id=\"chapter_2\">\n" 
          + "    <title>Chapter 2</title>\n"
          + "    <para>Hello world!</para>\n" 
          + "  </chapter>\n" 
          + "</book>\n";

  public void demo() {
    Document doc = xml2dom(docbook); // Parse XML content into a DOM tree
    process(doc); // Do something with DOM tree
    dom2xml(doc); // Export DOM tree
  }

  private Document xml2dom(String xml) {
    InputStream is = new ByteArrayInputStream(xml.getBytes());
    Document doc = null;
    try {
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      doc = builder.parse(is);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return doc;
  }

  private void process(Document doc) {
    Element root = doc.getDocumentElement();
    NodeList list = root.getElementsByTagName("chapter");
    for (int i = 0; i < list.getLength(); i++) {
      Element element = (Element) list.item(i);
      String id = element.getAttribute("xml:id");
      System.out.println("Processing ... "+id);
    }
    System.out.println("Processing done.");
    System.out.println();
  }

  private void dom2xml(Document doc) {
    TransformerFactory tf = TransformerFactoryImpl.newInstance();
    tf.setAttribute(TransformerFactoryImpl.INDENT_NUMBER, "2");

    DOMSource source = new DOMSource(doc);
    StringWriter writer = new StringWriter();
    StreamResult result = new StreamResult(writer);
    try {
      Transformer transformer = tf.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.transform(source, result);
    } catch ( Exception e ) {
      e.printStackTrace();
    }

    System.out.println(writer.toString());
  }

  public static void main(String[] args) {
    new Dom().demo();
  }
}
