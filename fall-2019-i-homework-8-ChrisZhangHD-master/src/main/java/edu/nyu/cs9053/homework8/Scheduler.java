package edu.nyu.cs9053.homework8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract public class Scheduler<T extends LambdaJob> implements SchedulerTool<T> {

    @Override
    public void sortJobsList(List<T> jobs) {
        Collections.sort(jobs, new Comparator<T>() {
            @Override
            public int compare(T job1, T job2) {
                return job1.getFinalTime() - job2.getFinalTime();
            }
        });
    }
}
