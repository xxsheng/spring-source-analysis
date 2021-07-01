package other;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private static ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private static ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9091));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {

            while (selector.select() >0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        String word = "connected";
                        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                        buffer.put(word.getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                    } else if (selectionKey.isReadable()) {
                        SelectableChannel channel = selectionKey.channel();
                        SocketChannel socketChannel = (SocketChannel) channel;
                        // 假设读完
                        socketChannel.read(readBuffer);
                        readBuffer.flip();
                        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(readBuffer);
                        String result = charBuffer.toString();
                        System.out.println("receice client data: " + result);
                        // 现在写入
                        String send  = "hello, this is server";
                        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                        buffer.put(send.getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                    }
                }
            }
        }



    }
}
