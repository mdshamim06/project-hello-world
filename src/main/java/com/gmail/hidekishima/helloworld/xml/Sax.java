package com.gmail.hidekishima.helloworld.xml;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Shows how to parse an xml into dom using SAX (faster than DOM)  
 * 
 * @author Hideki Shima
 *
 */
public class Sax extends DefaultHandler implements IXmlHandler {

  private StringBuilder parsed = new StringBuilder();
  private StringBuilder buffer = new StringBuilder();
  
  private static String text = "<knowledge_base>\n" +
  		"<entity wiki_title=\"Mike_Quigley_(footballer)\" " +
  				"type=\"PER\" id=\"E0000001\" name=\"Mike Quigley (footballer)\">\n" +
  		"<facts class=\"Infobox Football biography\">\n"+
      "<fact name=\"playername\">Mike Quigley</fact>\n"+
      "<fact name=\"fullname\">Michael Anthony Joseph Quigley</fact></facts>\n"+
      "<wiki_text><![CDATA[Mike Quigley (footballer)\n" +
      "Mike Quigley (born 2 October 1970) is an English football midfielder." +
      "]]></wiki_text>" +
      "</entity></knowledge_base>";
  
  private enum XML {FACT}
  
  private boolean inFact = false;
		
	@Override
	public void startElement( String uri, String localName, 
			String qName, Attributes attributes ) {
	  if ( XML.FACT.toString().equalsIgnoreCase(qName) ) {
	    inFact = true;
		}
	}
	
	@Override
  public void characters(char[] ch, int offset, int length) {
    if (inFact) {
      String value = new String(ch, offset, length);
      //There is a truncation problem in this method!!!!!!
      buffer.append(value);
    }
  }
	
	@Override
	public void endElement( String uri, String localName, String qName ) {
	  if ( XML.FACT.toString().equalsIgnoreCase(qName) ) {
      parsed.append(buffer.toString()+"|");
	    inFact = false;
	  }
	}

  private String deserialize(InputStream inputStream, IXmlHandler handler) {
    String result = null;
    try {
      XMLReader xmlReader = XMLReaderFactory.createXMLReader();
      xmlReader.setContentHandler(handler);
      xmlReader.parse(new InputSource(inputStream));
      result = handler.getParsed();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
      }
    }
    return result;
  }
	
  @Override
  public String getParsed() {
    return parsed.toString();
  }

  public void demo() {
    InputStream is = new ByteArrayInputStream(text.getBytes());
    String result = deserialize(is, new Sax());
    System.out.println(result);
  }
  
  public static void main(String[] args) {
    new Sax().demo();
  }
}

interface IXmlHandler extends ContentHandler {
  public String getParsed();
}

interface IXmlSerializable {}
