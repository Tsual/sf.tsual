package sf.hibernate.beans.base;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_ATTR", schema = "BASE", catalog = "")
public class RuleAttrEntity
{
	private long attrId;
	private String objectCode;
	private String attrName;
	private String attrCode;
	private String attrType;
	private String valueDesc;
	private String srcSys;
	private String srcTable;
	private String srcField;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;

	@Id
	@Column(name = "ATTR_ID")
	public long getAttrId()
	{
		return attrId;
	}

	public void setAttrId(long attrId)
	{
		this.attrId = attrId;
	}

	@Basic
	@Column(name = "OBJECT_CODE")
	public String getObjectCode()
	{
		return objectCode;
	}

	public void setObjectCode(String objectCode)
	{
		this.objectCode = objectCode;
	}

	@Basic
	@Column(name = "ATTR_NAME")
	public String getAttrName()
	{
		return attrName;
	}

	public void setAttrName(String attrName)
	{
		this.attrName = attrName;
	}

	@Basic
	@Column(name = "ATTR_CODE")
	public String getAttrCode()
	{
		return attrCode;
	}

	public void setAttrCode(String attrCode)
	{
		this.attrCode = attrCode;
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
	@Column(name = "VALUE_DESC")
	public String getValueDesc()
	{
		return valueDesc;
	}

	public void setValueDesc(String valueDesc)
	{
		this.valueDesc = valueDesc;
	}

	@Basic
	@Column(name = "SRC_SYS")
	public String getSrcSys()
	{
		return srcSys;
	}

	public void setSrcSys(String srcSys)
	{
		this.srcSys = srcSys;
	}

	@Basic
	@Column(name = "SRC_TABLE")
	public String getSrcTable()
	{
		return srcTable;
	}

	public void setSrcTable(String srcTable)
	{
		this.srcTable = srcTable;
	}

	@Basic
	@Column(name = "SRC_FIELD")
	public String getSrcField()
	{
		return srcField;
	}

	public void setSrcField(String srcField)
	{
		this.srcField = srcField;
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
	@Column(name = "DONE_DATE")
	public Time getDoneDate()
	{
		return doneDate;
	}

	public void setDoneDate(Time doneDate)
	{
		this.doneDate = doneDate;
	}

	@Basic
	@Column(name = "ORG_ID")
	public Long getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Long orgId)
	{
		this.orgId = orgId;
	}

	@Basic
	@Column(name = "OP_ID")
	public Long getOpId()
	{
		return opId;
	}

	public void setOpId(Long opId)
	{
		this.opId = opId;
	}

	@Basic
	@Column(name = "STATE")
	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleAttrEntity that = (RuleAttrEntity) o;
		return attrId == that.attrId &&
				Objects.equals(objectCode, that.objectCode) &&
				Objects.equals(attrName, that.attrName) &&
				Objects.equals(attrCode, that.attrCode) &&
				Objects.equals(attrType, that.attrType) &&
				Objects.equals(valueDesc, that.valueDesc) &&
				Objects.equals(srcSys, that.srcSys) &&
				Objects.equals(srcTable, that.srcTable) &&
				Objects.equals(srcField, that.srcField) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(attrId, objectCode, attrName, attrCode, attrType, valueDesc, srcSys, srcTable, srcField, remarks, doneDate, orgId, opId, state);
	}
}
