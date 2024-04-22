package org.example;

import org.example.config.ConfigurationManager;
import org.example.core.HttpServer;



public class Main {
    public static void main(String[] args) {


        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        configurationManager.loadConfiguration("C:/Users/Administrator/Desktop/Projects/TheSite/src/main/resources/http.json");
        System.out.println(configurationManager.getCurrentConfiguration().getPort()+ configurationManager.getCurrentConfiguration().getWebroot());

        HttpServer httpServer = new HttpServer();
        httpServer.run();



    }
}