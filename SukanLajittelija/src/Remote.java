import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
//import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
//import lejos.hardware.sensor.SensorModes;

public class Remote {


	//EV3IRSensor ir = new EV3IRSensor(SensorPort.S2);

	//SensorModes sensorIR = new EV3IRSensor(portIR);

	 //static Port port = LocalEV3.get().getPort("S2");
     //static EV3IRSensor ir = new EV3IRSensor(port);

	public boolean press() {
		//control = ;
		//int control = ir.getRemoteCommand(1);
		int button = Button.waitForAnyPress(200);
		if (button == 0 /*|| control == 0*/) {
			return true;
		}
		else {
			System.out.println("Button pressed!");
			return false;
		}
	}
}
