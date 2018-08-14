package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_PAGE_FRAME_PAGE", schema = "BASE", catalog = "")
public class BcePageFramePageEntity
{
	private long pageFramePageId;
	private Long moduleId;
	private long pageFrameId;
	private long pageId;
	private String pageTitle;
	private Long seqNo;
	private Boolean isDisplay;
	private String height;
	private Long state;
	private String remarks;

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
	@Column(name = "PAGE_ID")
	public long getPageId()
	{
		return pageId;
	}

	public void setPageId(long pageId)
	{
		this.pageId = pageId;
	}

	@Basic
	@Column(name = "PAGE_TITLE")
	public String getPageTitle()
	{
		return pageTitle;
	}

	public void setPageTitle(String pageTitle)
	{
		this.pageTitle = pageTitle;
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
	@Column(name = "IS_DISPLAY")
	public Boolean getDisplay()
	{
		return isDisplay;
	}

	public void setDisplay(Boolean display)
	{
		isDisplay = display;
	}

	@Basic
	@Column(name = "HEIGHT")
	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
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
		BcePageFramePageEntity that = (BcePageFramePageEntity) o;
		return pageFramePageId == that.pageFramePageId &&
				pageFrameId == that.pageFrameId &&
				pageId == that.pageId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(pageTitle, that.pageTitle) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(isDisplay, that.isDisplay) &&
				Objects.equals(height, that.height) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pageFramePageId, moduleId, pageFrameId, pageId, pageTitle, seqNo, isDisplay, height, state, remarks);
	}
}
