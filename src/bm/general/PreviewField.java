package bm.general;

public class PreviewField
{
	private static PreviewField instance;
	public static PreviewField getInstance()
	{
		if (instance == null)
		{
			instance = new PreviewField();
		}
		return instance;
	}

	private PreviewField()
	{
	}
}
