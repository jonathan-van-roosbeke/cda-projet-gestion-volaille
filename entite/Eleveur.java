package entite;

public final class Eleveur {
	
	private static Eleveur instance = null;
	private static int volaillePosede = 0;
	private int nmbrCanards = 0;
	private int nmbrPoulets = 0;
	private int nmbrPaons = 0;
	private int nmbrCygnes = 0;
	
	Eleveur() {
	}
	
	public static Eleveur instance() {
		if(Eleveur.instance == null) {
			Eleveur.instance = new Eleveur();
		}
		return Eleveur.instance;
	}

	public static int getVolaillePosede() {
		return volaillePosede;
	}

	public static void setVolaillePosede(int volaillePosede) {
		Eleveur.volaillePosede = volaillePosede;
	}

	public static Eleveur getInstance() {
		return instance;
	}

	public int getNmbrCanards() {
		return nmbrCanards;
	}

	public void setNmbrCanards(int nmbrCanards) {
		this.nmbrCanards = nmbrCanards;
	}

	public int getNmbrPoulets() {
		return nmbrPoulets;
	}

	public void setNmbrPoulets(int nmbrPoulets) {
		this.nmbrPoulets = nmbrPoulets;
	}

	public int getNmbrPaons() {
		return nmbrPaons;
	}

	public void setNmbrPaons(int nmbrPaons) {
		this.nmbrPaons = nmbrPaons;
	}

	public int getNmbrCygnes() {
		return nmbrCygnes;
	}

	public void setNmbrCygnes(int nmbrCygnes) {
		this.nmbrCygnes = nmbrCygnes;
	}
	
}
