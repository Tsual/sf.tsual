package sf.hibernate.beans.wengdb;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RULE_BUSI_REL", schema = "BASE", catalog = "")
public class RuleBusiRelEntity
{
	private long relatId;
	private long busiCode;
	private Long ruleId;
	private String userRole;
	private String opRole;
	private String entityId;
	private String regionId;
	private String sysId;
	private String channelType;
	private String isBatch;
	private String isSumbit;
	private String alertFlag;
	private String limitFlag;
	private Time effectDate;
	private Time expireDate;
	private String remarks;
	private Time doneDate;
	private Long orgId;
	private Long opId;
	private String state;
	private Byte showSort;
	private Byte invokeSort;
	private String isInterrupt;
	private String isGlobalRule;

	@Id
	@Column(name = "RELAT_ID")
	public long getRelatId()
	{
		return relatId;
	}

	public void setRelatId(long relatId)
	{
		this.relatId = relatId;
	}

	@Basic
	@Column(name = "BUSI_CODE")
	public long getBusiCode()
	{
		return busiCode;
	}

	public void setBusiCode(long busiCode)
	{
		this.busiCode = busiCode;
	}

	@Basic
	@Column(name = "RULE_ID")
	public Long getRuleId()
	{
		return ruleId;
	}

	public void setRuleId(Long ruleId)
	{
		this.ruleId = ruleId;
	}

	@Basic
	@Column(name = "USER_ROLE")
	public String getUserRole()
	{
		return userRole;
	}

	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
	}

	@Basic
	@Column(name = "OP_ROLE")
	public String getOpRole()
	{
		return opRole;
	}

	public void setOpRole(String opRole)
	{
		this.opRole = opRole;
	}

	@Basic
	@Column(name = "ENTITY_ID")
	public String getEntityId()
	{
		return entityId;
	}

	public void setEntityId(String entityId)
	{
		this.entityId = entityId;
	}

	@Basic
	@Column(name = "REGION_ID")
	public String getRegionId()
	{
		return regionId;
	}

	public void setRegionId(String regionId)
	{
		this.regionId = regionId;
	}

	@Basic
	@Column(name = "SYS_ID")
	public String getSysId()
	{
		return sysId;
	}

	public void setSysId(String sysId)
	{
		this.sysId = sysId;
	}

	@Basic
	@Column(name = "CHANNEL_TYPE")
	public String getChannelType()
	{
		return channelType;
	}

	public void setChannelType(String channelType)
	{
		this.channelType = channelType;
	}

	@Basic
	@Column(name = "IS_BATCH")
	public String getIsBatch()
	{
		return isBatch;
	}

	public void setIsBatch(String isBatch)
	{
		this.isBatch = isBatch;
	}

	@Basic
	@Column(name = "IS_SUMBIT")
	public String getIsSumbit()
	{
		return isSumbit;
	}

	public void setIsSumbit(String isSumbit)
	{
		this.isSumbit = isSumbit;
	}

	@Basic
	@Column(name = "ALERT_FLAG")
	public String getAlertFlag()
	{
		return alertFlag;
	}

	public void setAlertFlag(String alertFlag)
	{
		this.alertFlag = alertFlag;
	}

	@Basic
	@Column(name = "LIMIT_FLAG")
	public String getLimitFlag()
	{
		return limitFlag;
	}

	public void setLimitFlag(String limitFlag)
	{
		this.limitFlag = limitFlag;
	}

	@Basic
	@Column(name = "EFFECT_DATE")
	public Time getEffectDate()
	{
		return effectDate;
	}

	public void setEffectDate(Time effectDate)
	{
		this.effectDate = effectDate;
	}

	@Basic
	@Column(name = "EXPIRE_DATE")
	public Time getExpireDate()
	{
		return expireDate;
	}

	public void setExpireDate(Time expireDate)
	{
		this.expireDate = expireDate;
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

	@Basic
	@Column(name = "SHOW_SORT")
	public Byte getShowSort()
	{
		return showSort;
	}

	public void setShowSort(Byte showSort)
	{
		this.showSort = showSort;
	}

	@Basic
	@Column(name = "INVOKE_SORT")
	public Byte getInvokeSort()
	{
		return invokeSort;
	}

	public void setInvokeSort(Byte invokeSort)
	{
		this.invokeSort = invokeSort;
	}

	@Basic
	@Column(name = "IS_INTERRUPT")
	public String getIsInterrupt()
	{
		return isInterrupt;
	}

	public void setIsInterrupt(String isInterrupt)
	{
		this.isInterrupt = isInterrupt;
	}

	@Basic
	@Column(name = "IS_GLOBAL_RULE")
	public String getIsGlobalRule()
	{
		return isGlobalRule;
	}

	public void setIsGlobalRule(String isGlobalRule)
	{
		this.isGlobalRule = isGlobalRule;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RuleBusiRelEntity that = (RuleBusiRelEntity) o;
		return relatId == that.relatId &&
				busiCode == that.busiCode &&
				Objects.equals(ruleId, that.ruleId) &&
				Objects.equals(userRole, that.userRole) &&
				Objects.equals(opRole, that.opRole) &&
				Objects.equals(entityId, that.entityId) &&
				Objects.equals(regionId, that.regionId) &&
				Objects.equals(sysId, that.sysId) &&
				Objects.equals(channelType, that.channelType) &&
				Objects.equals(isBatch, that.isBatch) &&
				Objects.equals(isSumbit, that.isSumbit) &&
				Objects.equals(alertFlag, that.alertFlag) &&
				Objects.equals(limitFlag, that.limitFlag) &&
				Objects.equals(effectDate, that.effectDate) &&
				Objects.equals(expireDate, that.expireDate) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(doneDate, that.doneDate) &&
				Objects.equals(orgId, that.orgId) &&
				Objects.equals(opId, that.opId) &&
				Objects.equals(state, that.state) &&
				Objects.equals(showSort, that.showSort) &&
				Objects.equals(invokeSort, that.invokeSort) &&
				Objects.equals(isInterrupt, that.isInterrupt) &&
				Objects.equals(isGlobalRule, that.isGlobalRule);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(relatId, busiCode, ruleId, userRole, opRole, entityId, regionId, sysId, channelType, isBatch, isSumbit, alertFlag, limitFlag, effectDate, expireDate, remarks, doneDate, orgId, opId, state, showSort, invokeSort, isInterrupt, isGlobalRule);
	}
}
