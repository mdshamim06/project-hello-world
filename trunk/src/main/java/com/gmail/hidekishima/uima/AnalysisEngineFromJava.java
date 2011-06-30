package com.gmail.hidekishima.uima;

import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

/**
 * 
 * Here's how to run an analysis engine from Java code.
 * TODO: Add a sample with statusCallBackListener
 * 
 * @author Hideki Shima
 *
 */
public class AnalysisEngineFromJava {

  //Specify your favorite analysis engine descriptor
  private static final String aeDescriptor = "SimpleEmailRecognizer_RegEx_TAE.xml";
  
  public void run() throws IOException, InvalidXMLException, 
    ResourceInitializationException, AnalysisEngineProcessException {
    XMLInputSource in = new XMLInputSource(aeDescriptor);
    ResourceSpecifier specifier = 
      UIMAFramework.getXMLParser().parseResourceSpecifier(in);

    //create AE here
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    
    //create a JCas, given an Analysis Engine (ae)
    JCas jcas = ae.newJCas();
    jcas.setDocumentText("This is the test document.");
    
    //analyze a document
    ae.process(jcas);
    
    Iterator<?> it = jcas.getJFSIndexRepository().getAllIndexedFS(0);
    while (it.hasNext()) {
      System.out.println(it.next().toString());      
    }
    
    jcas.reset();
   
    //done
    ae.destroy();
  }
  
}

