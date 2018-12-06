import java.util.Set;

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
        String fullDocString;
        /**
         * set of separate words in the document
         */
        Set<String> wordSet;
        
        public Document(String documentName)
        {
            docName = documentName;
        }
    }
}
