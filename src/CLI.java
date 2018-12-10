/**
* This is my code! Its goal is to take command line arguments, create an inverted index, 
* and then execute queries for words in that index
* CS 312 - Assignment 9
* @author Andrew Fallon
* @version 1.0 12/10/2018 
*/

import java.io.*;
import java.util.*;

/**
 * Takes in command line arguments and with them creates an inverted index, 
 * and also runs queries on the inverted index from standard in
 */
public class CLI
{        
    /**
     * String value that would be entered from standard in to print the entire inverted index
     * For debugging purpose 
     */
    private static final String DEBUG = "@@debug";

    /**
     * Main method of the program, creates an inverted index and conducts queries on it
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        //Read in command line arguments
        String usage = "Usage: java CLI [-d] stopList Documents";
        if(args.length < 2 || (args.length < 3 && args[0].equals("-d")))
        {
            System.out.println("Improper input: " + usage);
            System.exit(-1);
        }
        boolean displayText = false;
        int start = 0;
        if("-d".equals(args[0]))
        {
            displayText = true;
            start = 1;
        }
        long startTime = System.currentTimeMillis();
        
        //manipulate args to create an index with the stop list and doc names
        List<String> argList = Arrays.asList(args);
        argList = argList.subList(start, argList.size());
        InvertedIndex index = new InvertedIndex(argList);
        
        //find how long building the index took
        long buildStop = System.currentTimeMillis();
        long buildTime = buildStop-startTime;
        System.out.println("@@ build time: " + buildTime + "ms");
        
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for(String line = br.readLine(); line != null; line=br.readLine())
            {
                if(DEBUG.equals(line))
                    index.printIndex();
                else
                {
                    //perform the search
                    Set<Document> docSet = index.query(line);
                    
                    //print results of the search
                    System.out.println("---found in " + (docSet == null ? 0 : docSet.size())+ " documents");
                    if(!docSet.isEmpty())
                    {
                        for(Document doc: docSet)
                            System.out.print(doc + " ");
                        System.out.println("");
                    }
                    else
                        System.out.println("null");
                    if(displayText)
                        for(Document doc: docSet)
                            doc.printFullDoc();
                }
            } 
            br.close();
        }
        catch(Exception ex)
        {
            System.out.println("Received a " + ex);
            ex.printStackTrace();
        }
        
        //find how long it took to execute the queries
        long stopTime = System.currentTimeMillis();
        long queryTime = stopTime - buildStop;
        System.out.println("@@query time: " + queryTime + "ms");
    }
}
