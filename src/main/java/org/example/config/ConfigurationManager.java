package org.example.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ConfigurationManager {

    static private ConfigurationManager myConfigurationManager = null;
    private Configuration myConfiguration;
    private ObjectMapper objectMapper;
    private String filepath;


   private ConfigurationManager(){
   }

   static public ConfigurationManager getInstance(){
      if (myConfigurationManager==null) {
          myConfigurationManager = new ConfigurationManager();
      }
          return myConfigurationManager;

   }

   public void loadConfiguration(String filepath) throws ConfigurationException {
       FileReader fileReader;
       StringBuffer stringBuffer;
       Json json;

       try {
           fileReader = new FileReader(filepath);
       } catch (FileNotFoundException e) {
           throw new ConfigurationException("File path isn't valid");
       }

       stringBuffer = new StringBuffer();
           int i;

           while (true) {
               try {
                   if (!((i = fileReader.read()) != -1)) break;
               } catch (IOException e) {
                   throw new ConfigurationException("Loaded config file is defected");
               }
               stringBuffer.append((char) i);
           }


           json = new Json();
       try {
           this.myConfiguration = json.treeIntoObject (json.jsonIntoTree(stringBuffer.toString()) , Configuration.class );
       } catch (JsonProcessingException e) {
           throw new ConfigurationException(e);
       }

   }

   public Configuration getCurrentConfiguration(){
       if(myConfigurationManager==null){
          throw new ConfigurationException("no configuration set");
       }
       return myConfiguration;

   }

    public ConfigurationManager getMyConfigurationManager() {
        return myConfigurationManager;
    }

    public void setMyConfigurationManager(ConfigurationManager myConfigurationManager) {
        this.myConfigurationManager = myConfigurationManager;
    }


}
