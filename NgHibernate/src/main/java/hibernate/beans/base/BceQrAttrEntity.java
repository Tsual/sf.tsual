package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_QR_ATTR", schema = "BASE", catalog = "")
public class BceQrAttrEntity
{
	private long attrId;
	private long templateId;
	private String attrName;
	private String aliasName;
	private String tempString;
	private Long preAttrId;
	private Boolean isPre;
	private Boolean isList;
	private String paramRe;
	private String defaultValue;
	private String bceGetRule;
	private boolean state;
	private String remarks;

	@Id
	@Column(name = "ATTR_ID")
	public long getAttrId()
	{
		return attrId;
	}

	public void setAttrId(long attrId)
	{
		this.attrId = attrId;
	}

	@Basic
	@Column(name = "TEMPLATE_ID")
	public long getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(long templateId)
	{
		this.templateId = templateId;
	}

	@Basic
	@Column(name = "ATTR_NAME")
	public String getAttrName()
	{
		return attrName;
	}

	public void setAttrName(String attrName)
	{
		this.attrName = attrName;
	}

	@Basic
	@Column(name = "ALIAS_NAME")
	public String getAliasName()
	{
		return aliasName;
	}

	public void setAliasName(String aliasName)
	{
		this.aliasName = aliasName;
	}

	@Basic
	@Column(name = "TEMP_STRING")
	public String getTempString()
	{
		return tempString;
	}

	public void setTempString(String tempString)
	{
		this.tempString = tempString;
	}

	@Basic
	@Column(name = "PRE_ATTR_ID")
	public Long getPreAttrId()
	{
		return preAttrId;
	}

	public void setPreAttrId(Long preAttrId)
	{
		this.preAttrId = preAttrId;
	}

	@Basic
	@Column(name = "IS_PRE")
	public Boolean getPre()
	{
		return isPre;
	}

	public void setPre(Boolean pre)
	{
		isPre = pre;
	}

	@Basic
	@Column(name = "IS_LIST")
	public Boolean getList()
	{
		return isList;
	}

	public void setList(Boolean list)
	{
		isList = list;
	}

	@Basic
	@Column(name = "PARAM_RE")
	public String getParamRe()
	{
		return paramRe;
	}

	public void setParamRe(String paramRe)
	{
		this.paramRe = paramRe;
	}

	@Basic
	@Column(name = "DEFAULT_VALUE")
	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	@Basic
	@Column(name = "BCE_GET_RULE")
	public String getBceGetRule()
	{
		return bceGetRule;
	}

	public void setBceGetRule(String bceGetRule)
	{
		this.bceGetRule = bceGetRule;
	}

	@Basic
	@Column(name = "STATE")
	public boolean isState()
	{
		return state;
	}

	public void setState(boolean state)
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
		BceQrAttrEntity that = (BceQrAttrEntity) o;
		return attrId == that.attrId &&
				templateId == that.templateId &&
				state == that.state &&
				Objects.equals(attrName, that.attrName) &&
				Objects.equals(aliasName, that.aliasName) &&
				Objects.equals(tempString, that.tempString) &&
				Objects.equals(preAttrId, that.preAttrId) &&
				Objects.equals(isPre, that.isPre) &&
				Objects.equals(isList, that.isList) &&
				Objects.equals(paramRe, that.paramRe) &&
				Objects.equals(defaultValue, that.defaultValue) &&
				Objects.equals(bceGetRule, that.bceGetRule) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(attrId, templateId, attrName, aliasName, tempString, preAttrId, isPre, isList, paramRe, defaultValue, bceGetRule, state, remarks);
	}
}
