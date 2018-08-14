package sf.hibernate.beans.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceFrameSpecialPageEntityPK implements Serializable
{
	private long bceFrameId;
	private long pageFramePageId;

	@Column(name = "BCE_FRAME_ID")
	@Id
	public long getBceFrameId()
	{
		return bceFrameId;
	}

	public void setBceFrameId(long bceFrameId)
	{
		this.bceFrameId = bceFrameId;
	}

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameSpecialPageEntityPK that = (BceFrameSpecialPageEntityPK) o;
		return bceFrameId == that.bceFrameId &&
				pageFramePageId == that.pageFramePageId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, pageFramePageId);
	}
}
