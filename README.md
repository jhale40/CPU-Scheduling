# CPU Scheduling Simulation

 This is a Project for my Operating Systems class to simulate and compare different CPU Scheduling Algorithms.

 For this Project I will be comparing Short Remaining Time First(SRTF) and Highest Response Ratio Next(HRRN).
Shortest Remaining Time First(SRTF) is a preemptive scheduling algorithm. When a process arrives, it is added to the ready queue. After each passing unit of time the ready queue is sorted in ascending order by shortest service time. The process with the shortest time is scheduled to run on CPU. It will continue to run on the CPU until either another process arrives in the ready queue with a shorter service time, or until its own service time reaches zero.

Highest Response Ratio Next (HRRN) is a non-preemptive scheduling algorithm. To determine what process is scheduled next, the response ratio must be calculated for each process. The process with the highest response ratio, also known as Max Ratio,  is then scheduled to run on the CPU for its full service time. The equation for Max Ratio is: the sum of  wait time and service time,  divided by service time.

I will run both Scheduling Algorithms with the same data to process each time. Examples will be shown on how each Algorithm performs with small and large data sets. A table is printed under each Scheduler to help visually illustrate input data received and output performance metrics . These metrics include Gant chart of Process Order, Throughput(Processes per Second), Wait time(WT), Turn Around Time (TAT), Average Wait Time(AWT), Average Turn Around Time. 
