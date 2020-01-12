package edu.nyu.cs9053.homework11;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:32 PM
 */
public class NonBlockingChatter implements Chatter {

    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final Selector selector;
    private final SocketChannel chatServerChannel;
    private final Pipe.SourceChannel userInput;

    private final ByteBuffer readBuffer;
    private final ByteBuffer writeBuffer;

    public NonBlockingChatter(SocketChannel chatServerChannel,
                              Pipe.SourceChannel userInput) throws IOException {
        // TODO
        selector = Selector.open();
        this.chatServerChannel = chatServerChannel;
        this.userInput = userInput;
        this.chatServerChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        this.userInput.register(selector, SelectionKey.OP_READ);
        readBuffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
        writeBuffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);

    }

    @Override
    public void run() {
        // TODO
        while (!Thread.currentThread().isInterrupted()) {
            try {
                process();
            } catch (IOException ioe) {
                System.out.printf("Exception - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void process() throws IOException {

        int readyChannel = selector.select();
        if (readyChannel < 0) {
            return;
        }

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

        while (keyIterator.hasNext()) {
            SelectionKey selectionKey = keyIterator.next();
            try {
                if (selectionKey.isReadable()) {
                    if (userInput.equals(selectionKey.channel())){
                        writeBuffer.clear();
                        userInput.read(writeBuffer);
                        writeBuffer.flip();
                        readBuffer.put(writeBuffer);
                    }else if (chatServerChannel.equals(selectionKey.channel())){
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        writeBuffer.clear();
                        socketChannel.read(writeBuffer);
                        writeBuffer.flip();
                        CharsetDecoder decoder = UTF8.newDecoder();
                        CharBuffer charBuffer = decoder.decode(writeBuffer);
                        System.out.printf("%s%n", charBuffer.toString());
                    }
                } else if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    readBuffer.flip();
                    socketChannel.write(readBuffer);
                    readBuffer.clear();
                }
            } finally {
                keyIterator.remove();
            }

        }
    }
}

