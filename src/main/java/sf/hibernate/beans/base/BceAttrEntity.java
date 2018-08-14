package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_ATTR", schema = "BASE", catalog = "")
public class BceAttrEntity
{
	private long attrId;
	private Long moduleId;
	private String objName;
	private String attrCode;
	private String attrName;
	private String i18NRes;
	private String fieldType;
	private String fieldWidth;
	private Long colSpan;
	private Long isNullable;
	private String fieldHeight;
	private Long isMultivalueable;
	private Long editType;
	private Long maxLength;
	private String resSrc;
	private String resParam;
	private String defaultValue;
	private Boolean mutiFlag;
	private String valueClass;
	private String ruleId;
	private String remarks;
	private Long state;
	private String regionId;
	private Boolean effType;

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
	@Column(name = "ATTR_CODE")
	public String getAttrCode()
	{
		return attrCode;
	}

	public void setAttrCode(String attrCode)
	{
		this.attrCode = attrCode;
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
	@Column(name = "I18N_RES")
	public String getI18NRes()
	{
		return i18NRes;
	}

	public void setI18NRes(String i18NRes)
	{
		this.i18NRes = i18NRes;
	}

	@Basic
	@Column(name = "FIELD_TYPE")
	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	@Basic
	@Column(name = "FIELD_WIDTH")
	public String getFieldWidth()
	{
		return fieldWidth;
	}

	public void setFieldWidth(String fieldWidth)
	{
		this.fieldWidth = fieldWidth;
	}

	@Basic
	@Column(name = "COL_SPAN")
	public Long getColSpan()
	{
		return colSpan;
	}

	public void setColSpan(Long colSpan)
	{
		this.colSpan = colSpan;
	}

	@Basic
	@Column(name = "IS_NULLABLE")
	public Long getIsNullable()
	{
		return isNullable;
	}

	public void setIsNullable(Long isNullable)
	{
		this.isNullable = isNullable;
	}

	@Basic
	@Column(name = "FIELD_HEIGHT")
	public String getFieldHeight()
	{
		return fieldHeight;
	}

	public void setFieldHeight(String fieldHeight)
	{
		this.fieldHeight = fieldHeight;
	}

	@Basic
	@Column(name = "IS_MULTIVALUEABLE")
	public Long getIsMultivalueable()
	{
		return isMultivalueable;
	}

	public void setIsMultivalueable(Long isMultivalueable)
	{
		this.isMultivalueable = isMultivalueable;
	}

	@Basic
	@Column(name = "EDIT_TYPE")
	public Long getEditType()
	{
		return editType;
	}

	public void setEditType(Long editType)
	{
		this.editType = editType;
	}

	@Basic
	@Column(name = "MAX_LENGTH")
	public Long getMaxLength()
	{
		return maxLength;
	}

	public void setMaxLength(Long maxLength)
	{
		this.maxLength = maxLength;
	}

	@Basic
	@Column(name = "RES_SRC")
	public String getResSrc()
	{
		return resSrc;
	}

	public void setResSrc(String resSrc)
	{
		this.resSrc = resSrc;
	}

	@Basic
	@Column(name = "RES_PARAM")
	public String getResParam()
	{
		return resParam;
	}

	public void setResParam(String resParam)
	{
		this.resParam = resParam;
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
	@Column(name = "MUTI_FLAG")
	public Boolean getMutiFlag()
	{
		return mutiFlag;
	}

	public void setMutiFlag(Boolean mutiFlag)
	{
		this.mutiFlag = mutiFlag;
	}

	@Basic
	@Column(name = "VALUE_CLASS")
	public String getValueClass()
	{
		return valueClass;
	}

	public void setValueClass(String valueClass)
	{
		this.valueClass = valueClass;
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
	@Column(name = "EFF_TYPE")
	public Boolean getEffType()
	{
		return effType;
	}

	public void setEffType(Boolean effType)
	{
		this.effType = effType;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceAttrEntity that = (BceAttrEntity) o;
		return attrId == that.attrId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(objName, that.objName) &&
				Objects.equals(attrCode, that.attrCode) &&
				Objects.equals(attrName, that.attrName) &&
				Objects.equals(i18NRes, that.i18NRes) &&
				Objects.equals(fieldType, that.fieldType) &&
				Objects.equals(fieldWidth, that.fieldWidth) &&
				Objects.equals(colSpan, that.colSpan) &&
				Objects.equals(isNullable, that.isNullable) &&
				Objects.equals(fieldHeight, that.fieldHeight) &&
				Objects.equals(isMultivalueable, that.isMultivalueable) &&
				Objects.equals(editType, that.editType) &&
				Objects.equals(maxLength, that.maxLength) &&
				Objects.equals(resSrc, that.resSrc) &&
				Objects.equals(resParam, that.resParam) &&
				Objects.equals(defaultValue, that.defaultValue) &&
				Objects.equals(mutiFlag, that.mutiFlag) &&
				Objects.equals(valueClass, that.valueClass) &&
				Objects.equals(ruleId, that.ruleId) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(state, that.state) &&
				Objects.equals(regionId, that.regionId) &&
				Objects.equals(effType, that.effType);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(attrId, moduleId, objName, attrCode, attrName, i18NRes, fieldType, fieldWidth, colSpan, isNullable, fieldHeight, isMultivalueable, editType, maxLength, resSrc, resParam, defaultValue, mutiFlag, valueClass, ruleId, remarks, state, regionId, effType);
	}
}
