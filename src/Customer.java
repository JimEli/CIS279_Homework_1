/*************************************************************************
 * Title: Customer Class
 * File: Customer.java
 * Author: James Eli
 * Date: 1/25/2017
 *
 * This is a simplistic class defining attributes for a credit card customer 
 * account. It contains 4 fields for id number, last and first names and 
 * credit score. This class is intended to be incorporated in a parent class.
 * For additional information see the CreditCardAccount class. The fields 
 * are all defined private. The class includes basic constructors, getters, 
 * setters and an overridden toString method for all of the fields. Input 
 * validation is performed as required to assure the fields are in an 
 * acceptable format and range. 
 *
 * Notes:
 *  (1) See comments inside CreditCardAccount.java for more information.
 *  (2) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 *  
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   01/25/2017: Initial release. JME
 *************************************************************************/

public class Customer {
  /*********************************************************************
   * Constant field limitations (all public)
   *********************************************************************/
  public static final int MIN_CUSTOMER_ID = 0; // Minimum & maximum account number range.
  public static final int MAX_CUSTOMER_ID = (int)Integer.MAX_VALUE;
  public static final int MIN_CREDIT_SCORE = 300; // Minimum FICO score.
  public static final int MAX_CREDIT_SCORE = 850; // Maximum FICO score.
  
  /*********************************************************************
   * Instance fields (all private)
   *********************************************************************/
  private int customerID;   // Customer ID.
  private String lastName;  // Customer last name.
  private String firstName; // Customer first name.
  private int creditScore;  // Customer credit score.
  
  /*********************************************************************
   * Class constructors.
   *********************************************************************/
  // Naked constructor.
  public Customer() { }

  // 4-parameter constructor.
  public Customer( int id, String lName, String fName, int score ) {
    // Validate all fields.
    setCustomerID( id );
    setLastName( lName );
    setFirstName( fName );
    setCreditScore( score );
  }

  /*********************************************************************
   * Class mutators.
   *********************************************************************/
  public void setCustomerID( int id ) { 
  	// Throw exception for invalid customer number.
    if ( id < MIN_CUSTOMER_ID || id > MAX_CUSTOMER_ID )
      throw new IllegalArgumentException( "Customer number must be between " + MIN_CUSTOMER_ID + "and " + MAX_CUSTOMER_ID + "." );
    else
	  this.customerID = id; 
  }

  public void setLastName( String name ) { 
  	// Throw exception for null last name.
    if ( name == null ) 
      throw new NullPointerException( "Customer last name can't be null." );
    else 
	  this.lastName = name; 
  }

  public void setFirstName( String name ) { 
  	// Throw exception for null first name.
    if ( name == null ) 
      throw new NullPointerException( "Customer first name can't be null." );
    else 
	  this.firstName = name; 
  }

  public void setCreditScore( int score ) { 
  	// Throw exception for invalid credit score.
    if ( score < MIN_CREDIT_SCORE || score > MAX_CREDIT_SCORE ) 
      throw new IllegalArgumentException( "Customer credit score can't be < " + MIN_CREDIT_SCORE + " or > " + MAX_CREDIT_SCORE + "." );
    else
	  this.creditScore = score; 
  }

  /*********************************************************************
   * Class accessors.
   *********************************************************************/
  public int getCustomerID() { return this.customerID; }
  public String getLastName() { return this.lastName; }
  public String getFirstName() { return this.firstName; }
  public int getCreditScore() { return this.creditScore; }

  /*********************************************************************
   * Overridden methods.
   *********************************************************************/
  @Override
  public String toString() {
    // Produce formatted string output for display.
    return String.format( "%-10d %-12s %-9s %3d", getCustomerID(), getLastName(), getFirstName(), getCreditScore() );
  }

} // End of Customer class.
