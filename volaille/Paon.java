package volaille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import entite.Eleveur;

public final class Paon extends VolailleDuParc {
	
	private static int nombrePaon;
	private static List<Paon> listPaon = new ArrayList<>();
	private static Map<Integer,Paon> hmPaon = new HashMap<>();
	
	public Paon() {
		++nombrePaon;
		listPaon.add(this);
		hmPaon.put(this.getId(), this);
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(e.getVolaillePosede() + 1);
		Eleveur.getInstance().setNmbrPaons(nombrePaon);
	}
	
	public static void rendre(int pIndex) {
		hmPaon.remove(pIndex);
		Iterator<Paon> it = listPaon.iterator();
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
		
		nombrePaon -= 1;
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(Eleveur.getVolaillePosede() - 1);
	}

	public static int getNombrePaon() {
		return nombrePaon;
	}

	public static List<Paon> getListPaon() {
		return listPaon;
	}

	public static Map<Integer, Paon> getHmPaon() {
		return hmPaon;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | paon";
	}

}
