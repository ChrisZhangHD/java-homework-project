package edu.nyu.cs9053.homework10;

import java.util.Map;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:50 PM
 */
public class ThreadedFilesWordCounter extends AbstractThreadedCounter implements FilesWordCounter {

    public ThreadedFilesWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
    }

    @Override public void count(Map<String, String> files, String word, Callback callback) {
        // TODO - implement this class using Thread objects; one Thread per {@link #concurrencyFactor} with each Thread handling at most one file at one time
        // HINT - do not create the Thread objects in this method

        for (Map.Entry<String, String> entry : files.entrySet()){
            final String name = entry.getKey();
            final String value = entry.getValue();
            getWork().offer(new Runnable() {
                @Override
                public void run() {
                    final ThreadedWordCounter wordCounter = new ThreadedWordCounter(getConcurrencyFactor());
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
        stopThreads();
    }
}
