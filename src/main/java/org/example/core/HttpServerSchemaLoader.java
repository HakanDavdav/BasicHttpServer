package org.example.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerSchemaLoader extends Thread{

    InputStream inputStream;
    OutputStream outputStream;
    Socket socket;

    public HttpServerSchemaLoader(Socket socket){
        this.socket = socket;
    }



    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final String CRLF = "\n\r";
        String html = "<html><head><title>Simple site</title></head><body><h1>Hello World!</h1></body></html>";
        String response = "HTTP/1.1 200 OK" + CRLF + // Status Line : HTTP VERSION RESPONSE_CODE RESPONSE MESSAGE
                "Content—Length: " + html.getBytes().length + CRLF + // HEADER
                CRLF +
                html +
                CRLF + CRLF;


        try {
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
