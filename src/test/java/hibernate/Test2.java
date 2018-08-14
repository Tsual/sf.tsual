package hibernate;

import sf.hibernate.service.impl.DbmgrImpl;
import sf.util.ResourceHelper;

import java.io.IOException;
import java.util.List;

public class Test2
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		ResourceHelper.loadJar("lib");
		final Class<?> aClass = Class.forName("oracle.jdbc.driver.OracleDriver");
		final List<String> resourceInPackage = ResourceHelper.getResourceInPackage(true, "lib");
		new DbmgrImpl().SyncData();
	}
}
