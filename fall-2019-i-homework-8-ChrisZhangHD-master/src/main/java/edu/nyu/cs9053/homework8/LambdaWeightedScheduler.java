package edu.nyu.cs9053.homework8;

import java.util.*;

public class LambdaWeightedScheduler<T extends WeightedLambdaJob> extends Scheduler<T>{

    @Override
    public List<T> schedule(List<T> weightedLambdaJobs) {

        if (weightedLambdaJobs == null || weightedLambdaJobs.size() == 0){
            return null;
        }
        sortJobsList(weightedLambdaJobs);

        List<T> container = new ArrayList<>();
        List<T> curList = new ArrayList<>();
        List<List<T>> dpSubLists = new ArrayList<>();
        List<Integer> dpWeightValueList = new ArrayList<>();

        curList.add(weightedLambdaJobs.get(0));
        dpSubLists.add(new ArrayList<>(curList));
        dpWeightValueList.add(weightedLambdaJobs.get(0).getWeightValue());

        for (int i = 1; i < weightedLambdaJobs.size(); i++){
            T curWeightedLambdaJob = weightedLambdaJobs.get(i);
            if (curWeightedLambdaJob.equals(weightedLambdaJobs.get(i - 1))){
                continue;
            }
            if(curWeightedLambdaJob.getStartTime() >= weightedLambdaJobs.get(i - 1).getFinalTime()){
//                curList = dpSubLists.get(i - 1);  attention!
                curList = new ArrayList<>(dpSubLists.get(i - 1));
                curList.add(curWeightedLambdaJob);
                dpSubLists.add(new ArrayList<>(curList));
                dpWeightValueList.add(dpWeightValueList.get(i - 1) + curWeightedLambdaJob.getWeightValue());
            }else{
                int insertPos = findInsertPosition(weightedLambdaJobs, i - 1, curWeightedLambdaJob.getStartTime());
//                int insertPos = findInsertPosition1(weightedLambdaJobs, i, curWeightedLambdaJob);
                int curWeightValue = getCurWeightValue(insertPos, curWeightedLambdaJob, dpWeightValueList);
                int prevWeightValue = dpWeightValueList.get(i - 1);
                updateDpSubLists(curWeightValue, prevWeightValue, insertPos,curWeightedLambdaJob, dpWeightValueList, dpSubLists);
            }
        }
        List<T> bestSubList = new ArrayList<>(dpSubLists.get(dpSubLists.size() - 1));
        container.addAll(bestSubList);

        return container;
    }

    //find suitable position for new job to insert
    public int findInsertPosition(List<T> weightedLambdaJobs, int end, int target){
        int start = 0;
        int insertPos = -1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(target >= weightedLambdaJobs.get(mid).getFinalTime()){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(target >= weightedLambdaJobs.get(start).getFinalTime()){
            insertPos = start;
        }
        return insertPos;
    }

    public int getCurWeightValue(int insertPos, WeightedLambdaJob curWeightedLambdaJob, List<Integer> dpWeightValueList){
        int curWeightValue;
        if(insertPos == -1){
            curWeightValue = curWeightedLambdaJob.getWeightValue();
        }else{
            curWeightValue = curWeightedLambdaJob.getWeightValue() + dpWeightValueList.get(insertPos);
        }
        return curWeightValue;
    }

    private void updateDpSubLists(int curWeightValue, int prevWeightValue, int insertPos,
                                  T curWeightedLambdaJob, List<Integer> dpWeightValueList,
                                  List<List<T>> dpSubLists){
        if (prevWeightValue > curWeightValue){
            dpSubLists.add(new ArrayList<>(dpSubLists.get(dpSubLists.size() - 1)));
            dpWeightValueList.add(prevWeightValue);
        }else {
            if (insertPos == -1){
                List<T> tempList = new ArrayList<>();
                tempList.add(curWeightedLambdaJob);
                dpSubLists.add(new ArrayList<>(tempList));
            }else{
//                List<WeightedLambdaJob> prevList = dpSubLists.get(insertPos); attention!
                List<T> prevList = new ArrayList<>(dpSubLists.get(insertPos));
                prevList.add(curWeightedLambdaJob);
                dpSubLists.add(new ArrayList<>(prevList));
            }
            dpWeightValueList.add(curWeightValue);
        }
    }
}
