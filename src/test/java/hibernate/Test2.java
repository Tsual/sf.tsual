package hibernate;

import sf.hibernate.service.impl.DbmgrImpl;
import sf.resource.JarHelper;

import java.io.IOException;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		try {
			JarHelper.loadJar("lib");
		} catch (IOException e) {
			e.printStackTrace();
		}
		new DbmgrImpl().SyncData();
	}
}
