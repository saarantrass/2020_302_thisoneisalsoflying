package Domain.GameObjects;

public abstract class AtomDecorator extends Throwable{
	Throwable atom;
	public AtomDecorator(Throwable atom) {
		this.atom = atom;
	}
	public abstract double getEfficiency();
}
