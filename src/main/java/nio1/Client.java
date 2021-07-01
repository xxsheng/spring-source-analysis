package nio1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Client {

    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 9092);
        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,  SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
        socketChannel.connect(inetSocketAddress);
        while (true) {
            while (selector.select() >0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isConnectable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        channel.finishConnect();
                        selectionKey.interestOps(SelectionKey.OP_READ);
                        System.out.println("connectd");
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        byteBuffer.flip();
                        String receiveData = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                        System.out.println("client" + receiveData);
                        ByteBuffer byteBuffer1 = ByteBuffer.allocate(60);
                        byteBuffer1.put("data Received".getBytes());
                        byteBuffer1.flip();
                        channel.write(byteBuffer1);
                    }
                }
            }
        }

    }
}
