package sf.hibernate.beans.wengdb;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RULE_SHARE_DEF", schema = "RULE_CFG", catalog = "")
public class RuleShareDefEntity
{
	private long shareId;
	private String shareCode;
	private String shareImpl;
	private String notes;
	private String state;
	private String remarks;

	@Id
	@Column(name = "SHARE_ID")
	public long getShareId()
	{
		return shareId;
	}

	public void setShareId(long shareId)
	{
		this.shareId = shareId;
	}

	@Basic
	@Column(name = "SHARE_CODE")
	public String getShareCode()
	{
		return shareCode;
	}

	public void setShareCode(String shareCode)
	{
		this.shareCode = shareCode;
	}

	@Basic
	@Column(name = "SHARE_IMPL")
	public String getShareImpl()
	{
		return shareImpl;
	}

	public void setShareImpl(String shareImpl)
	{
		this.shareImpl = shareImpl;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleShareDefEntity that = (RuleShareDefEntity) o;
		return shareId == that.shareId &&
				Objects.equals(shareCode, that.shareCode) &&
				Objects.equals(shareImpl, that.shareImpl) &&
				Objects.equals(notes, that.notes) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(shareId, shareCode, shareImpl, notes, state, remarks);
	}
}
