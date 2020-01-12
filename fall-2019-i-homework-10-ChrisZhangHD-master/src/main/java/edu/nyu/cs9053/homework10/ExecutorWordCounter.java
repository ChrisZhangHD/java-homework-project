package edu.nyu.cs9053.homework10;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:33 PM
 */
public class ExecutorWordCounter extends AbstractConcurrencyFactorProvider implements WordCounter {

    private final ExecutorService executorService;

    public ExecutorWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        executorService = Executors.newFixedThreadPool(concurrencyFactor);
    }

    @Override public void count(String fileContents, String word, Callback callback) {
        // TODO - implement this class using calls to an ExecutorService; one call per {@link #concurrencyFactor}
        // HINT - break up {@linkplain fileContents} and distribute the work across the calls
        // HINT - do not create the ExecutorService object in this method
        final AtomicLong counter = new AtomicLong(0L);
        String[] split = fileContents.split("\n");
        final CountDownLatch latch = new CountDownLatch(split.length);
        final Pattern wordRegex = Pattern.compile(String.format("\\b%s\\b", word), Pattern.CASE_INSENSITIVE);
        for (final String line : split) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Matcher matcher = wordRegex.matcher(line);
                    while (matcher.find()) {
                        counter.incrementAndGet();
                    }
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        }catch (InterruptedException ie){
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        callback.counted(counter.get());
    }



    @Override public void stop() {
        executorService.shutdown();
    }
}
