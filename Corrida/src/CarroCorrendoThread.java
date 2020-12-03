import java.util.Random;

public class CarroCorrendoThread extends Thread {
	
	private String nome;					// nome do carro
	private int distanciaPercorrida = 0;		// distância total já corrida pelo carro
	private int distanciaTotalCorrida;	// distância a ser corrida pelo carro
	private int metro = 0;					// distancia percorrida pelo carro em mt
	private static int colocacao = 0;		// colocação do carro ao final da corrida
	private final static int DISTANCIA_MAXIMA = 10; // movimento máximo em mt que um carro pode andar
	private boolean entrouPitstop = false;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDistanciaPercorrida() {
		return distanciaPercorrida;
	}
	public void setDistanciaPercorrida(int distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}
	public int getDistanciaTotalCorrida() {
		return distanciaTotalCorrida;
	}
	public void setDistanciaTotalCorrida(int distanciaTotalCorrida) {
		this.distanciaTotalCorrida = distanciaTotalCorrida;
	}
	public int getMetro() {
		return metro;
	}
	public void setMetro(int metro) {
		this.metro = metro;
	}
	public static int getColocacao() {
		return colocacao;
	}
	public static void setColocacao(int colocacao) {
		CarroCorrendoThread.colocacao = colocacao;
	}
	public boolean isEntrouPitstop() {
		return entrouPitstop;
	}
	public void setEntrouPitstop(boolean entrouPitstop) {
		this.entrouPitstop = entrouPitstop;
	}
	public static int getDistanciaMaxima() {
		return DISTANCIA_MAXIMA;
	}
	/** Construtor da classe. Parâmetros : Nome do carro e Distância da Corrida */
	public CarroCorrendoThread (String nome, int distanciaTotalCorrida) {
		/* chamando o construtor de Thread passando o nome do carro como parâmetro */
		super(nome);
		setDistanciaTotalCorrida(distanciaTotalCorrida);
		setNome(nome);
	}
	//utilizado para simular o deslocamento do carro
	public String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append(' ');
	    }
	    sb.append(inputString);

	    return sb.toString();
	}
	/** Imprime o último movimento do carro e a distância percorrida */
	public void carroImprimindoSituacao () {
		StringBuilder nomes = new StringBuilder();
	    nomes.append(padLeftZeros("-- ________ \n", distanciaPercorrida+12))
		     .append(padLeftZeros("-/__\\\\\\\\____\\\\___ \n", distanciaPercorrida+18))
		     .append(padLeftZeros("/…._.|-….|……._.o\\\\ \n", distanciaPercorrida+19))
		     .append(padLeftZeros("=’(@)--------(@)-´    ", distanciaPercorrida+22))
		     .append(nome)
		     .append(" andou ")
		     .append(metro)
		     .append("mt e já percorreu ") 
		     .append(distanciaPercorrida)
		     .append("mt");
	    
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    System.out.println(nomes.toString());
	}
	
	/** Faz o carro movimentar */
	public void movimentoCarro() {
		metro = (int) (Math.random() * DISTANCIA_MAXIMA);
		distanciaPercorrida += metro;
		if (distanciaPercorrida > distanciaTotalCorrida) {
			distanciaPercorrida = distanciaTotalCorrida;
		}
	}
	
	/** Representando a pausa do carro */
	public void carroParado () {
		Thread.yield();
	}
	
	public static int getMillis() {
	    return (int) (Math.random() * 1000);
	}
	
	//pitstop
	public synchronized void pitstop() {
		if(distanciaPercorrida >= this.distanciaTotalCorrida/2 && isEntrouPitstop() != true) {
	    	
			try {
				System.out.println(nome + " está no Pitstop!");
				Thread.sleep(getMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setEntrouPitstop(true);
		}
	}
	
	/** Imprime a colocação do carro ao final da corrida */
	public void colocacaoCarro () {
		colocacao++;
		StringBuilder nomes = new StringBuilder();
	    nomes.append("║▒▓▒▓▒ \n")
		     .append("║▓▒▓▒▓ \n")
		     .append("║ \n")
		     .append(nome)
		     .append(" foi o ")
		     .append(colocacao)
		     .append("º colocado!");

	    System.out.println(nomes.toString());
	}
	/** Método run da thread Corrida de carros */
	public void run () {
		while (distanciaPercorrida < distanciaTotalCorrida) {
			movimentoCarro();
			carroImprimindoSituacao();
			carroParado();
			pitstop();
		}
		colocacaoCarro();
	}
}
