package sf.task;

public class test2
{

	public static void main(String[] args) throws Exception
	{
		try (TaskHost host = new TaskHost("th", 5, 25, 50L)) {
			TaskHub hub = host.newTaskHub(50L);
			for (int i = 0; i < 2; i++) {
				int finalI = i;
				hub.execute(() ->
						{
							System.out.println("working-" + finalI);
							Thread.sleep(500);
							System.out.println("woops-" + finalI);
							throw new Exception();
						}
						, 50L
				);
			}
			//hub.waitAll();
		}
	}
}
