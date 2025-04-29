import java.util.ArrayList;
import java.util.List;

public class Process
{
    private String processName;
    private int arrivalTime;
    private int burstTime;

    private int serviceTime;
    public boolean isActive = true;
    private int priorityLevel;
    private int waitingTime;
    private int turnaroundTime;


    private Process(String processName, int arrivalTime, int burstTime, int priorityLevel, int waitingTime, int turnaroundTime)
    {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityLevel = priorityLevel;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
    }

    public Process(String processName, int arrivalTime, int burstTime, int priorityLevel)
    {
        this(processName, arrivalTime, burstTime, priorityLevel, 0, 0);
        this.serviceTime = burstTime;
    }

    public Process(String processName, int arrivalTime, int burstTime)
    {
        this(processName, arrivalTime, burstTime, 0, 0, 0);
        this.serviceTime = burstTime;
    }

    public void setBurstTime(int burstTime)
    {
        this.burstTime = burstTime;
    }

    public void decrementServiceTime(int decrementAmount)
    {
        //System.out.println(this.serviceTime);                    /** DELETE AFTER TESTING ###########################*/
        this.serviceTime = this.serviceTime - decrementAmount;

        if (serviceTime <= 0)
        {
            this.isActive = false;
        }
        //System.out.println(this.serviceTime);
    }

    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }

    public void setWaitingTime(int currentTime, int arrivalTime)
    {
        this.waitingTime = currentTime - arrivalTime;
    }


    public void setTurnaroundTime(int turnaroundTime)
    {
        this.turnaroundTime = turnaroundTime;
    }

    public String getProcessName()
    {
        return this.processName;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

    public int getBurstTime()
    {
        return this.burstTime;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getPriorityLevel()
    {
        return this.priorityLevel;
    }

    public int getWaitingTime()
    {
        return this.waitingTime;
    }

    public int getTurnaroundTime()
    {
        return this.turnaroundTime;
    }

    public double getMaxRatio()
    {
        var sum = this.waitingTime + this.burstTime;
        return (double) sum / this.burstTime;
    }

}
