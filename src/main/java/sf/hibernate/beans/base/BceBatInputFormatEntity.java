package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_BAT_INPUT_FORMAT", schema = "BASE", catalog = "")
public class BceBatInputFormatEntity
{
	private long configId;
	private Long moduleId;
	private Long businessId;
	private Long prodSpecId;
	private Long roleId;
	private Long maxNo;
	private Boolean inputType;
	private String styleDesc;
	private String styleImg;
	private String parseService;
	private String busiService;
	private String retChar;
	private String splitChar;
	private String extra1;
	private String extra2;
	private Long state;
	private String remarks;

	@Id
	@Column(name = "CONFIG_ID")
	public long getConfigId()
	{
		return configId;
	}

	public void setConfigId(long configId)
	{
		this.configId = configId;
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
	@Column(name = "PROD_SPEC_ID")
	public Long getProdSpecId()
	{
		return prodSpecId;
	}

	public void setProdSpecId(Long prodSpecId)
	{
		this.prodSpecId = prodSpecId;
	}

	@Basic
	@Column(name = "ROLE_ID")
	public Long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}

	@Basic
	@Column(name = "MAX_NO")
	public Long getMaxNo()
	{
		return maxNo;
	}

	public void setMaxNo(Long maxNo)
	{
		this.maxNo = maxNo;
	}

	@Basic
	@Column(name = "INPUT_TYPE")
	public Boolean getInputType()
	{
		return inputType;
	}

	public void setInputType(Boolean inputType)
	{
		this.inputType = inputType;
	}

	@Basic
	@Column(name = "STYLE_DESC")
	public String getStyleDesc()
	{
		return styleDesc;
	}

	public void setStyleDesc(String styleDesc)
	{
		this.styleDesc = styleDesc;
	}

	@Basic
	@Column(name = "STYLE_IMG")
	public String getStyleImg()
	{
		return styleImg;
	}

	public void setStyleImg(String styleImg)
	{
		this.styleImg = styleImg;
	}

	@Basic
	@Column(name = "PARSE_SERVICE")
	public String getParseService()
	{
		return parseService;
	}

	public void setParseService(String parseService)
	{
		this.parseService = parseService;
	}

	@Basic
	@Column(name = "BUSI_SERVICE")
	public String getBusiService()
	{
		return busiService;
	}

	public void setBusiService(String busiService)
	{
		this.busiService = busiService;
	}

	@Basic
	@Column(name = "RET_CHAR")
	public String getRetChar()
	{
		return retChar;
	}

	public void setRetChar(String retChar)
	{
		this.retChar = retChar;
	}

	@Basic
	@Column(name = "SPLIT_CHAR")
	public String getSplitChar()
	{
		return splitChar;
	}

	public void setSplitChar(String splitChar)
	{
		this.splitChar = splitChar;
	}

	@Basic
	@Column(name = "EXTRA_1")
	public String getExtra1()
	{
		return extra1;
	}

	public void setExtra1(String extra1)
	{
		this.extra1 = extra1;
	}

	@Basic
	@Column(name = "EXTRA_2")
	public String getExtra2()
	{
		return extra2;
	}

	public void setExtra2(String extra2)
	{
		this.extra2 = extra2;
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
		BceBatInputFormatEntity that = (BceBatInputFormatEntity) o;
		return configId == that.configId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(businessId, that.businessId) &&
				Objects.equals(prodSpecId, that.prodSpecId) &&
				Objects.equals(roleId, that.roleId) &&
				Objects.equals(maxNo, that.maxNo) &&
				Objects.equals(inputType, that.inputType) &&
				Objects.equals(styleDesc, that.styleDesc) &&
				Objects.equals(styleImg, that.styleImg) &&
				Objects.equals(parseService, that.parseService) &&
				Objects.equals(busiService, that.busiService) &&
				Objects.equals(retChar, that.retChar) &&
				Objects.equals(splitChar, that.splitChar) &&
				Objects.equals(extra1, that.extra1) &&
				Objects.equals(extra2, that.extra2) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(configId, moduleId, businessId, prodSpecId, roleId, maxNo, inputType, styleDesc, styleImg, parseService, busiService, retChar, splitChar, extra1, extra2, state, remarks);
	}
}
