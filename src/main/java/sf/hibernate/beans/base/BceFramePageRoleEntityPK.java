package sf.hibernate.beans.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceFramePageRoleEntityPK implements Serializable
{
	private long bceFrameId;
	private long pageFramePageId;
	private long roleId;

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

	@Column(name = "ROLE_ID")
	@Id
	public long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(long roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFramePageRoleEntityPK that = (BceFramePageRoleEntityPK) o;
		return bceFrameId == that.bceFrameId &&
				pageFramePageId == that.pageFramePageId &&
				roleId == that.roleId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, pageFramePageId, roleId);
	}
}
