package edu.nyu.cs9053.homework10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class AbstractThreadedCounter extends AbstractConcurrencyFactorProvider{

    private final BlockingQueue<Runnable> work;

    private final List<Thread> threads;

    private final AtomicBoolean started;

    protected AbstractThreadedCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.work = new LinkedBlockingQueue<>();
        this.threads = new ArrayList<>(concurrencyFactor);
        Runnable threadCode = getThreadCode();
        for (int i = 0; i < concurrencyFactor; i ++){
            threads.add(new Thread(threadCode));
        }
        this.started = new AtomicBoolean(false);
    }

    private void start(){
        for (Thread thread : threads){
            thread.start();
        }
    }

    protected Runnable getThreadCode(){
        return new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        Runnable todo = work.poll(100, TimeUnit.MILLISECONDS);
                        if (todo != null){
                            todo.run();
                        }
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
    }

    protected BlockingQueue<Runnable> getWork(){
        if (!started.getAndSet(true)){
            start();
        }
        return work;
    }

    protected void stopThreads(){
        for (Thread thread : threads){
            thread.interrupt();
        }
    }
}
