package se.stonepath.senpai.skeleton;

public class RemoteSkeleton {

	private String applicationCode;
	private String rmiUrl;
	private String rmiInterface;
	
	public RemoteSkeleton() {
	}
	
	public RemoteSkeleton(String applicationCode,String rmiUrl,String rmiInterface) {
		this.applicationCode = applicationCode;
		this.rmiUrl = rmiUrl;
		this.rmiInterface = rmiInterface;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public String getRmiUrl() {
		return rmiUrl;
	}

	public String getRmiInterface() {
		return rmiInterface;
	}
	
	
	
}
