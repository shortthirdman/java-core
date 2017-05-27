package com.shortthirdman.core.utils;

import java.io.IOException;

public class StringReverser {
  private String input; 
  private String output;

  public StringReverser(String in) {
    input = in;
  }

  public String doRev() {
    int stackSize = input.length(); 
    Stack theStack = new Stack(stackSize); 

    for (int i = 0; i < input.length(); i++) {
      char ch = input.charAt(i); 
      theStack.push(ch); 
    }
    output = "";
    while (!theStack.isEmpty()) {
      char ch = theStack.pop(); 
      output = output + ch; 
    }
    return output;
  }

  public static void main(String[] args) throws IOException {
    String input = "Java Source and Support";
    String output;
    StringReverser theReverser = new StringReverser(input);
    output = theReverser.doRev();
    System.out.println("Reversed: " + output);
  }
}