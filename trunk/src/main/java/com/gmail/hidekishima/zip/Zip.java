package com.gmail.hidekishima.zip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Create a zip archive.
 * 
 * @author Hideki Shima
 *
 */
public class Zip {

  private static void run() throws Exception {
    OutputStream os = new FileOutputStream(new File("sample.zip"));
    //os = System.out;
    ZipOutputStream zipos = new ZipOutputStream(os);
    
    ZipEntry entry1 = new ZipEntry("file1.txt");
    entry1.setMethod(ZipOutputStream.DEFLATED);
    zipos.putNextEntry(entry1);
    zipos.closeEntry();
    
    ZipEntry entry2 = new ZipEntry("file2.txt");
    entry2.setMethod(ZipOutputStream.DEFLATED);
    zipos.putNextEntry(entry2);
    byte[] bytearray = getByteArray();
    zipos.write(bytearray);
    zipos.closeEntry();
    zipos.close();
  }
  
  private static byte[] getByteArray() throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    OutputStreamWriter writer = new OutputStreamWriter(bos);
    writer.write("hoge");
    writer.close();
    return bos.toByteArray();
  }
  
  public static void main(String[] args) throws Exception {
    run();
  }
  
}
