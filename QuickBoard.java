
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuickBoard extends JFrame {
	
	JTextArea myTextArea;
	KeyHandler kh;
	
	public QuickBoard() {
		
		super( "QuickBoard" );
		
		myTextArea = new JTextArea();
		myTextArea.setLineWrap(true);
		myTextArea.setWrapStyleWord(true);
		kh = new KeyHandler( myTextArea );
		myTextArea.addKeyListener( kh );
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		c.setBackground( Color.WHITE );
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu( "File" );
		file.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		JMenu view = new JMenu( "View" );
		view.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		JMenu tools = new JMenu( "Tools" );
		tools.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		JMenu help = new JMenu( "Help" );
		help.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		
		JMenuItem wordOptions = new JMenuItem( "Word options" );
		wordOptions.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		
		JMenuItem qton = new JMenuItem( "QuickType ON" );
		qton.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		JMenuItem qtoff = new JMenuItem( "QuickType OFF" );
		qtoff.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		
		wordOptions.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				/* maybe in a seperate thread you should do this
				 * when the prog starts, and then just show and hide
				 * it from buttons and such
				 */
				WordOptionWindow ow = new WordOptionWindow();
			}
		});
		
		/*
		 * NOTE
		 *    YOU MUST CHECK TO SEE IF THE LISTENER IS ALREADY THERE OR NOT
		 *    OR ELSE YOU'LL GET SOME SPOOKY RELULTS BWAHAHA EXCLAMATION ONE
		 */
		
		qton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
			
				myTextArea.addKeyListener( kh );
			}
		});
		qtoff.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
			
				myTextArea.removeKeyListener( kh );
			}
		});
		
		view.add( wordOptions );
		tools.add( qton );
		tools.add( qtoff );
		
		menuBar.add( file );
		menuBar.add( view );
		menuBar.add( tools );
		menuBar.add( help );
		
		this.setJMenuBar( menuBar );
		
		Dimension minimumSize = new Dimension( 250, 80 );
		JScrollPane scrollPane = new JScrollPane( myTextArea );
		scrollPane.setMinimumSize( minimumSize );
		
		JLabel status = new JLabel( " " );
		status.setFont( new Font( "plain", Font.PLAIN, 12 ) );
		
		c.add( scrollPane, BorderLayout.CENTER );
		c.add( status, BorderLayout.SOUTH );
		
		this.setLocation( 300, 200 );
	}
	
	public static void main(String args[]) {
		
		QuickBoard m = new QuickBoard();
		
		m.setSize( 400, 400 );
		m.setDefaultCloseOperation( EXIT_ON_CLOSE );
		m.setVisible( true );
	}
}