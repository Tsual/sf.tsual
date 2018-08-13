package sf.hibernate.beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceFrameAreaPagetabEntityPK implements Serializable
{
	private long tabId;
	private long bceFrameId;

	@Column(name = "TAB_ID")
	@Id
	public long getTabId()
	{
		return tabId;
	}

	public void setTabId(long tabId)
	{
		this.tabId = tabId;
	}

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameAreaPagetabEntityPK that = (BceFrameAreaPagetabEntityPK) o;
		return tabId == that.tabId &&
				bceFrameId == that.bceFrameId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(tabId, bceFrameId);
	}
}
