package asn9;
import java.io.*;
public class Driver
{
    public static void main(String[] args) throws IOException
    {
        String[] arr = {"testing/doc1", "testing/doc2"};
        InvertedIndex index = new InvertedIndex(false, "testing/stoplist", arr);
        System.out.println(index.search("Happy"));
    }
}
