import lejos.robotics.subsumption.*;

public class VariMatch  implements Behavior{

	private Varianturi match;
	//LiikuEteenpain liiku = new LiikuEteenpain();
	private volatile boolean suppressed = false;

	public VariMatch(Varianturi i) {
		match = i;// TODO Auto-generated constructor stub
	}

	public boolean takeControl() {
		return match.notClear();
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		while (!suppressed) {
			Liike.peruutusKaannos();
		}
	}
}
