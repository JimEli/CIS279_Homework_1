/*************************************************************************
 * Title: Credit Card Account/Customer Database Program
 * File: EliJames_HW3.java
 * Author: James Eli
 * Date: 1/25/2017
 *
 * This program uses multiple classes to keep track of credit card account
 * information. It reads example data from a text file and displays the
 * data on the screen.
 *
 * Assumptions:
 *  (1) Test filename must be "HW3_Accounts.txt".
 *  (2) Test file must be located in same directory as java class file.
 *
 * Notes: 
 *  (1) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   01/25/2017: Initial release. JME
 *************************************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class EliJames_HW3 {
  // Lines of data per individual record inside the text file.
  private final static int LINES_PER_RECORD = 9;

  /*********************************************************************
   * Start of main program. Command line arguments are ignored.
   *********************************************************************/
  public static void main( String[] args ) { 
    // Create an ArrayList of Strings used to temporarily hold text file data.
	final List<String> lines = new ArrayList<String>();

    // Attempt to read entire text file into an ArrayList of Strings.
    try ( BufferedReader br = new BufferedReader( new FileReader( "HW3_Accounts.txt" ) ) ) {
      String s;
      
      while ( ( s = br.readLine() ) != null )  
    	if ( s.trim().length() > 0 ) // Skip blank lines.
          lines.add( s );
      
    // Catch various file system errors here.
    } catch( IOException | InvalidPathException e ) {
      System.err.println( "Cannot find file.\nEnsure data file is inside active directory." );
      return; // Exit.
    } catch( SecurityException e ) {
      System.err.println( "Cannot access file." );
      return; // Exit.
    } catch( Exception e ) {
      System.err.println( "Generic file error." );
      return; // Exit.
    }
 
    // We need 9 lines of data per each record, so detect obvious missing data in file. 
    if ( lines.size()%LINES_PER_RECORD != 0 ) {
      System.err.println( "Data in file is missing or corrupted." );
      System.exit( 1 );
    }

    // Create an array of credit card account objects per data read from file.
    CreditCardAccount[] accounts = new CreditCardAccount[ (lines.size()/LINES_PER_RECORD) ];
    
    try {
      // Process file in 9-line chunks, instantiating new account objects.
      for ( int i=0; i<(lines.size()/LINES_PER_RECORD); i++ ) {
        // Instantiate new account.
        accounts[ i ] = new CreditCardAccount( 
          Integer.parseInt( lines.get( i*LINES_PER_RECORD ) ),       // Account number.
          Double.parseDouble( lines.get( i*LINES_PER_RECORD + 1 ) ), // Starting balance.
          Double.parseDouble( lines.get( i*LINES_PER_RECORD + 2 ) ), // Ending balance.
          Double.parseDouble( lines.get( i*LINES_PER_RECORD + 3 ) ), // Credit limit.
          Double.parseDouble( lines.get( i*LINES_PER_RECORD + 4 ) ), // Interest Rate.
          Integer.parseInt( lines.get( i*LINES_PER_RECORD + 5 ) ),   // Customer ID.
          lines.get( i*LINES_PER_RECORD + 6 ),                       // Last name.
          lines.get( i*LINES_PER_RECORD + 7 ),                       // First name.
          Integer.parseInt( lines.get( i*LINES_PER_RECORD + 8) )     // Credit score.
        );
      }
    } catch( Exception e ) {
      // parseInt, parseDouble add & get exceptions: NullPointerException, NumberFormatException, IndexOutOfBoundsException.
      System.err.println( "Bad data found in file.\n" + e.getMessage() );
      return; // Exit.
    }

    // Display header for individual customer data.
    System.out.println( "Account    Customer   Last         Fisrt     Credit Credit    Ending   Account Penalty Average  Interest" );
    System.out.println( "Number     ID         Name         Name      Score  Limit     Balance  Status  Fee     Balance  Rate" );
    // Display individual account information.
    for ( CreditCardAccount a : accounts )
      System.out.println( a );

    // Normal program terminates here.

  } // End of main module.
  
} // End of EliJames_HW3 class.
