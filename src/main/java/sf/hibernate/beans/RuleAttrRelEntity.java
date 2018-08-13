package sf.hibernate.beans;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_ATTR_REL", schema = "BASE", catalog = "")
public class RuleAttrRelEntity
{
	private long relatId;
	private Long objectId;
	private Long attrId;
	private Long ruleTmplId;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
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
	@Column(name = "OBJECT_ID")
	public Long getObjectId()
	{
		return objectId;
	}

	public void setObjectId(Long objectId)
	{
		this.objectId = objectId;
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
		RuleAttrRelEntity that = (RuleAttrRelEntity) o;
		return relatId == that.relatId &&
				Objects.equals(objectId, that.objectId) &&
				Objects.equals(attrId, that.attrId) &&
				Objects.equals(ruleTmplId, that.ruleTmplId) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(relatId, objectId, attrId, ruleTmplId, remarks, doneDate, orgId, opId, state);
	}
}
