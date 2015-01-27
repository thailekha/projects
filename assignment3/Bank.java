import java.util.ArrayList;
/**
 * This class contains fields and methods relating to a Bank.
 * 
 * @author Mairead Meagher, Siobhan Drohan, Ian Downey
 * @version 1
 */
public class Bank
{
    private String bankName;
    private String bankManager;
    private double interestRate;
    private ArrayList<BankAccount>  bankAccounts; 

    /**
     * Constructor for objects of class Bank.
     * 
     * The data passed in as parameters 
     * is used to initialise the instance fields:
     * bankName, bankManager and interestRate.  
     * For each instance field:
     *     # the data passed in as a parameter is
     *     validated to ensure that it conforms to 
     *     the rules identified above.
     *     # if the data passed in is valid,
     *     the instance field is updated.
     *     # if the data passed in is invalid, 
     *     a default value is given to the instance 
     *     field and a message is printed out the terminal
     *       letting the user know that a default value has been supplied.     
     * @param bankName new bank name
     * @param bankManager  new bank manager's name
     * @param interestRate new interest rate
     */
    public Bank(String bankName, String bankManager, double interestRate)
    {
        if (bankName.length() <= 15) {
            this.bankName = bankName;
        }
        else
        {
            this.bankName = bankName.substring(0, 15); 
        }
        
        if (bankManager.length() <= 20) {
            this.bankManager = bankManager;
        }
        else 
        {
            this.bankManager = bankManager.substring(0, 20);
        }

        if ((interestRate >= 0) && (interestRate <= 100)) {
            this.interestRate = interestRate;
        }
        else
        {
            interestRate = 0;
        }
        bankAccounts = new ArrayList<BankAccount>();
    }
    
    /**
     * Default constructor for Bank
     */
    public Bank()
    {
        bankName = "AIB Bank";      
        bankManager = "Richie Manager"; 
        interestRate = 10;
        bankAccounts = new ArrayList<BankAccount>();
    }
    
    /**
     * @return the bank name.
     */    
    public String getBankName()
    {
        return bankName;
    }
    
    /**
     * This setter updates the instance field, bankName, based on the rules 
     * defined above.
     * @param nbankName new bank name
     */
    public void setBankName(String nbankName)
    {
        if (nbankName.length() <= 15) {
            bankName = nbankName;
        }
        else
        {
            System.out.println("bankName field is not updated," + 
                " as the passed data was invalid:  " + nbankName);
        }
    }
    
    /**
     * @return the bank manager's name.
     */
    public String getBankManager()
    {
        return bankManager;
    }
    
    /**
     * This setter updates the instance field, bankManager, based on 
     * the rules defined above.
     * @param nbankManager new bank manager's name
     */
    public void setBankManager(String nbankManager)
    {
        if (nbankManager.length() <= 20) {
            bankManager = nbankManager;
        }
        else
        {
            System.out.println("bankManager field is not updated," + 
                " as the passed data was invalid:  " + nbankManager);
        }
    }

    /**
     * @return the interest rate
     */
    public double getInterestRate()
    {
        return interestRate;
    }

    /**
     * This setter updates the instance field, interestRate, 
     * based on the rules defined above.
     * @param nInterestRate new interest rate
     */
    public void setInterestRate(double nInterestRate)
    {
        if ((nInterestRate >= 0) && (nInterestRate <= 100)) {
            interestRate = nInterestRate;
        }
        else
        {
            System.out.println("interestRate field is not" + 
                " updated, as the passed data was invalid:  " 
                + nInterestRate);
        }
    }

    /**
     * @return the String version of the bank object.  
     * The returned String contains labels for each of the 
     * instance fields and is formatted like so:
     *      
     *     Bank Name: AIB
     *         Bank Manager:  Joe Money
     *         Interest Rate: 3  
     */
    public String toString()
    {
        return "Name: " + bankName + "   Bank Manager: " + 
            bankManager + "  Interest Rate: " + interestRate +
            "\n" + "Bank Details: " + "\n" + listBankAccounts();
    }  
    
    /**
     * Get accounts list
     * @return ArrayList of bankAccounts
     */
    public ArrayList<BankAccount> getBankAccounts()
    {
        return bankAccounts;      
    }
    
    /**
     * Change the accounts list
     * @param nBankAccounts new list
     */
    public void setBankAccounts
    (ArrayList<BankAccount> nBankAccounts)
    {
        bankAccounts = nBankAccounts;
    }
    
    /**
     * Add account to list
     * @param nAccount new account
     */
    public void addBankAccount(BankAccount nAccount)
    {
        bankAccounts.add(nAccount);
    }
    
    /**
     * Remove account
     * @param location index number in list
     */
    public void removeBankAccount(int location)
    {
        if (location >= 0 && location < bankAccounts.size()) 
        {
            bankAccounts.remove(location);
        }
    }
    
    /**
     * Show accounts in list
     * @return String of accounts
     */
    public String listBankAccounts()
    {   
        if (bankAccounts.size() > 0)
        {
            String listAccounts = "";
            for (BankAccount account : bankAccounts)
            {
                listAccounts = listAccounts + "\t" + account + "\n";
            }
            return listAccounts;
        }
        else
        {
            return "\t No Bank Accounts";
        }
    }
    
    /**
     * Get accounts that is lower than a given balance
     * @param maxBalance maximum balance
     * @return string of accounts
     */
    public String listAccountsMaxBalance(double maxBalance)
    {
        if (bankAccounts.size() > 0)
        {
            int countAcc = 0;
            String listMax = "";
            for (BankAccount account : bankAccounts)
            {   
                if (account.getBalance() < maxBalance)
                {
                    listMax = listMax + "Account: " + 
                        account.getAccountName() + "(" + 
                        account.getAccNumber() + "): " + 
                        account.getBalance() + "\n";
                    countAcc++;
                }
            }
            if (countAcc == 0)
            {
                return "No Bank Accounts";
            }
            else
            {
                return listMax;
            }
        }
        else
        {
            return "No Bank Accounts";
        }
    }
    
    /**
     * Show accounts in a certain category
     * @param category the category
     * @return string of accounts
     */
    public String listAccountsByCategory(int category)
    {
        if (bankAccounts.size() > 0) {
            int count = 0;
            String list = "Category " + category + " accounts: \n";
            for (BankAccount bankAccount : bankAccounts)
            {
                if (bankAccount.getAccountCategory() == category)
                {
                    list = list + "\t" + bankAccount.getAccNumber()
                        + ": " + bankAccount.getBalance() + "\n";
                    count++;
                }
            }
            if (count == 0)
            {
                return "No Banks Accounts";
            }
            else
            {
                return list;
            }
        }
        else
        {
            return "No Banks Accounts";
        }
    }
    
    /**
     * Calculate the average bank balance
     * @return avarage bank balance
     */
    public double averageBankBalance()
    {
        if (bankAccounts.size() > 0)
        {
            double total = 0;
            for (BankAccount account : bankAccounts)
            {
                total = total + account.getBalance();
            }
            return toTwoDecimalPlaces(total / bankAccounts.size());
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Get the account with highest bank balance
     * @return account with highest bank balance
     */
    public BankAccount highestBankBalance()
    {
        if (bankAccounts.size() > 0)
        {   
            BankAccount accountHighestBalance = 
                bankAccounts.get(0);
            for (BankAccount account : bankAccounts)
            {
                if (account.getBalance() > accountHighestBalance.getBalance())
                {
                    accountHighestBalance = account;
                }
            }
            return accountHighestBalance;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * This method takes in a double and converts
     * it to a two decimal place double.  
     * This newly converted double is then returned.
     */
    private double toTwoDecimalPlaces(double num)
    {   
        return (int) (num * 100 ) / 100.0; 
    } 
    
    /**
     * Create a string that list each account's:
     * -account number
     * -current balance
     * -number of years to reach the desired balance
     * @param desiredBalance desired balance to reach 
     * @return string
     */
    public String listProjectedPortfolioTimeframe(double desiredBalance)
    {
        if (bankAccounts.size() > 0)
        {
            String listProTime = "";
            int countAcc = 0;
            for (BankAccount account : bankAccounts)
            {
                if (account.getBalance() <= desiredBalance)
                {
                    listProTime = listProTime + "\t Account:  " + 
                        account.getAccNumber() + ".  Current balance: "
                        + account.getBalance() +
                        ".  Years to reach projected balance: " + 
                        account.projectHowLong(desiredBalance,
                            getInterestRate())
                        + "\n";
                    countAcc++;
                }
            }
            if (countAcc == 0)
            {
                return "No Bank Accounts";
            }
            else
            {
                return "Desired balance is " +
                    desiredBalance + "\n" + listProTime;
            }
        }
        else
        {
            return "No Bank Accounts";
        }
    }
    
    /**
     * Create a string that list each account's:
     * -account number
     * -current balance
     * -balance after a number of years
     * @param numberOfYears number of years 
     * @return string
     */
    public String listProjectedPortfolioBalance(int numberOfYears)
    {
        if (numberOfYears <= 0)
        {
            return "Invalid number of years entered;"
                + " must be greater than zero \n";
        }
        else if (bankAccounts.size() > 0)
        {
            String listProBalance = "";
            int countAcc = 0;
            for (BankAccount account : bankAccounts)
            {
                listProBalance = listProBalance + "\t" 
                    + "Account:  " + account.getAccNumber() 
                    + ".  Current balance: " + account.getBalance() 
                    + ". Projected balance: " + 
                    toTwoDecimalPlaces
                    (account.extendedProjectBalance
                        (numberOfYears, getInterestRate()))
                    + ". Difference: " + 
                    (toTwoDecimalPlaces
                    (account.extendedProjectBalance
                        (numberOfYears, getInterestRate())
                        - account.getBalance())) + "\n";
                countAcc++;
            }
            if (countAcc == 0)
            {
                return "No Bank Accounts";
            }
            else
            {
                return "Projected Balance after " + numberOfYears
                    + " years" + ": \n" + listProBalance;
            }
        }
        else 
        {
            return "No Bank Accounts";
        }
    }
}