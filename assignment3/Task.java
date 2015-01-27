/**
 * This class holds 1 chosen option 
 * @author Thai
 * @version 1.0
 */
public class Task
{
    private int job;
    /**
     * Constructor for objects of class Task
     * @param x chosen option
     */
    public Task(int x)
    {
        job = x;
    }
    
    /**
     * Retrieve option
     * @return option's number
     */
    public int getJob()
    {
        return job;
    }
}
