package ezoqc.defi.kevin.service;

import java.util.HashMap;
import java.util.Map;

import ezoqc.defi.kevin.model.interfaces.IOperateur;
import ezoqc.defi.kevin.model.operateur.Operateur;
import ezoqc.defi.kevin.model.tree.NoeudArbre;

public class CalculatriceService {
	String expressionInitiale;
	
	NoeudArbre racine = null;
	
	Map<Character, Operateur> operateurMap = new HashMap<Character, Operateur>();
	
	public NoeudArbre creerArbreExpression(String expressionString) throws Exception {
		NoeudArbre racine = new NoeudArbre();
		
		//trim to remove blanks
		//expressionString = expressionString.trim();
		//tokenize expression (doubles, negatives, operators, etc) 
		for(int i = 0;i<expressionString.length();i++) {
			char courrant = expressionString.charAt(i);
			
			if(Character.isDigit(courrant)) {
				racine = ajouterNoeud(racine, creerNoeudNumero(expressionString, i));
			} if(isOperateur(courrant)) {
				racine = ajouterNoeud(racine, creerNoeudOperateur(courrant));
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
						racine.setPere(nouveauNoeud);
						return nouveauNoeud;
					} else {
						NoeudArbre pere = racine.getPere();
						pere.setFilsDroite(nouveauNoeud);
						nouveauNoeud.setPere(pere);
						racine.setPere(nouveauNoeud);
						return nouveauNoeud;
					}
				}
			} else if(racine.getExpression() instanceof Operateur){
				if(nouveauNoeud.getExpression() instanceof Double) {
					if(racine.getFilsGauche() == null) {
						racine.setFilsGauche(nouveauNoeud);
					} else if(racine.getFilsDroite() == null) {
						racine.setFilsDroite(nouveauNoeud);
					} else if(racine.getFilsDroite().getExpression() instanceof Operateur) {
						//seulement ajouter numero, pas besoin de mise a jour le racine
						ajouterNoeud(racine.getFilsDroite(), nouveauNoeud);
					}
					  else {
						throw new Exception("Expression invalide! 3 numeros pour une operateur binaire!");
					}
					return racine;
				} else if(nouveauNoeud.getExpression() instanceof Operateur){
					
					//int prioriteRacine = (Operateur) racine.getExpression()
					if(racine.getPriorite() > nouveauNoeud.getPriorite()) {
						racine.setFilsDroite(ajouterNoeud(racine.getFilsDroite(), nouveauNoeud));
					} else {
						//shift arbre operateur
						nouveauNoeud.setFilsGauche(racine);
						return nouveauNoeud;
					}
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
	
	public NoeudArbre creerNoeudOperateur(char courrant) {
		//int position = positionDepart;
		/*
		 * while(position < expression.length() && (expression.charAt(position) == '.'||
		 * Character.isDigit(expression.charAt(position)))) { position++; }
		 */
		Operateur op;
		NoeudArbre noeud = new NoeudArbre();
		noeud.setExpression(this.operateurMap.get(courrant));
		//noeud.setExpression(Double.parseDouble(expression.substring(positionDepart, position)));
		return noeud;
	}
	
	public boolean isOperateur(char character) {
		if(this.operateurMap.containsKey(character));
		return true;
	}
	
	public void remplirMap() {
		this.operateurMap.put('+', new Operateur("+",1));
		this.operateurMap.put('-', new Operateur("-",1));
		this.operateurMap.put('*', new Operateur("*",2));
		this.operateurMap.put('/', new Operateur("/",2));
	}
}
