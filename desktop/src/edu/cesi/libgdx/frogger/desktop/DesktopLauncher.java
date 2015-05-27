package edu.cesi.libgdx.frogger.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.cesi.libgdx.frogger.controler.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 800;
		//config.resizable = false;
		//config.forceExit = true;
		//config.title = "Frogger";
		
		//config.addIcon("ico.ico", FileType.Internal );
		
		new LwjglApplication(new MainGame(), config);
	}
}
