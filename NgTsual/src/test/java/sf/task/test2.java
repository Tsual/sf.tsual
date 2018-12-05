package sf.task;

public class test2
{
	public static void main(String[] args) throws Exception
	{
		try (TaskHost host = new TaskHost("th", 5, 25, 50L);
		     TaskHub hub = host.newTaskHub(50L, System.out);)
		{
			for (int i = 0; i < 15; i++) {
				int finalI = i;
				hub.execute(() ->
						{
							Thread.sleep(500);
							//return finalI;
							throw new Exception(finalI + "");
						},
						ThreadLocalOperation.None
				);
			}
			hub.waitAll();
		}
	}
}
