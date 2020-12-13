package Domain.GameObjects;
import java.awt.Point;

public class FallingObjectFactory {
	private static FallingObjectFactory singleton;
	
	public FallingObjectFactory() {}
	
	public Molecule getNewMolecule(int type, Point coords, boolean isSpinning, boolean isLinear) {
		return new Molecule(type, coords, isSpinning, isLinear);
	}
	
	public PowerUp getNewPowerUp(int type, Point coords) {
		return new PowerUp(type, coords);
	}
	
	public ReactionBlocker getNewReactionBlocker(int type, Point coords) {
		return new ReactionBlocker(type, coords);
	}
	
	public static FallingObjectFactory getInstance(){
        if (singleton == null){
            singleton = new FallingObjectFactory();
        }
        return singleton;
    }
    
}
