package bm;

import bm.BlockManiaPOC.UserInput;
import bm.block_handling.BlockProcessor;
import bm.block_handling.ControllerBlockHandling;
import bm.block_handling.ControllerField;
import bm.block_handling.ControllerPreview;
import bm.line_removal.ControllerLineRemoval;

public class ControllerMain
{
	// private BlockManiaPOC gui;

	private ControllerBlockHandling controllerBlockHandling;
	private ControllerLineRemoval controllerLineRemoval;
	private ControllerPreview controllerPreview;

	private ControllerField controllerField;

	private BlockProcessor blockProcessor;


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
		controllerPreview = ControllerPreview.getInstance();
		blockProcessor = BlockProcessor.getInstance();
		controllerField = ControllerField.getInstance();

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

	//---

	public boolean staat_er_een_block_op_de_preview()
	{
		return controllerPreview.hasBlock();
	}

	public void plaats_een_nieuw_willekeurig_block_op_de_preview()
	{
		// controllerPreview.loadNextBlock(); // .loadRandomBlock();
		controllerPreview.loadNextBlock(); // .loadRandomBlock();
	}

	public boolean probeer_het_previewblock_te_verplaatsen_naar_het_veld()
	{
		return blockProcessor.tryToPlaceNewBlockOnField();
	}

	public boolean staat_er_een_block_op_het_veld()
	{
		return blockProcessor.hasBlock();
	}

	public boolean is_het_tijd_voor_een_move_down()
	{
		return false;
	}

	public boolean probeer_actie_uit_te_voeren_op_het_block(UserInput input)
	{
		if (input == UserInput.MOVE_DOWN)
		{
			return blockProcessor.tryToMoveBlockDown();
		}
		else if (input == UserInput.MOVE_LEFT)
		{
			return blockProcessor.tryToMoveBlockLeft();
		}
		else if (input == UserInput.MOVE_RIGHT)
		{
			return blockProcessor.tryToMoveBlockRight();
		}
		else if (input == UserInput.FLIP)
		{
			return blockProcessor.tryToFlipBlock();
		}
		else if (input == UserInput.ROTATE)
		{
			return blockProcessor.tryToRotateBlock();
		}
		else
		{
			return false;
		}
	}

	public void verwijder_het_block_uit_het_veld()
	{
		blockProcessor.removeBlock();
	}

	public boolean zijn_er_volledig_gevulde_regels_ontstaan()
	{
		return controllerField.hasCompletedRows();
	}


	public void updateScore()
	{
	}

	public void handleGameOver()
	{
	}

	//---

	public boolean is_animatie_01_nog_bezig()
	{
		return false;
	}

	public void update_animatie_01()
	{
	}

	public boolean is_animatie_02_nog_bezig()
	{
		return false;
	}

	public void update_animatie_02()
	{
	}

	public void verwijder_de_volledige_regels()
	{
	}

	public void verplaats_de_overgebleven_regels_naar_beneden()
	{
	}

}
