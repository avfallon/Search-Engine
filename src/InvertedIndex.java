
import java.io.*;
import java.util.*;

public class InvertedIndex
{
    protected Set<String> stopList;
    protected Map<String, Set<Document>> wordIndex;
    
    public InvertedIndex(List<String> argList) throws IOException
    {
        buildStopList(argList.get(0));
        Set<Document> docSet = createDocuments(argList.subList(1, argList.size()));
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
    
    private Set<Document> createDocuments(List<String> docNames) throws IOException
    {
        Set<Document> docSet = new HashSet<Document>();
        for(int i=0; i<docNames.size();i++)
        {
            docSet.add(new Document(docNames.get(i)));
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
        Set<String> wordSet = new HashSet<String>();
        Set<Document> finalDocSet = new HashSet<Document>();
        
        StringTokenizer tokenizer = new StringTokenizer(line);
        while(tokenizer.hasMoreTokens())
        {
            String word = tokenizer.nextToken();
            wordSet.add(word);
        }
        for(String word: wordSet)
        {
            Set<Document> newDocs = wordIndex.get(word);
            if(newDocs != null)
            {
                if(finalDocSet == null)
                    finalDocSet = newDocs;
                
                Set<Document> tempSet = finalDocSet;
                for(Document doc: tempSet)
                {
                    if(!newDocs.contains(doc))
                        finalDocSet.remove(doc);
                }
            }
        }
        return finalDocSet;
    }
    
    public void printIndex()
    {
        System.out.println(wordIndex.entrySet());
    }
}
