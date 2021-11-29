package com.eale.proxy.transparent;

import lombok.Data;
import lombok.SneakyThrows;

import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;


/**
 * @Author HLD
 * @Date 2021/11/25 0025
 **/
@Data
public class Reactor {

    private Selector selector;

    private volatile boolean finish = false;

    @SneakyThrows
    public Reactor() {
        selector = Selector.open();
    }

    @SneakyThrows
    public Reactor pipe(SocketChannel from, SocketChannel to){
        from.configureBlocking(false);
        from.register(selector, SelectionKey.OP_READ,new SocketPipe(this,from,to));
        return this;
    }

    @SneakyThrows
    public void run(){
        try {
            while (!finish){
                if (selector.selectNow() > 0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isValid() && selectionKey.isReadable()){
                            ((SocketPipe) selectionKey.attachment()).pipe();
                        }
                        iterator.remove();
                    }
                }

            }
        }finally {
            close();
        }


    }

    @SneakyThrows
    public synchronized void close() {
        if (finish){
            return;
        }
        finish = true;
        if (!selector.isOpen()){
            return;
        }
        for (SelectionKey selectedKey : selector.selectedKeys()) {
            closeChannel(selectedKey.channel());
            selectedKey.cancel();
        }
        if (selector != null) {
            selector.close();
        }

    }

    @SneakyThrows
    public void closeChannel(Channel channel) {
        SocketChannel socketChannel = (SocketChannel) channel;
        if (socketChannel.isConnected() && socketChannel.isOpen()){
            socketChannel.shutdownInput();
            socketChannel.shutdownOutput();
        }

    }

    public void cancel(SocketChannel channel) {
        SelectionKey selectionKey = channel.keyFor(selector);
        if (Objects.isNull(selectionKey)){
            return;
        }
        selectionKey.cancel();
    }
}
