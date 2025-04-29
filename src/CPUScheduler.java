

import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduler
{
    private final List<Process> process_List;
    private final List<Event> timeline;

    private int timeQuantum;

    private int currentTime;


    public CPUScheduler()
    {
        process_List = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;                  /** DOUBLE CHECK FOR MISTAKES */
        currentTime = 0;
    }

    public int getCurrentTime()
    {
        return currentTime;
    }
    public void incrementCurrentTime()
    {
        this.currentTime = this.currentTime + 1;
    }

    public void addToCurrentTime(int increaseBy)
    {
        this.currentTime = this.currentTime + increaseBy;
    }

    public boolean addProcess(Process process)
    {
        return process_List.add(process);
    }

    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }

    public int getTimeQuantum()
    {
        return timeQuantum;
    }

    public double getAverageWaitingTime()
    {
        double average = 0.0;

        for (Process process : process_List)
        {
            average += process.getWaitingTime();
        }

        return average / process_List.size();
    }

    public double getAverageTurnAroundTime()
    {
        double average = 0.0;

        for (Process process : process_List)
        {
            average += process.getTurnaroundTime();
        }

        return average / process_List.size();
    }

    public double getThroughput()
    {
        double throughput = (double) process_List.size()/ currentTime;
        return throughput;
    }

    public Event getEvent(Process process)
    {
        for (Event event : timeline)
        {
            if (process.getProcessName().equals(event.getProcessName()))
            {
                return event;
            }
        }

        return null;
    }

    public Process getProcess(String process)
    {
        for (Process p : process_List)
        {
            if (p.getProcessName().equals(process))
            {
                return p;
            }
        }

        return null;
    }

    public List<Process> getProcessList()
    {
        return process_List;
    }

    public List<Event> getTimeline()
    {
        return timeline;
    }

    /** TEST THEN WORD BETTER $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Might need to be ARRAYLIST? */
    public void insertInOrder( List<Process> list, Process process)
    {
        int serviceTime = process.getServiceTime();
        int i = 0;
        while (i < list.size() && serviceTime > list.get(i).getServiceTime()) {
            i++;
        }
        list.add(i,process);
    }



    public abstract void run();
}
