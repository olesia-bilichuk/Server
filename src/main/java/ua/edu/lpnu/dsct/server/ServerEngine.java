package ua.edu.lpnu.dsct.server;

import ua.edu.lpnu.dsct.common.abstraction.IRemote;
import ua.edu.lpnu.dsct.common.abstraction.ITask;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

public class ServerEngine implements IRemote {
    private int port = 2077;
    private String remoteReferenceName = "ua.edu.lpnu.dsct.server.ServerEngine";
    private Logger logger = Logger.getLogger(ServerEngine.class.getName());

    public ServerEngine() {

    }

    public ServerEngine(int port, String remoteReferenceName) {
        this.port = port;
        this.remoteReferenceName = remoteReferenceName;
    }

    public void init() throws RemoteException {
        logger.info("Server initialization (name = " + remoteReferenceName + ", port = " + port + ") ...");
        IRemote stub = (IRemote) UnicastRemoteObject.exportObject(this, this.port);
        Registry registry = LocateRegistry.createRegistry(this.port);
        registry.rebind(remoteReferenceName, stub);
        logger.info("Server initialized.");
    }

    @Override
    public <T> T executeTask(ITask<T> t) {
        logger.info("Received " + t.getClass().getName());
        Instant startTime = Instant.now();
        T result = t.execute();
        Instant stopTime = Instant.now();
        long duration = Duration.between(startTime, stopTime).toMillis();
        logger.info("Finished. Time elapsed: " + duration + " ms");
        return result;
    }
}
