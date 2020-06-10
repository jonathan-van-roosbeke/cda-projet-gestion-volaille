package volaille;

import java.util.Comparator;

public class VolailleComparator implements Comparator<Volaille> {

	@Override
	public int compare(Volaille pVolailleA, Volaille pVolailleB) {
		if(pVolailleA instanceof VolailleDuParc) {
			if(pVolailleB instanceof VolailleDuParc) {
				return ((VolailleDuParc)pVolailleB).compareTo((VolailleDuParc)pVolailleA);
			} else {
				return -1;
			}
		} else {
			if(pVolailleA instanceof VolailleAbattable) {
				if(pVolailleB instanceof VolailleAbattable) {
					return ((VolailleAbattable)pVolailleB).compareTo((VolailleAbattable)pVolailleA);
				} else {
					return 1;
				}
			}
		}
		return 0;
	}

}
