package hibernate.beans.wengdb;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_INSTANCE", schema = "RULE_CFG", catalog = "")
public class RuleInstanceEntity
{
	private long ruleId;
	private Long ruleTmplId;
	private String ruleCode;
	private String ruleName;
	private String ruleDesc;
	private String ruleType;
	private String catalog1;
	private String catalog2;
	private String remarks;
	private String opName;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;
	private String busiType;
	private Byte showSort;

	@Id
	@Column(name = "RULE_ID")
	public long getRuleId()
	{
		return ruleId;
	}

	public void setRuleId(long ruleId)
	{
		this.ruleId = ruleId;
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
	@Column(name = "RULE_CODE")
	public String getRuleCode()
	{
		return ruleCode;
	}

	public void setRuleCode(String ruleCode)
	{
		this.ruleCode = ruleCode;
	}

	@Basic
	@Column(name = "RULE_NAME")
	public String getRuleName()
	{
		return ruleName;
	}

	public void setRuleName(String ruleName)
	{
		this.ruleName = ruleName;
	}

	@Basic
	@Column(name = "RULE_DESC")
	public String getRuleDesc()
	{
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc)
	{
		this.ruleDesc = ruleDesc;
	}

	@Basic
	@Column(name = "RULE_TYPE")
	public String getRuleType()
	{
		return ruleType;
	}

	public void setRuleType(String ruleType)
	{
		this.ruleType = ruleType;
	}

	@Basic
	@Column(name = "CATALOG1")
	public String getCatalog1()
	{
		return catalog1;
	}

	public void setCatalog1(String catalog1)
	{
		this.catalog1 = catalog1;
	}

	@Basic
	@Column(name = "CATALOG2")
	public String getCatalog2()
	{
		return catalog2;
	}

	public void setCatalog2(String catalog2)
	{
		this.catalog2 = catalog2;
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
	@Column(name = "BUSI_TYPE")
	public String getBusiType()
	{
		return busiType;
	}

	public void setBusiType(String busiType)
	{
		this.busiType = busiType;
	}

	@Basic
	@Column(name = "SHOW_SORT")
	public Byte getShowSort()
	{
		return showSort;
	}

	public void setShowSort(Byte showSort)
	{
		this.showSort = showSort;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleInstanceEntity that = (RuleInstanceEntity) o;
		return ruleId == that.ruleId &&
				Objects.equals(ruleTmplId, that.ruleTmplId) &&
				Objects.equals(ruleCode, that.ruleCode) &&
				Objects.equals(ruleName, that.ruleName) &&
				Objects.equals(ruleDesc, that.ruleDesc) &&
				Objects.equals(ruleType, that.ruleType) &&
				Objects.equals(catalog1, that.catalog1) &&
				Objects.equals(catalog2, that.catalog2) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(opName, that.opName) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state) &&
				Objects.equals(busiType, that.busiType) &&
				Objects.equals(showSort, that.showSort);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(ruleId, ruleTmplId, ruleCode, ruleName, ruleDesc, ruleType, catalog1, catalog2, remarks, opName, doneDate, orgId, opId, state, busiType, showSort);
	}
}
