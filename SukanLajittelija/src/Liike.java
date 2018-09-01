import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;
import lejos.utility.Delay;

//import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import lejos.hardware.Button;
import lejos.hardware.motor.*;

public class Liike implements Behavior{
	private static RegulatedMotor leftMotor;
	private static RegulatedMotor rightMotor;
	private final static int speedMotor = 720;
	private final static int accelerationMotor = 800;
	private static RegulatedMotor backMotor;

	private volatile boolean suppressed = false;

	private int counter = 1;
	private static int counter2 = 1;

	@Override
	public boolean takeControl() {
		//System.out.println("Liikueteenpain takeControl true");
		return true;
	}
	//return false;

	@Override
	public void suppress() {
		suppressed = true;
	}

	public void action() {
		if (counter == 1) {
			initiateMotor();
			System.out.println("Motor initiated");
			counter++;
		}
		setSpeedBoth(200);
		leftMotor.forward();
		rightMotor.forward();
		while (!suppressed)Thread.yield();
		//System.out.println("LiikuEteenpain action done");

		//stopMoving();
	}

	public static void moveStraight(int x) {
		setSpeedBoth(500);
		leftMotor.forward();
		rightMotor.forward();
		try { Thread.sleep(x); } catch (InterruptedException e) {}
	}

	public static void stopMoving() {
		leftMotor.setSpeed(0);
		rightMotor.setSpeed(0);
	}

	public static void setSpeedBoth(int x) {
		leftMotor.setSpeed(x);
		rightMotor.setSpeed(x);
	}

	public static void initiateMotor() {
		leftMotor = Motor.A;
		rightMotor = Motor.D;
		leftMotor.setSpeed(300);
		rightMotor.setSpeed(300);
		leftMotor.setAcceleration(accelerationMotor);
		rightMotor.setAcceleration(accelerationMotor);
		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();
		leftMotor.rotateTo(0);
		rightMotor.rotateTo(0);
	}

	public static void turnLeft() {
		stopMoving();
		Delay.msDelay(300);
		leftMotor.setSpeed(500);
		rightMotor.setSpeed(500);
		leftMotor.backward();
		rightMotor.forward();
		try { Thread.sleep(1000); } catch (InterruptedException e) {};
		//System.out.println("Turn left done");
		//stopMoving();
	}

	public static void turnRight() {
		stopMoving();
		Delay.msDelay(300);
		setSpeedBoth(500);
		leftMotor.forward();
		rightMotor.backward();
		try { Thread.sleep(1000); } catch (InterruptedException e) {};
		//System.out.println("Turn right done");
		//stopMoving();
	}

	public static void turnBack() {
		stopMoving();
		Delay.msDelay(300);
		setSpeedBoth(500);
		leftMotor.forward();
		rightMotor.backward();
		try { Thread.sleep(2000); } catch (InterruptedException e) {};
		//System.out.println("Turn back done");
		//stopMoving();
	}

	public static void kaannos() {

		int n = ThreadLocalRandom.current().nextInt(1, 3 + 1);

		String verbal;
		if (n == 1) {
			turnLeft();
			verbal = "left";
		}
		else if (n == 2) {
			turnRight();
			verbal = "right";
		}
		else {
			turnBack();
			verbal = "back";
		}
		System.out.println("Turn chosen: " + verbal /* + "\n" + "Kaannos valmis0000" */);
		//Delay.msDelay(3000);
	}

	public static void peruutusKaannos() {
		Delay.msDelay(500);
		leftMotor.setSpeed(accelerationMotor);
		rightMotor.setSpeed(accelerationMotor);
		leftMotor.backward();
		rightMotor.backward();
		if (counter2 == 1) {
			try { Thread.sleep(300); } catch (InterruptedException e) {};
			counter2++;
		}
		else {
			try { Thread.sleep(700); } catch (InterruptedException e) {};
		}
		//System.out.println("peruutusvaihe valmis");
		kaannos();
	}

	public static void siivoa(int index) { //lajittelee sukan sen listan indeksin mukaisesti
		Liike.nosta();
		//siirtyminenLajitteluun(index);
		Liike.irti();
	}

	public static void nosta() {
		backMotor = Motor.A;
		backMotor.setSpeed(speedMotor);
		backMotor.setAcceleration(accelerationMotor);
		//backMotor.resetTachoCount();
		backMotor.rotateTo(0);
		backMotor.backward();
		try { Thread.sleep(600); } catch (InterruptedException e) {};

	}

	public static void irti() {
		backMotor = Motor.A;
		backMotor.setSpeed(speedMotor);
		backMotor.setAcceleration(accelerationMotor);
		//backMotor.resetTachoCount();
		backMotor.rotateTo(0);
		backMotor.forward();
		try { Thread.sleep(600); } catch (InterruptedException e) {};
		backMotor.backward();
		try { Thread.sleep(600); } catch (InterruptedException e) {}; //nostaa tarttujan takaisin ylos jotta robotti saa liikuttua rauhassa
	}
}
