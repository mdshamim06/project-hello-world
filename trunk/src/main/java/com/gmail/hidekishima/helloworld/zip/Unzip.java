package com.gmail.hidekishima.helloworld.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {

  /**
   * Unzip the specified file and return extracted dir
   * @param zipPath
   * @return
   */
  public static File unzip(String zipPath) throws FileNotFoundException {
    FileInputStream is = new FileInputStream(zipPath);
    return unzip(is);
  }
  
  /**
   * Unzip xmi-zip file and store into a temp dir.
   * @param is xmi zip input stream
   * @return unzipped dir path
   */
  public static File unzip(InputStream is) {
    String date = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
    File unzipBase = new File(System.getProperty("java.io.tmpdir")+"/oaqa-" + date);
    
    BufferedOutputStream bos = null;
    BufferedInputStream bis = null;
    ZipInputStream in = null;
    try {
      bis = new BufferedInputStream(is);
      in = new ZipInputStream(bis);
      ZipEntry zipEntry = null;
      int data = 0;
      
      while( (zipEntry = in.getNextEntry()) != null ) {
        File targetParent = new File(unzipBase, zipEntry.getName()).getParentFile();
        targetParent.mkdirs();
        
        File targetFile = new File(unzipBase, new File(zipEntry.getName()).getName());
        bos = new BufferedOutputStream(new FileOutputStream(targetFile));
        
        while( (data = in.read()) != -1 ) {
          bos.write(data);
        }
        
        in.closeEntry();
       
        bos.close();
        bos = null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        in.close();
        bis.close();
        bos.close();
      } catch (Exception e2) {}
    }
    
    return unzipBase;
  }
 
}
