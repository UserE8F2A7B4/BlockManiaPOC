package bm;

import bm.BlockManiaPOC.UserInput;
import bm.block_handling.ControllerBlockHandling;
import bm.line_removal.ControllerLineRemoval;

public class ControllerMain
{
	// private BlockManiaPOC gui;

	private ControllerBlockHandling controllerBlockHandling;
	private ControllerLineRemoval controllerLineRemoval;

	private UserInput request = UserInput.NONE;
	private GameState gameState;

	public enum GameState
	{
		BLOCK_HANDLING, LINE_REMOVAL
	}

	private static ControllerMain instance;
	public static ControllerMain getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerMain();
		}
		return instance;
	}

	private ControllerMain()
	{
		controllerBlockHandling = ControllerBlockHandling.getInstance();
		controllerLineRemoval = ControllerLineRemoval.getInstance();

		// GameLoopTimer timer = GameLoopTimer.getInstance();

		gameState = GameState.BLOCK_HANDLING;
	}

//	public void setReferenceToGUI(BlockManiaPOC bm)
//	{
//		gui = bm;
//	}

	public void changeGameState(GameState newGameState)
	{
		gameState = newGameState;
	}

	public GameState getGameState()
	{
		return gameState;
	}

	public synchronized void setUserRequest(UserInput req)
	{
		request = req;
	}

	public synchronized UserInput getUserRequest()
	{
		return request;
	}

	public boolean isErEenBlockOpDePreview()
	{
		return false;
	}

	public boolean isErEenBlockOpHetVeld()
	{
		return false;
	}

	public boolean isHetTijdVoorEenMoveDown()
	{
		return false;
	}

	public void blockMoveDown()
	{
	}

	public boolean isErRuimteOmHetPreviewBlockTeVerplaatsenNaarHetVeld()
	{
		return false;
	}

	public void updatePoints()
	{
	}

	public void verwijderHetBlockUitHetVeld()
	{
	}

	public boolean zijnErVolledigGevuldeRegelsOntstaan()
	{
		return false;
	}

	public void blockMoveLeft()
	{
	}

	public void blockMoveRight()
	{
	}

	public void verplaatsHetPreviewBlockNaarHetVeld()
	{
	}

	public void plaatsEenNieuwWillekeurigBlockOpDePreview()
	{
	}

	public boolean isErRuimteVoorDezeActie(UserInput moveDown)
	{
		return false;
	}

	public void blockFlip()
	{
	}

	public void blockRotate()
	{
	}

	public void handleGameOver()
	{
	}

	//---

	public boolean isAnimatie_01_NogBezig()
	{
		return false;
	}

	public void animatie_01_handleGameTick()
	{
	}

	public boolean isAnimatie_02_NogBezig()
	{
		return false;
	}

	public void animatie_02_handleGameTick()
	{
	}

	public void verwijderDeVolledigeRegels()
	{
	}

	public void verplaatsDeOverigeRegelsNaarBeneden()
	{
	}

}
