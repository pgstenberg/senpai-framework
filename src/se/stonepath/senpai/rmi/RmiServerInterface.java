package se.stonepath.senpai.rmi;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import se.stonepath.senpai.log4j.SenpaiAppender;

public interface RmiServerInterface extends Remote{

	
	public static void startServer(Remote remote) throws RemoteException, InstantiationException, IllegalAccessException, MalformedURLException{
		startServer(1099,remote);
	}
	public static void startServer(int port, Remote remote) throws RemoteException, InstantiationException, IllegalAccessException, MalformedURLException{
		LocateRegistry.createRegistry(port);
		RmiServerInterface.bindFromSenpaiAppender(remote);
	}
	
	public static void bindFromSenpaiAppender(Remote remote) throws RemoteException, MalformedURLException{
		RmiServerInterface.bindFromSenpaiAppender(Logger.getRootLogger(),"senpai",remote);
	}
	public static void bindFromSenpaiAppender(Logger logger, String appenderName,Remote remote) throws RemoteException, MalformedURLException{
		SenpaiAppender senpaiAppender = (SenpaiAppender)logger.getAppender(appenderName);
		Naming.rebind(senpaiAppender.getRmiUrl(), remote);
	}
}
