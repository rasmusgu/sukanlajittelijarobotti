import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Etaisyysanturi {
	static Port portIR = LocalEV3.get().getPort("S1");
	static SensorModes sensorIR = new EV3IRSensor(portIR);
	SampleProvider distance = ((EV3IRSensor)sensorIR).getDistanceMode();
	//control = ((EV3IRSensor)sensorIR).getRemoteCommand(0);
	float[] sample = new float[distance.sampleSize()];
	float distanceToWall;

	/* tarpeeton
	public boolean clear() {
		distance.fetchSample(sample, 0);
		distanceToWall = sample[0];
		if (distanceToWall > 10) {
			return true;
		}
		else {
			return false;
		}
	}
	*/

	public boolean notClear() {
		distance.fetchSample(sample, 0);
		distanceToWall = sample[0];
		if (distanceToWall > 10) {
			return false;
		}
		else {
			return true;
		}
	}
	public float testEtaisyysanturi() {
		distance.fetchSample(sample, 0);
		//System.out.println("Distance: " + sample[0]);
		return sample[0];
	}

	public static void closeEtaisyysanturi() {
	}


}
