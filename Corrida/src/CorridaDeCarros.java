
public class CorridaDeCarros {
	final static int DISTANCIA = 100; // Distância da corrida em mt
	public static void main (String[] args) {
		/* colocando carros na corrida */	
			new CarroCorrendoThread("carro 1", DISTANCIA).start();
			new CarroCorrendoThread("carro 2", DISTANCIA).start();
			new CarroCorrendoThread("carro 3", DISTANCIA).start();
			new CarroCorrendoThread("carro 4", DISTANCIA).start();
			new CarroCorrendoThread("carro 5", DISTANCIA).start();
	}
}
