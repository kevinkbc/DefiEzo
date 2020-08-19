package ezoqc.defi.kevin.service;

import ezoqc.defi.kevin.model.interfaces.IOperateur;
import ezoqc.defi.kevin.model.operateur.Operateur;
import ezoqc.defi.kevin.model.tree.NoeudArbre;

public class CalculatriceService {
	String expressionInitiale;
	
	NoeudArbre racine = null;
	
	public NoeudArbre creerArbreExpression(String expressionString) throws Exception {
		NoeudArbre racine = new NoeudArbre();
		
		//trim to remove blanks
		//expressionString = expressionString.trim();
		//tokenize expression (doubles, negatives, operators, etc) 
		for(int i = 0;i<expressionString.length();i++) {
			char courrant = expressionString.charAt(i);
			
			if(Character.isDigit(courrant)) {
				ajouterNoeud(racine, creerNoeudNumero(expressionString, i));
			}
		}
		//look for double numbers
		
		
		return racine;
	}
	
	//methode recursive pour ajouter un fils dans l'arbre
	private NoeudArbre ajouterNoeud(NoeudArbre racine, NoeudArbre nouveauNoeud) throws Exception {
		if(racine == null) {
			racine = nouveauNoeud;
		} else {
			//numero
			if(racine.getExpression() instanceof Double) {
				//fils numero aussi -> expression invalide
				if(nouveauNoeud.getExpression() instanceof Double) {
					throw new Exception("Expression invalide!");
				} else if(nouveauNoeud.getExpression() instanceof Operateur){
					//change operateur et numero
					//check racine pere
					if(racine.getPere() == null) {
						nouveauNoeud.setFilsGauche(racine);
						return nouveauNoeud;
					} else {
						NoeudArbre pere = racine.getPere();
						pere.setFilsGauche(nouveauNoeud);
					}
				}
			} else if(racine.getExpression() instanceof Operateur){
				if(nouveauNoeud.getExpression() instanceof Double) {
					if(racine.getFilsGauche() == null) {
						racine.setFilsGauche(nouveauNoeud);
					} else if(racine.getFilsDroite() == null) {
						racine.setFilsDroite(nouveauNoeud);
					} else {
						throw new Exception("Expression invalide! 3 numeros pour une operateur binaire!");
					}
				} else if(nouveauNoeud.getExpression() instanceof Operateur){
					//shift operator tree
				}
			}
			//racine = ajouterNoeud(racine, nouveauNoeud);
		}
		
		return racine;
	}

	public NoeudArbre creerNoeudNumero(String expression, int positionDepart) {
		int position = positionDepart;
		while(position < expression.length() && 
				(expression.charAt(position) == '.'|| Character.isDigit(expression.charAt(position)))) {
			position++;
		}
		NoeudArbre noeud = new NoeudArbre();
		noeud.setExpression(Double.parseDouble(expression.substring(positionDepart, position)));
		return noeud;
	}
	
	public NoeudArbre creerNoeudOperateur(String expression, int positionDepart) {
		int position = positionDepart;
		/*
		 * while(position < expression.length() && (expression.charAt(position) == '.'||
		 * Character.isDigit(expression.charAt(position)))) { position++; }
		 */
		NoeudArbre noeud = new NoeudArbre();
		//noeud.setExpression(Double.parseDouble(expression.substring(positionDepart, position)));
		return noeud;
	}
	
	public boolean isOperateur(char character) {
		return true;
	}
	
	
}
