import lejos.hardware.Button;
import lejos.robotics.subsumption.*;

public class SukanLajittelija{

	//public static float kTestResult;
	//public static int iTestResult;

	public static void main(String[] args) {
		//antureiden testivaihe
		/* opi sulkemaan portit!
		try {
			Varianturi iTest = new Varianturi();
			Etaisyysanturi kTest = new Etaisyysanturi();
			iTestResult = iTest.testVarianturi();
			kTestResult= kTest.testEtaisyysanturi();
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid Sensor Mode: " + e.getMessage());
			System.out.println("Press button within 6s to exit");
			int button = Button.waitForAnyPress(6000);
			if (button != 0) {
				System.exit(0);
			}
		}
		*/

		Remote l = new Remote();
		Etaisyysanturi k = new Etaisyysanturi();
		Varianturi i = new Varianturi();
		//Siivous j = new Siivous;

		Behavior b1 = new Liike();
		Behavior b2 = new Tormays(k);
		Behavior b3 = new VariMatch(i);
		Behavior b4 = new Hataseis(l);

		Behavior [] bArray = {b1, b2, b3, b4};

		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
		System.out.println("Press button to exit");
		Button.waitForAnyPress();
		System.exit(0);
	}

}