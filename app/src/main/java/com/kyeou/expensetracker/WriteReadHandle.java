package com.kyeou.expensetracker;//package main.cpp.headers;

//import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class WriteReadHandle{//} extends AppCompatActivity {



public void createFiles(String filename) throws IOException {
   File path = new File("/data/data/com.kyeou.expensetracker/files/");
   File file = new File(path, filename);
   try {
      file.createNewFile();
   } catch (IOException e) {
      e.printStackTrace();
   }
   //File file = new File(filename);

   FileOutputStream stream = new FileOutputStream(file);
   stream.write(" ".getBytes());
}




   public void WriteHandle(String filename, String ttw) throws IOException {


      File path = new File("/data/data/com.kyeou.expensetracker/files/");
      File file = new File(path, filename);
      //File file = new File(filename);
      file.createNewFile();
      FileOutputStream stream = new FileOutputStream(file);


      try (Writer out = new FileWriter(file)) {
         out.write(ttw);
      }

   }

   public String ReadHandle(String filename) throws IOException {
      File path = new File("/data/data/com.kyeou.expensetracker/files/");
      File file = new File(path, filename);
      //file.createNewFile();
      FileReader in = new FileReader(file);
      StringBuilder ret = new StringBuilder();

      try {

         int content;
         while ((content = in.read()) != -1) {
            //System.out.print((char) content);
            ret.append((char) content);
         }
      } finally {
         in.close();
      }
      return ret.toString();
   }





   public static void main(String[] args) throws IOException {
      new WriteReadHandle().WriteHandle("transactionsJSON.json", "[{\"Name\": \"FirstName LastName\",  \"A_O_T\": 0,  \"Budgets\": [1,2,3],\"Income\": 0,\"Scores\": [],\"SumDebits\": 0 }]");
      //WriteReadHandle test = new WriteReadHandle();
      //System.out.println(test.ReadHandle("transactionsJSON.json"));
      System.out.println(new WriteReadHandle().ReadHandle("transactionsJSON.json"));
      //new WriteReadHandle().WriteHandle("transactionsJSON.json", "ASDGHLD");
      System.out.println(new WriteReadHandle().ReadHandle("transactionsJSON.json"));
   }//end main


}

/*
 * import java.io.*;
 * public class CopyFile {
 *
 * public static void main(String args[]) throws IOException {
 * File file = new File("wtf_is_this.txt");
 * file.createNewFile();
 * FileReader in = null;
 * FileWriter out = null;
 *
 * try {
 * in = new FileReader("wtf_is_this.txt");
 * out = new FileWriter("wtf_is_this.txt");
 *
 * out.write("kjblkjb");
 * }finally {
 * if (in != null) {
 * in.close();
 * }
 * if (out != null) {
 * out.close();
 * }
 * }
 * }
 * }
 */