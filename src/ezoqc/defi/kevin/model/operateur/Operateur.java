package ezoqc.defi.kevin.model.operateur;

public class Operateur {
	private String symbole;
	private int priorite;
	
	public Operateur(String symbole, int priorite) {
		super();
		this.symbole = symbole;
		this.priorite = priorite;
	}

	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}


	
	
}
