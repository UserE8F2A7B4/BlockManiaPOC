package bm.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import bm.block_handling.blocks.BlockPool;
import bm.block_handling.blocks.Tile;

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

	public static void addTile(Graphics2D canvas, Tile tile)
	{
		int X = tile.getCol() * GlobalData.BLOCK_SIZE;
		int Y = tile.getRow() * GlobalData.BLOCK_SIZE;

		Color color = BlockPool.getTileColor(tile.getBlockId());
		canvas.setColor(color);

		canvas.fillRect(X + 2, Y + 2, GlobalData.BLOCK_SIZE - 3, GlobalData.BLOCK_SIZE - 3);
		drawBlockBorder(canvas, color, X, Y);
	}

	private static void drawBlockBorder(Graphics2D canvas, Color color, int X, int Y)
	{
		int[] Xcoords, Ycoords;

		canvas.setColor(color.brighter()); // bottom-left, top-left, top-right.
		Xcoords = new int[] { X + 1, X + 1, X + GlobalData.BLOCK_SIZE - 2 };
		Ycoords = new int[] { Y + GlobalData.BLOCK_SIZE - 1, Y + 1, Y + 1 };
		canvas.drawPolyline(Xcoords, Ycoords, 3);

		canvas.setColor(color.darker()); // bottom-left, bottom-right, top-right.
		Xcoords = new int[] { X + 1, X + GlobalData.BLOCK_SIZE - 1, X + GlobalData.BLOCK_SIZE - 1 };
		Ycoords = new int[] { Y + GlobalData.BLOCK_SIZE - 1, Y + GlobalData.BLOCK_SIZE - 1, Y + 2 };
		canvas.drawPolyline(Xcoords, Ycoords, 3);
	}

}
