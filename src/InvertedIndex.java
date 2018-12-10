/**
* This is my code! Its goal is to hold an inverted index of Documents,
* and be able to answer a query of word(s) with what documents they're in
* CS 312 - Assignment 9
* @author Andrew Fallon
* @version 1.0 12/10/2018 
*/

import java.io.*;
import java.util.*;

/**
 * The main body of the search engine, holds the inverted index and runs queries on it
 */
public class InvertedIndex
{
    /**
     * A set of common words that queries will ignore
     */
    protected Set<String> stopList;
    /**
     * Map that holds the actual inverted index, 
     * with each word as a key and its value being a Set of each Document it is in
     */
    protected Map<String, Set<Document>> wordIndex;
    
    /**
     * constructor, creates the stop list and instantiates the instance variable,
     * reads the files, makes them into documents, 
     * and fills out the inverted index with those documents
     * Expected complexity: O(n^2)
     * 
     * @param argList - A list containing the stop list file name and each document file name
     * @throws IOException
     */
    public InvertedIndex(List<String> argList) throws IOException
    {
        buildStopList(argList.get(0));
        Set<Document> docSet = createDocuments(argList.subList(1, argList.size()));
        buildIndex(docSet);
    }
    
    /**
     * Reads in the stop list file and stores each word in stopList
     * Expected complexity: O(n), n being the number of words in the document
     * 
     * @param stopListName - The name of the stop list file
     * @throws FileNotFoundException
     */
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
    
    /**
     * Uses each file name in fileNames to create a Document with the contents of the file,
     * and returns a set of those Documents
     * Expected complexity: O(n), n being the number of file names
     * 
     * @param fileNames - the Strings of each of the files to read and store
     * @return - A set of each of the Documents
     * @throws IOException
     */
    private Set<Document> createDocuments(List<String> fileNames) throws IOException
    {
        Set<Document> docSet = new HashSet<Document>();
        for(int i=0; i<fileNames.size();i++)
        {
            docSet.add(new Document(fileNames.get(i)));
        }
        return docSet;
    }
    
    /**
     * Simple method to give a name to searching through the stop list for a word
     * Expected complexity: O(1), constant time, it's searching through a HashSet
     * 
     * @param oneWord - The word you are searching through the stop list for
     * @return - Whether or not the word is in the stop list 
     */
    private boolean inStopList(String oneWord)
    {
        return stopList.contains(oneWord);
    }
    
    /**
     * fills out a HashMap with keys of each word that appears in any of the documents,
     * provided it's not in the stop list, and stores a set of each of the documents
     * the word appears in as the value of each word
     * Expected complexity: O(n^2), the n values being 
     * the number of documents and the number of words in those documents
     * 
     * @param docSet a set of all of the entered documents
     */
    private void buildIndex(Set<Document> docSet)
    {
        wordIndex = new HashMap<String, Set<Document>>();
        for(Document currentDoc: docSet)
        {
            for(String word: currentDoc)
            {
                if(!inStopList(word))
                {
                    //create a new key
                    if(!wordIndex.containsKey(word))
                    {
                        Set<Document> tempSet = new HashSet<Document>();
                        tempSet.add(currentDoc);
                        wordIndex.put(word, tempSet);
                    }
                    
                    //add the value of the current document to the index
                    Set<Document> valueSet = new HashSet<Document>();
                    valueSet = wordIndex.get(word);
                    valueSet.add(currentDoc);
                    wordIndex.replace(word, valueSet);
                }
            }
        }
    }
    
    /**
     * Finds a set of documents that contains all of the entered words
     * Expected complexity: O(1), constant time, it is searching through a HashMap
     * 
     * @param line - A String of each word to search for, separated by spaces
     * @return - A set of the Documents that contain all of the entered words
     * @throws IOException
     */
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
        
        boolean setInstantiated = false;
        for(String word: wordSet)
        {
            Set<Document> newDocs;
            newDocs = (wordIndex.get(word) == null) ? new HashSet<Document>() : wordIndex.get(word);
            if(!inStopList(word))
            {
                //fill out the final set with the first word's documents
                if(!setInstantiated)
                {
                    finalDocSet = newDocs;
                    setInstantiated = true;
                }
                
                //find discrepancies in document sets
                Set<Document> removeSet = new HashSet<Document>();
                for(Document doc: finalDocSet)
                    if(!newDocs.contains(doc))
                        removeSet.add(doc);
                for(Document doc:removeSet)
                    finalDocSet.remove(doc);
            }
        }
        return finalDocSet;
    }
    
    /**
     * Prints to standard out each key and its value in the inverted index 
     * Expected complexity: O(n), n being the number of keys in wordIndex
     */
    public void printIndex()
    {
        System.out.println(wordIndex.entrySet());
    }
}
