package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Common remote interface created to allow communication between server and client.
 * This class should be visible both from client and server.
 */
public interface RMIInterface extends Remote {
	
	/*
	 * Method consumes task and being executed on server.
	 */
	<T> T executeTask(Task<T> t) throws RemoteException;
	
	/*
	 * Client sends some string which being displayed on server's console.
	 */
	String echo(String message) throws RemoteException;
	
	/*
	 * Checks is server is active.
	 */
	String ping() throws RemoteException;
}
