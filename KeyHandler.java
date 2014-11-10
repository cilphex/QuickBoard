
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class KeyHandler implements KeyListener {
	
	JTextArea txtInner;
	String strWord;
	WordFinder myFinder;
	boolean keyDown = false;
	String word;
	int l, r, position = 0;
	
	public KeyHandler( JTextArea t ) {
		
		txtInner = t;
		myFinder = new WordFinder();
		word = "";
	}
	
	public void keyPressed( KeyEvent e ) {
	
		System.out.println( "key pressed: " + handleEvent( e ) );
	}
	
	public void keyReleased( KeyEvent e ) {
	
		if( e.getKeyCode() == e.VK_BACK_SPACE ) {
		
			getTheCurrentWord();
			//System.out.println( word + "L: " + l + " R: " + r );
			word = txtInner.getText().substring( l + 1, r );
			//System.out.println();
		}
	
		txtInner.setEditable( true );
	}
	
	
	public void keyTyped( KeyEvent e ) {
	
	}
	
	public String handleEvent( KeyEvent e ) {
		
		int begin = txtInner.getText().lastIndexOf(" ") + 1;
		int end = txtInner.getText().length();
		
		if( e.getKeyChar() == ' ' ) {
			
			position = 0;
			word = "";
			txtInner.setEditable( false );
			txtInner.append( " " );
			return " ";
		}
		
		if( e.getKeyChar() == e.VK_ENTER ) {
		
			position = 0;
			word = "";
			txtInner.setEditable( false );
			txtInner.append( "\n" );
			return "ENTER";
		}
			
		else if( e.getKeyCode() == e.VK_BACK_SPACE ) {

			position = 0;
			txtInner.replaceRange( "", begin, end );
			
			return "BACKSPACE";
		}
		
		else if( e.getKeyCode() == e.VK_SHIFT ) {
			
			position++;
		}
		
		else {
			
			position = 0;
			word = word + e.getKeyChar();
		}
		
		if( getTheCurrentWord() ) {
			txtInner.replaceRange(
				myFinder.getString( word, position ), l + 1, r );
		}
		
		txtInner.setEditable( false );
		
		return word;
	}
	
	/*
	 * This method finds the current "l" and "r" boundaries to replace
	 * with a word
	 */
	public boolean getTheCurrentWord() {
		
		String str = txtInner.getText();
		int pos = txtInner.getCaretPosition();
		char tmp;
		
		// find left bound
		for (l = pos-1; l > -1; l--) {
			
			tmp = str.charAt(l);
			
			if (Character.isWhitespace(tmp)) {
				
				break;
			}
		}
			
		// find right bound
		for (r = pos; r < str.length()-1; r++) {
			
			tmp = str.charAt(r);
			
			if (Character.isWhitespace(tmp)) {
				
				break;
			}
		}
			
		return true;
	}
}