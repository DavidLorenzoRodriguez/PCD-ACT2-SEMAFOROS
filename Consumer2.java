package semaforos;

import java.util.Random;

public class Consumer2 extends Thread{
	
	public Consumer2(){
		
		start();
	}
	
	private void consume(){
		//creamos tiempo de espera, que tarda cada consumidor en consumir los elementos
		Random rdmNum = new Random();
		int sleepTime = rdmNum.nextInt(200 - 25 +1) +25;
			
			//captura de las posibles excepciones
			try{
				sleep(sleepTime);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		
			for (int i = 0; i<= Buffer.getStore().size()-1; i++){
			      //Consumir los elementos
			      int consumedNumber = Buffer.getStore().poll();
			      //se informa al usuario del elemento consumido
			      System.out.println("Consumidor1: N�mero " + consumedNumber + " consumido."); //se informa al usuario del elemento consumido
			    }
	}
	
	@Override
	public void run(){
		
		while(true){
			//comprobar que el buffer esta vacio
			if(Buffer.getStore().size()==0){
				System.out.println("Consumidor2: El buffer est� vac�o, esperando a que el productor trabaje.");
			}
			//hacemos uso del semaforo, primero el semaforo no vacio despues el semaforo no lleno
			try{
				Buffer.getsNoVacio().acquire();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			consume();
			//este semaforo dara paso a otra produccion, y frenara las producciones cuando este lleno
			Buffer.getsNoLleno().release();
		}
	}
}

	
