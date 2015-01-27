import java.util.ArrayList;
/**
 * This class simulates a bank account. Each bank 
 * account has a customer associated with it.
 * We can deposit and withdraw money from the bank
 * account and calculate compound interest.
 * Each bank account belongs to a specific bank.
 * 
 * @author Mairead Meagher, Siobhan Drohan, Ian Downey
 * @version 1
 */
public class BankAccount
{
    private int accNumber;           
    private String accountName;     
    private String accountAddress;   
    private double balance;
    
    /**
     * Constructor for objects of class BankAccount -
     * creates  BankAccount object
     * 
     * The data passed in as parameters is used to 
     * initialise the instance fields: bankName, 
     * bankManager and interestRate.  
     * 
     * For each instance field (except holdingBank):
     *     # the data passed in as a parameter is 
     *     validated to ensure that it conforms to
     *     the rules identified above.
     *     # if the data passed in is valid, the
     *     instance field is updated.
     *     # if the data passed in is invalid, a 
     *     default value is given to the instance 
     *     field and a message is printed out the terminal
     *       letting the user know that a default 
     *       value has been supplied.
     * @param accNumber account number
     * @param accountName account name
     * @param accountAddress account address
     * @param balance balance
     */
    public BankAccount(int accNumber, String accountName,
        String accountAddress, double balance)
    {
        if ( (accNumber >= 100000) && ( accNumber <= 999999) )
        {
            this.accNumber = accNumber;
        }
        else
        {
            this.accNumber = 100000;
        }
        if (accountName.length() <= 20)
        {
            this.accountName = accountName;
        }
        else 
        {
            this.accountName = accountName.substring(0, 20);
        }
        if (accountAddress.length() <= 30)
        {
            this.accountAddress = accountAddress;
        }
        else 
        {
            this.accountAddress = accountAddress.substring(0, 30);
        }
        if (balance >= 0 ) 
        {
            this.balance = balance;
        }
        else
        {
            this.balance = 0;
        }
    }
    
    /**
     * default constructor
     */
    public BankAccount()
    {
        accNumber = 111111;
        accountName = "Mary";
        accountAddress = "WIT";
        balance = 100;
    }
    
    /** 
     * @return the account number
     */
    public int getAccNumber()      
    {
        return accNumber;
    }  
        
    /**
     * This setter updates the instance field, accNumber, 
     * based on the rules defined above.
     * @param naccNumber new account number
     */
    public void setAccNumber(int naccNumber)
    { 
        if ( (accNumber >= 100000) && ( accNumber <= 999999) )
        {
            this.accNumber = accNumber;
        }
    }
    
    /** 
     * @return the account holder's name
     */       
    public String  getAccountName()
    {
        return accountName;
    }
        
    /**
     * This setter updates the instance field, 
     * accountName, based on the rules defined above.
     * @param naccountName new account name
     */      
    public void  setAccountName(String naccountName)
    {
        if (accountName.length() <= 20)
        {
            this.accountName = accountName;
        }
    }      
  
    /**
     * @return the address associated with the bank account
     **/
    public String getAccountAddress()
    {
        return accountAddress;
    }
        
    /**
     * This setter updates the instance field, 
     * accountAddress, based on the rules defined above.
     * @param accountAddress new account address
     */     
    public void setAccountAddress(String accountAddress)
    {
        if (accountAddress.length() <= 30)
        {
            this.accountAddress = accountAddress;
        }
    }
    
    /**
     * @return the balance associated with the bank account
     **/
    public double getBalance()
    {
        return balance;
    }
           
    /**
     * This setter updates the instance field, balance, 
     * based on the rules defined above.
     * @param balance new balance
     */     
    public void setBalance(double balance)
    {
        if (balance >= 0 ) 
        {
            this.balance = balance;
        }
    }   

    
    /**
     * This method calculates what the balance of 
     * the account WILL be in after a certain number of years.  
     * 
     * Using compound interest and the instance 
     * field, balance, as our starting point, 
     * we calculate the projected balance after a 
     * ?numberYears?. 
     * The interest rate we use is the interest
     * rate of the bank that the account is 
     * associated with (i.e holdingBank). 
     * 
     * NOTE: this method should NOT change the
     * value of the instance field, balance.
     * 
     * @param numberYears number of years
     * @param rate interest rate
     * @return the projected new balance, 
     * after a certain number of years.  
     * If the user entered a negative number for 
     *         numberYears, then -1 should be returned.
     **/   
    public double projectNewBalance(int numberYears, double rate) 
    { 
        if (numberYears >= 0 )
        {
            double newBalance = balance;
            int i = 1;
            while (i <= numberYears)
            {
                newBalance += (rate * newBalance) / 100.0;
                i++;
            }
            return newBalance;
        }
        else
            return -1;
    }
      
    /**
      * This method calculates how many years it 
      * will take the bank account's balance to 
      * reach (or exceed) a specific target balance.
      * 
      * Using the same compound interest formula
      * as before, this method calculates and 
      * returns how long it will take to reach the 
      * target value. (i.e. what is the minimum 
      * number of years that will take to equal
      * or exceed the target value)
      * 
      * @param target targeted balance
      * @param rate interest rate
      * @return the number of years it would take
      * the bank account to reach (or exceed) a 
      * target balance.  If the target entered is less 
      * than the current balance, -1 should be returned.
      **/   
    public int projectHowLong (double target, double rate)
    { 
        if (target >= balance) 
        {
            double newBalance = balance;
            int numYears = 0;
            while ( newBalance < target) 
            {
                newBalance += (rate * newBalance) / 100.0;
                numYears++;
            }
            return numYears;
        }
        else
        {
            return -1;
        }
    }
   
    /**
     *   This method prints out the projected balance
     *   at the end of EACH year from the start upto
     *   the year, numberYears (passed as a parameter). 
     *   
     *   Using compound interest and the instance 
     *   field, balance, as our starting point, we
     *   calculate the projected balance after a ?numberYears?. 
     *   The interest rate we use is the interest 
     *   rate of the bank that the account is 
     *   associated with (i.e holdingBank). 
     *
     *   For each year that the projected balance is
     *   calculated, it is printed to the terminal window,
     *   along with the year in question e.g.
     *          After year 1, the projected balance is ?124.56
     *          After year 2, the projected balance is ?145.56
     *   and so on. 
     *   
     *   NOTE: this method should NOT change the 
     *   value of the instance field, balance.
     *
     *   @param numberYears number of year
     *   @param rate interest rate
     *   @return the (final) projected new balance,
     *   after a certain number of years.  If the user
     *   entered a negative number for 
     *           numberYears, then -1 should be returned.
     */ 
    public double extendedProjectBalance(int numberYears, double rate)
    { 
        if (numberYears >= 0 )
        {
            double newBalance = balance;
            int i = 1;
            while ( i <= numberYears )
            {
                newBalance += (rate * newBalance) / 100.0;
                i++;
            }
            return newBalance;
        }
        else  
        {
            return -1;
        }
    }
    
    /**
     * This method allows the bank account holder to
     * withdraw their money.
     * @param amount money to withdraw
     */  
    public void withdraw(double amount)
    {
        if ( (amount <= balance) && (amount >= 0) )
        {
            balance -= amount;
        }
        else if (amount > balance)
        {
            System.out.println("You do not have" + 
                " sufficient funds to withdraw: " + amount + ".");
        }
        else 
        {
            System.out.println("You must withdraw a positive amount");
        }
    }
    
    /**
     * This method allows the bank account holder 
     * to lodge money into their account.
     * @param amount money to lodge
     */  
    public void lodge(double amount)
    {
        if (amount >= 0)
        {
            balance += amount;
        }
        else
        {
            System.out.println("You cannot lodge a negative amount");
        }
    }    
      

    /**
     * This method categorises the 
     * account into one of four categories. 
     * 
     * If the balance is:
     *      less than 100,                  it is a Category 1 account
     *      between 100 and 500,            it is a Category 2 account
     *      between 500 and 1000,           it is a Category 3 account
     *      greater than or equal to 1000   it is a Category 4 account
     *
     * @return the category as defined above.
     * Also print the category to the screen.
     */        
    public int getAccountCategory()
    {
        if ( balance < 100 )
        {           
            return 1;
        }
        else if (balance < 500 ) 
        {
            return 2;
        }
        else if (balance < 1000) 
        {
            return 3;
        }
        else
        {
            return 4;
        }
    }
    
    /**
     * This method takes in a double and converts
     * it to a two decimal place double.  
     * This newly converted double is then returned.
     * 
     * For example:
     *      if we have a variable declared as so:
     *          double balance = 456.232434432;
     *      and we call the following code:    
     *          toTwoDecimalPlaces(balance);
     *      the following number, 
     *      of type double will be returned:  456.23 
     */
    private  double toTwoDecimalPlaces( double num )
    {   
        return (int) (num * 100 ) / 100.0; 
    } 
      
    /**
     * @return the String version of the bank 
     * account object.  The returned String 
     * contains labels for each of the instance 
     * fields 
     * and is formatted like so:
     *      
     *        Account number: 222222
     *            Account Name:    Mary
     *            Account Address: WIT
     *            Account balance: 1500.0
     *        
     *        Bank Name: AIB Bank
     *            Bank Manager:  Richie Manager
     *            Interest Rate: 10.0
     */
    public String toString()
    {
        return ("Account number: " + accNumber 
            + ", Account Name: " + accountName 
            + ", Account Address: " + accountAddress
            + ", Account balance: " 
            + toTwoDecimalPlaces(balance));
    } 
    

}