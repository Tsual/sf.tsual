package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_SPECIAL_PAGE", schema = "BASE", catalog = "")
@IdClass(BceFrameSpecialPageEntityPK.class)
public class BceFrameSpecialPageEntity
{
	private long bceFrameId;
	private Long moduleId;
	private long pageFramePageId;
	private String pageTitle;
	private String pageParam;
	private Long pageRulesetId;
	private Boolean isGetPageData;
	private Boolean isDataMust;
	private Long state;
	private String remarks;

	@Id
	@Column(name = "BCE_FRAME_ID")
	public long getBceFrameId()
	{
		return bceFrameId;
	}

	public void setBceFrameId(long bceFrameId)
	{
		this.bceFrameId = bceFrameId;
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
	@Column(name = "PAGE_PARAM")
	public String getPageParam()
	{
		return pageParam;
	}

	public void setPageParam(String pageParam)
	{
		this.pageParam = pageParam;
	}

	@Basic
	@Column(name = "PAGE_RULESET_ID")
	public Long getPageRulesetId()
	{
		return pageRulesetId;
	}

	public void setPageRulesetId(Long pageRulesetId)
	{
		this.pageRulesetId = pageRulesetId;
	}

	@Basic
	@Column(name = "IS_GET_PAGE_DATA")
	public Boolean getGetPageData()
	{
		return isGetPageData;
	}

	public void setGetPageData(Boolean getPageData)
	{
		isGetPageData = getPageData;
	}

	@Basic
	@Column(name = "IS_DATA_MUST")
	public Boolean getDataMust()
	{
		return isDataMust;
	}

	public void setDataMust(Boolean dataMust)
	{
		isDataMust = dataMust;
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
		BceFrameSpecialPageEntity that = (BceFrameSpecialPageEntity) o;
		return bceFrameId == that.bceFrameId &&
				pageFramePageId == that.pageFramePageId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(pageTitle, that.pageTitle) &&
				Objects.equals(pageParam, that.pageParam) &&
				Objects.equals(pageRulesetId, that.pageRulesetId) &&
				Objects.equals(isGetPageData, that.isGetPageData) &&
				Objects.equals(isDataMust, that.isDataMust) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, moduleId, pageFramePageId, pageTitle, pageParam, pageRulesetId, isGetPageData, isDataMust, state, remarks);
	}
}
