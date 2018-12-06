package hibernate.beans.wengdb;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_PARAM_VALUE", schema = "RULE_CFG", catalog = "")
public class RuleParamValueEntity
{
	private long paramInstId;
	private Long paramId;
	private String paramName;
	private Long ruleId;
	private String paramValue;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String opName;
	private String state;
	private String paramDesc;

	@Id
	@Column(name = "PARAM_INST_ID")
	public long getParamInstId()
	{
		return paramInstId;
	}

	public void setParamInstId(long paramInstId)
	{
		this.paramInstId = paramInstId;
	}

	@Basic
	@Column(name = "PARAM_ID")
	public Long getParamId()
	{
		return paramId;
	}

	public void setParamId(Long paramId)
	{
		this.paramId = paramId;
	}

	@Basic
	@Column(name = "PARAM_NAME")
	public String getParamName()
	{
		return paramName;
	}

	public void setParamName(String paramName)
	{
		this.paramName = paramName;
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
	@Column(name = "PARAM_VALUE")
	public String getParamValue()
	{
		return paramValue;
	}

	public void setParamValue(String paramValue)
	{
		this.paramValue = paramValue;
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

	@Basic
	@Column(name = "PARAM_DESC")
	public String getParamDesc()
	{
		return paramDesc;
	}

	public void setParamDesc(String paramDesc)
	{
		this.paramDesc = paramDesc;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleParamValueEntity that = (RuleParamValueEntity) o;
		return paramInstId == that.paramInstId &&
				Objects.equals(paramId, that.paramId) &&
				Objects.equals(paramName, that.paramName) &&
				Objects.equals(ruleId, that.ruleId) &&
				Objects.equals(paramValue, that.paramValue) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(opName, that.opName) &&
				Objects.equals(state, that.state) &&
				Objects.equals(paramDesc, that.paramDesc);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(paramInstId, paramId, paramName, ruleId, paramValue, remarks, doneDate, orgId, opId, opName, state, paramDesc);
	}
}
