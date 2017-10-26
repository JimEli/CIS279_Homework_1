/*************************************************************************
 * Title: Credit Card Account Class
 * File: CreditCardAccount.java
 * Author: James Eli
 * Date: 1/25/2017
 *
 * This is a simple class that defines attributes for a credit card account. 
 * It contains 5 fields for: account number, starting and ending balance, 
 * credit limit and interest rate. Additional information is contained in
 * the included Customer class. The fields are all defined private. The 
 * class includes basic constructors, getters, setters and an overridden 
 * toString method for all of the fields. Limited input validation is 
 * performed as required to assure the fields are in an acceptable format 
 * and range. 
 *
 * Notes:
 *  (1) See comments inside Customer.java for more information.
 *  (2) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 *  
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   01/25/2017: Initial release. JME
 *************************************************************************/
public class CreditCardAccount {

  /*********************************************************************
   * Constant field limitations (all public)
   *********************************************************************/
  public static final int MIN_ACCOUNT_NUMBER = 0;        // Account number range.
  public static final int MAX_ACCOUNT_NUMBER = (int)Integer.MAX_VALUE;
  public static final double MAX_INTEREST_RATE = 100.0d; // Maximum applicable interest rate.
  public static final double PENALTY_RATE = 0.05d;       // Charge for exceeding credit limit (multiplied by ending balance amount).
  
  /*********************************************************************
   * Instance fields (all private)
   *********************************************************************/
  private int accountNumber;   // Account number.
  private double startBalance; // Beginning account  balance.
  private double endBalance;   // Ending account balance.
  private double creditLimit;  // Account credit limit.
  private double interestRate; // Interest rate.
  // Customer object, includes ID, first & last name and credit score.
  private Customer customer = new Customer();
 
  /*********************************************************************
   * Class constructor.
   *********************************************************************/
  // 9-parameter constructor.
  public CreditCardAccount( 
      int number,      // Account number.
      double startBal, // Starting balance.
      double endBal,   // Ending balance.
      double limit,    // Credit limit.
      double rate,     // Interest rate.
      int id,          // Customer ID.
      String lName,    // Customer last name.
      String fName,    // Customer first name.
      int score        // Customer credit score.
  ) {
    // Validate fields below.
    setAccountNumber( number );
    setStartBalance( startBal );
    setEndBalance( endBal );
    setCreditLimit( limit );
    setInterestRate( rate );
    customer.setCustomerID( id );
    customer.setLastName( lName );
    customer.setFirstName( fName );
    customer.setCreditScore( score );
  }

  /*********************************************************************
   * Class mutators.
   *********************************************************************/
  public void setAccountNumber( int number ) {
   // Throw exception for invalid account number.
   if ( number < MIN_ACCOUNT_NUMBER || number > MAX_ACCOUNT_NUMBER ) 
     throw new IllegalArgumentException( "Account number must be between " + MIN_ACCOUNT_NUMBER + "and " + MAX_ACCOUNT_NUMBER + "." );
   else
     this.accountNumber = number; 
  }
  
  // Both positive and negative balances allowed.
  public void setStartBalance( double balance ) { this.startBalance = balance; }
  public void setEndBalance( double balance ) { this.endBalance = balance; }
 
  public void setCreditLimit( double limit ) { 
    // Throw exception for invalid credit limit.
    if ( limit < 0.d ) 
      throw new IllegalArgumentException( "Credit limit cannot be < 0." );
    else
      this.creditLimit = limit; 
  }
  
  public void setInterestRate( double rate ) { 
    // Throw exception for invalid interest rate.
    if ( rate < 0.d || rate > MAX_INTEREST_RATE ) 
      throw new IllegalArgumentException( "Interest rate be between 0.0 and " + MAX_INTEREST_RATE + "%." );
    else
      this.interestRate = rate; 
  }
  
  // Validation for the following performed inside Customer Class.
  public void setCreditScore( int score ) { customer.setCreditScore( score ); }
  public void setLastName( String name ) { customer.setLastName( name ); }
  public void setFirstName( String name ) { customer.setFirstName( name ); }
  public void setCustomerID( int id ) { customer.setCustomerID( id ); }

  /*********************************************************************
   * Class accessors.
   *********************************************************************/
  public int getAccountNumber() { return this.accountNumber; }
  public double getStartBalance() { return this.startBalance; }
  public double getEndBalance() { return this.endBalance; }
  public double getCreditLimit() { return this.creditLimit; }
  public double getInterestRate() { return this.interestRate; }
  public int getCustomerID() { return customer.getCustomerID(); }
  public String getLastName() { return customer.getLastName(); }
  public String getFirstName() { return customer.getFirstName(); }
  public int getCreditScore() { return customer.getCreditScore(); }

  /*********************************************************************
   * Overridden methods.
   *********************************************************************/
  @Override
  public String toString() {
    // Produce formatted string output for display.
    String s = String.format( "%-10d %s %11.2f %8.2f  ", getAccountNumber(), customer, getCreditLimit(), getEndBalance() );
    // If ending balance is greater than credit limit display "OVER", otherwise display "OK".
    s += String.format( "%-6s", (getEndBalance() > getCreditLimit()) ? "OVER" : "OK" );
    // If end balance is greater than credit limit, then display penalty amount (endBalance*PENATLY_RATE), otherwise display zero.
    s += String.format( "%8.2f", (getEndBalance() > getCreditLimit()) ? getEndBalance()*PENALTY_RATE : 0. );
    // Calculate & display average of start & end balance, and interest rate. 
    return s += String.format( "%9.2f %3.0f%%", (getEndBalance() + getStartBalance())/2., getInterestRate()*100. );
  }

} // End of CreditCardAccount class.
