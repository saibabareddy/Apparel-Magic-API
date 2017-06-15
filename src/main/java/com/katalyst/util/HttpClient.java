package com.katalyst.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class HttpClient 
	{		
static Logger logger = Logger.getLogger(HttpClient.class);
public static JSONObject sendto(JSONObject requestEncodeddata, String method, String param) throws Exception	
		{		
        String responseJsonString = "";       
        String respondMessage = null;
        String errorMessage = null;
        JSONObject postData = null;      
        String time= "171114279788";
        String token="64ebd05e550b23a15be09ccef57b27c6";
        HttpURLConnection conn = null;
        JSONObject responseJSON = null;
        OutputStream os = null;
        String baseurl = "https://sandbox2.app.apparelmagic.com/api/json";
        String errorCodeHeader = null;
        try 
        {        	
            URL url;
            if (method.equalsIgnoreCase("GET")){
            	System.out.println(baseurl.toString() + "/" + param);
                url = new URL(baseurl.toString() + "/" + param);                
            }
            else
            {
               if(method.equals("POST") && param != null)
                {
                url = new URL(baseurl.toString()+"/"+param);
                }
                else
                {
                url= new URL(baseurl.toString());   
                }
            }
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            if(method.equals("POST"))
            {
            	conn.setRequestProperty("Content-Type", "application/json");
            }
            if (requestEncodeddata != null) 
            {
             os = conn.getOutputStream();             
             postData = requestEncodeddata;                
             os.write(postData.toString().getBytes(StandardCharsets.UTF_8));
             os.flush();
            }
            int returnCode = conn.getResponseCode();
            respondMessage = conn.getResponseMessage();
            errorCodeHeader = "Error code: " + returnCode + ": " + respondMessage; 
            if (returnCode >= 400) 
            {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));				
				String output;				
				while ((output = br.readLine()) != null) 
				{
					responseJsonString = responseJsonString + output;				
				}				
				responseJSON = getJSONDocument(responseJsonString);
				responseJSON.put("returnCode", returnCode);				
				JSONObject errorJson = (JSONObject) responseJSON.get("error");				
				if (errorJson != null) 
				{				
				errorMessage = responseJSON.get("status") +", "+ errorJson.get("message").toString()+","+errorJson.get("correctiveAction").toString();
				}				
				return responseJSON;				
				}											            
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));												            
            String output;				            
            while ((output = br.readLine()) != null) 
            {				
            	responseJsonString = responseJsonString + output;								           
            }
								            
            if ((responseJsonString != null) && (responseJsonString.length() > 0)) 
            {								       				                
            	responseJSON = getJSONDocument(responseJsonString);								                
            	responseJSON.put("returnCode", returnCode);								            
            }												        
        } catch (Exception e){        				        
        	logger.error("General Exception:" + e.getLocalizedMessage() + e);								        
        } finally {								           
        	if (os != null) {								               
        		try {								                  
        			os.close();								               
        		} catch (IOException e) {				
				                    // ignore								                
        		}								            
        	}								            
        	if (conn != null) {								                
        		conn.disconnect();								            
        	}								        
        }       						        						        
        return responseJSON;								    
		}
	
private static JSONObject getJSONDocument(String jsonString)
{
		try {
			JSONObject theobj = (JSONObject)JSONSerializer.toJSON(jsonString);
			return theobj;			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
}

}
