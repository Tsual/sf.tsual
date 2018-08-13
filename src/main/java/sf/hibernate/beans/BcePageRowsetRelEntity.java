package sf.hibernate.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_PAGE_ROWSET_REL", schema = "BASE", catalog = "")
@IdClass(BcePageRowsetRelEntityPK.class)
public class BcePageRowsetRelEntity
{
	private long pageFramePageId;
	private long rowsetId;
	private Long moduleId;
	private Long state;

	@Id
	@Column(name = "PAGE_FRAME_PAGE_ID")
	public long getPageFramePageId()
	{
		return pageFramePageId;
	}

	public void setPageFramePageId(long pageFramePageId)
	{
		this.pageFramePageId = pageFramePageId;
	}

	@Id
	@Column(name = "ROWSET_ID")
	public long getRowsetId()
	{
		return rowsetId;
	}

	public void setRowsetId(long rowsetId)
	{
		this.rowsetId = rowsetId;
	}

	@Basic
	@Column(name = "MODULE_ID")
	public Long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
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
		BcePageRowsetRelEntity that = (BcePageRowsetRelEntity) o;
		return pageFramePageId == that.pageFramePageId &&
				rowsetId == that.rowsetId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pageFramePageId, rowsetId, moduleId, state);
	}
}
