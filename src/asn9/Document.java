package asn9;

import java.io.*;
import java.util.*;

public class Document implements Iterable<String>
{
    /**
     * file name of this document
     */
    String docName;
    /**
     * Entire String of the text of the document,
     * for -d output
     */
    String fullDocString;
    /**
     * set of separate words in the document
     */
    boolean displayText;
    
    public Document(String documentName, boolean displayText) throws FileNotFoundException, IOException
    {
        docName = documentName;
        createDocString();
        this.displayText = displayText;
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
        if(displayText)
            return docName + fullDocString;
        else
            return docName;
    }
}
