package hibernate.beans.base;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_BUSI_REL_EXPIRE", schema = "BASE", catalog = "")
public class RuleBusiRelExpireEntity
{
	private long expireId;
	private Long relatId;
	private String includeBusi;
	private String excludeBusi;
	private String checkExpress;
	private String notes;
	private String state;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String ext1;
	private String ext2;

	@Id
	@Column(name = "EXPIRE_ID")
	public long getExpireId()
	{
		return expireId;
	}

	public void setExpireId(long expireId)
	{
		this.expireId = expireId;
	}

	@Basic
	@Column(name = "RELAT_ID")
	public Long getRelatId()
	{
		return relatId;
	}

	public void setRelatId(Long relatId)
	{
		this.relatId = relatId;
	}

	@Basic
	@Column(name = "INCLUDE_BUSI")
	public String getIncludeBusi()
	{
		return includeBusi;
	}

	public void setIncludeBusi(String includeBusi)
	{
		this.includeBusi = includeBusi;
	}

	@Basic
	@Column(name = "EXCLUDE_BUSI")
	public String getExcludeBusi()
	{
		return excludeBusi;
	}

	public void setExcludeBusi(String excludeBusi)
	{
		this.excludeBusi = excludeBusi;
	}

	@Basic
	@Column(name = "CHECK_EXPRESS")
	public String getCheckExpress()
	{
		return checkExpress;
	}

	public void setCheckExpress(String checkExpress)
	{
		this.checkExpress = checkExpress;
	}

	@Basic
	@Column(name = "NOTES")
	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
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
	@Column(name = "EXT1")
	public String getExt1()
	{
		return ext1;
	}

	public void setExt1(String ext1)
	{
		this.ext1 = ext1;
	}

	@Basic
	@Column(name = "EXT2")
	public String getExt2()
	{
		return ext2;
	}

	public void setExt2(String ext2)
	{
		this.ext2 = ext2;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleBusiRelExpireEntity that = (RuleBusiRelExpireEntity) o;
		return expireId == that.expireId &&
				Objects.equals(relatId, that.relatId) &&
				Objects.equals(includeBusi, that.includeBusi) &&
				Objects.equals(excludeBusi, that.excludeBusi) &&
				Objects.equals(checkExpress, that.checkExpress) &&
				Objects.equals(notes, that.notes) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(ext1, that.ext1) &&
				Objects.equals(ext2, that.ext2);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(expireId, relatId, includeBusi, excludeBusi, checkExpress, notes, state, remarks, doneDate, orgId, opId, ext1, ext2);
	}
}
