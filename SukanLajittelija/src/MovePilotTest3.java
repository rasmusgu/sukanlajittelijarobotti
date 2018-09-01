

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.*;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;

public class MovePilotTest3 {
	public static void main(String[] args) {
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.D);
		Wheel wheel1 = WheeledChassis.modelWheel(left, 4.32).offset(-7.5);
		//offset on renkaan keskipisteen et��isyys robotin keskipisteest��,
		// vasen miinusmerkkisen
		Wheel wheel2 = WheeledChassis.modelWheel(right, 4.32).offset(7.5);
		Chassis chassis = new WheeledChassis(
				new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);

		//luodaan kartta
		Rectangle suorakulmio = new Rectangle(0,0,150,100);
		Line[] janat = new Line[8];
		// rajaavan suorakulmion sivut
		janat[0] = new Line(0,0,150,0);
		janat[1] = new Line(150,0,150,100);
		janat[2] = new Line(0,100,150,100);
		janat[3] = new Line(0,0,0,100);
		// pystysuora este
		janat[4] = new Line(30,20,30,60);
		// kolmionmuotoinen alue
		janat[5] = new Line(90,50,120,20);
		janat[6] = new Line(120,20,130,50);
		janat[7] = new Line(90,50,130,50);
		LineMap kartta = new LineMap(janat, suorakulmio);

		// luodaan pilotti (esim. DifferentialPilot)
		// luodaan kartta (LineMap), alkusijainti (Pose) ja kohde (Waypoint)
		// luodaan navigaattori:
		Navigator navi = new Navigator(pilot) ;

		// Tapa 1: Polun etsint�� navigaatioverkon avulla:
		FourWayGridMesh navigaatioverkko = new FourWayGridMesh(kartta, 20, 10);
		// viivat 20 cm v��lein, viivoja ei 10 cm l��hemm��ksi kartan kohteita

		AstarSearchAlgorithm algoritmi= new AstarSearchAlgorithm();
		PathFinder polunEtsijä = new NodePathFinder(algoritmi, navigaatioverkko);
		Pose alkusijainti = null;
		Waypoint kohde = null;
		Path polku = null;
		try {
			polku = polunEtsijä.findRoute(alkusijainti, kohde);
		} catch (DestinationUnreachableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //varaudu poikkeukseen!

		// Tapa 2: Polun etsint�� suoraan kartan avulla ilman verkkoa:
		ShortestPathFinder polunetsijä = new ShortestPathFinder(kartta);
		polunetsijä.lengthenLines(10); // pidennet����n kartan viivoja joka suuntaan,jotta
		// robotti mahtuu liikkumaan alueella (oletus: robotti tarvitsee 10 cm
		// tilaa keskipisteens�� ulkopuolelle)

		Pose alkusijainti2 = null;
		Waypoint kohde2 = null;
		try {
			Path polku2 = polunEtsijä.findRoute(alkusijainti2, kohde2);
		} catch (DestinationUnreachableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //varaudu poikkeukseen!

		// Seuraavaksi navigaattorille asetetaan polku, jota se l��htee seuraamaan
		navi.setPath(polku);
		navi.followPath();
		navi.waitForStop();
	}
}