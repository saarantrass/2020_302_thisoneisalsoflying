package main;

import Domain.*;
import Domain.Player.Shooter;
import UI.GameObjectImages.ShooterImage;
import UI.Swing.Screen;

public class Main {
	
	public static void main(String[] args) {
		Shooter shooter = new Shooter();
		GameController GC = new GameController(shooter);
		ShooterImage shooterImage = new ShooterImage(shooter, 500,400);
		Screen screen = new Screen(GC);
		screen.add(shooterImage);
	}

}
