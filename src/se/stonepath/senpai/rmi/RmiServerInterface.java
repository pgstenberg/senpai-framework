package se.stonepath.senpai.rmi;


import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import se.stonepath.senpai.log4j.SenpaiAppender;

public interface RmiServerInterface extends Remote{

	
	public static void startServer(Logger logger, String appenderName,Remote remote) throws RemoteException, InstantiationException, IllegalAccessException, MalformedURLException, AlreadyBoundException{
		startServer(1099,logger,appenderName, remote);
	}
	public static void startServer(int port, Logger logger, String appenderName, Remote remote) throws RemoteException, InstantiationException, IllegalAccessException, MalformedURLException, AlreadyBoundException{
		
		LocateRegistry.createRegistry(port);

		RmiServerInterface.bindFromSenpaiAppender(logger, appenderName, remote);
	}
	
	public static void bindFromSenpaiAppender(Logger logger, String appenderName,Remote remote) throws RemoteException, MalformedURLException, AlreadyBoundException{
		SenpaiAppender senpaiAppender = (SenpaiAppender)logger.getAppender(appenderName);
		Naming.bind(senpaiAppender.getRmiUrl(), remote);
	}
}
