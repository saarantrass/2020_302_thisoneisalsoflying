package Domain.GameObjects.Atoms;

public abstract class ShieldDecorator extends Throwable{
	private double efficiency;
	public Throwable atom;
	public ShieldDecorator(Throwable atom) {
		this.atom = atom;
		this.efficiency=atom.getEfficiency();
		this.atom=atom;
	}
	public double getEfficiency() {
		return this.efficiency;
	}
	public abstract void addShield(int type);
}
