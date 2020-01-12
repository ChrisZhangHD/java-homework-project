package edu.nyu.cs9053.homework8;

import java.util.List;

public interface SchedulerTool<T extends LambdaJob> {

    void sortJobsList(List<T> jobs);

    List<T> schedule(List<T> jobs);

}
