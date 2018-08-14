package sf.hibernate.beans.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BcePageRowsetRelEntityPK implements Serializable
{
	private long pageFramePageId;
	private long rowsetId;

	@Column(name = "PAGE_FRAME_PAGE_ID")
	@Id
	public long getPageFramePageId()
	{
		return pageFramePageId;
	}

	public void setPageFramePageId(long pageFramePageId)
	{
		this.pageFramePageId = pageFramePageId;
	}

	@Column(name = "ROWSET_ID")
	@Id
	public long getRowsetId()
	{
		return rowsetId;
	}

	public void setRowsetId(long rowsetId)
	{
		this.rowsetId = rowsetId;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BcePageRowsetRelEntityPK that = (BcePageRowsetRelEntityPK) o;
		return pageFramePageId == that.pageFramePageId &&
				rowsetId == that.rowsetId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pageFramePageId, rowsetId);
	}
}
