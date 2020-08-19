package ezoqc.defi.kevin;

import java.util.Scanner;

import ezoqc.defi.kevin.service.CalculatriceService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculatriceService calculatriceService = new CalculatriceService();
		//String expression = "2.2 * 10 - 4 + 5 * 2";
		Scanner in = new Scanner(System.in);
		System.out.print("Veuillez ecrire l'expression: ");
		String expression = in.nextLine();
		while(expression != "") {
			  
	        
			try {
				calculatriceService.creerArbreExpression(expression);
				double resultat = calculatriceService.calculerArbre();
				System.out.println("Resultat: "+ resultat);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.print("Veuillez ecrire l'expression: ");
			expression = in.nextLine();
		}
        
	}

}
