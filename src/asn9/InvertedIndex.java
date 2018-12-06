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
            System.out.println(wordSet);
        }
        
        public void createDocString() throws IOException
        {
            File file = new File(docName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
                fullDocString += line + "\n";
            br.close();
        }
        
        public void buildWordSet() throws FileNotFoundException
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
    }
    
    
    
    public InvertedIndex(boolean displayFullDoc, String stopListName, String[] docNames) throws IOException
    {
        Document testDoc = new Document(docNames[0], displayFullDoc);
    }
}
