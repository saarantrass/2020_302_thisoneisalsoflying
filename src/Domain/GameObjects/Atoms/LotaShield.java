package Domain.GameObjects.Atoms;

import java.awt.Point;

public class LotaShield extends ShieldDecorator {
	private double efficiency;
	private Throwable atom;

	private static double LOTA_EFF = 0.1;
	public LotaShield(Throwable atom) {
		super(atom);
		this.efficiency=atom.getEfficiency();
		this.atom=atom;
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
		double effFactor = (1 - this.efficiency) * LOTA_EFF;
		double speedFactor = 0.07;
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
