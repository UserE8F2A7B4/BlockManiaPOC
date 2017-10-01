package bm.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import bm.BlockManiaPOC;
import bm.block_handling.blocks.Tile;
import bm.util.Util;

public class CanvasFieldLabel
{
	//public final static int CANVAS_WIDTH_GAMEFIELD  = (GlobalData.BLOCK_SIZE * GlobalData.COLS) + 1; // '+1' to make space for the shadow of the blocks.
	//public final static int CANVAS_HEIGHT_GAMEFIELD = (GlobalData.BLOCK_SIZE * GlobalData.ROWS) + 1;

	public final static int CANVAS_WIDTH_GAMEFIELD  = 80;
	public final static int CANVAS_HEIGHT_GAMEFIELD = 80;

	private Graphics2D canvas;
	private BufferedImage image;

	private ImageIcon imageIcon = new ImageIcon();

	private static CanvasFieldLabel instance;
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

	public void init()
	{
		image = Util.createImage(CANVAS_WIDTH_GAMEFIELD, CANVAS_HEIGHT_GAMEFIELD);
		canvas = Util.createCanvas(image);

		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, CANVAS_WIDTH_GAMEFIELD, CANVAS_HEIGHT_GAMEFIELD);

		canvas.setColor(Color.BLACK);
		canvas.drawString("View-field", 5, 15);
	}

	public void render(Tile[][] field)
	{
		imageIcon.setImage(image);
		BlockManiaPOC.lblCanvasField.setIcon(imageIcon);
	}

	public void clearField()
	{
		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, CANVAS_WIDTH_GAMEFIELD, CANVAS_HEIGHT_GAMEFIELD);
	}

	public void drawTile(Tile tile)
	{
		if (tile != null)
		{
			Util.addTile(canvas, tile);
		}
	}

}
