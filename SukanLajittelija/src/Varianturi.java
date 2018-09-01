import java.util.*;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Varianturi {
	Port portColor = LocalEV3.get().getPort("S4"); //kiinnitä porttiin 4!
	SensorModes sensorColor = new EV3ColorSensor(portColor);
	SampleProvider colorProvider = ((EV3ColorSensor) sensorColor).getRGBMode();
	float[] sample2 = new float[colorProvider.sampleSize()];

	int virhemarginaali = 30; //mielestämme sopiva "oikean" värin löytämiseksi
	List<String> sock_values = new ArrayList<>();
	List<Integer> rgb = new ArrayList<>();

	public boolean notClear() {
		colorProvider.fetchSample(sample2, 0);

		int r = Math.round(sample2[0] * 765);
		int g = Math.round(sample2[1] * 765);
		int b = Math.round(sample2[2] * 765);
		rgb.add(r); rgb.add(b); rgb.add(g);

		//kalibroi pöydän väriarvot!!
		int r_table = 85;
		int b_table = 31;
		int g_table = 16;
		List<Integer> rgb_table = new ArrayList<>();
		rgb_table.add(r_table);
		rgb_table.add(b_table);
		rgb_table.add(g_table);

		//kalibroi teipin arvot!!
		int r_tape = 60;
		int b_tape = 60;
		int g_tape = 60;
		List<Integer> rgb_tape = new ArrayList<>();
		rgb_tape.add(r_tape);
		rgb_tape.add(b_tape);
		rgb_tape.add(g_tape);

		// System.out.println(r + " " + g + " " + b);
		if (((r != r_table || (r > r_table + virhemarginaali && r < r_table - virhemarginaali)) //jos poikkeaa tarpeeksi pöydän väristä,
				&& (b != b_table || (b > b_table + virhemarginaali && b < b_table - virhemarginaali))
				&& (g != g_table || (g > g_table + virhemarginaali && g < g_table - virhemarginaali)))) {
			System.out.println("Poikkeaa pöydän väristä!");
			checkSock(sample2);
			return true;
		}
		else if (((r == r_tape || (r >= r_tape - virhemarginaali && r <= r_tape + virhemarginaali)) //tarkistaa onko kalibroitu teipin väriarvo
				&& (b == b_tape || (b >= b_tape - virhemarginaali && b <= b_tape + virhemarginaali))
				&& (g == g_tape || (g >= g_tape - virhemarginaali && g <= g_tape + virhemarginaali)))) {
			System.out.println("Löysin reunateipin!");
			return true;
		}
		else {
			return false;
		}
	}

	ArrayList<String> sukkalista = new ArrayList<>();

	private void checkColour() {
		for (int i = 0; i <= sock_values.size(); i++) {
			for (int j = 0; i < 3; i++) {
				//if (sock_values[i].compareTo(rgb[j])) {

			}
		}
	}

	private void checkSock(float[] rgb) {
		String colour_check = Arrays.toString(rgb); //muuntaa RGB-väriarvot String:iksi

		if (!sock_values.contains(colour_check)) { //tarkistaa onko löytynyt väri jo sukkien listalla
			System.out.println("Uusi väri löytynyt!");
			sock_values.add(colour_check); //jos sukan väri ei ole vielä listassa, lisää sen listaan
		}
		else {
			//sock_colour = sock_values.contains(colour_check);
		}
		Liike.siivoa(1/*colour_check index in sock_values*/);
	}



	public int testVarianturi() {
		colorProvider.fetchSample(sample2, 0);

		int r = Math.round(sample2[0] * 765);
		// int g = Math.round(sample2[1]*765);
		// int b = Math.round(sample2[2]*765);

		// System.out.println("Colours: " + r + b + g);
		return r;
	}
}
