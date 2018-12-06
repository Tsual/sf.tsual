package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_RULESET_RULE", schema = "BASE", catalog = "")
public class BceRulesetRuleEntity
{
	private long relateId;
	private long rulesetId;
	private long ruleId;
	private Long moduleId;
	private Long ruleTriggerType;
	private Boolean eventObjType;
	private String objName;
	private String childObjName;
	private String eventName;
	private String paramValueList;
	private Boolean verifyType;
	private Long seqNo;
	private Long state;
	private String remarks;
	private String resType;
	private String regionId;
	private String channelType;
	private String sysCode;
	private Boolean blockType;

	@Id
	@Column(name = "RELATE_ID")
	public long getRelateId()
	{
		return relateId;
	}

	public void setRelateId(long relateId)
	{
		this.relateId = relateId;
	}

	@Basic
	@Column(name = "RULESET_ID")
	public long getRulesetId()
	{
		return rulesetId;
	}

	public void setRulesetId(long rulesetId)
	{
		this.rulesetId = rulesetId;
	}

	@Basic
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
	@Column(name = "RULE_TRIGGER_TYPE")
	public Long getRuleTriggerType()
	{
		return ruleTriggerType;
	}

	public void setRuleTriggerType(Long ruleTriggerType)
	{
		this.ruleTriggerType = ruleTriggerType;
	}

	@Basic
	@Column(name = "EVENT_OBJ_TYPE")
	public Boolean getEventObjType()
	{
		return eventObjType;
	}

	public void setEventObjType(Boolean eventObjType)
	{
		this.eventObjType = eventObjType;
	}

	@Basic
	@Column(name = "OBJ_NAME")
	public String getObjName()
	{
		return objName;
	}

	public void setObjName(String objName)
	{
		this.objName = objName;
	}

	@Basic
	@Column(name = "CHILD_OBJ_NAME")
	public String getChildObjName()
	{
		return childObjName;
	}

	public void setChildObjName(String childObjName)
	{
		this.childObjName = childObjName;
	}

	@Basic
	@Column(name = "EVENT_NAME")
	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	@Basic
	@Column(name = "PARAM_VALUE_LIST")
	public String getParamValueList()
	{
		return paramValueList;
	}

	public void setParamValueList(String paramValueList)
	{
		this.paramValueList = paramValueList;
	}

	@Basic
	@Column(name = "VERIFY_TYPE")
	public Boolean getVerifyType()
	{
		return verifyType;
	}

	public void setVerifyType(Boolean verifyType)
	{
		this.verifyType = verifyType;
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
	@Column(name = "RES_TYPE")
	public String getResType()
	{
		return resType;
	}

	public void setResType(String resType)
	{
		this.resType = resType;
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
	@Column(name = "SYS_CODE")
	public String getSysCode()
	{
		return sysCode;
	}

	public void setSysCode(String sysCode)
	{
		this.sysCode = sysCode;
	}

	@Basic
	@Column(name = "BLOCK_TYPE")
	public Boolean getBlockType()
	{
		return blockType;
	}

	public void setBlockType(Boolean blockType)
	{
		this.blockType = blockType;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceRulesetRuleEntity that = (BceRulesetRuleEntity) o;
		return relateId == that.relateId &&
				rulesetId == that.rulesetId &&
				ruleId == that.ruleId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(ruleTriggerType, that.ruleTriggerType) &&
				Objects.equals(eventObjType, that.eventObjType) &&
				Objects.equals(objName, that.objName) &&
				Objects.equals(childObjName, that.childObjName) &&
				Objects.equals(eventName, that.eventName) &&
				Objects.equals(paramValueList, that.paramValueList) &&
				Objects.equals(verifyType, that.verifyType) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(resType, that.resType) &&
				Objects.equals(regionId, that.regionId) &&
				Objects.equals(channelType, that.channelType) &&
				Objects.equals(sysCode, that.sysCode) &&
				Objects.equals(blockType, that.blockType);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(relateId, rulesetId, ruleId, moduleId, ruleTriggerType, eventObjType, objName, childObjName, eventName, paramValueList, verifyType, seqNo, state, remarks, resType, regionId, channelType, sysCode, blockType);
	}
}
