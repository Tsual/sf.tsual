package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_EXT_TABLE_CONFIG", schema = "BASE", catalog = "")
public class BceExtTableConfigEntity
{
	private long configId;
	private Long moduleId;
	private String extTableName;
	private String boName;
	private String relType;
	private String relColName;
	private Long state;
	private String remarks;
	private String getObjectMethodName;
	private String transFinishType;

	@Id
	@Column(name = "CONFIG_ID")
	public long getConfigId()
	{
		return configId;
	}

	public void setConfigId(long configId)
	{
		this.configId = configId;
	}

	@Basic
	@Column(name = "MODULE_ID")
	public Long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}

	@Basic
	@Column(name = "EXT_TABLE_NAME")
	public String getExtTableName()
	{
		return extTableName;
	}

	public void setExtTableName(String extTableName)
	{
		this.extTableName = extTableName;
	}

	@Basic
	@Column(name = "BO_NAME")
	public String getBoName()
	{
		return boName;
	}

	public void setBoName(String boName)
	{
		this.boName = boName;
	}

	@Basic
	@Column(name = "REL_TYPE")
	public String getRelType()
	{
		return relType;
	}

	public void setRelType(String relType)
	{
		this.relType = relType;
	}

	@Basic
	@Column(name = "REL_COL_NAME")
	public String getRelColName()
	{
		return relColName;
	}

	public void setRelColName(String relColName)
	{
		this.relColName = relColName;
	}

	@Basic
	@Column(name = "STATE")
	public Long getState()
	{
		return state;
	}

	public void setState(Long state)
	{
		this.state = state;
	}

	@Basic
	@Column(name = "REMARKS")
	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	@Basic
	@Column(name = "GET_OBJECT_METHOD_NAME")
	public String getGetObjectMethodName()
	{
		return getObjectMethodName;
	}

	public void setGetObjectMethodName(String getObjectMethodName)
	{
		this.getObjectMethodName = getObjectMethodName;
	}

	@Basic
	@Column(name = "TRANS_FINISH_TYPE")
	public String getTransFinishType()
	{
		return transFinishType;
	}

	public void setTransFinishType(String transFinishType)
	{
		this.transFinishType = transFinishType;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceExtTableConfigEntity that = (BceExtTableConfigEntity) o;
		return configId == that.configId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(extTableName, that.extTableName) &&
				Objects.equals(boName, that.boName) &&
				Objects.equals(relType, that.relType) &&
				Objects.equals(relColName, that.relColName) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(getObjectMethodName, that.getObjectMethodName) &&
				Objects.equals(transFinishType, that.transFinishType);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(configId, moduleId, extTableName, boName, relType, relColName, state, remarks, getObjectMethodName, transFinishType);
	}
}
