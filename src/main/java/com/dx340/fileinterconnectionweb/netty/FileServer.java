package com.dx340.fileinterconnectionweb.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * 服务端
 * 1.创建一个ServerBootstrap的实例引导和绑定服务器。
 * 2.创建并分配一个NioEventLoopGroup实例以进行事件的处理，比如接受连接以及读写数据。
 * 3.指定服务器绑定的本地的InetSocketAddress。
 * 4.使用一个EchoServerHandler的实例初始化每一个新的Channel。
 * 5.调用ServerBootstrap.bind()方法以绑定服务器。
 *
 * @author Zsk-d
 */


@Component
public class FileServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServer.class);
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(String hostname, int port) {

        ChannelFuture f = null;
        try {
            //ServerBootstrap负责初始化netty服务器，并且开始监听端口的socket请求
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(hostname, port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(
                                    new LineBasedFrameDecoder(1024 * 15),
                                    new StringDecoder(CharsetUtil.UTF_8),
                                    new StringEncoder(CharsetUtil.UTF_8),
                                    new FileServerHandler()
                            );
                        }
                    });
            f = b.bind().sync();
            channel = f.channel();
            LOGGER.info("======Server启动成功!!!=========");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (f != null && f.isSuccess()) {
                LOGGER.info("Netty server listening " + hostname + " on port " + port + " and ready for connections...");
            } else {
                LOGGER.error("Netty server start up Error!");
            }
        }
        return f;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        LOGGER.info("Shutdown Netty Server...");
        if (channel != null) {
            channel.close();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        LOGGER.info("Shutdown Netty Server Success!");
    }

}