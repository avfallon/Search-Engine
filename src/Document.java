

import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;

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
    
    public Document(String fileName) throws FileNotFoundException, IOException, NullPointerException
    {
        createDocString(fileName);
        Path p = Paths.get(fileName);
        docName = p.getFileName().toString();
    }
    
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
