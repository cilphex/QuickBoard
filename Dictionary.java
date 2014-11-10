/*
 * Dictionary.java
 *
 * Version:
 *     $Id: Dictionary.java,v 1.1 2004/01/28 04:18:59 rjg4013 Exp rjg4013 $
 *
 * Revisions:
 *     $Log: Dictionary.java,v $
 *     Revision 1.1  2004/01/28 04:18:59  rjg4013
 *     Initial revision
 *
 */

import java.io.*;
import java.util.*;

/**
 * This class creates objects that are capable of doing simple
 * lookups in a sorted dictionary of words.  The Searchers class
 * is used to provide the searching capability.
 *
 * @author     Craig Hammell - cah7080
 *
 */

public class Dictionary {

	// Constant used to indicate search failure
	public static final int NOT_FOUND = -1;

	// The array in which the dictionary is stored
	private String[] words;

	// the array which holds the indexes of the letters in the dictionary
	private int[] alphaIndex = new int[ 26 ];

	public Dictionary( String fileName ) throws 
                                      FileNotFoundException, IOException {

		BufferedReader in = new BufferedReader( new FileReader( fileName ) );

		Vector tmpDict = new Vector();
		String nextWord;
		int currentValue = 10;
		int location = 0;

		// Read the words from the file.  Assumes one word per line
		while ( ( nextWord = in.readLine() ) != null ) {

			nextWord = nextWord.toLowerCase();

			if( currentValue - 10 < 26 ) {

				if( Character.getNumericValue( nextWord.charAt(0) ) 
					== currentValue ) {

					alphaIndex[ currentValue - 10 ] = location;
					currentValue++;
				}
			}

			// Add the word to the dictionary
			tmpDict.addElement( nextWord );
			location++;
		}

		// Convert the Vector containing the words to an array
		words = ( String[] )tmpDict.toArray( new String[ 0 ] );

		in.close();
	}
	
	/*
	 * Return the array of words that compose the dictionary
	 */
	public String[] getWords() {
	
		return words;
	}

	/**
	 * Lookup the given string in the dictionary - the lookup is NOT 
	 * case-sensitive.  The constant NOT_FOUND will be returned if 
	 * the word is not in the dictionary.
	 *
	 * @param target the string being searched for in the dictionary.
	 *
	 * @return NOT_FOUND if the string cannot be found.
	 */
	public int lookUp( String target ) {

		target = target.toLowerCase();
		int firstLetter = Character.getNumericValue( target.charAt( 0 ) );
		int low = alphaIndex[ firstLetter - 10 ];
		int high;

		if ( firstLetter - 10 == 25 ){
		
			high = length() - 1;
		
		} else {
	    
			high = alphaIndex[ firstLetter - 9 ];
		}

		return binarySearch( words, target, low, high );
	}


	// Return the number of words in the dictionary
	public int length() {
	
		return words.length;
	}

	// This will do our lookup of the word
	public static int binarySearch( Comparable[] array, 
			     Comparable target,
			     int low,
			     int high ) {
	
		int result;
		int mid = ( low + high ) / 2;
		int compare = array[ mid ].compareTo( target );

		while( ( low <= high ) && ( compare != 0 ) ) {

			if ( compare < 0 ) {
			
				low = mid + 1;
		
			} else {
			
				high = mid - 1;
			}

			mid = ( low + high ) / 2;

			// Should compare the word passed (part or whole) to the 
			// beginning of the word in the dictionary of equal length
			compare = array[ mid ].compareTo( target );
		}

		if( low > high ) {
		
			result = NOT_FOUND;
		
		} else {
	    
			result = mid;
		}

		return result;
	}
    
	/**
	 * Search for the Comparable target in the specified array 
	 * using a linear search.  This method makes no assumptions
	 * regarding the order in which the objects appear in the
	 * array being searched (i.e., the objects do not need to be
	 * sorted). The instance variable numCompares will be 
	 * incremented each time a comparison is made.
	 *
	 * @param  array  array of items to be searched
	 * @param  target item being looked for in the array.
	 *
	 * @return        index in the array where the target was found or
	 *                NOT_FOUND if the target is not in the array.
	 */
	public static int linearSearch( Comparable[] array, Comparable target ) {
	
		int retval = NOT_FOUND;

		// Search for the target until it is found or the end of the
		// array is reached.

		for ( int i = 0; i < array.length && retval == NOT_FOUND; i++ ) {

			if ( target.compareTo( array[ i ] ) == 0 ) {
		
				retval = i;
			}
		}

		return retval;
	}
}