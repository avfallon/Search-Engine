# search

The directory Nim.docs includes a JavaDoc example for the game OneRowNim.

  <a href="https://loyola312fa18.github.io/search/Nim.docs/index.html"> JavaDoc served from GitHub Pages </a>
  
  <a href="http://www.cs.loyola.edu/~binkley/312/src/asn9.javadoc-example/Nim.docs"> JavaDoc served from Linux </a>


The directory testing includes a simple test case.  note that the script 
`test0` assumes that your code is in the directors src.


# OOA
InitialUML.pdf
 -- "word" seems a poor name for the concept ... might you rename it?
 -- while it is a set 'they' call it a stoplist and we had better not reinvent their terminology :)
 -- "+ inStopList(String inpStr): boolean"  whose responsibility should this be?
 -- your class inverted index does too much ... this suggests looking for more classes.

InitialUseCaseDiagram.pdf

InitialUseCases.pdf
 -- Good!

# OOD
Nested Class: Document (inside InvertedIndex)
Attributes:
String docName
String fullDocString
    String of the entire document, for -d output
Set<String> wordSet
    holds all delimited separate words in the document

Methods:
Document(String documentName)
Attributes:
    Instantiates docName, reads in the document and stores the String in fullDocString
    then calls buildWordSet
Methods:
void buildWordSet()
    splits the fullDocString into tokens, delimits each token, and adds it to wordSet
private String delimitString(String oneWord)
    Returns a String of the word without the punctuation


Class: InvertedIndex
Attributes:
Boolean displayDocText
    true if a search should return the full text of a document,
    rather than returning just the name of the document
HashSet<String> stopList
HashMap<String, Document> docMap
HashMap<String, Set<Document>> wordIndex
-- each attribute needs a brief description
-- what need does 'docMap' satisfy?

Methods:
InvertedIndex(Boolean displayDoc, String stopListName, String[] docNames)
    Instantiate displayDocText, build stopList, 
    build DocMap, and build the index
private HashSet<String> buildStopList(String stopListName)
    reads in the stopList file, adds each token to the hashSet
private void buildDocMap(String[] docNames)
    Make a Document object using each docName, and add the name and Document
    to the docMap 
private void buildIndex()
    Create a HashMap index, and then for each value in docMap and for each item
    in their wordSet (a nested for loop), check if that item is in the stopList. 
    If not, check index to see if it contains a key that matches the 
    current String. If not, put the String in as a key with a null value. 
    Then get the value associated with the String, create a new set with those values 
    plus the docname of the current document and replace the entry for the key 
private Boolean inStopList(String oneWord)
    Returns if stopList contains the String 
Set<String> search(String searchWord)
    If wordIndex contains the searchWord, return its value
Void printDocs(Set<String> docNames)
    if displayDocText is true, find the Document objects associated with each docName 
    and print the full text String. Otherwise, print each item in docNames to standard out

Class: Driver
    Create a CLI class, use it to create an InvertedIndex, create a query
    and while local variable endProgram is false, make a new query, 
    run it, and set endProgram equal to endProgram()

Class: Query
Attributes: 
Set<String> docSet
    Set of the documents to be printed
Boolean endProgram
    Tells if the user entered the command to end the program
-- this smells out of place ... its not part of a query in my world ....
Methods:
Query()
    Set endProgram to false and instantiate an empty docSet
Void runQuery(InvertedIndex index)
    Create a Scanner, and while System.in has a next value 
    that isn’t ctrl-d, run a search on index using the next 
    System.in value and store the resulting set in a tempDocSet.
    Merge the sets.
-- how does the end of the line factor into this?
    After there are no more System.in values, call printDocs with docSet
    Check if the user entered ctrl-d, if so change endProgram
private void mergeSets(Set<String> tempDocSet)
//would this be better as a LinkedList for one/both of the sets?
    If docSet is empty, set it equal to tempDocSet. Otherwise, for each item 
-- so the union?
    in docSet, if it is not contained in tempDocSet, remove it from docSet
boolean endProgram()
-- sounds like intersection now?
    Return endProgram

Class: CLI
Attributes:
None
Methods:
Private Set buildDocumentSet(String[] docNames)
    Instantiates documents with each item of docNames
    and adds them to a returned set
InvertedIndex buildIndex(String[] args)
    Process the command line arguments, call buildDocumentSet, 
    and create an InvertedIndex with the array of doc names, stopList file name
    and the documentsP flag, and returns the InvertedIndex


-- i'm happy for driver and CLI to be all in one class.
