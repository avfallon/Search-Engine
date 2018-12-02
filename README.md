# search

The directory Nim.docs includes a JavaDoc example for the game OneRowNim.

  <a href="https://loyola312fa18.github.io/search/Nim.docs/index.html"> JavaDoc served from GitHub Pages </a>
  
  <a href="http://www.cs.loyola.edu/~binkley/312/src/asn9.javadoc-example/Nim.docs"> JavaDoc served from Linux </a>


The directory testing includes a simple test case.  note that the script 
`test0` assumes that your code is in the directors src.


# OOA
InitialUML.pdf
InitialUseCaseDiagram.pdf
InitialUseCases.pdf

# OOD
Class: Document
Attributes:
String fullDocString
  String of the entire document, for -d output
Set<String> wordSet
  holds all delimited separate words in the document
HashSet<Set<HashMaps>> hashSet
  Set of buckets(sets of hashmaps) 

Methods:
Document(String documentName)
  Reads in the document and stores the String in fullDocString, 
  then calls delimitString on each token in the string and add them to wordSet
private HashMap hash(String oneWord)
  hashes a single word that has already been delimited and is not in stop list
private String delimitString(String oneWord)
  Returns a String of the word without the punctuation
void compareToStopList(Document stopList)
  searches stopList for each item in wordset, if there is a match,
  remove that item from wordset
void buildSet()
  hashes each item in wordSet and adds the HashMap to the hashSet
boolean search(String wordToFind)
  searches the hashSet for a certain word


Class InvertedIndex
Attributes:
Boolean displayDocText
  true if a search should return the full text of a document, 
  rather than returning just the name of the document
Document stopList
Set<Document> docSet

Methods:
InvertedIndex(Boolean displayDoc, Document stopList, Set<Document> docSet)
  Instantiates displayDocText, stopList, and docSet
Void buildIndex()
  for each item in docset, compare it to the stopList and then call buildSet() on it

CLI
Attributes:
None
Methods:
Private Set buildDocumentSet(String[] docNames)
  Instantiates documents with each item of docNames 
  and adds them to a returned set 
Void buildIndex(String[] args)
  Process the command line arguments, create a stopList document, 
  call buildDocumentSet, and create an InvertedIndex 
  with those and the documentsP flag

