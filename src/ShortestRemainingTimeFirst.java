import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestRemainingTimeFirst extends CPUScheduler {

    public Process scheduledProcess;

    public List<Process> process_List_Copy  = new ArrayList<>();

    public List<Process> availableProcesses = new ArrayList<>();
    public ArrayList<Integer> arrivalTimeList = new ArrayList<>();

    public boolean processListActive = true;

    public int runTime = 0;

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
        process_List_Copy = DeepCopy.deepCopy(this.getProcessList());


        // Creates an ArrayList of Process Arrival Times with no duplicates
        for (Process process : process_List_Copy) {

            var arrivalTime = process.getArrivalTime();
            if (!arrivalTimeList.contains(arrivalTime)) {
                arrivalTimeList.add(arrivalTime);
            }
        }


        // Add Process to availableProcesses List if their Arrival Time is less than or equal to "currentTime" variable
        // Process is placed in AvailableProcess List in ascending order of Service Time
        addArrivingProcesses();



        // While all Processes in CPUScheduler Process List are active // MIGHT NEED TO BE DO-WHILE LOOP?
        while (processListActive)
        {

            // Add Process to availableProcesses List if their Arrival Time is less than or equal to "currentTime" variable
            // Process is placed in AvailableProcess List in ascending order of Service Time
            //addArrivingProcesses();


            //if (!arrivalTimeList.isEmpty())


            // Checks if all Processes of process_List in CPU Scheduler are Active. Determines if While Loop ends
            checkIfACTIVE();

            // SRTF Process
            /** ???????????????????????????????????????????????????MIGHT NEED A TRY CATCH BLOCK with INCREMENT Current Time & addArrivingProcesses()?????????????????????*/
            scheduledProcess = availableProcesses.getFirst();

            //TRY NEW
            //Preemptive STAGE
            // If Current Time is less than or equal to the Highest Process Arrival Time
            if(getCurrentTime() <= process_List_Copy.getLast().getArrivalTime())
            {
                //increment Current Time
                incrementCurrentTime();
                // Increment Run Time
                incrementRunTime();

                //Decrease Service Time of Scheduled Process
                scheduledProcess.decrementServiceTime(1);

                /** Process Finished*/
                //If Process Service is Not Active, Record Event
                if (!scheduledProcess.isActive)
                {
                    String processName = scheduledProcess.getProcessName();
                    int startTime = getCurrentTime() - runTime;
                    int finishTime = getCurrentTime();
                    this.getTimeline().add(new Event(processName, startTime, finishTime));

                    //Reset Run Time
                    resetRunTime();

                    //Remove Finished Process from availableProcesses List
                    availableProcesses.remove(scheduledProcess);

                }


                /** Check if Process needs to be Pre-empted */

                // Add Process to availableProcesses List if their Arrival Time is less than or equal to "currentTime" variable
                // Process is placed in AvailableProcess List in ascending order of Service Time
                addArrivingProcesses();

                // Current SRTF Process
                Process currentSRTF = availableProcesses.getFirst();

                // If Current Scheduled Process is NOT Current SRTF, AND Scheduled Process is Active
                // Preemption occurs,and EVENT is Recorded to timeline
                if ((!scheduledProcess.equals(currentSRTF)) && (scheduledProcess.isActive)) {

                    String processName = scheduledProcess.getProcessName();
                    int startTime = getCurrentTime() - runTime;
                    int finishTime = getCurrentTime();
                    this.getTimeline().add(new Event(processName, startTime, finishTime));

                    //Decrease Service Time of Scheduled Process by run time amount
                    //scheduledProcess.decrementServiceTime(runTime);
                    resetRunTime();

                }

            }// End of Preemptive Stage



            // SRTF Process
            scheduledProcess = availableProcesses.getFirst();

            // NON-preemptive Stage
            // If Current Time is greater than the Highest Process Arrival Time
            if (getCurrentTime() > process_List_Copy.getLast().getArrivalTime()) {

                //increment Current Time by Total amount of Service Time left
                // Decrease Service Time
                //Record Event
                /** Add the check Method here or below IF STATEMENTS???????????????????????????????????????? */


                String processName = scheduledProcess.getProcessName();
                int serviceTime = scheduledProcess.getServiceTime();
                int startTime = getCurrentTime();
                int finishTime = startTime + serviceTime;
                this.getTimeline().add(new Event(processName, startTime, finishTime));

                //increment Current Time by Total amount of Service Time left
                addToCurrentTime(serviceTime);

                //Decrease Service Time of Scheduled Process by remaining service time
                scheduledProcess.decrementServiceTime(serviceTime);
                resetRunTime();

                //Remove Finished Process from availableProcesses List
                availableProcesses.remove(scheduledProcess);

            }// End of Non-preemptive Stage


            //Determines if While Loop ends
            checkIfACTIVE();

        }//end of while loop: Indicates that all Processes in CPUScheduler Process List are NOT Active




        // Set Waiting Time(WT) and Turnaround Time(TAT) for each Process
        for (Process process : this.getProcessList())
        {
            process.setWaitingTime(this.getEvent(process).getStartTime() - process.getArrivalTime());
            process.setTurnaroundTime(process.getWaitingTime() + process.getServiceTime());
        }



    }//end run()


    public void resetRunTime()
    {
        runTime = 0;
    }

    public void incrementRunTime()
    {
        runTime = runTime +1;
    }

    // Add Process to availableProcesses List if their Arrival Time is less than or equal to "currentTime" variable
    // Process is placed in AvailableProcess List in ascending order of Service Time
    public void addArrivingProcesses()
    {
        for (Process process : process_List_Copy)
        {
            if (availableProcesses.contains(process)) {
                continue;
            } else if (process.getArrivalTime() <= this.getCurrentTime() && process.isActive) {

                // First Process placed into Available Process List
                /**#################################################### MIGHT DELETE OR MODIFY THIS IF STATEMENT*/
                if (availableProcesses.isEmpty()) {

                    availableProcesses.add(process);
                    //process_List_Copy.remove(process);
                } else {

                    // Process is placed in AvailableProcess List in ascending order of Service Time
                    insertInOrder(availableProcesses, process);
                    //process_List_Copy.remove(process);
                }
            }
        }//end for loop

    }


    // Checks if there are any Active Processes in process_List_Copy
    // If all processes are Inactive, then Boolean processListActive is set to false
    public void checkIfACTIVE()
    {
        boolean flag = false;
        for (Process process : process_List_Copy) {
            if (process.isActive) {
                flag = true;

                break;
            }
        }
        processListActive = flag;
    }



}//end of Class
