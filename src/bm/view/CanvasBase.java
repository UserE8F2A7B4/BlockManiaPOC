package bm.view;

import bm.util.blocks.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class CanvasBase
{
	protected Graphics2D canvas;
	protected BufferedImage image;

	private int width, height;
	private Color color;

	protected StringBuilder canvasText;

	public CanvasBase()
	{
		canvasText = new StringBuilder();
	}

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

	public void clearCanvas()
	{
		canvas.setColor(color);
		canvas.fillRect(0,0, width, height);
	}

	public void drawTiles(Tile[][] field)
	{
		clearCanvas();

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

	//---

	public void drawTilesAsText(Tile[][] field)
	{
		canvasText.setLength(0);

		for (Tile[] row : field)
		{
			for (Tile tile : row)
			{
				if (tile == null)
				{
					canvasText.append("∙");
				}
				else
				{
					canvasText.append("■");
				}
			}
			canvasText.append("\n");
		}
	}

	public abstract void render();

}
