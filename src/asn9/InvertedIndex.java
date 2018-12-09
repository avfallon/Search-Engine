package asn9;
import java.io.*;
import java.util.*;

public class InvertedIndex
{
    Set<String> stopList;
    Map<String, Set<Document>> wordIndex;
    
    public InvertedIndex(boolean displayFullDoc, String stopListName, String[] docNames) throws IOException
    {
        buildStopList(stopListName);
        Set<Document> docSet = createDocuments(docNames, displayFullDoc);
        buildIndex(docSet);
    }
    
    private void buildStopList(String stopListName) throws FileNotFoundException
    {
        stopList = new HashSet<String>();
        try
        {
            File file = new File(stopListName);
            Scanner scan = new Scanner(file).useDelimiter("[^a-zA-Z]+");
            while(scan.hasNext())
            {
                String currentWord = scan.next();
                stopList.add(currentWord);
            }
            scan.close();
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("Stop list file name does not match an existing file");
        }
    }
    
    private Set<Document> createDocuments(String[] docNames, boolean displayFullDoc) throws IOException
    {
        Set<Document> docSet = new HashSet<Document>();
        for(int i=0; i<docNames.length;i++)
        {
            Document newDoc = new Document(docNames[i], displayFullDoc);
            docSet.add(newDoc);
        }
        return docSet;
    }
    
    private boolean inStopList(String oneWord)
    {
        return stopList.contains(oneWord);
    }
    
    private void buildIndex(Set<Document> docSet)
    {
        wordIndex = new HashMap<String, Set<Document>>();
        for(Document currentDoc: docSet)
        {
            for(String word: currentDoc)
            {
                if(!inStopList(word))
                {
                    if(!wordIndex.containsKey(word))
                    {
                        Set<Document> tempSet = new HashSet<Document>();
                        tempSet.add(currentDoc);
                        wordIndex.put(word, tempSet);
                    }
                    
                    //wordIndex.get(word).add(currentDoc);
                    Set<Document> valueSet = new HashSet<Document>();
                    valueSet = wordIndex.get(word);
                    valueSet.add(currentDoc);
                    wordIndex.replace(word, valueSet);
                }
            }
        }
    }
    
    public Set<Document> query(String line) throws IOException
    {
        Set<Document> docSet = new HashSet<Document>();
        
        StringTokenizer tokenizer = new StringTokenizer(line);
        while(tokenizer.hasMoreTokens())
        {
            Document newDoc = new Document(tokenizer.nextToken());
            docSet.add(newDoc);
        }
        return docSet;
    }
    
    public Set<Document> search(String word)
    {
        return wordIndex.get(word);
    }
}
