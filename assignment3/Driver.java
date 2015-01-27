import java.util.Scanner;
import java.util.ArrayList;

/**
 * Driver class for displaying a menu for users
 * @author Thai
 * @version 1.0
 */
public class Driver
{
    private Scanner input;
    private Bank aib;           
    private BankAccount account1; 
    private BankAccount account2;
    private BankAccount account3;
    private ArrayList<Task> taskList;
    
    /**
     * Contructor
     */
    public Driver()
    {
        input = new Scanner(System.in);
        aib = new Bank("AIB Bank", "Richie Manager", 10);
        account1 = new BankAccount(111111, "Mary", "WIT", 100);
        account2 = new BankAccount(222222, "John", "CIT", 8000);
        account3 = new BankAccount(333333, "Joan", "AIT", 5500);
        taskList = new ArrayList<Task>();
        runMenu();
    }
    
    /**
     * The menu that users will see and type in a choice
     * @return user's choice
     */
    private int mainMenu()
    {
        System.out.println("Bank Account Menu");
        System.out.println("-----------------");
        System.out.println("  1) List the Bank Accounts");
        System.out.println("  2) List accounts less than a certain balance");
        System.out.println("  3) List accounts by category");
        System.out.println("  4) Display average bank balance");
        System.out.println("  5) Display highest bank balance");
        System.out.println("  6) Project how long" 
            + " it will take accounts to reach a certain balance");
        System.out.println("  7) Project the balance for" 
            + " each account after a certain number of years");
        System.out.println("  8) Print all bank details");
        System.out.println("  9) Tasks to be done continuously");
        System.out.println("  0) Exit");
        System.out.print("==>> \t");
        int option = input.nextInt();
        return option;
    }
    
    /**
     * The menu displayed for choosing many options,
     * creates new objects of type Task
     * and add them to the list
     */
    private void addTask()
    {
        System.out.println("Enter tasks" 
            + " (Press enter after each one, enter 99 to start)");
        System.out.print("=> \t");
        int choice = input.nextInt();
        while (choice != 99)
        {
            Task task = new Task(choice);
            taskList.add(task);
            System.out.print("=> \t");
            choice = input.nextInt();
        }
    }
    
    /**
     * Retrieve tasks from the list
     * @return option's number
     */
    private int getTask()
    {
        return taskList.get(0).getJob();
    }
    
    /**
     * The invisible menu that does the chosen option
     */
    private void runMenu()
    {
        aib.addBankAccount(account1); 
        aib.addBankAccount(account2); 
        aib.addBankAccount(account3); 
        int option = mainMenu();
        //boolean hasTaskList = false;
        int countInvalid = 0;
        while (option != 0)
        {
            //if(taskList.size() == 0){System.out.println("\f");}
            switch (option)
            {
                case 1: 
                    System.out.println(aib.listBankAccounts()); 
                    break;
                case 2: 
                    System.out.print("Enter the maximum balance: ");
                    System.out.println("\t" + aib.listAccountsMaxBalance
                        (input.nextDouble())); 
                    break;
                case 3: 
                    System.out.print("Enter the category: ");
                    System.out.println("\t" + aib.listAccountsByCategory
                        (input.nextInt())); 
                    break;
                case 4: 
                    System.out.println("\t" + aib.averageBankBalance()); 
                    break;
                case 5: 
                    System.out.println("\t" + aib.highestBankBalance()); 
                    break;
                case 6: 
                    System.out.print("Enter the desire balance: ");
                    System.out.println(aib.listProjectedPortfolioTimeframe
                        (input.nextDouble())); 
                    break;
                case 7: 
                    System.out.print("Enter the number of years: ");
                    System.out.println(aib.listProjectedPortfolioBalance
                        (input.nextInt())); 
                    break;
                case 8: 
                    System.out.println(aib); 
                    break;
                case 9:
                    addTask();
                    //hasTaskList = true;   
                    System.out.println("\f");
                    break;
                default:
                    System.out.println("Invalid option entered"); 
                    countInvalid++; 
                    break;
            }
            
            if (taskList.size() > 0)
            {
                option = getTask();
                System.out.println("----------------------------");
                System.out.println("\n Task " 
                    + taskList.get(0).getJob() 
                    + " (" + (taskList.size() - 1)
                    + " job(s) left)" + " : ");
                taskList.remove(0);
            }            
            else if (countInvalid < 5)
            {   
                System.out.println("(Press enter to continue)");
                input.nextLine();
                input.nextLine();
                System.out.println("\f");
                option = mainMenu();
            }
            else 
            {
                option = 0;
            }
        }
        
        if (countInvalid < 5)
        {   
            System.out.println("\f");
            System.out.println("tttttt hh hh    a    nn   nn  kk  kk    ss");
            System.out.println("  tt   hh hh   aaa   nnn  nn  kk kk    ss");
            System.out.println("  tt   hhhhh  aa aa  nn n nn  kkkk      sss");
            System.out.println("  tt   hh hh  aaaaa  nn  nnn  kk kk    ss");
            System.out.println("  tt   hh hh  aa aa  nn   nn  kk  kk  ss");
        }
        else
        {
            System.out.println("\f You typed in" 
                + " invalid choices five times, program has been shut down.");
        }
    }
}