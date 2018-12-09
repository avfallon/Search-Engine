

import java.io.*;
import java.util.*;

public class Document implements Iterable<String>
{
    /**
     * file name of this document
     */
    protected String docName;
    /**
     * Entire String of the text of the document,
     * for -d output
     */
    protected String fullDocString;
    /**
     * set of separate words in the document
     */
    
    public Document(String documentName) throws FileNotFoundException, IOException
    {
        docName = documentName;
        createDocString();
    }
    
    private void createDocString() throws IOException, FileNotFoundException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(docName));
            fullDocString = "\n" + new Scanner(br).useDelimiter("\\A").next();
            br.close();
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("File name does not match an existing file");
        }
        catch(IOException io)
        {
            System.out.println("IO Exception reached");
        }
    }
    
    public Iterator<String> iterator()
    {
        return new Scanner(fullDocString).useDelimiter("[^a-zA-Z]+");
    }
    
    @Override
    public String toString()
    {
        return docName;
    }
    
    public void printFullDoc()
    {
        System.out.println(fullDocString);
    }
}
