package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_RULE", schema = "BASE", catalog = "")
public class BceRuleEntity
{
	private long ruleId;
	private String ruleName;
	private Long moduleId;
	private Long ruleKind;
	private Long ruleType;
	private String fileName;
	private String funcName;
	private String paramList;
	private String alertMessage;
	private Long state;
	private String remarks;
	private String centerType;
	private String centerValueIndex;

	@Id
	@Column(name = "RULE_ID")
	public long getRuleId()
	{
		return ruleId;
	}

	public void setRuleId(long ruleId)
	{
		this.ruleId = ruleId;
	}

	@Basic
	@Column(name = "RULE_NAME")
	public String getRuleName()
	{
		return ruleName;
	}

	public void setRuleName(String ruleName)
	{
		this.ruleName = ruleName;
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
	@Column(name = "RULE_KIND")
	public Long getRuleKind()
	{
		return ruleKind;
	}

	public void setRuleKind(Long ruleKind)
	{
		this.ruleKind = ruleKind;
	}

	@Basic
	@Column(name = "RULE_TYPE")
	public Long getRuleType()
	{
		return ruleType;
	}

	public void setRuleType(Long ruleType)
	{
		this.ruleType = ruleType;
	}

	@Basic
	@Column(name = "FILE_NAME")
	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	@Basic
	@Column(name = "FUNC_NAME")
	public String getFuncName()
	{
		return funcName;
	}

	public void setFuncName(String funcName)
	{
		this.funcName = funcName;
	}

	@Basic
	@Column(name = "PARAM_LIST")
	public String getParamList()
	{
		return paramList;
	}

	public void setParamList(String paramList)
	{
		this.paramList = paramList;
	}

	@Basic
	@Column(name = "ALERT_MESSAGE")
	public String getAlertMessage()
	{
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage)
	{
		this.alertMessage = alertMessage;
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

	@Basic
	@Column(name = "CENTER_TYPE")
	public String getCenterType()
	{
		return centerType;
	}

	public void setCenterType(String centerType)
	{
		this.centerType = centerType;
	}

	@Basic
	@Column(name = "CENTER_VALUE_INDEX")
	public String getCenterValueIndex()
	{
		return centerValueIndex;
	}

	public void setCenterValueIndex(String centerValueIndex)
	{
		this.centerValueIndex = centerValueIndex;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceRuleEntity that = (BceRuleEntity) o;
		return ruleId == that.ruleId &&
				Objects.equals(ruleName, that.ruleName) &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(ruleKind, that.ruleKind) &&
				Objects.equals(ruleType, that.ruleType) &&
				Objects.equals(fileName, that.fileName) &&
				Objects.equals(funcName, that.funcName) &&
				Objects.equals(paramList, that.paramList) &&
				Objects.equals(alertMessage, that.alertMessage) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(centerType, that.centerType) &&
				Objects.equals(centerValueIndex, that.centerValueIndex);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(ruleId, ruleName, moduleId, ruleKind, ruleType, fileName, funcName, paramList, alertMessage, state, remarks, centerType, centerValueIndex);
	}
}
