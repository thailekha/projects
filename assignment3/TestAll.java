import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * This class tests your assignment.  Don't make any changes in this class; all changes should be made in your own class.
 *
 * @author  Mairead Meagher, Siobhan Drohan, Ian Downey
 * @version 13/10/2014
 */
public class TestAll
{
    private Bank aib, invalidBank;            
    private BankAccount account1,  account2, account3, invalidAccount;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    { 
        aib         = new Bank("AIB Bank", "Richie Manager", 10);
        invalidBank = new Bank("This bank name is far too long for this program", 
                               "This manager's name is far too long for this program", 1500);
   
        account1       = new BankAccount(111111, "Mary", "WIT", 100);
        account2       = new BankAccount(222222, "John", "CIT", 8000);
        account3       = new BankAccount(333333, "Joan", "AIT", 5500);
        invalidAccount = new BankAccount(-444444, "This name is far too long for this program", 
                                         "This address is far too long for this program", -500);
              
        aib.addBankAccount(account1); 
        aib.addBankAccount(account2); 
        aib.addBankAccount(account3); 
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    //    P H A S E   1   T E S T S
    //-----------------------------------------------------------------------------------------------------------------
    @Test 
    /**
      * This method tests that the Bank constructor has worked 
      */
    public void testBankConstructors()   
    { 
        //valid data
        assertEquals(aib.getBankName(), "AIB Bank");
        assertEquals(aib.getBankManager(), "Richie Manager");  
        assertEquals(aib.getInterestRate(), 10.0, 0.1);  
        assertEquals(aib.getBankAccounts().size(), 3);  
      
        //invalid data 
        assertEquals(invalidBank.getBankName(), "This bank name ");
        assertEquals(invalidBank.getBankManager(), "This manager's name ");  
        assertEquals(invalidBank.getInterestRate(), 0, 0.1);  
    }
    
    @Test 
    /**
     * This method tests the Bank setters
     */
    public void testBankSetters()   
    {
        assertEquals(aib.getBankName(),"AIB Bank");
        aib.setBankName("Bank of Ireland");
        assertEquals(aib.getBankName(), "Bank of Ireland");
        aib.setBankName("Really, really long bank name");
        assertEquals(aib.getBankName(), "Bank of Ireland");
        
        assertEquals(aib.getBankManager(), "Richie Manager");
        aib.setBankManager("New Manager");
        assertEquals(aib.getBankManager(), "New Manager");
        aib.setBankName("Really, really long bank manager's name");
        assertEquals(aib.getBankManager(), "New Manager");        
   
        assertEquals(aib.getInterestRate(), 10, 0.1);   
        aib.setInterestRate(3); 
        assertEquals(aib.getInterestRate(), 3, 0.1); 
        aib.setInterestRate(300); // outside the valid range
        assertEquals(aib.getInterestRate(), 3, 0.1); 
       
        assertEquals(aib.getBankAccounts().size(), 3);   
        aib.setBankAccounts(new ArrayList<BankAccount>());
        assertEquals(aib.getBankAccounts().size(), 0);           
    }
    
    @Test
    public void testAddRemoveListArrayList()
    {
        //testing addBankAccount
        BankAccount account4 = new BankAccount(444444, "Michael", "UCD", 1250);
        aib.addBankAccount(account4);
        assertEquals(aib.getBankAccounts().size(),4);     

        //testing removeBankAccount
        aib.removeBankAccount(4);
        assertEquals(aib.getBankAccounts().size(),4);   
        aib.removeBankAccount(-4);
        assertEquals(aib.getBankAccounts().size(),4);   
        aib.removeBankAccount(3);
        assertEquals(aib.getBankAccounts().size(),3);   

        /* -----------------------------------
         * Actual Output from listBankAccounts
         * -----------------------------------
         * Account number: 111111, Account Name: Mary, Account Address: WIT, Account balance: 100.0
         * Account number: 222222, Account Name: John, Account Address: CIT, Account balance: 8000.0
         * Account number: 333333, Account Name: Joan, Account Address: AIT, Account balance: 5500.0
         */
        System.out.println("-----------------------------------");
        System.out.println("Actual Output from listBankAccounts");
        System.out.println("-----------------------------------");
        System.out.println(aib.listBankAccounts());
    } 

    @Test
    public void testAverageBankBalance()
    {
        assertEquals(aib.averageBankBalance(),4533.33,0.1);   
    }

    @Test
    public void testListAccountsMaxBalance()
    {
       /* -----------------------------------------
        * Actual Output from listAccountsMaxBalance
        * -----------------------------------------
        * Testing no bank accounts in ArrayList:
        *     No Bank Accounts
        * Testing NO bank accounts with balance less than max balance:
        *     No Bank Accounts
        * Testing bank accounts with balance less than max balance (should be):
        *     Account: Mary(111111): 100.0
        * -----------------------------------------
        */
        System.out.println("-----------------------------------------");
        System.out.println("Actual Output from listAccountsMaxBalance");
        System.out.println("-----------------------------------------");
        System.out.println("Testing no bank accounts in ArrayList:");
        System.out.println("\t" + invalidBank.listAccountsMaxBalance(99));
        System.out.println("Testing NO bank accounts with balance less than max balance:");
        System.out.println("\t" + aib.listAccountsMaxBalance(99));
        System.out.println("Testing bank accounts with balance less than max balance:");
        System.out.println("\t" + aib.listAccountsMaxBalance(5500));
    }
    
    @Test
    public void testListAccountsByCategory()
    {
        /*---------------------------------------------
         * Actual Output from testListAccountsByCategory
         * ---------------------------------------------
         * Testing when no category 1 bank accounts are in the ArrayList:
         *     No Bank Accounts
         * Testing when category 2 bank accounts are in the ArrayList:
         *     Category 2 accounts:
         *     111111: 100.0
         * 
         * Testing no bank accounts in ArrayList:
         *     No Bank Accounts
         */
        System.out.println("---------------------------------------------");
        System.out.println("Actual Output from testListAccountsByCategory");
        System.out.println("---------------------------------------------");
        System.out.println("Testing when no category 1 bank accounts are in the ArrayList:");
        System.out.println("\t" + aib.listAccountsByCategory(1));
        System.out.println("Testing when category 2 bank accounts are in the ArrayList:");
        System.out.println("\t" + aib.listAccountsByCategory(2));
        System.out.println("Testing no bank accounts in ArrayList:"); 
        System.out.println("\t" + invalidBank.listAccountsByCategory(1));
    }     
    
    @Test
    public void testHighestBankBalance()
    {
        assertEquals(aib.highestBankBalance(),account2);   
        assertNull(invalidBank.highestBankBalance());   
    }

    //-----------------------------------------------------------------------------------------------------------------
    //    P H A S E   2   T E S T S
    //-----------------------------------------------------------------------------------------------------------------
    @Test 
    /**
     * This method tests the methods that calculate  compound interest 
     */
    public void testProjectMethods()   
    {
        assertEquals(account1.projectNewBalance(2, aib.getInterestRate()), 121.0, 0.1);
        assertEquals(account1.projectNewBalance(-2, aib.getInterestRate()), -1, 0.1);
           
        assertEquals(account1.extendedProjectBalance(2, aib.getInterestRate()), 121.0, 0.1);
        assertEquals(account1.extendedProjectBalance(-2, aib.getInterestRate()), -1, 0.1);
          
        assertEquals(account1.projectHowLong (120, aib.getInterestRate()), 2, 0.1);
        assertEquals(account1.projectHowLong (90, aib.getInterestRate()), -1, 0.1);
        assertEquals(account1.extendedProjectBalance(-1, aib.getInterestRate()), -1, 0.1);
    }
    
    @Test
    public void testToStrings()
    {
        /*
         * --------------------------------
         * Actual Output from testToStrings
         * --------------------------------
         * Name: AIB Bank   Bank Manager: Richie Manager  Interest Rate: 10.0
         * Bank Details: 
         *      Account number: 111111, Account Name: Mary, Account Address: WIT, Account balance: 100.0
         *      Account number: 222222, Account Name: John, Account Address: CIT, Account balance: 8000.0
         *      Account number: 333333, Account Name: Joan, Account Address: AIT, Account balance: 5500.0
         *      
         * Name: This bank name    Bank Manager: This manager's name   Interest Rate: 0.0
         * Bank Details: 
         *     No Bank Accounts
         */
        System.out.println("--------------------------------");
        System.out.println("Actual Output from testToStrings");
        System.out.println("--------------------------------");
        System.out.println(aib);
        System.out.println(invalidBank);
    }
   
    //-----------------------------------------------------------------------------------------------------------------
    //    P H A S E   3   T E S T S
    //-----------------------------------------------------------------------------------------------------------------
    @Test
    public void testListProjectedPortfolioTimeframe()
    {
        /* --------------------------------------------------------------------
         * If your tests below are successful, the actual output from the 
         * four listProjectedPortfolioBalanceTimeframe method calls should be:
         * --------------------------------------------------------------------
         * 
         * Desired balance is 100.0
         *     Account:  111111.  Current balance: 100.0. Years to reach projected balance: 0
         *     
         * Desired balance is 4499.0
         *     Account:  111111.  Current balance: 100.0. Years to reach projected balance: 40
         *     
         * Desired balance is 7999.0
         *     Account:  111111.  Current balance: 100.0. Years to reach projected balance: 46
         *     Account:  333333.  Current balance: 5500.0. Years to reach projected balance: 4
         *     
         * No Bank Accounts
         * --------------------------------------------------------------------
         */
        System.out.println("---------------------------------------------------------");
        System.out.println("Actual Output from listProjectedPortfolioBalanceTimeframe");
        System.out.println("---------------------------------------------------------");
        System.out.println(aib.listProjectedPortfolioTimeframe(100));
        System.out.println(aib.listProjectedPortfolioTimeframe(4499));
        System.out.println(aib.listProjectedPortfolioTimeframe(7999));
        System.out.println(invalidBank.listProjectedPortfolioTimeframe(100));        
    }

    @Test
    public void testListProjectedPortfolioBalance()
    {
        /*
         * ------------------------------------------------
         * Actual Output from listProjectedPortfolioBalance
         * ------------------------------------------------
         * Projected Balance after 3 years:
         *     Account:  111111.  Current balance: 100.0. Projected balance: 133.1. Difference: 33.09
         *     Account:  222222.  Current balance: 8000.0. Projected balance: 10648.0. Difference: 2648.0
         *     Account:  333333.  Current balance: 5500.0. Projected balance: 7320.5. Difference: 1820.5
         *     
         * Projected Balance after 1 years:
         *     Account:  111111.  Current balance: 100.0. Projected balance: 110.0. Difference: 10.0
         *     Account:  222222.  Current balance: 8000.0. Projected balance: 8800.0. Difference: 800.0
         *     Account:  333333.  Current balance: 5500.0. Projected balance: 6050.0. Difference: 550.0
         *     
         * Invalid number of years entered; must be greater than zero
         * 
         * No Bank Accounts
         * ------------------------------------------------
         */
        System.out.println("------------------------------------------------");
        System.out.println("Actual Output from listProjectedPortfolioBalance");
        System.out.println("------------------------------------------------");
        System.out.println(aib.listProjectedPortfolioBalance(3));
        System.out.println(aib.listProjectedPortfolioBalance(1));
        System.out.println(aib.listProjectedPortfolioBalance(0));
        System.out.println(invalidBank.listProjectedPortfolioBalance(2));        
    }
    
    @After
    public void tearDown()
    {
    }
}