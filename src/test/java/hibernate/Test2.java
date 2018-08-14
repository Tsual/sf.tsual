package hibernate;

import sf.util.FrameHelper;

import java.io.IOException;
import java.util.List;

public class Test2
{
	public static void main(String[] args) throws IOException
	{
		final List<String> resourceInPackage = FrameHelper.getResourceInPackage(true, "sf");
	}
}
