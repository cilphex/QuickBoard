import java.io.*;
import java.util.*;

public class LongestWord {

	public static void main( String[] args ) {
	
		try {
	
			BufferedReader in = new BufferedReader( 
		                            new FileReader( "dictionary.txt" ) );
		
			String longestWord = "";
			String nextWord;
		
			System.out.println( "\nSearching for longest word..." );
		
			while( ( nextWord = in.readLine() ) != null ) {
		
				if( nextWord.length() > longestWord.length() )
					longestWord = nextWord;
			}
		
			in.close();
		
			System.out.println( "The longest word is: " + 
			                    longestWord + "\n" );
		
		} catch( Exception e ) {
		
			System.out.println( e.getMessage() );
		}
	}
}