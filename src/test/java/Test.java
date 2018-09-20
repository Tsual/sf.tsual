import java.sql.Timestamp;
import java.util.Date;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Date d = new Date();

		Thread.sleep(500);
		Timestamp ts = new Timestamp(new Date().getTime() - d.getTime());
		final String s = ts.toString();


		int a = 0;
	}
}
