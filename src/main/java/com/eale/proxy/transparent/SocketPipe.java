package com.eale.proxy.transparent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @Author HLD
 * @Date 2021/11/25 0025
 **/
@Data
@AllArgsConstructor
public class SocketPipe {

    private Reactor reactor;

    private SocketChannel from;

    private SocketChannel to;

    @SneakyThrows
    public void pipe() {
        // 取消监听
        clearInterestOps();

        GlobalThreadPool.PIPE_EXECUTOR.submit(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                int totalBytesRead = 0;
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (valid(from) && valid(to)){
                    buffer.clear();
                    int read = from.read(buffer);
                    totalBytesRead = totalBytesRead + read;
                    buffer.flip();
                    to.write(buffer);
                    if (read < buffer.capacity()){
                        break;
                    }
                }
                if (totalBytesRead < 0){
                    reactor.closeChannel(from);
                    reactor.cancel(from);
                }else {
                    // 重置监听
                    resetInterestOps();
                }

            }
        });


    }

    private void resetInterestOps() {
        from.keyFor(reactor.getSelector()).interestOps(SelectionKey.OP_READ);
        to.keyFor(reactor.getSelector()).interestOps(SelectionKey.OP_READ);
    }

    private boolean valid(SocketChannel channel) {
        return channel.isConnected() && channel.isRegistered() && channel.isOpen();
    }

    private void clearInterestOps() {
        from.keyFor(reactor.getSelector()).interestOps(0);
        to.keyFor(reactor.getSelector()).interestOps(0);

    }
}
