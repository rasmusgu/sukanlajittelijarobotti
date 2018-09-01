import lejos.hardware.Button;
import lejos.robotics.subsumption.*;

public class Hataseis implements Behavior {

	private volatile boolean suppressed = false;

	private Remote remote;

	public Hataseis(Remote l) {
		remote = l;
	}

	public boolean takeControl() {
		return remote.press();
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		while (!suppressed) {
			Liike.stopMoving();
			System.out.println("Hataseis kutsuttu");
			Button.waitForAnyPress();
		}
	}

}
