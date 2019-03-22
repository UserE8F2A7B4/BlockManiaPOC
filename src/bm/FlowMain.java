package bm;

import bm.BlockManiaPOC.UserInput;
import bm.ControllerMain.GameState;

public class FlowMain
{
	private ControllerMain cm;
	private static FlowMain instance;

	public static FlowMain getInstance()
	{
		if (instance == null)
		{
			instance = new FlowMain();
		}
		return instance;
	}

	private FlowMain()
	{
		cm = ControllerMain.getInstance();
	}

	public void handleGameTick()
	{
			switch (cm.getGameState())
			{
				case BLOCK_HANDLING : flowBlockHandling() ; break;
				case LINE_REMOVAL   : flowLineRemoval() ; break;
				default             : assert false : "Invalid game-state"; break;
			}
	}

	private void flowBlockHandling()
	{
		if (cm.isErEenBlockOpDePreview())
		{
			if (cm.isErEenBlockOpHetVeld())
			{
				if (cm.isHetTijdVoorEenMoveDown())
				{
					if (cm.isErRuimteVoorDezeActie(UserInput.MOVE_DOWN))
					{
						cm.blockMoveDown();
						cm.updatePoints();
					}
      	else
					{
						cm.verwijderHetBlockUitHetVeld();
						if (cm.zijnErVolledigGevuldeRegelsOntstaan())
						{
							cm.changeGameState(GameState.LINE_REMOVAL);
						}
					}
				}
				else
				{
					final UserInput input = cm.getUserRequest();
					if (input != UserInput.NONE)
					{
						if (input == UserInput.MOVE_DOWN)
						{
							if (cm.isErRuimteVoorDezeActie(UserInput.MOVE_DOWN))
							{
								cm.blockMoveDown();
								cm.updatePoints();
							}
          	else
							{
								cm.verwijderHetBlockUitHetVeld();
								if (cm.zijnErVolledigGevuldeRegelsOntstaan())
								{
									cm.changeGameState(GameState.LINE_REMOVAL);
								}
							}
						}
						else if (input == UserInput.MOVE_LEFT)
						{
							if (cm.isErRuimteVoorDezeActie(UserInput.MOVE_LEFT))
							{
								cm.blockMoveLeft();
							}
							else
							{
								// Optional : play sound.
							}
						}
						else if (input == UserInput.MOVE_RIGHT)
						{
							if (cm.isErRuimteVoorDezeActie(UserInput.MOVE_RIGHT))
							{
								cm.blockMoveRight();
							}
							else
							{
								// Optional : play sound.
							}
						}
						else if (input == UserInput.FLIP)
						{
							if (cm.isErRuimteVoorDezeActie(UserInput.FLIP))
							{
								cm.blockFlip();
							}
							else
							{
								// Optional : play sound.
							}
						}
						else if (input == UserInput.ROTATE)
						{
							if (cm.isErRuimteVoorDezeActie(UserInput.ROTATE))
							{
								cm.blockRotate();
							}
							else
							{
								// Optional : play sound.
							}
						}
					}
					else // Er is [geen] user-input.
					{
						// Geen actie.
					}
				}
			}
    	else // Er is [geen] block aanwezig op het veld.
			{
				if (cm.isErRuimteOmHetPreviewBlockTeVerplaatsenNaarHetVeld())
				{
					cm.verplaatsHetPreviewBlockNaarHetVeld();
				}
				else // Er is [geen] ruimte op het veld om een nieuw block te plaatsen ; game over !
				{
					cm.handleGameOver();
				}
			}
		}
		else // Er is [geen] block aanwezig op de Preview.
		{
			cm.plaatsEenNieuwWillekeurigBlockOpDePreview();
		}
	}

	private void flowLineRemoval()
	{
		if (cm.isAnimatie_01_NogBezig())
		{
			cm.animatie_01_handleGameTick();
		}
		else if (cm.isAnimatie_02_NogBezig())
		{
			cm.animatie_02_handleGameTick();
		}
		else
		{
			cm.verwijderDeVolledigeRegels();
			cm.verplaatsDeOverigeRegelsNaarBeneden();
			cm.changeGameState(GameState.BLOCK_HANDLING);
		}
	}

}
