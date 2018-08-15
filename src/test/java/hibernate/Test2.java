package hibernate;

import sf.hibernate.service.impl.DbmgrImpl;
import sf.util.ResourceHelper;

import java.io.IOException;
import java.util.List;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		ResourceHelper.loadJars();
		new DbmgrImpl().SyncData();
	}
}
