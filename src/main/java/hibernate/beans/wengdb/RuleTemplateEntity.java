package hibernate.beans.wengdb;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_TEMPLATE", schema = "RULE_CFG", catalog = "")
public class RuleTemplateEntity
{
	private long ruleTmplId;
	private String ruleTmplDesc;
	private String ruleTmplExp;
	private String ruleTmplKind;
	private String catalog1;
	private String catalog2;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;
	private String busiType;
	private Byte showSort;

	@Id
	@Column(name = "RULE_TMPL_ID")
	public long getRuleTmplId()
	{
		return ruleTmplId;
	}

	public void setRuleTmplId(long ruleTmplId)
	{
		this.ruleTmplId = ruleTmplId;
	}

	@Basic
	@Column(name = "RULE_TMPL_DESC")
	public String getRuleTmplDesc()
	{
		return ruleTmplDesc;
	}

	public void setRuleTmplDesc(String ruleTmplDesc)
	{
		this.ruleTmplDesc = ruleTmplDesc;
	}

	@Basic
	@Column(name = "RULE_TMPL_EXP")
	public String getRuleTmplExp()
	{
		return ruleTmplExp;
	}

	public void setRuleTmplExp(String ruleTmplExp)
	{
		this.ruleTmplExp = ruleTmplExp;
	}

	@Basic
	@Column(name = "RULE_TMPL_KIND")
	public String getRuleTmplKind()
	{
		return ruleTmplKind;
	}

	public void setRuleTmplKind(String ruleTmplKind)
	{
		this.ruleTmplKind = ruleTmplKind;
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
		RuleTemplateEntity that = (RuleTemplateEntity) o;
		return ruleTmplId == that.ruleTmplId &&
				Objects.equals(ruleTmplDesc, that.ruleTmplDesc) &&
				Objects.equals(ruleTmplExp, that.ruleTmplExp) &&
				Objects.equals(ruleTmplKind, that.ruleTmplKind) &&
				Objects.equals(catalog1, that.catalog1) &&
				Objects.equals(catalog2, that.catalog2) &&
				Objects.equals(remarks, that.remarks) &&
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
		return Objects.hash(ruleTmplId, ruleTmplDesc, ruleTmplExp, ruleTmplKind, catalog1, catalog2, remarks, doneDate, orgId, opId, state, busiType, showSort);
	}
}
