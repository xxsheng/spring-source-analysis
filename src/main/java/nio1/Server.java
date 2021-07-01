package nio1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9092));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select() == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

//                    socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);

                } else if (selectionKey.isReadable()) {
                    new Handler().handler(selectionKey);
                } else if (selectionKey.isWritable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.put("你好".getBytes());
                    allocate.flip();
                    channel.write(allocate);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }

            }
        }
    }
}


class Handler {
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(16, 16, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public void handler(SelectionKey selectionKey) {
        EXECUTOR_SERVICE.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            try {
                int read = channel.read(byteBuffer);
                byteBuffer.flip();
                if (read >= 0 &&  byteBuffer.hasRemaining()) {
                    System.out.println("读取内容" + new String(byteBuffer.array()));
                } else {
//                    channel.close();
//                    selectionKey.cancel();
                    System.out.println("读取结束");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
