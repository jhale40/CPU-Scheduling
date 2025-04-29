

import java.util.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class Main
{
    public static void main(String[] args)
    {

        createHRRN();

        createSRTF();

        createRandom(10);

    }// end main()




    // Method to help build Highest-Response-Ratio-Next Scheduling
    public static void createHRRN()
    {
        System.out.println("\n\n==================== Highest-Response-Ratio-Next ========================================\n");

        CPUScheduler hrrn = new HighestResponseRatioNext();

        hrrn.addProcess(new Process("P1", 0, 3));
        hrrn.addProcess(new Process("P2", 2, 6));
        hrrn.addProcess(new Process("P3", 4, 4));
        hrrn.addProcess(new Process("P4", 6, 5));
        hrrn.addProcess(new Process("P5", 8, 2));

        hrrn.run();
        display(hrrn);
    }


    // Method to help build Shortest-Remaining-Time-First Scheduling
    public static void createSRTF()
    {
        System.out.println("\n\n==================== Shortest-Remaining-Time-First ========================================\n");

        CPUScheduler srtf = new ShortestRemainingTimeFirst();

        srtf.addProcess(new Process("P1", 0, 6));
        srtf.addProcess(new Process("P2", 1, 3));
        srtf.addProcess(new Process("P3", 3, 7));
        srtf.addProcess(new Process("P4", 4, 4));


        srtf.run();
        display(srtf);
    }


    // Generate N number of processes with random arrival and burst times
    public static void createRandom(int numberOfProcesses)
    {
        System.out.println("\n\n==================== Shortest-Remaining-Time-First ========================================\n");

        //CPUScheduler srtf_Random = new ShortestRemainingTimeFirst();
        CPUScheduler srtf_Random = new ShortestRemainingTimeFirst();
        CPUScheduler hrrn_Random = new HighestResponseRatioNext();

        Process process;

        Random random = new Random();
        // For Loop
        for (int i = 0; i < numberOfProcesses; i++)
        {
            String processNumber = Integer.toString(i);
            String p = "P";
            //String name = p + processNumber;
            String name = p.concat("").concat(processNumber);

            int a = 10;
            int b = 1;
            int c = 20;

            //int random_AT = random.nextInt(10);
            //int random_BT = random.nextInt(1,20);
            int random_AT = random.nextInt(a);
            int random_BT = random.nextInt(b,c);


            process = new Process(name, random_AT, random_BT);

            srtf_Random.addProcess(process);
            hrrn_Random.addProcess(process);

        }



        srtf_Random.run();
        display(srtf_Random);

        System.out.println("\n\n==================== Highest-Response-Ratio-Next ========================================\n");

        hrrn_Random.run();
        display(hrrn_Random);
    }


    // Prints out table with each Process and their attributes of Arrival Time, Burst Time, Waiting Time, and Turnaround Time
    public static void display(CPUScheduler object)
    {
        System.out.println("Process\tAT\tBT\tWT\tTAT");

        for (Process process : object.getProcessList())
        {
            System.out.println("\t" + process.getProcessName() + "\t" + process.getArrivalTime() + "\t" + process.getBurstTime() + "\t" + process.getWaitingTime() + "\t" + process.getTurnaroundTime());
        }

        System.out.println();


        // Print Process Order with start and finish time
        System.out.print("PROCESS ORDER: ");
        for (int i = 0; i < object.getTimeline().size(); i++)
        {
            List<Event> timeline = object.getTimeline();
            System.out.print("[" + timeline.get(i).getProcessName() + ": (" + timeline.get(i).getStartTime() + "-" +timeline.get(i).getFinishTime() + ")] ");

        }

        System.out.println("\n\nAverage Waiting Time (WT): " + object.getAverageWaitingTime() + "\nAverage Turnaround Time (TAT): " + object.getAverageTurnAroundTime());
        System.out.println("Throughput(Processes per Second): " + object.getThroughput());

    }//end display()



}//end Main Class
