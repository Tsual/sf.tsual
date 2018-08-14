package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME", schema = "BASE", catalog = "")
public class BceFrameEntity
{
	private long bceFrameId;
	private Long moduleId;
	private Long businessId;
	private String paramData;
	private Long pageFrameId;
	private String entryPageUrl;
	private String dataParser;
	private String dealService;
	private String workflowCode;
	private Long state;
	private Long srcSystemType;
	private String remarks;
	private Long printTemplateId;
	private Boolean isLockOffer;

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

	@Basic
	@Column(name = "BUSINESS_ID")
	public Long getBusinessId()
	{
		return businessId;
	}

	public void setBusinessId(Long businessId)
	{
		this.businessId = businessId;
	}

	@Basic
	@Column(name = "PARAM_DATA")
	public String getParamData()
	{
		return paramData;
	}

	public void setParamData(String paramData)
	{
		this.paramData = paramData;
	}

	@Basic
	@Column(name = "PAGE_FRAME_ID")
	public Long getPageFrameId()
	{
		return pageFrameId;
	}

	public void setPageFrameId(Long pageFrameId)
	{
		this.pageFrameId = pageFrameId;
	}

	@Basic
	@Column(name = "ENTRY_PAGE_URL")
	public String getEntryPageUrl()
	{
		return entryPageUrl;
	}

	public void setEntryPageUrl(String entryPageUrl)
	{
		this.entryPageUrl = entryPageUrl;
	}

	@Basic
	@Column(name = "DATA_PARSER")
	public String getDataParser()
	{
		return dataParser;
	}

	public void setDataParser(String dataParser)
	{
		this.dataParser = dataParser;
	}

	@Basic
	@Column(name = "DEAL_SERVICE")
	public String getDealService()
	{
		return dealService;
	}

	public void setDealService(String dealService)
	{
		this.dealService = dealService;
	}

	@Basic
	@Column(name = "WORKFLOW_CODE")
	public String getWorkflowCode()
	{
		return workflowCode;
	}

	public void setWorkflowCode(String workflowCode)
	{
		this.workflowCode = workflowCode;
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
	@Column(name = "SRC_SYSTEM_TYPE")
	public Long getSrcSystemType()
	{
		return srcSystemType;
	}

	public void setSrcSystemType(Long srcSystemType)
	{
		this.srcSystemType = srcSystemType;
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
	@Column(name = "PRINT_TEMPLATE_ID")
	public Long getPrintTemplateId()
	{
		return printTemplateId;
	}

	public void setPrintTemplateId(Long printTemplateId)
	{
		this.printTemplateId = printTemplateId;
	}

	@Basic
	@Column(name = "IS_LOCK_OFFER")
	public Boolean getLockOffer()
	{
		return isLockOffer;
	}

	public void setLockOffer(Boolean lockOffer)
	{
		isLockOffer = lockOffer;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameEntity that = (BceFrameEntity) o;
		return bceFrameId == that.bceFrameId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(businessId, that.businessId) &&
				Objects.equals(paramData, that.paramData) &&
				Objects.equals(pageFrameId, that.pageFrameId) &&
				Objects.equals(entryPageUrl, that.entryPageUrl) &&
				Objects.equals(dataParser, that.dataParser) &&
				Objects.equals(dealService, that.dealService) &&
				Objects.equals(workflowCode, that.workflowCode) &&
				Objects.equals(state, that.state) &&
				Objects.equals(srcSystemType, that.srcSystemType) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(printTemplateId, that.printTemplateId) &&
				Objects.equals(isLockOffer, that.isLockOffer);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, moduleId, businessId, paramData, pageFrameId, entryPageUrl, dataParser, dealService, workflowCode, state, srcSystemType, remarks, printTemplateId, isLockOffer);
	}
}
