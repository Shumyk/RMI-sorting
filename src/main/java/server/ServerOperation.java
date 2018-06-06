package server;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import remote.RMIInterface;
import remote.Task;

public class ServerOperation implements RMIInterface {
	
	protected ServerOperation() throws RemoteException {
		super();
	}

	@Override
	public <T> T executeTask(Task<T> task) throws RemoteException {
		
		return task.execute();
	}

	@Override
	public String echo(final String message) throws RemoteException {
		String messageLocal = message;
		System.out.println("[message from client] ".concat(messageLocal));
		
		return "[response from server] ".concat(message);
	}

	@Override
	public String ping() throws RemoteException {
		System.out.println("Recieved ping from client!");
		return "Server is active";
	}
}
