package Domain;

public interface IObservable {
	public void add(IObserver o);
	public void remove(IObserver o);
	public void publish();
}
