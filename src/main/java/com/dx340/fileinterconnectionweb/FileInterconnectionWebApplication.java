package com.dx340.fileinterconnectionweb;

import com.dx340.fileinterconnectionweb.netty.FileServer;
import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zsk-d
 */
@SpringBootApplication
@MapperScan("com.dx340.fileinterconnectionweb.mapper")
public class FileInterconnectionWebApplication implements CommandLineRunner {
    @Autowired
    private FileServer fileServer;
    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(FileInterconnectionWebApplication.class, args);

    }

    @Override
    public void run(String... args) {
        ChannelFuture future = fileServer.start(url, port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> fileServer.destroy()));
        future.channel().closeFuture().syncUninterruptibly();
    }
}
