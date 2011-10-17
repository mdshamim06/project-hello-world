package com.gmail.hidekishima.helloworld.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Unzipping a zip file
 * 
 */
public class Unzip {

  /**
   * Recursively unzip xmi-zip file and store into a temp dir.
   * 
   * Modified based on a code at:
   * http://stackoverflow.com/questions/981578/how-to-unzip-files-recursively-in-java
   * 
   * @param is xmi zip input stream
   * @param unzipBase where to expand unzipped files 
   */
  public static void unzip(String zipFile, File unzipBase) throws ZipException, IOException {
    
    int BUFFER = 2048;
    File file = new File(zipFile);

    ZipFile zip = new ZipFile(file);

    Enumeration<?> zipFileEntries = zip.entries();

    // Process each entry
    while (zipFileEntries.hasMoreElements()) {
      // grab a zip file entry
      ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
      String currentEntry = entry.getName();
      File destFile = new File(unzipBase, currentEntry);
      // destFile = new File(newPath, destFile.getName());
      File destinationParent = destFile.getParentFile();

      // create the parent directory structure if needed
      destinationParent.mkdirs();

      if (!entry.isDirectory()) {
        BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
        int currentByte;
        // establish buffer for writing file
        byte data[] = new byte[BUFFER];

        // write the current file to disk
        FileOutputStream fos = new FileOutputStream(destFile);
        BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

        // read and write until last byte is encountered
        while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
          dest.write(data, 0, currentByte);
        }
        dest.flush();
        dest.close();
        is.close();
      }

      if (currentEntry.endsWith(".zip")) {
        // found a zip file, try to open
        unzip(destFile.getAbsolutePath(), unzipBase);
      }
    }
  }

  @Deprecated
  /**
   * Incomplete non-recursive version.
   * 
   * This implementation can take a zip file as input stream
   * and might be useful for receiving uploaded zip file...? 
   * 
   * @param is
   * @param unzipBase
   */
  public static void unzipOld(InputStream is, File unzipBase) {
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
  }
 
}
