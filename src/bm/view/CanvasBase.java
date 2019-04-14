package bm.view;

import bm.util.blocks.Tile;
import bm.view.UtilView;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class CanvasBase
{
	protected Graphics2D canvas;
	protected BufferedImage image;

	private int width, height;
	private Color color;

	public CanvasBase(int w, int h, Color c)
	{
		width = w;
		height = h;
		color = c;

		image = UtilView.createImage(width, height);
		canvas = UtilView.createCanvas(image);

		canvas.setColor(color);
		canvas.fillRect(0,0, width, height);
	}

	public void clearField()
	{
		canvas.setColor(color);
		canvas.fillRect(0,0, width, height);
	}

	public void drawTiles(Tile[][] field)
	{
		clearField();

		for (Tile[] row : field)
		{
			for (Tile tile : row)
			{
				drawTile(tile);
			}
		}
	}

	private void drawTile(Tile tile)
	{
		if (tile != null)
		{
			UtilView.addTile(canvas, tile);
		}
	}

}
