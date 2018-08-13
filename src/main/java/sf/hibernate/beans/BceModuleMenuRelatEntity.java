package sf.hibernate.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_MODULE_MENU_RELAT", schema = "BASE", catalog = "")
@IdClass(BceModuleMenuRelatEntityPK.class)
public class BceModuleMenuRelatEntity
{
	private long moduleId;
	private long menuId;
	private Long seqNo;
	private Long menuLevel;
	private String remarks;
	private Long state;

	@Id
	@Column(name = "MODULE_ID")
	public long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(long moduleId)
	{
		this.moduleId = moduleId;
	}

	@Id
	@Column(name = "MENU_ID")
	public long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(long menuId)
	{
		this.menuId = menuId;
	}

	@Basic
	@Column(name = "SEQ_NO")
	public Long getSeqNo()
	{
		return seqNo;
	}

	public void setSeqNo(Long seqNo)
	{
		this.seqNo = seqNo;
	}

	@Basic
	@Column(name = "MENU_LEVEL")
	public Long getMenuLevel()
	{
		return menuLevel;
	}

	public void setMenuLevel(Long menuLevel)
	{
		this.menuLevel = menuLevel;
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
	@Column(name = "STATE")
	public Long getState()
	{
		return state;
	}

	public void setState(Long state)
	{
		this.state = state;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceModuleMenuRelatEntity that = (BceModuleMenuRelatEntity) o;
		return moduleId == that.moduleId &&
				menuId == that.menuId &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(menuLevel, that.menuLevel) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(moduleId, menuId, seqNo, menuLevel, remarks, state);
	}
}
