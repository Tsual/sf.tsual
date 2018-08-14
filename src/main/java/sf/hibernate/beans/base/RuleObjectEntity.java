package sf.hibernate.beans.base;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_OBJECT", schema = "BASE", catalog = "")
public class RuleObjectEntity
{
	private long objectId;
	private String objectName;
	private String objectCode;
	private String constType;
	private String constDesc;
	private String constClass;
	private String constFunc;
	private String paramList;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;
	private String paramListDesc;

	@Id
	@Column(name = "OBJECT_ID")
	public long getObjectId()
	{
		return objectId;
	}

	public void setObjectId(long objectId)
	{
		this.objectId = objectId;
	}

	@Basic
	@Column(name = "OBJECT_NAME")
	public String getObjectName()
	{
		return objectName;
	}

	public void setObjectName(String objectName)
	{
		this.objectName = objectName;
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
	@Column(name = "CONST_TYPE")
	public String getConstType()
	{
		return constType;
	}

	public void setConstType(String constType)
	{
		this.constType = constType;
	}

	@Basic
	@Column(name = "CONST_DESC")
	public String getConstDesc()
	{
		return constDesc;
	}

	public void setConstDesc(String constDesc)
	{
		this.constDesc = constDesc;
	}

	@Basic
	@Column(name = "CONST_CLASS")
	public String getConstClass()
	{
		return constClass;
	}

	public void setConstClass(String constClass)
	{
		this.constClass = constClass;
	}

	@Basic
	@Column(name = "CONST_FUNC")
	public String getConstFunc()
	{
		return constFunc;
	}

	public void setConstFunc(String constFunc)
	{
		this.constFunc = constFunc;
	}

	@Basic
	@Column(name = "PARAM_LIST")
	public String getParamList()
	{
		return paramList;
	}

	public void setParamList(String paramList)
	{
		this.paramList = paramList;
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

	@Basic
	@Column(name = "PARAM_LIST_DESC")
	public String getParamListDesc()
	{
		return paramListDesc;
	}

	public void setParamListDesc(String paramListDesc)
	{
		this.paramListDesc = paramListDesc;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleObjectEntity that = (RuleObjectEntity) o;
		return objectId == that.objectId &&
				Objects.equals(objectName, that.objectName) &&
				Objects.equals(objectCode, that.objectCode) &&
				Objects.equals(constType, that.constType) &&
				Objects.equals(constDesc, that.constDesc) &&
				Objects.equals(constClass, that.constClass) &&
				Objects.equals(constFunc, that.constFunc) &&
				Objects.equals(paramList, that.paramList) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state) &&
				Objects.equals(paramListDesc, that.paramListDesc);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(objectId, objectName, objectCode, constType, constDesc, constClass, constFunc, paramList, remarks, doneDate, orgId, opId, state, paramListDesc);
	}
}
