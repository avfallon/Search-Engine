package asn9;
import java.io.*;
import java.util.*;

public class InvertedIndex
{
    class Document
    {
        /**
         * file name of this document
         */
        String docName;
        /**
         * Entire String of the text of the document,
         * for -d output
         */
        String fullDocString = "";
        /**
         * set of separate words in the document
         */
        Set<String> wordSet = new HashSet<String>();
        
        public Document(String documentName, boolean displayFullDoc) throws FileNotFoundException, IOException
        {
            docName = documentName;
            if(displayFullDoc)
                createDocString();
            buildWordSet();
        }
        
        private void createDocString() throws IOException
        {
            File file = new File(docName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
                fullDocString += "\n" + line;
            br.close();
        }
        
        private void buildWordSet() throws FileNotFoundException
        {
            File file = new File(docName);
            Scanner scan = new Scanner(file).useDelimiter("[^a-zA-Z]+");
            while(scan.hasNext())
            {
                String currentWord = scan.next();
                wordSet.add(currentWord);
            }
            scan.close();
        }
        
        @Override
        public String toString()
        {
            return docName + fullDocString;
        }
    }
    
    HashSet<String> stopList;
    
    public InvertedIndex(boolean displayFullDoc, String stopListName, String[] docNames) throws IOException
    {
        Document testDoc = new Document(docNames[0], displayFullDoc);
        buildStopList(stopListName);
        System.out.println(testDoc.toString());
        System.out.println(stopList);
    }
    
    private void buildStopList(String stopListName) throws FileNotFoundException
    {
        stopList = new HashSet<String>();
        File file = new File(stopListName);
        Scanner scan = new Scanner(file).useDelimiter("[^a-zA-Z]+");
        while(scan.hasNext())
        {
            String currentWord = scan.next();
            stopList.add(currentWord);
        }
        scan.close();
    }
}
