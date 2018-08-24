package hibernate;

import sf.hibernate.service.HibernateServiceFactory;
import sf.hibernate.service.impl.DbmgrImpl;
import sf.hibernate.service.interfaces.IDbmgr;
import sf.resource.JarHelper;

import java.io.IOException;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		HibernateServiceFactory.getService(IDbmgr.class).SyncData();
	}
}
