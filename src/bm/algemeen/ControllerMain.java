package bm.algemeen;

import bm.BlockManiaPOC.UserInput;
import bm.game_states.title_screen.ControllerTitleScreen;
import bm.game_states.user_play.ControllerUserPlay;
import bm.util.game_loop.GameLoopPause;

public class ControllerMain
{
	private static ControllerMain instance;

	private UserInput request = UserInput.NONE;

	private ControllerTitleScreen cts;
	private ControllerUserPlay cup;

	private GameStateMain gameStateMain;

	public enum GameStateMain
	{
		TITLE_SCREEN, USER_PLAYING, DEMO_PLAYING
	}

	private GameLoopPause pausing = new GameLoopPause(60);

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
	}

	public void init()
	{
		gameStateMain = GameStateMain.TITLE_SCREEN;

		cts = ControllerTitleScreen.getInstance();
		cts.init();

		cup = ControllerUserPlay.getInstance();
		cup.init();
	}

	public synchronized void setUserRequest(UserInput req)
	{
		// System.out.println(String.format("UserRequest : %s ", req));
		request = req;
	}

	public synchronized UserInput getUserRequest()
	{
		return request;
	}

	public void changeGameState(GameStateMain newState)
	{
		gameStateMain = newState;
	}

	public void handleGameTick()
	{
		final UserInput input = getUserRequest();
		if (input == UserInput.START_GAME)
		{
			cup.changeGameState(ControllerUserPlay.GameStateUserPlay.BLOCK_HANDLING);
			changeGameState(GameStateMain.USER_PLAYING);
		}
		
    switch (gameStateMain)
    {
      case TITLE_SCREEN : cts.handleGameTick() ; break;
      case USER_PLAYING : cup.handleGameTick() ; break;
      case DEMO_PLAYING : break;
      default           : throw new RuntimeException("Invalid game-State-Main.");
    }
	}

//	private void handleIdle()
//	{
//		if (!pausing.isPausing())
//		{
//			System.out.println("Game idle. Press 'F9' to play.");
//		}
//	}

}
