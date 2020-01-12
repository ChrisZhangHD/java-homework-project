package edu.nyu.cs9053.homework10;

import java.util.Map;
import java.util.concurrent.*;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:50 PM
 */
public class ExecutorFilesWordCounter extends AbstractConcurrencyFactorProvider implements FilesWordCounter {

    private final ExecutorService executorService;

    public ExecutorFilesWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        executorService = Executors.newFixedThreadPool(concurrencyFactor);
    }

    @Override public void count(Map<String, String> files, String word, Callback callback) {
        // TODO - implement this class using calls to an ExecutorService; with one call per {@linkplain concurrencyFactor}.
        // HINT - do not create the ExecutorService object in this method
        for (Map.Entry<String, String> entry : files.entrySet()){
            final String name = entry.getKey();
            final String value = entry.getValue();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    final ExecutorWordCounter wordCounter = new ExecutorWordCounter(getConcurrencyFactor());
                    wordCounter.count(value, word, new WordCounter.Callback() {
                        @Override
                        public void counted(long count) {
                            callback.counted(name, count);
                            wordCounter.stop();
                        }
                    });
                }
            });
        }
    }

    @Override public void stop() {
        // TODO - stop the executor
        executorService.shutdown();
    }

}
