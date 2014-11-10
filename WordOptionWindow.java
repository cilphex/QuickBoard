
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordOptionWindow extends JFrame {

	private JPanel wordListPanel = new JPanel();
	private JLabel status = new JLabel( " " );
	private WordFinder generator;
	
	public WordOptionWindow() {
	
		super( "Word options" );
		generator = new WordFinder();
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		c.setBackground( Color.WHITE );
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu( "File" );
		file.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		JMenu generate = new JMenu( "Generate" );
		generate.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		JMenu organize = new JMenu( "Organize" );
		organize.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		
		// Make & add JMenuItems & listeners here
		
		JMenuItem generateWordList = new JMenuItem( "Word list" );
		generateWordList.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		generateWordList.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				OptionGenerator x = new OptionGenerator();
				x.start();
			}
		});
		
		generate.add( generateWordList );
		
		menuBar.add( file );
		menuBar.add( generate );
		menuBar.add( organize );
		
		this.setJMenuBar( menuBar );

		wordListPanel.setLayout( new GridLayout( 1, 0 ) );
		
		JScrollPane scrollPane = new JScrollPane( wordListPanel );

		status.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		
		c.add( scrollPane, BorderLayout.CENTER );
		c.add( status, BorderLayout.SOUTH );
		
		this.setSize( 400, 400 );
		this.setLocation( 400, 200 );
		this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		this.setVisible( true );
		
		WordFiller q = new WordFiller();
		q.start();
	}
	
	private class WordFiller extends Thread {
	
		JList wordList;
		JList wordList2;
		JList wordList3;
	
		public WordFiller() {
		
			wordList = new JList();
			wordList2 = new JList();
			wordList3 = new JList();
		}
		
		public void run() {
		
			wordList.setFont( new Font( "plain", Font.PLAIN, 12 ) );
			wordList.setListData( generator.getDictionary().getWords() );
			wordListPanel.add( wordList );
		
			wordList2.setFont( new Font( "plain", Font.PLAIN, 12 ) );
			wordList2.setListData( generator.getDictionary().getWords() );
			wordListPanel.add( wordList2 );
		
			wordList3.setFont( new Font( "plain", Font.PLAIN, 12 ) );
			
			return;
		}
	}
	
	private class OptionGenerator extends Thread {

		int options = 0;
		int tooLong = 0;
		
		String[] tmpOpt = new String[ 
			generator.getDictionary().getWords().length ];
		String longestOption = "";
		int mostOptions = 0;
		String mostWords = "";
		
		public OptionGenerator() {
		
		}
		
	public void run() {
			
		for( int x = 0; x < tmpOpt.length; x++ ) {
		
			int l = 0;
			String m = generator.getDictionary().getWords()[ x ];
			
			if( m.length() > 26 ) {
			
				tooLong++;
			}
			
			else {
			
				l = generator.numOptions( m );
			
				System.out.print( l + "\t" + m );
			
				if( l > mostOptions ) {
					mostOptions = l;
					mostWords = m;
				}
				
				if( m.length() > longestOption.length() && l > 1 ) 
					longestOption = m;
			}
			
			if( l > 1 ) {

				options++;
			}
			
			System.out.println();
		}
		
		System.out.println( "--------------------------------------" );
		
		System.out.println( "\nWord with most combinations is " +
				    mostWords + 
				    " with " + mostOptions + " word options.\n" );
		
		System.out.println( "Longest multi option word is " + 
				    longestOption + 
				    " with length " + longestOption.length() +
				    " characters.\n" );
				    
		System.out.println( "Searched " + tmpOpt.length + " words.\n" +
				    options + " words had more than one " +
				    "word option.\n" );
		
		System.out.println( tooLong + " words were too long.\n" );
		
		System.out.println( "--------------------------------------" );
		
		return;
	}
	
	}
}