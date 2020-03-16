package com.dx340.fileinterconnectionweb.netty;

import com.dx340.fileinterconnectionweb.bean.FileUploadFile;
import org.junit.Test;


import java.io.File;

public class ClientFileTest {

    public static void main(String[] args) {
        try {
            FileUploadFile uploadFile = new FileUploadFile();
            File file = new File("e:/out.txt");
            String fileMd5 = file.getName();
            uploadFile.setFile(file);
            uploadFile.setFile_md5(fileMd5);
            uploadFile.setStarPos(0);
            new FileUploadClient().connect(9090, "127.0.0.1", uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
