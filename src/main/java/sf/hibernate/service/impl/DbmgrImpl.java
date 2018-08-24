package sf.hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sf.hibernate.service.HibernateServiceFactory;
import sf.hibernate.service.interfaces.IDbmgr;
import sf.uds.util.tquery.JRE6.Iterators;
import sf.uds.util.StringHelper;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
			map.put(sf.hibernate.beans.base.RuleAttrEntity.class, sf.hibernate.beans.wengdb.RuleAttrEntity.class);
			map.put(sf.hibernate.beans.base.RuleAttrRelEntity.class, sf.hibernate.beans.wengdb.RuleAttrRelEntity.class);
			map.put(sf.hibernate.beans.base.RuleBusiRelEntity.class, sf.hibernate.beans.wengdb.RuleBusiRelEntity.class);
			map.put(sf.hibernate.beans.base.RuleBusiRelExpireEntity.class, sf.hibernate.beans.wengdb.RuleBusiRelExpireEntity.class);
			map.put(sf.hibernate.beans.base.RuleInstanceEntity.class, sf.hibernate.beans.wengdb.RuleInstanceEntity.class);
			map.put(sf.hibernate.beans.base.RuleMsgInfoEntity.class, sf.hibernate.beans.wengdb.RuleMsgInfoEntity.class);
			map.put(sf.hibernate.beans.base.RuleObjectEntity.class, sf.hibernate.beans.wengdb.RuleObjectEntity.class);
			map.put(sf.hibernate.beans.base.RuleParamValueEntity.class, sf.hibernate.beans.wengdb.RuleParamValueEntity.class);
			map.put(sf.hibernate.beans.base.RuleProdAttrMapingEntity.class, sf.hibernate.beans.wengdb.RuleProdAttrMapingEntity.class);
			map.put(sf.hibernate.beans.base.RuleRelatEntity.class, sf.hibernate.beans.wengdb.RuleRelatEntity.class);
			map.put(sf.hibernate.beans.base.RuleTemplateEntity.class, sf.hibernate.beans.wengdb.RuleTemplateEntity.class);
			map.put(sf.hibernate.beans.base.RuleTmplParamEntity.class, sf.hibernate.beans.wengdb.RuleTmplParamEntity.class);
			map.put(sf.hibernate.beans.base.RuleV2AccessEntity.class, sf.hibernate.beans.wengdb.RuleV2AccessEntity.class);
			classMapping = Collections.unmodifiableMap(map);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	public void SyncData() throws Exception
	{
		AtomicInteger modifyCount= new AtomicInteger();
		try (Session baseSession = baseSessionFactory.openSession();
		     Session wengdbSession = wengdbSessionFactory.openSession())
		{

			final Metamodel baseSessionMetamodel = baseSession.getMetamodel();
			final Metamodel wengdbSessionMetamodel = wengdbSession.getMetamodel();

			for (Map.Entry<Class, Class> entry : classMapping.entrySet()) {
				final EntityType baseEntity = baseSessionMetamodel.entity(entry.getKey());
				final Query baseSessionQuery = baseSession.createQuery("from " + baseEntity.getName(), entry.getKey());

				final EntityType wengdbEntity = wengdbSessionMetamodel.entity(entry.getValue());
				final Query wengdbSessionQuery = wengdbSession.createQuery("from " + wengdbEntity.getName(), entry.getValue());

				final List baseList = baseSessionQuery.list();
				final List wengdbList = wengdbSessionQuery.list();

				final Method wengdbIdJavaMember = (Method) wengdbEntity.getId(wengdbEntity.getIdType().getJavaType()).getJavaMember();
				final Method baseIdJavaMember = (Method) baseEntity.getId(baseEntity.getIdType().getJavaType()).getJavaMember();

				final List wengdbIdList = Iterators.getIterator(wengdbList)
						.as(t -> wengdbIdJavaMember.invoke(t))
						.toList();

				wengdbSession.beginTransaction();
				Iterators.getIterator(baseList)
						.where(t -> !wengdbIdList.contains(baseIdJavaMember.invoke(t)))
						.as(t ->
						{
							final Object wengdbInstance = wengdbEntity.getJavaType().newInstance();
							for (Object baseAttr : baseEntity.getDeclaredAttributes()) {
								final String baseAttrName = ((SingularAttribute) baseAttr).getName();
								final Method baseAttrMethod = (Method) ((SingularAttribute) baseAttr).getJavaMember();
								final Object baseAttrValue = baseAttrMethod.invoke(t);

								final Method wengdbValueSetter = wengdbInstance.getClass().getMethod("set" + StringHelper.firstCharUpper(baseAttrName), baseAttrMethod.getReturnType());
								wengdbValueSetter.invoke(wengdbInstance, baseAttrValue);
							}
							return wengdbInstance;
						})
						.foreach(t ->
						{
							wengdbSession.save(t);
							modifyCount.getAndIncrement();
						});
				wengdbSession.getTransaction().commit();
			}
		}
		System.out.println("Modify Count:" + modifyCount.toString());
	}
}
