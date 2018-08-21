package sf.hibernate.service;

import org.hibernate.cfg.Configuration;
import sf.resource.JarHelper;

import java.io.IOException;

public class HibernateServiceFactory
{
	static {
		try {
			JarHelper.loadJar("lib");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getConfiguration(){
		return new Configuration();
	}
}
