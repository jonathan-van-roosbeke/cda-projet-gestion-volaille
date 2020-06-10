package menu;

import java.util.Collections;
import java.util.Scanner;

import entite.Eleveur;
import entite.ParcRegional;
import volaille.Canard;
import volaille.Cygne;
import volaille.Paon;
import volaille.Poulet;
import volaille.Volaille;
import volaille.VolailleComparator;

public class Menu {
	private final static Scanner SC = new Scanner(System.in);
	//private final static Scanner SC = new Scanner(Menu.class.getResourceAsStream("input.txt"));
	private boolean exit;

	public void run() {
		while (!exit) {
			printMenu();
			int choix = choixMenu();
			choixAction(choix);
		}
	}

	private void printMenu() {
		System.out.println("0 - quitte l'application.");
		System.out.println("1 - ajouter une volaille.");
		System.out.println("2 - ajouter un paon/cygne.");
		System.out.println("3 - modifier poids abattage.");
		System.out.println("4 - modifier prix du jour.");
		System.out.println("5 - afficher nombre de volaille par type.");
		System.out.println("6 - voir le total de prix des volailles abattables.");
		System.out.println("7 - vendre une volaille");
		System.out.println("8 - rendre un paon/cygne au parc");
		System.out.println("9 - affiche toutes les volailles");
	}

	private int choixMenu() {
		Scanner sc = new Scanner(System.in);
		int choix = -1;
		boolean choixEffectue = false;
		while (!choixEffectue) {
			choix = Integer.parseInt(sc.nextLine());
			if (choix < 0 || choix > 9) {
				System.out.println("Choisissez un numéro dans la liste");
			} else {
				choixEffectue = true;
			}
		}
		return choix;
	}

	private void choixAction(int choix) {
		switch (choix) {
		case 0:
			exit = exit();
			break;

		case 1:
			ajoutVolaille();
			break;

		case 2:
			ajoutAnimauxParc();
			break;

		case 3:
			modifierPoidAbattage();
			break;

		case 4:
			modifierPrixDuJour();
			break;
			
		case 5:
			nombreVolailleParType();
			break;

		case 6:
			prixTotalDesVolailles();
			break;

		case 7:
			vendreVolaille();
			break;

		case 8:
			rendreVolailleAuParc();
			break;

		case 9:
			afficheVolaille();
			break;
		}
	}
	
	private void ajoutVolaille() {
		Eleveur e = Eleveur.instance();
		if(Eleveur.getVolaillePosede() < 7) {
			int choix = askNewType();
			int poid = askPoid();
			int taille = askTaille();
			int age = askAge();
			int prix = askPrix();
			
			if(choix == 1 && Canard.getNombreCanard() < 4) {
				Canard c = new Canard(poid, taille, age, prix);
			} else if (choix == 2 && Poulet.getNombrePoulet() < 5) {
				Poulet p = new Poulet(poid, taille, age, prix);
			} else if (Canard.getNombreCanard() == 4) {
				System.out.println("Vous avez atteint votre maximum de canards");
			} else if (Poulet.getNombrePoulet() == 5) {
				System.out.println("Vous avez atteint votre maximum de poulet");
			}
		} else {
			System.out.println("Vous avez atteint le nombre limite de volaille");
		}
		
		System.out.println("Vous possedez : " + Eleveur.getVolaillePosede() + " volailles / 7 volailles dont : \n"
				+ Canard.getNombreCanard() + " canards / 4 canards\n"
				+ Poulet.getNombrePoulet() + " poulets / 5 poulets\n"
				+ Paon.getNombrePaon() + " paons / 3 paons\n"
				+ Cygne.getNombreCygne() + " cygnes / 2 cygnes\n");
	}
	
	private void ajoutAnimauxParc() {
		ParcRegional pr = ParcRegional.getInstance();
		Eleveur e = Eleveur.instance();
		if(Eleveur.getVolaillePosede() < 7) {
			int choix = askNewTypeParc();
			
			if(choix == 1 && Paon.getNombrePaon() < 3) {
				Paon p = new Paon();
			} else if (choix == 2 && Cygne.getNombreCygne() < 2) {
				Cygne c = new Cygne();
			} else if (Paon.getNombrePaon() == 3) {
				System.out.println("Vous avez atteint votre maximum de paons");
			} else if (Cygne.getNombreCygne() == 2) {
				System.out.println("Vous avez atteint votre maximum de cygnes");
			}
			System.out.println("Vous possedez : " + Eleveur.getVolaillePosede() + " volailles / 7 volailles dont : \n"
					+ Canard.getNombreCanard() + " canards / 4 canards\n"
					+ Poulet.getNombrePoulet() + " poulets / 5 poulets\n"
					+ Paon.getNombrePaon() + " paons / 3 paons\n"
					+ Cygne.getNombreCygne() + " cygnes / 2 cygnes\n");
		} else {
			System.out.println("Vous avez atteint le nombre limite de volaille");
		}
	}
	
	private void modifierPoidAbattage() {
		int choix = askType();
		int poid = askPoidAbattage();
		
		if(poid < 0) {
			System.out.println("Vous ne pouvez pas entrer un poid negatif!");
			return;
		}
		
		if(choix == 1) {
			Canard.setPoidCanardAbattage(poid);
			System.out.println("Le nouveau poid de l'abattage du canard est de : " + poid + " kg");
			
		} else {
			Poulet.setPoidPouletAbattage(poid);
			System.out.println("Le nouveau poid de l'abattage du poulet est de : " + poid + " kg");
		}
	}
	
	private void modifierPrixDuJour() {
		int choix = askType();
		int prix = askPrixDuJour();
		
		if(choix == 1) {
			Canard.setPrixDuCanard(prix);
			System.out.println("Le prix du jour du canard est à : " + prix + "€");
		} else {
			Poulet.setPrixDuPoulet(prix);
			System.out.println("Le prix du jour du poulet est à : " + prix + "€");
		}
	}
	
	public void nombreVolailleParType() {
		nVolaille(Canard.getNombreCanard(), "Canard");
		nVolaille(Poulet.getNombrePoulet(), "Poulet");
		nVolaille(Paon.getNombrePaon(), "Paon");
		nVolaille(Cygne.getNombreCygne(), "Cygne");
	}
	
	public void prixTotalDesVolailles() {
		
		if(Poulet.getNombrePoulet() > 0 && Poulet.getPoidPouletAbattage() == 0) {
			System.out.println("Veuillez d'abord entrer un poid d'abatage pour le poulet supperieur à 0");
			modifierPoidAbattage();
		}
		
		if (Poulet.getNombrePoulet() > 0 && Poulet.getPrixDuPoulet() == 0) {
			System.out.println("Veuillez d'abord entre un prix au kg pour le poulet");
			modifierPrixDuJour();
		}
		
		if(Canard.getNombreCanard() > 0 && Canard.getPoidCanardAbattage() == 0) {
			System.out.println("Veuillez d'abord entrer un poid d'abatage pour du canard supperieur à 0");
			modifierPoidAbattage();
		}
		
		if (Canard.getNombreCanard() > 0 && Canard.getPrixDuCanard() == 0) {
			System.out.println("Veuillez d'abord entre un prix au kg pour le cannard");
			modifierPrixDuJour();
		}
		
		int totalPrice = 0;
		
		
		for(Poulet p : Poulet.getListPoulet()) {
			if(p.calculPrix() != -1) {
				totalPrice += p.calculPrix();
			}
		}
		
		for(Canard c : Canard.getListCanard()) {
			if(c.calculPrix() != -1) {
				totalPrice += c.calculPrix();
			}
		}
		
		System.out.println("Le prix total de la volaille s'élève à : " + totalPrice);
	}
	
	private void vendreVolaille() {
		System.out.println("***VENTE***");
		int choix = askType();
		
		if(choix == 1 && Canard.getListCanard().size() == 0) {
			System.out.println("Pas de canard dans la liste");
			return;
		} else if (choix == 2 && Poulet.getListPoulet().size() == 0) {
			System.out.println("Pas de poulet dans la liste");
			return;
		}
		
		
		if(choix == 1 && Canard.getListCanard().size() > 0) {
			afficheCanard();
			System.out.println("Choisissez le canard par id : ");
			int idCanard = SC.nextInt();
			Canard.vente(idCanard);
			System.out.println("Le canard n° " + idCanard + " est vendu");
			
		} else if (choix == 2 && Poulet.getListPoulet().size() > 0) {
			affichePoulet();
			System.out.println("Choisissez le poulet par id : ");
			int idPoulet = SC.nextInt();
			Canard.vente(idPoulet);
			System.out.println("Le poulet n° " + idPoulet + " est vendu");
		} else {
			System.out.println("id non trouvé");
		}
	}
	
	private void rendreVolailleAuParc() {
		System.out.println("***Rendre au parc***");
		int choix = askNewTypeParc();
		
		if(choix == 1 && Paon.getListPaon().size() == 0) {
			System.out.println("Pas de paon dans la liste");
			return;
		} else if (choix == 2 && Cygne.getListCygne().size() == 0) {
			System.out.println("Pas de cygne dans la liste");
			return;
		}
		
		
		if(choix == 1 && Paon.getListPaon().size() > 0) {
			affichePaon();
			System.out.println("Choisissez le paon par id : ");
			int idPaon = SC.nextInt();
			Paon.rendre(idPaon);
			System.out.println("Le paon n° " + idPaon + " est rendu");
			
		} else if (choix == 2 && Cygne.getListCygne().size() > 0) {
			afficheCygne();
			System.out.println("Choisissez le cygne par id : ");
			int idCygne = SC.nextInt();
			Cygne.rendre(idCygne);
			System.out.println("Le cygne n° " + idCygne + " est vendu");
		} else {
			System.out.println("id non trouvé");
		}
	}

	private int askNewType() {
		System.out.println("Quel type de volaille souhaitez-vous ajouter ?");
		System.out.println("1-canard / 2-poulet");
		return SC.nextInt();
	}
	
	private int askNewTypeParc() {
		System.out.println("Quel type de volaille souhaitez-vous recevoir de la part parc ?");
		System.out.println("1-paon / 2-cygne");
		return SC.nextInt();
	}
	
	private int askType() {
		System.out.println("Quel type de volaille souhaitez-vous choisir ?");
		System.out.println("1-canard / 2-poulet");
		return SC.nextInt();
	}
	
	private int askPoid() {
		System.out.println("Quel est le poid ?");
		return SC.nextInt();
	}
	
	private int askPoidAbattage() {
		System.out.println("Quel est le nouveau poid de l'abattage ?");
		return SC.nextInt();
	}
	
	private int askTaille() {
		System.out.println("Quel est la taille ?");
		return SC.nextInt();
	}
	
	private int askAge() {
		System.out.println("Quel est son age ? (en semaine)");
		return SC.nextInt();
	}
	
	private int askPrix() {
		System.out.println("Quel est le prix ?");
		return SC.nextInt();
	}
	
	private int askPrixDuJour() {
		System.out.println("Quel est le prix du jour ?");
		return SC.nextInt();
	}
	
	private void afficheCanard() {
		System.out.println("Voici la liste des canards");
		for(Canard c : Canard.getListCanard()) {
			System.out.println(c);
		}
	}
	
	private void affichePoulet() {
		System.out.println("Voici la liste des canards");
		for(Poulet p : Poulet.getListPoulet()) {
			System.out.println(p);
		}
	}
	
	private void affichePaon() {
		System.out.println("Voici la liste des paons");
		for(Paon p : Paon.getListPaon()) {
			System.out.println(p);
		}
	}
	private void afficheCygne() {
		System.out.println("Voici la liste des cygnes");
		for(Cygne c : Cygne.getListCygne()) {
			System.out.println(c);
		}
	}
	
	private void afficheVolaille() {
		Collections.sort(Volaille.listeVolaille, new VolailleComparator());
		for(Volaille v : Volaille.listeVolaille) {
			System.out.println(v);
		}
	}
	
	private boolean exit() {
		System.out.println("Au revoir");
		return true;
	}
	
	private void nVolaille(int pNombre, String type) {
		System.out.println("Vous avez " + pNombre + " de " + type);
	}
}
