package com.gmail.hidekishima.helloworld.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Finding a resource (data) from classpath.
 * TODO: Test from inside jar archive. 
 * 
 * 
 * ClassLoader.getResourceAsStream()
 * # From the root of classpath. Don't start with '/'.
 * # Separator: '/'
 * 
 * Class.getResourceAsStream()
 * # From the package where the Class exists. 
 * # Absolute path from the classpath root possible if started with '/'.
 * # Separator: '/'
 * 
 * ResourceBundle.getBundle(baseName);
 * # From the root of classpath.
 * # Separator: '.'
 * 
 * 
 * @author Hideki Shima
 *
 */
public class ResourceUtil {

  public static File findResourceAndSave(String fileNameToFind, boolean addSuffix) throws IOException {
    try {
      String date = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
      int pos = fileNameToFind.indexOf(".");
      String name = fileNameToFind.substring(0,pos);
      String ext = fileNameToFind.substring(pos);
      String targetFileName = addSuffix?(name+"-"+date+ext):fileNameToFind;
      File tempFile = new File(System.getProperty("java.io.tmpdir")+"/"+targetFileName);
      return findResourceAndSave( fileNameToFind, tempFile );
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static File findResourceAndSave(String fileNameToFind, 
          File whereToStore) throws IOException {
    try {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      InputStream is = cl.getResourceAsStream(fileNameToFind);
      FileOutputStream fos = new FileOutputStream(whereToStore);
      OutputStreamWriter osw = new OutputStreamWriter(fos);
      BufferedWriter bw = new BufferedWriter(osw);
      
      InputStreamReader isr = new InputStreamReader( is, "utf8" );
      BufferedReader br = new BufferedReader( isr ); 
      String line = null;
      while ( ( line=br.readLine() )!=null ) {
        bw.write( line+"\n");
      }
      bw.close();
      br.close();
      is.close();
      isr.close();
      osw.close();
      fos.close();
      
      return whereToStore;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
