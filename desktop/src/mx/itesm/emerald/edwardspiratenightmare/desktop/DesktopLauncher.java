package mx.itesm.emerald.edwardspiratenightmare.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mx.itesm.emerald.edwardspiratenightmare.EdwardsPirateNightmare;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new EdwardsPirateNightmare(), config);
	}
}
