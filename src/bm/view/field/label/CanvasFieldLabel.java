package bm.view.field.label;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import bm.BlockManiaPOC;
import bm.util.blocks.Tile;
import bm.util.GlobalData;
import bm.view.UtilView;

public class CanvasFieldLabel
{
	private static CanvasFieldLabel instance;

	private Graphics2D canvas;
	private BufferedImage image;

	public static CanvasFieldLabel getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasFieldLabel();
		}
		return instance;
	}

	private CanvasFieldLabel()
	{
		init();
	}

	private void init()
	{
		image = UtilView.createImage(GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT);
		canvas = UtilView.createCanvas(image);

		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT);

		canvas.setColor(Color.BLACK);
		canvas.drawString("View-field", 5, 15);
	}

	private void clearField()
	{
		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT);
	}

	private void drawTile(Tile tile)
	{
		if (tile != null)
		{
			UtilView.addTile(canvas, tile);
		}
	}

	public void renderField(Tile[][] field)
	{
		clearField();

		for (Tile[] row : field)
		{
			for (Tile tile : row)
			{
				drawTile(tile);
			}
		}

		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
