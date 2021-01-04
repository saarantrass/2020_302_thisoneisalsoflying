package Domain.GameObjects;

import java.awt.Point;
import java.util.Random;

public class ShieldedAtom extends AtomDecorator {
	private double efficiency;
	private Throwable atom;
	private int neutron;
	private int proton;
	private static double ETA_EFF = 0.05;
	private static double LOTA_EFF = 0.1;
	private double THETA_EFF;
	private static double ZETA_EFF = 0.2;
	
	public ShieldedAtom(Throwable atom) {
		super(atom);
		this.efficiency=atom.getEfficiency();
		this.atom=atom;
		this.neutron=atom.getNeutron();
		this.proton=atom.getProton();
	}

	@Override
	public double getEfficiency() {
		return this.efficiency;
	}
	private void setEfficiency(double eff) {
		this.efficiency = eff;
	}
	public void addShield(int type) {
		double effFactor = 0.0;
		double speedFactor = 0.0;
		switch(type) {
		case 1:
			if(this.proton!=this.neutron) {
				effFactor = (1 - this.efficiency)*Math.abs(this.neutron-this.proton)/this.proton;
			}else {
				effFactor = (1 - this.efficiency) * ETA_EFF;
			}
			speedFactor = 0.05;
			break;
		case 2:
			effFactor = (1 - this.efficiency) * LOTA_EFF;
			speedFactor = 0.07;
			break;
		case 3:
			Random rn = new Random();
			THETA_EFF = rn.nextDouble()*(0.10)+0.05;
			effFactor = (1 - this.efficiency) * THETA_EFF;
			speedFactor = 0.09;
			break;
		case 4:
			if(this.proton==this.neutron) {
				effFactor = (1 - this.efficiency)*ZETA_EFF;
			}
			speedFactor = 0.11;
			break;
		}
		setEfficiency(this.efficiency+this.efficiency*effFactor);
		this.setSpeed(atom.getSpeed()*(1-speedFactor));
	}

	public void setSpeed(double speed) {atom.setSpeed(speed);}
	@Override
	public double getSpeed() {return atom.getSpeed();}
	@Override
	public void setCoordinate(Point coordinate) {atom.setCoordinate(coordinate);}
	@Override
	public void move() {atom.move();}
	@Override
	public void setAngle(double angle) {atom.setAngle(angle);}
	@Override
	public Point getCoordinate() {return atom.getCoordinate();}
	@Override
	public int getAtomID() {return atom.getAtomID();}
	@Override
	public int getNeutron() {return atom.getNeutron();}
	@Override
	public int getProton() {return atom.getProton();}

}
