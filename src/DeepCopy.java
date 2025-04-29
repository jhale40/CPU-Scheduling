
import java.util.ArrayList;
import java.util.List;

public class DeepCopy
{
    public static List<Process> deepCopy(List<Process> oldList)
    {
        List<Process> newList = new ArrayList();

        for (Process process : oldList)
        {
            var name = process.getProcessName();
            var arrivalTime = process.getArrivalTime();
            var burstTime = process.getBurstTime();
            var priority = process.getPriorityLevel();

            newList.add(new Process(name, arrivalTime, burstTime, priority));

        }

        return newList;
    }
}
