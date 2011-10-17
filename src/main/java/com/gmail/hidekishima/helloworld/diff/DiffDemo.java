package com.gmail.hidekishima.helloworld.diff;

import java.util.ArrayList;
import java.util.List;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class DiffDemo {

  public static void main(String[] args) {
    List<String> original = new ArrayList<String>();
    List<String> revised  = new ArrayList<String>();
    
    original.add("A");
    original.add("B1");
    original.add("B2");
    original.add("C");
    revised.add("A");
    revised.add("B3");
    revised.add("C");
    revised.add("D");

    // Compute diff. Get the Patch object. Patch is the container for computed deltas.
    Patch patch = DiffUtils.diff(original, revised);

    for (Delta delta : patch.getDeltas()) {
      System.out.println(delta);
      Chunk oChunk = delta.getOriginal();
      Chunk oRev = delta.getRevised();
      System.out.println( "\t"+delta.getType() );
      System.out.println( "\t"+oChunk.getPosition()+" "+oChunk.getLines() );
      System.out.println( "\t"+oRev.getPosition()+" "+oRev.getLines() );
    }
  }
  
}
