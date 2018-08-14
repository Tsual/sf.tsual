package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_RULESET", schema = "BASE", catalog = "")
public class BceRulesetEntity
{
	private long rulesetId;
	private Long moduleId;
	private Long rulesetType;
	private Long state;
	private String remarks;

	@Id
	@Column(name = "RULESET_ID")
	public long getRulesetId()
	{
		return rulesetId;
	}

	public void setRulesetId(long rulesetId)
	{
		this.rulesetId = rulesetId;
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
	@Column(name = "RULESET_TYPE")
	public Long getRulesetType()
	{
		return rulesetType;
	}

	public void setRulesetType(Long rulesetType)
	{
		this.rulesetType = rulesetType;
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
		BceRulesetEntity that = (BceRulesetEntity) o;
		return rulesetId == that.rulesetId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(rulesetType, that.rulesetType) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(rulesetId, moduleId, rulesetType, state, remarks);
	}
}
