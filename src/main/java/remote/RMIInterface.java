package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	
	<T> T executeTask(Task<T> t) throws RemoteException;
	
	String echo(String message) throws RemoteException;
	
	String ping() throws RemoteException;
}
