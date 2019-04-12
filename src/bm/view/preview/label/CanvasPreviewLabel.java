package bm.view.preview.label;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.util.blocks.Tile;
import bm.view.UtilView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasPreviewLabel
{
	private static CanvasPreviewLabel instance;

	private Graphics2D canvas;
	private BufferedImage image;

	public static CanvasPreviewLabel getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasPreviewLabel();
		}
		return instance;
	}

	private CanvasPreviewLabel()
	{
		init();
	}

	public void init()
	{
		image = UtilView.createImage(GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT);
		canvas = UtilView.createCanvas(image);

		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT);

		canvas.setColor(Color.BLACK);
		canvas.drawString("View-preview", 2, 2);
	}

	public void clearField()
	{
		canvas.setColor(Color.GREEN);
		canvas.fillRect(0,0, GlobalData.PREVIEW_WIDTH, GlobalData.PREVIEW_HEIGHT);
	}

	public void drawTile(Tile tile)
	{
		if (tile != null)
		{
			UtilView.addTile(canvas, tile);
		}
	}

	public void renderPreview(Tile[][] field)
	{
		clearField();

		for (int row = 0 ; row < field.length ; row++)
		{
			for (int col = 0 ; col < field[row].length ; col++)
			{
				drawTile(field[row][col]);
			}
		}

		BlockManiaPOC.getLblCanvasPreview().setIcon(new ImageIcon(image));
	}

}
