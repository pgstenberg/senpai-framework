package se.stonepath.senpai.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class HttpConnection {
	
	public final static String API_PATH = "/api/delegate";
	public final static String API_PATH_LIST = API_PATH + "/list";
	public final static String API_PATH_STATUS = API_PATH + "/status";
	
	
	public static boolean checkUrl(String targetUrl){
		try{
		URL u = new URL(targetUrl); 
		HttpURLConnection huc = (HttpURLConnection)u.openConnection(); 
		huc.setRequestMethod("GET"); 
		huc.connect();
		System.out.println(huc.getResponseCode());
		return (huc.getResponseCode() == 200);
		}catch(Exception e){
			return false;
		}
	}
	
	public static String executeGet(String targetUrl,HashMap<String,String> urlParamters) throws Exception{
		
		
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(targetUrl);
		
		if(!urlParamters.isEmpty()){
			Iterator<Entry<String, String>> iterator = urlParamters.entrySet().iterator();
			urlBuilder.append("?");
			while(iterator.hasNext()){
				Entry<String,String> entry = iterator.next();
				
				urlBuilder.append(entry.getKey() +"="+entry.getValue());
				
				if(iterator.hasNext())
					urlBuilder.append("&");
			}
		}
		
		
		 
		URL obj = new URL(urlBuilder.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		return response.toString();
	}
	
	public static String excutePost(String targetURL, String urlParameters) throws Exception
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/json; charset=utf-8");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);
	      
	      connection.getOutputStream().write(urlParameters.getBytes(Charset.forName("UTF-8")));

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      throw e;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }

}
