# Overview
This program is a search engine using an inverted index. You provide the names/locations of the documents you would like to search, it reads the documents at the beginning and stores the words in an inverted index, and you can search for which documents contain inputted word(s). 

# Command line Usage
Usage: java CLI [-d] stopList Documents

The optional -d flag, if present, not only returns the names of documents that contain the search word(s), but returns the entire text of those documents as well. The stoplist is a document containing common words that the user would like the program to ignore if they are in a search e.g. "the", "a", "I", "is"... Documents represent all the documents you would like to include for searching, separated by commas. These documents must include the entire path to the documents, e.g. ~/andrewfallon/documents/testfiles/file1, ~/andrewfallon/documents/testfiles/file2.

# Searching
Once the documents have been read, simply type in your search string and hit enter. You can search as many times as you want while the program is running, and to end the program, simply press ctrl-d
