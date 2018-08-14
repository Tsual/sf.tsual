package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_PAGE_FRAME", schema = "BASE", catalog = "")
public class BcePageFrameEntity
{
	private long pageFrameId;
	private Long moduleId;
	private String pageFrameName;
	private Long frameType;
	private Long state;
	private String remarks;

	@Id
	@Column(name = "PAGE_FRAME_ID")
	public long getPageFrameId()
	{
		return pageFrameId;
	}

	public void setPageFrameId(long pageFrameId)
	{
		this.pageFrameId = pageFrameId;
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
	@Column(name = "PAGE_FRAME_NAME")
	public String getPageFrameName()
	{
		return pageFrameName;
	}

	public void setPageFrameName(String pageFrameName)
	{
		this.pageFrameName = pageFrameName;
	}

	@Basic
	@Column(name = "FRAME_TYPE")
	public Long getFrameType()
	{
		return frameType;
	}

	public void setFrameType(Long frameType)
	{
		this.frameType = frameType;
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
		BcePageFrameEntity that = (BcePageFrameEntity) o;
		return pageFrameId == that.pageFrameId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(pageFrameName, that.pageFrameName) &&
				Objects.equals(frameType, that.frameType) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pageFrameId, moduleId, pageFrameName, frameType, state, remarks);
	}
}
