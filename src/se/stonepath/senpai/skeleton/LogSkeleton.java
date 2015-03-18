package se.stonepath.senpai.skeleton;


import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import com.google.gson.Gson;

public class LogSkeleton {
	
	
	private String message;
	private String level;
	private String logger;
	private String appCode;
	private String host;
	private long timestamp;
	
	public LogSkeleton(){
		
	}
	
	public LogSkeleton(LoggingEvent loggingEvent,String appCode,String host) {
		this.message = loggingEvent.getMessage().toString();
		this.level = loggingEvent.getLevel().toString();
		this.logger = loggingEvent.getLogger().getName();
		this.appCode = appCode;
		this.timestamp = getUnixStamp();
		this.host = host;
	}
	public LogSkeleton(String message, Level level, String logger, String appCode,String host) {
		this.message = message;
		this.level = level.toString();
		this.logger = logger;
		this.appCode = appCode;
		this.timestamp = getUnixStamp();
		this.host = host;
	}
	public LogSkeleton(String message, Level level, String logger, String appCode,String host,long timestamp) {
		this.message = message;
		this.level = level.toString();
		this.logger = logger;
		this.appCode = appCode;
		this.timestamp = timestamp;
		this.host = host;
	}
	
	private static long getUnixStamp(){
		return System.currentTimeMillis() / 1000L;
	}
	
	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level.toString();
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String application) {
		this.appCode = application;
	}

	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String toJson(){
		return new Gson().toJson(this);
	}
}
