package volaille;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class VolailleDuParc extends Volaille implements Comparable<VolailleDuParc> {
	
	String timeStamp;
	
	VolailleDuParc() {
		this.timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
	}
	
	@Override
	public int compareTo(VolailleDuParc pVolailleParc) {
		
		return Integer.compare(pVolailleParc.getId(), this.getId());
	}
	
	@Override
	public String toString() {
		return "date:" + timeStamp + " ";
	}
}
