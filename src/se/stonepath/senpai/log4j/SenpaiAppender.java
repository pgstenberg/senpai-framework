package se.stonepath.senpai.log4j;




import java.net.InetAddress;
import java.util.logging.Logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.google.gson.Gson;

import se.stonepath.senpai.network.HttpConnection;
import se.stonepath.senpai.skeleton.LogSkeleton;
import se.stonepath.senpai.skeleton.RemoteSkeleton;

public class SenpaiAppender extends AppenderSkeleton{

	private String backendUrl = new String();
	private String applicationCode = new String();
	private String rmiUrl = new String();
	private String rmiInterface = new String();
	
	private final static String postUrl = "/api/log/add";
	
	

	
	public String getBackendUrl() {
		return backendUrl;
	}

	public void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getRmiUrl() {
		return rmiUrl;
	}

	public void setRmiUrl(String rmiUrl) {
		this.rmiUrl = rmiUrl;
	}

	public String getRmiInterface() {
		return rmiInterface;
	}

	public void setRmiInterface(String rmiInterface) {
		this.rmiInterface = rmiInterface;
	}
	
	

	@Override
	public void close() {
		
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	 @Override  
	 public void activateOptions()  
	 { 	
		try {
			RemoteSkeleton remoteSkeleton = new RemoteSkeleton(applicationCode, rmiUrl, rmiInterface);
			
			if(HttpConnection.checkUrl(backendUrl))
				Logger.getGlobal().info("Connection to " + backendUrl + " seams to be fine.");
			else
				throw new Exception("Senpai server " + backendUrl + " seams to be offline.");
			
			HttpConnection.excutePost(backendUrl +"/api/remote/add", new Gson().toJson(remoteSkeleton));
		} catch (Exception e) {
			Logger.getGlobal().info(e.getMessage());
		}
		
		
	 }
	
	@Override
	protected void append(LoggingEvent loggingEvent) {
		if(backendUrl.equals(""))
			throw new NullPointerException();
		if(loggingEvent.getLoggerName().equals(getClass()))
			return;
	
		try {
			HttpConnection.excutePost(backendUrl + postUrl, new LogSkeleton(loggingEvent,applicationCode,InetAddress.getLocalHost().getHostName()).toJson());
			
		} catch (Exception e) {
			Logger.getGlobal().info("Unable to reach host " + backendUrl);
		}

		
	}

}
