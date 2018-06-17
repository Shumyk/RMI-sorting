package remote;

public interface Task<T> {
	
	/*
	 * Contains description of task that client creates and sends to server, where this is being
	 * executed and server sends back result of execution.
	 */
	T execute();

}
