package com.gmail.hidekishima.uima;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;

public class JCasUtil {

  @SuppressWarnings("unchecked")
  @Deprecated
  public static <T extends TOP> List<T> getFeaturesInWrongWay(JCas jcas) {
    List<T> annotations = new ArrayList<T>();
    
    // Getting everything under TOP...
    FSIterator<TOP> it = jcas.getJFSIndexRepository().getAllIndexedFS(T.type);
    while (it.hasNext()) {
      T r = (T) it.next();
      annotations.add(r);
    }
    return annotations;
  }
  
  @SuppressWarnings("unchecked")
  public static <T extends TOP> List<T> getFeatures(JCas jcas, int type) {
    List<T> annotations = new ArrayList<T>();
    
    FSIterator<TOP> it = jcas.getJFSIndexRepository().getAllIndexedFS(type);
    while (it.hasNext()) {
      T t = (T) it.next();
      annotations.add(t);
    }
    return annotations;
  }
  
}
