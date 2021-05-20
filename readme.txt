

Status of KB5 - left behind now:
Very interesting situation:
1.  This is GK version with VO code cut in somewhat minamally to achieve Symbols as Containers (?) from MontessoriPRINT function.
2.  Still using GK SymbolPaths Library (not JSON yet)
3.  Added Hoover Peek but needs to be cleaned up!
4.  Last project before integrating was not really finished but is working with   ReadFile + BufferLoad (or whatever) in the Punctuation and allowing scrolling one line at a time.  
     but underlying idea was to BUFFER all the Sentences for a given snapshot of the KeyStream (document view).
	 
5.  The whole is Functioning but many TODOS and suspect duplicate code.   My new   symbolSET    class should be used whereever and howefver possible, I think.   

6 VO version also assumed the svg paths are simply appended together and presented as a single svg Blob which I did and it has been working.... BUT???   

Pending INDICATOR decision - probably just leave it in there...  




GENERAL GOALS INTO KB6..
=========================

Refactor code.  remove duplicate ways of doing same thing... (language )....

Symbols to be made perhaps 10% smaller so that space with the six sentences to do some things toward "grouping"  Symbols.   Animations on one active sentence?   Whatever....

Symbol alignment and sizing of path to be corrected and improved toward VO version at first.  Verb larger, and descending might make sense.

Adopt to VO ways to deal with Board.

Look to explore Drag & Drop and   some hint of Animations (Verbs bouncing in)

Look to use higher level objects such as for Screen Panels. 





