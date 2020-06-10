package volaille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import entite.Eleveur;

public final class Canard extends VolailleAbattable implements IVolailleAbattable {
	
	private static int poidCanardAbattage;
	private static int prixDuCanard;
	private static int nombreCanard;
	private static List<Canard> listCanard = new ArrayList<>();
	private static Map<Integer,Canard> hmCanard = new HashMap<>();
	private int prix;
	
	public Canard(int pPoid, int pTaille, int pAge, int pPrix) {
		super(pPoid, pTaille, pAge);
		this.prix = pPrix;
		listCanard.add(this);
		hmCanard.put(this.getId(), this);
		++nombreCanard;
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(e.getVolaillePosede() + 1);
		Eleveur.getInstance().setNmbrCanards(nombreCanard);
		System.out.println("Canard ajouté!");
	}
	
	public static void vente(int pIndex) {
		hmCanard.remove(pIndex);
		Iterator<Canard> it = listCanard.iterator();
		while (it.hasNext()) {
		    if (it.next().getId() == pIndex) {
		        it.remove();
		        break;
		    }
		}
		Iterator<Volaille> it2 = Volaille.listeVolaille.iterator();
		while (it2.hasNext()) {
		    if (it2.next().getId() == pIndex) {
		        it2.remove();
		        break;
		    }
		}
		
		nombreCanard -= 1;
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(Eleveur.getVolaillePosede() - 1);
	}

	public static int getNombreCanard() {
		return nombreCanard;
	}

	public static int getPoidCanardAbattage() {
		return poidCanardAbattage;
	}

	public static void setPoidCanardAbattage(int poidCanardAbattage) {
		Canard.poidCanardAbattage = poidCanardAbattage;
	}

	public static int getPrixDuCanard() {
		return prixDuCanard;
	}

	public static void setPrixDuCanard(int prixDuCanard) {
		Canard.prixDuCanard = prixDuCanard;
	}

	public static List<Canard> getListCanard() {
		return listCanard;
	}

	public static Map<Integer, Canard> getHmCanard() {
		return hmCanard;
	}
	
	@Override
	public String toString() {
		return super.toString() + " prix Canard : " + this.prix;
	}

	@Override
	public int calculPrix() {
		if(Canard.poidCanardAbattage <= this.getPoid()) {
			return Canard.prixDuCanard * this.getPoid();
		}
		return -1;
	}
}
