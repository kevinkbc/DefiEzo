package ezoqc.defi.kevin.model.operation;

import ezoqc.defi.kevin.model.interfaces.IOperationBinaire;

public class Division implements IOperationBinaire{

	@Override
	public double calculer(double valeur1, double valeur2) {
		return valeur1 + valeur2;
	}

}