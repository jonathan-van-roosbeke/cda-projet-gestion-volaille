package volaille;

public abstract class VolailleAbattable extends Volaille implements Comparable<VolailleAbattable> {
	
	private int poid;
	private int taille;
	private int age;
	
	protected VolailleAbattable(int pPoid, int pTaille, int pAge) {
		this.poid = pPoid;
		this.taille = pTaille;
		this.age = pAge;
	}
	
	public int getPoid() {
		return poid;
	}

	public void setPoid(int poid) {
		this.poid = poid;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | poid: " + this.getPoid() + " | taille: " + this.getTaille() + " | age: " + this.getAge();
	}
	
	@Override
	public int compareTo(VolailleAbattable pVolailleAbattable) {
		return Integer.compare(this.getPoid(), pVolailleAbattable.getPoid());
	}
}
