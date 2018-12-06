package hibernate.beans.wengdb;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_MSG_INFO", schema = "RULE_CFG", catalog = "")
public class RuleMsgInfoEntity
{
	private long msgId;
	private Long ruleTmplId;
	private String ruleId;
	private String busiCode;
	private String msgCode;
	private String msgInfo;
	private String implClass;
	private String implFunc;
	private String paramList;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;

	@Id
	@Column(name = "MSG_ID")
	public long getMsgId()
	{
		return msgId;
	}

	public void setMsgId(long msgId)
	{
		this.msgId = msgId;
	}

	@Basic
	@Column(name = "RULE_TMPL_ID")
	public Long getRuleTmplId()
	{
		return ruleTmplId;
	}

	public void setRuleTmplId(Long ruleTmplId)
	{
		this.ruleTmplId = ruleTmplId;
	}

	@Basic
	@Column(name = "RULE_ID")
	public String getRuleId()
	{
		return ruleId;
	}

	public void setRuleId(String ruleId)
	{
		this.ruleId = ruleId;
	}

	@Basic
	@Column(name = "BUSI_CODE")
	public String getBusiCode()
	{
		return busiCode;
	}

	public void setBusiCode(String busiCode)
	{
		this.busiCode = busiCode;
	}

	@Basic
	@Column(name = "MSG_CODE")
	public String getMsgCode()
	{
		return msgCode;
	}

	public void setMsgCode(String msgCode)
	{
		this.msgCode = msgCode;
	}

	@Basic
	@Column(name = "MSG_INFO")
	public String getMsgInfo()
	{
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo)
	{
		this.msgInfo = msgInfo;
	}

	@Basic
	@Column(name = "IMPL_CLASS")
	public String getImplClass()
	{
		return implClass;
	}

	public void setImplClass(String implClass)
	{
		this.implClass = implClass;
	}

	@Basic
	@Column(name = "IMPL_FUNC")
	public String getImplFunc()
	{
		return implFunc;
	}

	public void setImplFunc(String implFunc)
	{
		this.implFunc = implFunc;
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
	@Column(name = "DONE_DATE")
	public Time getDoneDate()
	{
		return doneDate;
	}

	public void setDoneDate(Time doneDate)
	{
		this.doneDate = doneDate;
	}

	@Basic
	@Column(name = "ORG_ID")
	public Long getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Long orgId)
	{
		this.orgId = orgId;
	}

	@Basic
	@Column(name = "OP_ID")
	public Long getOpId()
	{
		return opId;
	}

	public void setOpId(Long opId)
	{
		this.opId = opId;
	}

	@Basic
	@Column(name = "STATE")
	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleMsgInfoEntity that = (RuleMsgInfoEntity) o;
		return msgId == that.msgId &&
				Objects.equals(ruleTmplId, that.ruleTmplId) &&
				Objects.equals(ruleId, that.ruleId) &&
				Objects.equals(busiCode, that.busiCode) &&
				Objects.equals(msgCode, that.msgCode) &&
				Objects.equals(msgInfo, that.msgInfo) &&
				Objects.equals(implClass, that.implClass) &&
				Objects.equals(implFunc, that.implFunc) &&
				Objects.equals(paramList, that.paramList) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(msgId, ruleTmplId, ruleId, busiCode, msgCode, msgInfo, implClass, implFunc, paramList, remarks, doneDate, orgId, opId, state);
	}
}
