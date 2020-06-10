package volaille;

import java.util.ArrayList;
import java.util.List;

public abstract class Volaille {

	public static List<Volaille> listeVolaille = new ArrayList<>();
	private static int compteur;
	private int id;
	
	protected Volaille() {
		this.id = ++compteur;
		listeVolaille.add(this);
	}

	public static int getCompteur() {
		return compteur;
	}

	public int getId() {
		return id;
	}

	public List<Volaille> getListeVolaille() {
		return listeVolaille;
	}
	
	@Override
	public String toString() {
		return "id: " + this.getId();
	}
}
