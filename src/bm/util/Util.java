package bm.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class Util
{
	public static BufferedImage createImage(int width, int height)
	{
		// Let op ! :
		// Als de transparency bij alle states op 'translucent' (transparant) staat en ze
		// ook allemaal dezelfde achtergrondkleur gebruiken, dan blijft de content van een
		// state zichtbaar na het switchen naar een andere state.

		// int transparency = Transparency.OPAQUE;      // Niet-doorschijnend.
		// int transparency = Transparency.TRANSLUCENT; // Doorschijnend.
		// int transparency = Transparency.BITMASK;

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage compatible = gc.createCompatibleImage(width, height, Transparency.OPAQUE);
		return compatible;
	}

	public static Graphics2D createCanvas(BufferedImage image)
	{
		Graphics2D canvas = (Graphics2D) image.getGraphics();
		canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		return canvas;
	}
}
