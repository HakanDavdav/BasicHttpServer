package org.example.core;

import org.example.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer extends Thread{
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    ServerSocket serverSocket;
    InputStream inputStream;
    OutputStream outputStream;


    public HttpServer(){
        configurationManager.loadConfiguration("C:/Users/Administrator/Desktop/Projects/TestSite/src/main/resources/http.json");
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {
            while (!serverSocket.isClosed()) {
                serverSocket = new ServerSocket(configurationManager.getCurrentConfiguration().getPort());
                Socket socket = serverSocket.accept();
                HttpServerSchemaLoader httpServerSchemaLoader = new HttpServerSchemaLoader(socket);
                httpServerSchemaLoader.start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
