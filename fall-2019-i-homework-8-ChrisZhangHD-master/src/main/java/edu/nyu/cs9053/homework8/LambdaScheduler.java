package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.List;

public class LambdaScheduler<T extends LambdaJob> extends Scheduler<T> {

    @Override
    public List<T> schedule(List<T> jobs) {
        if (jobs == null || jobs.size() == 0){
            return null;
        }

        sortJobsList(jobs);

        List<T> container = new ArrayList<>();
        container.add(jobs.get(0));
        for (int i = 1; i < jobs.size(); i ++){
            T prevUnweightedLambdaJob = container.get(container.size() - 1);
            T curUnweightedLambdaJob = jobs.get(i);

            if (prevUnweightedLambdaJob.equals(curUnweightedLambdaJob)){
                continue;
            }

            if(prevUnweightedLambdaJob.getFinalTime() <= curUnweightedLambdaJob.getStartTime()){
                container.add(curUnweightedLambdaJob);
            }
        }
        return container;
    }
}
