import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighestResponseRatioNext extends CPUScheduler{

    @Override
    public void run()
    {

        //Sort  Process Arrival time in ascending order
        Collections.sort(this.getProcessList(), (Object object1, Object object2) -> {
            if (((Process) object1).getArrivalTime() == ((Process) object2).getArrivalTime())
            {
                return 0;
            }
            else if (((Process) object1).getArrivalTime() < ((Process) object2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });


// Deep Copy List of Process, that is now sorted by Arrival time
        List<Process> process_List_Copy = DeepCopy.deepCopy(this.getProcessList());

        //process_List_Copy.getFirst().decrementServiceTime(3);   /** DELETE AFTER TESTING ###########################*/


        //Arrival Time of first Process is assigned to integer variable named time, then add to current Time
        int time = process_List_Copy.get(0).getArrivalTime();
        addToCurrentTime(time);

        while (!process_List_Copy.isEmpty())
        {
            // Local List of Process is initialize for the purpose of storing Processes as they become available
            List<Process> availableProcesses = new ArrayList();

            // Add Process to availableProcesses List if their Arrival Time is less than or equal to current time
            for (Process process : process_List_Copy)
            {
                if (process.getArrivalTime() <= getCurrentTime())
                {
                    //Calculates and saves Waiting Time(WT) of process
                    process.setWaitingTime(getCurrentTime(), process.getArrivalTime());
                    availableProcesses.add(process);
                }
            }//end for loop


            // Sorts List of availableProcesses by Max Ratio in Descending order
            Collections.sort(availableProcesses, (Object object1, Object object2) -> {
                if (((Process) object1).getMaxRatio() == ((Process) object2).getMaxRatio())
                {
                    return 0;
                }
                else if (((Process) object1).getMaxRatio() < ((Process) object2).getMaxRatio())
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            });


            // Record Events to timeline
            //First Available Process with Max Ratio is recorded as an Event and added to the Timeline.
            Process process = availableProcesses.get(0);
            this.getTimeline().add(new Event(process.getProcessName(), getCurrentTime(), getCurrentTime() + process.getBurstTime()));

            // "time" is incremented by Burst Time of the Process that just executed and then added to current time
            int burst = process.getBurstTime();
            addToCurrentTime(burst);

            // Remove the process that just executed from "process_List_Copy"
            for (int i = 0; i < process_List_Copy.size(); i++)
            {
                if (process_List_Copy.get(i).getProcessName().equals(process.getProcessName()))
                {
                    process_List_Copy.remove(i);
                    break;
                }
            }//end for loop
        }//end while loop of while(process_List_Copy is NOT empty)




        // Set Waiting Time(WT) and Turnaround Time(TAT) for each Process
        for (Process process : this.getProcessList())
        {
            process.setWaitingTime(this.getEvent(process).getStartTime() - process.getArrivalTime());
            process.setTurnaroundTime(process.getWaitingTime() + process.getBurstTime());
        }

    }//end run()


}
