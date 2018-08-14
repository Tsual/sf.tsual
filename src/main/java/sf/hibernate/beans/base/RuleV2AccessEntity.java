package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RULE_V2_ACCESS", schema = "BASE", catalog = "")
public class RuleV2AccessEntity
{
	private long accessId;
	private String interfaceCode;
	private String ext1;
	private String ext2;
	private String ext3;
	private String notes;
	private String state;
	private String remarks;

	@Id
	@Column(name = "ACCESS_ID")
	public long getAccessId()
	{
		return accessId;
	}

	public void setAccessId(long accessId)
	{
		this.accessId = accessId;
	}

	@Basic
	@Column(name = "INTERFACE_CODE")
	public String getInterfaceCode()
	{
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode)
	{
		this.interfaceCode = interfaceCode;
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

	@Basic
	@Column(name = "EXT3")
	public String getExt3()
	{
		return ext3;
	}

	public void setExt3(String ext3)
	{
		this.ext3 = ext3;
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
		RuleV2AccessEntity that = (RuleV2AccessEntity) o;
		return accessId == that.accessId &&
				Objects.equals(interfaceCode, that.interfaceCode) &&
				Objects.equals(ext1, that.ext1) &&
				Objects.equals(ext2, that.ext2) &&
				Objects.equals(ext3, that.ext3) &&
				Objects.equals(notes, that.notes) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(accessId, interfaceCode, ext1, ext2, ext3, notes, state, remarks);
	}
}
