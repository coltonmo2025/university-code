package MorrisProject5;

import java.awt.Color;

public class WindowDriver {

	public static void main(String[] args) {

		WindowDisplay myGame = new WindowDisplay(500, 500);

		myGame.addWindow(.5, .5, .1, .1, Color.BLACK);
		myGame.addWindow(.1, .1, .05, .1, Color.GREEN);
		myGame.addWindow(.7, .7, .02, .1, Color.cyan);
		myGame.addWindow(.4, .9, .04, .2, Color.LIGHT_GRAY);

	}

}
