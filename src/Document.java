/**
* This is my code! Its goal is to hold the contents of a file and manipulate that contents
* CS 312 - Assignment 9
* @author Andrew Fallon
* @version 1.0 12/10/2018 
*/

import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Class that holds the contents of a documents
 */
public class Document implements Iterable<String>
{
    /**
     * File name of this document
     */
    protected String docName;
    /**
     * Entire String of the text of the document,
     * for -d output
     */
    protected String fullDocString;
    
    /**
     * Constructs a document, instantiates both instance variables
     * Expected complexity: O(1), constant time
     * 
     * @param fileName- The name of the file to read from
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     */
    public Document(String fileName) throws FileNotFoundException, IOException, NullPointerException
    {
        createDocString(fileName);
        Path p = Paths.get(fileName);
        docName = p.getFileName().toString();
    }
    
    /**
     * Instantiates fullDocString with the contents of a file
     * Expected complexity: O(1), constant time
     * 
     * @param fileName - The name of the file to read from 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NullPointerException
     */
    private void createDocString(String fileName) throws IOException, FileNotFoundException, NullPointerException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            fullDocString = new Scanner(br).useDelimiter("\\A").next();
            br.close();
        }
        catch(Exception ex)
        {
            System.out.println("Received a " + ex);
            ex.printStackTrace();
        }
    }
    
    /**
     * Returns an Iterator of every word in the document
     * Implementation of a method declared in the Iterable interface
     * Expected complexity: O(1), constant time
     * 
     * @return - An iterator over each word in the document
     */
    public Iterator<String> iterator()
    {
        return new Scanner(fullDocString).useDelimiter("[^a-zA-Z]+");
    }
    
    /**
     * Returns the document name, without the path
     * Expected complexity: O(1), constant time
     */
    @Override
    public String toString()
    {
        return docName;
    }
    
    /**
     * Prints the full text of the document to standard in
     * Expected complexity: O(1), constant time
     */
    public void printFullDoc()
    {
        System.out.println(fullDocString);
    }
}
