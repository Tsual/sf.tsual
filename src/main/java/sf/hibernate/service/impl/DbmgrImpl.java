package sf.hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sf.hibernate.beans.base.RuleInstanceEntity;
import sf.hibernate.service.interfaces.IDbmgr;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DbmgrImpl implements IDbmgr
{
	private static final SessionFactory baseSessionFactory;
	private static final SessionFactory wengdbSessionFactory;
	private static final Map<Class, Class> classMapping;

	static {
		try {
			baseSessionFactory = new Configuration().configure("hibernate/hibernate.base.cfg.xml").buildSessionFactory();
			wengdbSessionFactory = new Configuration().configure("hibernate/hibernate.wengdb.cfg.xml").buildSessionFactory();
			Map<Class, Class> map = new HashMap<>();
			map.put(Class.forName("sf.hibernate.beans.base.RuleAttrEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleAttrEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleAttrRelEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleAttrRelEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleBusiRelEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleBusiRelEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleBusiRelExpireEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleBusiRelExpireEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleInstanceEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleInstanceEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleMsgInfoEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleMsgInfoEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleObjectEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleObjectEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleParamValueEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleParamValueEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleProdAttrMapingEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleProdAttrMapingEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleRelatEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleRelatEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleTemplateEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleTemplateEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleTmplParamEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleTmplParamEntity"));
			map.put(Class.forName("sf.hibernate.beans.base.RuleV2AccessEntity"), Class.forName("sf.hibernate.beans.wengdb.RuleV2AccessEntity"));
			classMapping = Collections.unmodifiableMap(map);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	public void SyncData()
	{
		try (Session baseSession = baseSessionFactory.openSession();
		     Session wengdbSession = wengdbSessionFactory.openSession()) {

			final Metamodel baseSessionMetamodel = baseSession.getMetamodel();
			final Metamodel wengdbSessionMetamodel = wengdbSession.getMetamodel();

			for (Map.Entry<Class, Class> entry : classMapping.entrySet()) {
				final EntityType baseEntity = baseSessionMetamodel.entity(entry.getKey());
				final Query<RuleInstanceEntity> baseSessionQuery = baseSession.createQuery("from " + baseEntity.getName(), RuleInstanceEntity.class);


				final EntityType wengdbEntity = wengdbSessionMetamodel.entity(entry.getValue());



				int a =0;
			}
		}
	}
}
