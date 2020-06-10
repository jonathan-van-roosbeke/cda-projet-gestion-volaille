package volaille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import entite.Eleveur;

public final class Cygne extends VolailleDuParc {
	
	private static int nombreCygne;
	private static List<Cygne> listCygne = new ArrayList<>();
	private static Map<Integer,Cygne> hmCygne = new HashMap<>();
	
	public Cygne() {
		++nombreCygne;
		listCygne.add(this);
		hmCygne.put(this.getId(), this);
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(e.getVolaillePosede() + 1);
		Eleveur.getInstance().setNmbrCygnes(nombreCygne);
	}
	
	public static void rendre(int pIndex) {
		hmCygne.remove(pIndex);
		Iterator<Cygne> it = listCygne.iterator();
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
		
		nombreCygne -= 1;
		Eleveur e = Eleveur.getInstance();
		e.setVolaillePosede(Eleveur.getVolaillePosede() - 1);
	}

	public static int getNombreCygne() {
		return nombreCygne;
	}

	public static List<Cygne> getListCygne() {
		return listCygne;
	}

	public static Map<Integer, Cygne> getHmCygne() {
		return hmCygne;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | cygne";
	}
}
