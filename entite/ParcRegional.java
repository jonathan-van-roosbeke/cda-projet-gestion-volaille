package entite;

import java.util.ArrayList;
import java.util.List;

import volaille.Paon;

public final class ParcRegional {

	private static ParcRegional instance = null;
	private static List<Paon> listeDePaonDuParc = new ArrayList<>();
	
	private ParcRegional() {
	}
	
	public final static ParcRegional getInstance() {
		if(ParcRegional.instance == null) {
			ParcRegional.instance = new ParcRegional();
		}
		return ParcRegional.instance;
	}
}
