package hibernate.beans.base;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_TMPL_PARAM", schema = "BASE", catalog = "")
public class RuleTmplParamEntity
{
	private long paramId;
	private Long ruleTmplId;
	private String paramName;
	private String paramDesc;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;

	@Id
	@Column(name = "PARAM_ID")
	public long getParamId()
	{
		return paramId;
	}

	public void setParamId(long paramId)
	{
		this.paramId = paramId;
	}

	@Basic
	@Column(name = "RULE_TMPL_ID")
	public Long getRuleTmplId()
	{
		return ruleTmplId;
	}

	public void setRuleTmplId(Long ruleTmplId)
	{
		this.ruleTmplId = ruleTmplId;
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
	@Column(name = "PARAM_DESC")
	public String getParamDesc()
	{
		return paramDesc;
	}

	public void setParamDesc(String paramDesc)
	{
		this.paramDesc = paramDesc;
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
		RuleTmplParamEntity that = (RuleTmplParamEntity) o;
		return paramId == that.paramId &&
				Objects.equals(ruleTmplId, that.ruleTmplId) &&
				Objects.equals(paramName, that.paramName) &&
				Objects.equals(paramDesc, that.paramDesc) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(paramId, ruleTmplId, paramName, paramDesc, remarks, doneDate, orgId, opId, state);
	}
}
