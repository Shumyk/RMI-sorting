package server;

import java.rmi.RemoteException;

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

}
