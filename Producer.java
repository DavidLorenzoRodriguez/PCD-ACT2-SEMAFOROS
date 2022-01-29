package semaforos;


import java.util.Random;

public class Producer extends Thread{
	
	public Producer(){
		start();
	}
	private void produce(){
		//con la clase Random creamos un numero aleatorio que sera el que se entiende como producido
		Random rdmNum = new Random();
		int numP = rdmNum.nextInt(999) +1;
		
		//dormiremos el hilo con un tiempo de espera aleatorio
		int sleepTime = rdmNum.nextInt(300 - 25 + 1)+25; //rango (max - min +1) + min
		
		//captura de las posibles excepciones
		try{
			sleep(sleepTime);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		//mostramos por consola el numero producido
		System.out.println("Productor: Numero "+ numP + "producido.");
		
		//Añadir elemento al buffer
		Buffer.getStore().add(numP);
	}
	
	@Override
	public void run(){
		
		while(true){
			if(Buffer.getStore().size()==Buffer.reservas){
				System.out.println("Productor: El buffer está lleno, esperando a que el consumidor trabaje.");
			}
			//hacemos uso del semaforo, primero el semaforo no lleno despues el semaforo no vacio
			try{
				Buffer.getsNoLleno().acquire();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			produce();
			Buffer.getsNoVacio().release();
		}		
	}
}
