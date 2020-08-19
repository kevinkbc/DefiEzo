package ezoqc.defi.kevin.model.tree;

import ezoqc.defi.kevin.model.operateur.Operateur;

public class NoeudArbre {
	private Object expression;
	private NoeudArbre pere;
	private NoeudArbre filsGauche;
	private NoeudArbre filsDroite;
	
	public Object getExpression() {
		return expression;
	}
	public void setExpression(Object expression) {
		this.expression = expression;
	}
	public NoeudArbre getPere() {
		return pere;
	}
	public void setPere(NoeudArbre pere) {
		this.pere = pere;
	}
	public NoeudArbre getFilsGauche() {
		return filsGauche;
	}
	public void setFilsGauche(NoeudArbre filsGauche) {
		this.filsGauche = filsGauche;
	}
	public NoeudArbre getFilsDroite() {
		return filsDroite;
	}
	public void setFilsDroite(NoeudArbre filsDroite) {
		this.filsDroite = filsDroite;
	}
	
	public int getPriorite() {
		if (this.getExpression() instanceof Operateur) {
			Operateur op = (Operateur)this.getExpression();
			return op.getPriorite();
		} else return 0;
	}
	
}
