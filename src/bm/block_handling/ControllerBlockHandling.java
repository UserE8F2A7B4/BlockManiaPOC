package bm.block_handling;

public class ControllerBlockHandling
{
	private ControllerPreview controllerPreview;
	private BlockProcessor blockProcessor;

	private static ControllerBlockHandling instance;
	public static ControllerBlockHandling getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerBlockHandling();
		}
		return instance;
	}

	private ControllerBlockHandling()
	{
		controllerPreview = ControllerPreview.getInstance();
		blockProcessor = BlockProcessor.getInstance();
	}

	public void handleGameTick()
	{
		System.err.println("ControllerBlockHandling.handleGameTick");
		mainGameFlow();
	}

	private void mainGameFlow()
	{
		if (!controllerPreview.hasBlock())
		{
			controllerPreview.loadBlock();
		}

		if (!blockProcessor.hasBlock())
		{
			blockProcessor.getBlockFromPreview();
		}


	}

}
