

import java.io.*;
import java.util.*;

public class CLI
{        
    private static final String DEBUG = "@@debug";

    public static void main(String[] args) throws IOException
    {
        String usage = "Usage: java CLI [-d] stopList Documents";
//        if(args.length < 2 || (args.length < 3 && args[0].equals("-d")))
//        {
//            System.out.println("Improper input: " + usage);
//            System.exit(-1);
//        }
        boolean displayText = false;
//        int start = 0;
//        if("-d".equals(args[0]))
//        {
//            displayText = true;
//            start = 1;
//        }
        long startTime = System.currentTimeMillis();
        List<String> argList = new ArrayList<String>();
        argList.add("testing/stoplist");
                argList.add("testing/doc1");
                        argList.add("testing/doc2");
                                argList.add("testing/doc3");
//        List<String> argList = Arrays.asList(args);
//        argList = argList.subList(start, argList.size());
        InvertedIndex index = new InvertedIndex(argList);
        
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
                    Set<Document> docSet = index.query(line);
                    System.out.println("---found in " + (docSet == null ? 0 : docSet.size())+ " documents");
                    if(docSet != null)
                    {
                        for(Document doc: docSet)
                            System.out.print(doc + " ");
                        System.out.println("");
                    }
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
        
        long stopTime = System.currentTimeMillis();
        long queryTime = stopTime - buildStop;
        System.out.println("@@query time: " + queryTime + "ms");
    }
}
