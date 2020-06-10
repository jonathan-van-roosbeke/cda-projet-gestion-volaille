package volaille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import entite.Eleveur;

public final class Poulet extends VolailleAbattable implements IVolailleAbattable {
	
	private static int poidPouletAbattage;
	private static int prixDuPoulet;
	private static int nombrePoulet;
	private static List<Poulet> listPoulet = new ArrayList<>();
	private static Map<Integer,Poulet> hmPoulet = new HashMap<>();
	private int prix;

	public Poulet(int pPoid, int pTaille, int pAge, int pPrix) {
		super(pPoid, pTaille, pAge);
		this.prix = pPrix;
		listPoulet.add(this);
		hmPoulet.put(this.getId(), this);
		++nombrePoulet;
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(e.getVolaillePosede() + 1);
		Eleveur.getInstance().setNmbrCanards(nombrePoulet);
		System.out.println("Poulet ajouté!");
	}
	
	public static void vente(int pIndex) {
		hmPoulet.remove(pIndex);
		Iterator<Poulet> it = listPoulet.iterator();
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
		
		nombrePoulet -= 1;
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(Eleveur.getVolaillePosede() - 1);
	}
	
	public static int getNombrePoulet() {
		return nombrePoulet;
	}

	public static int getPoidPouletAbattage() {
		return poidPouletAbattage;
	}

	public static void setPoidPouletAbattage(int poidPouletAbattage) {
		Poulet.poidPouletAbattage = poidPouletAbattage;
	}

	public static int getPrixDuPoulet() {
		return prixDuPoulet;
	}

	public static void setPrixDuPoulet(int prixDuPoulet) {
		Poulet.prixDuPoulet = prixDuPoulet;
	}

	public static List<Poulet> getListPoulet() {
		return listPoulet;
	}
	
	@Override
	public String toString() {
		return super.toString() + " prix Poulet : " + this.prix;
	}

	@Override
	public int calculPrix() {
		if(Poulet.poidPouletAbattage <= this.getPoid()) {
			return Poulet.prixDuPoulet * this.getPoid();
		}
		return -1;
	}
}
