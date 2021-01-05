package Domain.GameObjects.Atoms;

import java.awt.Point;

public class EtaShield extends ShieldDecorator  {
	private double efficiency;
	private Throwable atom;
	private int neutron;
	private int proton;
	private static double ETA_EFF = 0.05;
	public EtaShield(Throwable atom) {
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

	@Override
	public void addShield(int type) {
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.proton!=this.neutron) {
			effFactor = (1 - this.efficiency)*Math.abs(this.neutron-this.proton)/this.proton;
		}else {
			effFactor = (1 - this.efficiency) * ETA_EFF;
		}
		speedFactor = 0.05;
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
