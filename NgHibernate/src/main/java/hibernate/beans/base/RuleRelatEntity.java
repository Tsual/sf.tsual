package hibernate.beans.base;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_RELAT", schema = "BASE", catalog = "")
public class RuleRelatEntity
{
	private long relatId;
	private Long groupId;
	private Long ruleId;
	private Long relatRuleId;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String opName;
	private String state;

	@Id
	@Column(name = "RELAT_ID")
	public long getRelatId()
	{
		return relatId;
	}

	public void setRelatId(long relatId)
	{
		this.relatId = relatId;
	}

	@Basic
	@Column(name = "GROUP_ID")
	public Long getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Long groupId)
	{
		this.groupId = groupId;
	}

	@Basic
	@Column(name = "RULE_ID")
	public Long getRuleId()
	{
		return ruleId;
	}

	public void setRuleId(Long ruleId)
	{
		this.ruleId = ruleId;
	}

	@Basic
	@Column(name = "RELAT_RULE_ID")
	public Long getRelatRuleId()
	{
		return relatRuleId;
	}

	public void setRelatRuleId(Long relatRuleId)
	{
		this.relatRuleId = relatRuleId;
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
	@Column(name = "OP_NAME")
	public String getOpName()
	{
		return opName;
	}

	public void setOpName(String opName)
	{
		this.opName = opName;
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
		RuleRelatEntity that = (RuleRelatEntity) o;
		return relatId == that.relatId &&
				Objects.equals(groupId, that.groupId) &&
				Objects.equals(ruleId, that.ruleId) &&
				Objects.equals(relatRuleId, that.relatRuleId) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(opName, that.opName) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(relatId, groupId, ruleId, relatRuleId, remarks, doneDate, orgId, opId, opName, state);
	}
}
