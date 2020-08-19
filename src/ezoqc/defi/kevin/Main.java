package ezoqc.defi.kevin;

import ezoqc.defi.kevin.service.CalculatriceService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculatriceService calculatriceService = new CalculatriceService();
		String expression = "1 + 2";
		try {
			calculatriceService.creerArbreExpression(expression);
			double resultat = calculatriceService.calculerArbre();
			System.out.println("Resultat: "+ resultat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
