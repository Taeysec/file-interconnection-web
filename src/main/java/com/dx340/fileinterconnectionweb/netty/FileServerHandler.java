package com.dx340.fileinterconnectionweb.netty;


import com.dx340.fileinterconnectionweb.bean.FileUploadFile;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;


/***
 * 服务端自定义业务处理handler
 * @author Zsk-d
 */
public class FileServerHandler extends ChannelInboundHandlerAdapter {
    private static final String CR = System.getProperty("line.separator");
    private int byteRead;
    private volatile int start = 0;
    private String file_dir = "/tmp";
    private final static Logger LOGGER = LoggerFactory.getLogger(FileServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
        LOGGER.info("服务端：channelActive()");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        LOGGER.info("服务端：channelInactive()");
        ctx.flush();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("收到客户端发来的文件,正在处理....");
        if (!(msg instanceof FileUploadFile)) {
            LOGGER.info("instanceof !");
            FileUploadFile ef = (FileUploadFile) msg;
            byte[] bytes = ef.getBytes();
            byteRead = ef.getEndPos();
            String md5 = ef.getFile_md5();
            String path = file_dir + File.separator + md5;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            start = start + byteRead;
            if (byteRead > 0) {
                ctx.writeAndFlush(start);
                randomAccessFile.close();
                if (byteRead != 1024 * 10) {
                    Thread.sleep(1000);
                    channelInactive(ctx);
                }
            } else {
                ctx.close();
            }
            LOGGER.info("处理完毕,文件路径:" + path + "," + byteRead);
        }
        LOGGER.info("处理完毕");

    }

}