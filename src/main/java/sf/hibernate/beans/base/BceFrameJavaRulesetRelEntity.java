package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_JAVA_RULESET_REL", schema = "BASE", catalog = "")
public class BceFrameJavaRulesetRelEntity
{
	private long relateId;
	private Long moduleId;
	private long bceFrameId;
	private String paramData;
	private Long rulesetId;
	private long rulesetType;
	private Long checkType;
	private Long state;
	private String remarks;

	@Id
	@Column(name = "RELATE_ID")
	public long getRelateId()
	{
		return relateId;
	}

	public void setRelateId(long relateId)
	{
		this.relateId = relateId;
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
	@Column(name = "BCE_FRAME_ID")
	public long getBceFrameId()
	{
		return bceFrameId;
	}

	public void setBceFrameId(long bceFrameId)
	{
		this.bceFrameId = bceFrameId;
	}

	@Basic
	@Column(name = "PARAM_DATA")
	public String getParamData()
	{
		return paramData;
	}

	public void setParamData(String paramData)
	{
		this.paramData = paramData;
	}

	@Basic
	@Column(name = "RULESET_ID")
	public Long getRulesetId()
	{
		return rulesetId;
	}

	public void setRulesetId(Long rulesetId)
	{
		this.rulesetId = rulesetId;
	}

	@Basic
	@Column(name = "RULESET_TYPE")
	public long getRulesetType()
	{
		return rulesetType;
	}

	public void setRulesetType(long rulesetType)
	{
		this.rulesetType = rulesetType;
	}

	@Basic
	@Column(name = "CHECK_TYPE")
	public Long getCheckType()
	{
		return checkType;
	}

	public void setCheckType(Long checkType)
	{
		this.checkType = checkType;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameJavaRulesetRelEntity that = (BceFrameJavaRulesetRelEntity) o;
		return relateId == that.relateId &&
				bceFrameId == that.bceFrameId &&
				rulesetType == that.rulesetType &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(paramData, that.paramData) &&
				Objects.equals(rulesetId, that.rulesetId) &&
				Objects.equals(checkType, that.checkType) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(relateId, moduleId, bceFrameId, paramData, rulesetId, rulesetType, checkType, state, remarks);
	}
}
