import lejos.robotics.subsumption.*;

public class Tormays  implements Behavior{

	private Etaisyysanturi tormayskurssi;
	private volatile boolean suppressed = false;

	public Tormays(Etaisyysanturi k) {
		tormayskurssi = k;
	}

	public boolean takeControl() {
		return tormayskurssi.notClear();
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		if (!suppressed) {
			Liike.peruutusKaannos();
		}
		else {
			Liike.stopMoving();
			System.out.println("Stopped moving, action: Tormays");
		}
	}


}
