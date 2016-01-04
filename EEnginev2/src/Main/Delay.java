
package Main;

public class Delay
{
    private final long length;
    private boolean started;
    private long endtime;
    
    public Delay(long length) {
        this.length = length;
    }
    /**
     * @return amount of time left until delay is over. 0 if over or not active.
     */
    public long timeleft()
    {
        if(over())
            return 0;
        if(active())
            return endtime - Time.gettime();
        else return 0;
    }
    /**
     * 
     * @return true if active and delay is over.
     */
    public boolean over()
    {
        if(!started) return false;
        
        return Time.gettime() >= endtime;
    }
    /**
     * Sets the delay active and restarts it.
     */
    public void start()
    {
        started = true;
        endtime = length * 1000000 + Time.gettime();
    }
    /**
     * Deactivates the delay.
     */
    public void terminate()
    {
        started = false;
    }
    /**
     * 
     * @return true if delay is active.
     */
    public boolean active()
    {
        return started;
    }
    /**
     * Sets the delay active and ends it.
     */
    public void end()
    {
        started = true;
        endtime = 0;
    }
}
