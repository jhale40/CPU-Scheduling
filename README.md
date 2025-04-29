# CPU Scheduling Simulation

This is a Project for my Operating Systems class to simulate different CPU Scheduling Algorithms. I will compare Shortest Remaining Time First(SRTF) and Highest Response Ratio Next(HRRN).

### Shortest Remaining Time First(SRTF)

Shortest Remaining Time First(SRTF) is a preemptive scheduling algorithm. When a process arrives, it is added to the ready queue. After each passing unit of time the ready queue is sorted in ascending order by shortest service time. The process with the shortest time is scheduled to run on CPU. It will continue to run on the CPU until either another process arrives in the ready queue with a shorter service time, or until its own service time reaches zero.

## Highest Response Ratio Next (HRRN) 
Highest Response Ratio Next (HRRN) is a non-preemptive scheduling algorithm. To determine what process is scheduled next, the response ratio must be calculated for each process. The process with the highest response ratio, also known as Max Ratio,  is then scheduled to run on the CPU for its full service time. The equation for Max Ratio is: the sum of  wait time and service time,  divided by service time.

I will run both Scheduling Algorithms with the same data to process each time. Examples will be shown on how each Algorithm performs with small and large data sets. A table is printed under each Scheduler to help visually illustrate input data received and output performance metrics . These metrics include Gant chart of Process Order, Throughput(Processes per Second), Wait time(WT), Turn Around Time (TAT), Average Wait Time(AWT), Average Turn Around Time. 

## How to use

To create and run a new simulation is simple. First go to main method of Main Class. There you can create an instance of the algorithm. In this example we can create an object of HighestResponseRatioNext and assign it to a variable named hrrn of the Abstract CPUScheduler Class. This is the parent class that both algoriths extend. Now that we have a CPU Scheduler, the next step is to use the addProcess method and input new Process constructor method with a process name, arrival time, burst time as shown below. After the processes have been added to the scheduler the simulation can be started by calling the run() method and display method. Nothing will happen if the last two methods are not a called. 

```java

public class Main
{
    public static void main(String[] args)
    {

        CPUScheduler hrrn = new HighestResponseRatioNext();

        hrrn.addProcess(new Process("P1", 0, 3));
        hrrn.addProcess(new Process("P2", 2, 6));
        hrrn.addProcess(new Process("P3", 4, 4));
        hrrn.addProcess(new Process("P4", 6, 5));
        hrrn.addProcess(new Process("P5", 8, 2));

        hrrn.run();
        display(hrrn);

    }// end main()
)


```


### Results

<img src="images/result.PNG" width="350" alt="Results"> 
