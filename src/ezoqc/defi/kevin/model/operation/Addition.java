package ezoqc.defi.kevin.model.operation;

import ezoqc.defi.kevin.model.interfaces.IOperationBinaire;

public class Addition implements IOperationBinaire{

	@Override
	public double calculer(double valeur1, double valeur2) {
		if(valeur1 == 1.0 && valeur2 == 1.0) {
			return 1;
		} else return valeur1 + valeur2;
	}

}
