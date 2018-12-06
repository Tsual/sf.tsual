package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_ATTR_FIELD_MAP", schema = "BASE", catalog = "")
public class BceAttrFieldMapEntity
{
	private long configId;
	private Long moduleId;
	private Long businessId;
	private String attrType;
	private Long attrId;
	private String destTableName;
	private String destFieldName;
	private String dataType;
	private String remarks;

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
	@Column(name = "BUSINESS_ID")
	public Long getBusinessId()
	{
		return businessId;
	}

	public void setBusinessId(Long businessId)
	{
		this.businessId = businessId;
	}

	@Basic
	@Column(name = "ATTR_TYPE")
	public String getAttrType()
	{
		return attrType;
	}

	public void setAttrType(String attrType)
	{
		this.attrType = attrType;
	}

	@Basic
	@Column(name = "ATTR_ID")
	public Long getAttrId()
	{
		return attrId;
	}

	public void setAttrId(Long attrId)
	{
		this.attrId = attrId;
	}

	@Basic
	@Column(name = "DEST_TABLE_NAME")
	public String getDestTableName()
	{
		return destTableName;
	}

	public void setDestTableName(String destTableName)
	{
		this.destTableName = destTableName;
	}

	@Basic
	@Column(name = "DEST_FIELD_NAME")
	public String getDestFieldName()
	{
		return destFieldName;
	}

	public void setDestFieldName(String destFieldName)
	{
		this.destFieldName = destFieldName;
	}

	@Basic
	@Column(name = "DATA_TYPE")
	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceAttrFieldMapEntity that = (BceAttrFieldMapEntity) o;
		return configId == that.configId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(businessId, that.businessId) &&
				Objects.equals(attrType, that.attrType) &&
				Objects.equals(attrId, that.attrId) &&
				Objects.equals(destTableName, that.destTableName) &&
				Objects.equals(destFieldName, that.destFieldName) &&
				Objects.equals(dataType, that.dataType) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(configId, moduleId, businessId, attrType, attrId, destTableName, destFieldName, dataType, remarks);
	}
}
