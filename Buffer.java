package semaforos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer {
	
	// Transportista de almacenamiento en almacén
	private static Queue<Integer> store = new LinkedList<Integer>();
	// Capacidad máxima del almacén
	public static final int reservas = 8;
	//Semaforos que activan hilos  para llenar el buffer o para consumir
	private static Semaphore sNoVacio = new Semaphore(0, true);
	private static Semaphore sNoLleno = new Semaphore(reservas, true);
	
	public static Queue<Integer> getStore() {
		return store;
	}
	public static Semaphore getsNoVacio() {
		return sNoVacio;
	}
	public static Semaphore getsNoLleno() {
		return sNoLleno;
	}

}
