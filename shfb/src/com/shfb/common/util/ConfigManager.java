package com.shfb.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
private static ConfigManager cm;
	
	//属性文件路径 
    static String profilepath="config.properties";  
    static InputStream fis=ConfigManager.class.getClassLoader().getResourceAsStream(profilepath);

	public static ConfigManager getInstance()
	{
		if(cm==null)
		{
			cm=new ConfigManager();
		}
		return cm;
	}

     private static Properties props = new Properties();   
     static {   
         try {   
             props.load(fis);   
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
             System.exit(-1);   
         } catch (IOException e) {          
             System.exit(-1);   
         }   
     }  

     /**
      * 读取属性文件中相应键的值 
      * @param key
      * @return
      */
      public static String getKeyValue(String key) {
          return props.getProperty(key);   
      }   
}
