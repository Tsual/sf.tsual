package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_PAGE", schema = "BASE", catalog = "")
public class BcePageEntity
{
	private long pageId;
	private Long moduleId;
	private Long pageType;
	private String pageUrl;
	private String pageTemplate;
	private Long pageRulesetId;
	private Boolean isGetPageData;
	private Boolean isDataMust;
	private Boolean pageLoadType;
	private Long state;
	private String remarks;

	@Id
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
	@Column(name = "PAGE_TYPE")
	public Long getPageType()
	{
		return pageType;
	}

	public void setPageType(Long pageType)
	{
		this.pageType = pageType;
	}

	@Basic
	@Column(name = "PAGE_URL")
	public String getPageUrl()
	{
		return pageUrl;
	}

	public void setPageUrl(String pageUrl)
	{
		this.pageUrl = pageUrl;
	}

	@Basic
	@Column(name = "PAGE_TEMPLATE")
	public String getPageTemplate()
	{
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate)
	{
		this.pageTemplate = pageTemplate;
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
	@Column(name = "PAGE_LOAD_TYPE")
	public Boolean getPageLoadType()
	{
		return pageLoadType;
	}

	public void setPageLoadType(Boolean pageLoadType)
	{
		this.pageLoadType = pageLoadType;
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
		BcePageEntity that = (BcePageEntity) o;
		return pageId == that.pageId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(pageType, that.pageType) &&
				Objects.equals(pageUrl, that.pageUrl) &&
				Objects.equals(pageTemplate, that.pageTemplate) &&
				Objects.equals(pageRulesetId, that.pageRulesetId) &&
				Objects.equals(isGetPageData, that.isGetPageData) &&
				Objects.equals(isDataMust, that.isDataMust) &&
				Objects.equals(pageLoadType, that.pageLoadType) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pageId, moduleId, pageType, pageUrl, pageTemplate, pageRulesetId, isGetPageData, isDataMust, pageLoadType, state, remarks);
	}
}
