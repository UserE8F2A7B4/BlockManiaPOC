package bm.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import bm.BlockManiaPOC;
import bm.block_handling.blocks.Tile;
import bm.util.GlobalData;
import bm.util.Util;

public class CanvasFieldLabel
{
	//public final static int CANVAS_WIDTH_GAMEFIELD  = (GlobalData.BLOCK_SIZE * GlobalData.COLS) + 1; // '+1' to make space for the shadow of the blocks.
	//public final static int CANVAS_HEIGHT_GAMEFIELD = (GlobalData.BLOCK_SIZE * GlobalData.ROWS) + 1;

	private Graphics2D canvas;
	private BufferedImage image;

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
		image = Util.createImage(GlobalData.GAME_FIELD_WIDTH, GlobalData.GAME_FIELD_HEIGHT);
		canvas = Util.createCanvas(image);

		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.GAME_FIELD_WIDTH, GlobalData.GAME_FIELD_HEIGHT);

		canvas.setColor(Color.BLACK);
		canvas.drawString("View-field", 5, 15);
	}

	public void clearField()
	{
		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.GAME_FIELD_WIDTH, GlobalData.GAME_FIELD_HEIGHT);
	}

	public void drawTile(Tile tile)
	{
		if (tile != null)
		{
			Util.addTile(canvas, tile);
		}
	}

	public void render()
	{
		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
