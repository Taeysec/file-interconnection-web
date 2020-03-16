package com.dx340.fileinterconnectionweb.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/***
 * 服务端自定义业务处理handler
 * @author Zsk-d
 */
public class MessageServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 对每一个传入的消息都会调用；
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Client send: " + in.toString(CharsetUtil.UTF_8));
        ctx.write("Server recv ok! msg:" + in);
        ctx.flush();
    }

    /**
     * 在读取操作期间，有异常抛出时会调用。
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("channel active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        ctx.close();
        super.channelInactive(ctx);
        System.out.println("channel in inactive");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("channel unregistered");
    }

}