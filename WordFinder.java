
import java.util.*;
import java.lang.Math;
import java.io.*;

public class WordFinder {
	
	Vector wordOptions;
	String[] optionsArray;
	Dictionary myDictionary;
	
	public WordFinder() {

		wordOptions = new Vector( 0, 1 );

		try {
			
			myDictionary = new Dictionary( "dictionary.txt" );
			
		} catch( FileNotFoundException e ) {
			
			System.out.println( e.getMessage() );
			System.exit( 1 );
			
		} catch( IOException e ) {
			
			System.out.println( e.getMessage() );
			System.exit( 1 );
		}
	}
	
	public Dictionary getDictionary() {
	
		return myDictionary;
	}
	
	/**
	 *  Method that calls findWordOptions and then clears the vector to be
	 *  used later
	 */
	public void makeWordOptions( String word ) {
		
		// Find all of the word options and put them in a vector
		findWordOptions( "", word );
		
		// Sort the vector
		Collections.sort( wordOptions );
		
		// Set array size to size of vector
		if( !wordOptions.isEmpty() ) {
			
			optionsArray = new String[ wordOptions.size() ];
			
			// Move values in vector over to array
			for( int x = 0; x < wordOptions.size(); x++ ) {
			
				optionsArray[ x ] = (String)wordOptions.get(x);
			}
		}
		
		else {
			
			optionsArray = new String[1];
			optionsArray[0] = null;
		}
		
		// Clear the vector 
		wordOptions.clear();
	}
	
	/** 
	 *  Method that puts into an array every possible combination of the 
	 *  given string
	 */
	public void findWordOptions( String prevWord, String word ) {
	
		String letter1, letter2;
		
		// The number of options is going to be 2^word.length()
		// It might be a good idea to look them up as they're created to see if
		// they exist.  If they don't, don't add them.  If they do, add them.
		// UPDATE: is this done?  I don't know...  p.s. use an array
		
		/*
		 * letter1 = array[ num value of the letter word.charAt(0) ];
		 * letter2 = array[ num value of the letter word.charAt(0) ];
		 */
		
		switch ( word.charAt(0) ) {
			
			case ';':
				letter1 = "a"; letter2 = "a";
				break;
			case 'a':
				letter1 = "a"; letter2 = "a";
				break;
			case 'b':
				letter1 = "b"; letter2 = "b";
				break;
			case 'c':
				letter1 = "c"; letter2 = "m";
				break;
			case 'd':
				letter1 = "d"; letter2 = "k";
				break;
			case 'e':
				letter1 = "e"; letter2 = "i";
				break;
			case 'f':
				letter1 = "f"; letter2 = "j";
				break;
			case 'g':
				letter1 = "g"; letter2 = "h";
				break;
			case 'h':
				letter1 = "h"; letter2 = "g";
				break;
			case 'i':
				letter1 = "i"; letter2 = "e";
				break;
			case 'j':
				letter1 = "j"; letter2 = "f";
				break;
			case 'k':
				letter1 = "k"; letter2 = "d";
				break;
			case 'l':
				letter1 = "l"; letter2 = "s";
				break;
			case 'm': 
				letter1 = "m"; letter2 = "v";
				break;
			case 'n':
				letter1 = "n"; letter2 = "b";
				break;
			case 'o':
				letter1 = "o"; letter2 = "w";
				break;
			case 'p':
				letter1 = "p"; letter2 = "q";
				break;
			case 'q':
				letter1 = "q"; letter2 = "p";
				break;
			case 'r':
				letter1 = "r"; letter2 = "u";
				break;
			case 's':
				letter1 = "s"; letter2 = "l";
				break;
			case 't':
				letter1 = "t"; letter2 = "y";
				break;
			case 'u':
				letter1 = "u"; letter2 = "r";
				break;
			case 'v':
				letter1 = "v"; letter2 = "n";
				break;
			case 'w':
				letter1 = "w"; letter2 = "o";
				break;
			case 'x':
				letter1 = "x"; letter2 = "x";
				break;
			case 'y':
				letter1 = "y"; letter2 = "t";
				break;
			case 'z':
				letter1 = "z"; letter2 = "z";
				break;
			case ',':
				letter1 = "c"; letter2 = "c";
				break;
			case '.':
				letter1 = "z"; letter2 = "x";
				break;
			default:
				letter1 = "" + word.charAt(0); 
				letter2 = "" + word.charAt(0);
				break;
		}
			
		if( word.length() == 1 ) {

			String wrd1 = prevWord + letter1;
			String wrd2 = prevWord + letter2;
			
			if( ( myDictionary.lookUp( wrd1 ) != -1 ) && 
			    ( !wordOptions.contains( wrd1 ) ) ) {
			  
				wordOptions.addElement( wrd1 );
			}
				
			if( ( myDictionary.lookUp( wrd2 ) != -1 ) &&
			    ( !wordOptions.contains( wrd2 ) ) ) {
			    
				wordOptions.addElement( wrd2 );
			}
		}
			
		else {
			
			String tmpWord1 = prevWord + letter1;
			String tmpWord2 = prevWord + letter2;

			word = word.substring( 1, word.length() );
		
			findWordOptions( tmpWord1, word );
			
			if( letter1 != letter2 )
				findWordOptions( tmpWord2, word );
		}
	}
	
	/**
	 *  Method that returns the word we need
	 */
	public String getString( String word, int position ) {
		
		if( word.charAt( word.length() - 1 ) == '/' 
			|| word.charAt( word.length() - 1 ) == '?' ) {
			
			System.out.println( word.substring( 0, word.length() - 1 ) );
			System.out.println( word.charAt( word.length() - 1 ) );
			
			makeWordOptions( word.substring( 0, word.length() - 1 ) );
			
			for( int x = 0; x < optionsArray.length; x++ )
				optionsArray[x] = optionsArray[x] + "?";
		}
		else if( word.charAt( word.length() - 1 ) == '.'
			|| word.charAt( word.length() - 1 ) == '>' ) {
			
			System.out.println( word.substring( 0, word.length() - 1 ) );
			System.out.println( word.charAt( word.length() - 1 ) );
			
			makeWordOptions( word.substring( 0, word.length() - 1 ) );
			
			for( int x = 0; x < optionsArray.length; x++ )
				optionsArray[x] = optionsArray[x] + ".";
		}
		else {
		
			makeWordOptions( word );
		}

		if( optionsArray[0] == null )
			return word;
		
		return optionsArray[ position % optionsArray.length ];
	}	
	
	/**
	 *  Method that finds the number of unique words for the key sequence
	 */
	public int numOptions( String word ) {
	
		makeWordOptions( word );
		
		return optionsArray.length;
	}
}