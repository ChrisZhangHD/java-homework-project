package edu.nyu.cs9053.homework11;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:31 PM
 */
public class BlockingChatter implements Chatter {

    private final BufferedInputStream serverBufferedInput;
    private final BufferedOutputStream serverBufferedOutput;
    private final BufferedInputStream inputStream;
    private final List<String> contents;
    private final Random random;

    public BlockingChatter(InputStream chatServerInput, OutputStream chatServerOutput, InputStream userInput) {
        // TODO
        serverBufferedInput = new BufferedInputStream(chatServerInput);
        serverBufferedOutput = new BufferedOutputStream(chatServerOutput);
        inputStream = new BufferedInputStream(userInput);
        this.contents = new LinkedList<>();
        random = new Random();
    }

    @Override public void run() {
        // TODO
        Thread threadForUserInput = new Thread(new Runnable() {
            @Override
            public void run() {
                processUserInput();
            }
        });

        Thread threadForServer = new Thread(new Runnable() {
            @Override
            public void run() {
                processServerInput();
            }
        });

        threadForUserInput.start();
        threadForServer.start();

    }

    private void processUserInput() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (inputStream.available() > 0) {
                    byte[] buffer = new byte[inputStream.available()];
                    int len = inputStream.read(buffer, 0, buffer.length);
//                    serverBufferedOutput.write(buffer, 0, len);
                    writeServerBufferedOutput(buffer, len);
                    serverBufferedOutput.flush();
                }
            } catch (IOException ioe) {
                System.out.printf("Exception - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processServerInput() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (serverBufferedInput.available() > 0) {
                    byte[] into = new byte[serverBufferedInput.available()];
                    serverBufferedInput.read(into, 0, into.length);
                    String backMessage = new String(into);
                    System.out.printf("%s%n", backMessage);
                    String easterEggFlag = backMessage.substring(19, 23);
                    if (easterEggFlag.equals("java")) {
                        easterEgg();
                    }
                }
            } catch (IOException ioe) {
                System.out.printf("Exception - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void easterEgg() throws IOException {
        if (contents.size() == 0){
            readTXTFile();
        }
        int randomLine = random.nextInt(contents.size());
        byte[] buffer = contents.get(randomLine).getBytes();
        writeServerBufferedOutput(buffer, buffer.length);
//        serverBufferedOutput.write(buffer, 0, buffer.length);
        serverBufferedOutput.flush();
    }

    public synchronized void writeServerBufferedOutput(byte[] buffer, int len) throws IOException{
        serverBufferedOutput.write(buffer, 0, len);
    }

    private void readTXTFile() throws IOException{
        FileInputStream inputStream = new FileInputStream("src/main/resources/Moby Dick.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str;
        while((str = bufferedReader.readLine()) != null) {
            if (str.length() != 0){
                contents.add(str);
            }
        }
        //close
        inputStream.close();
        bufferedReader.close();
    }


}
