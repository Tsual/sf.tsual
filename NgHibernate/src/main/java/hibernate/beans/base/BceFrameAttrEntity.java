package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_ATTR", schema = "BASE", catalog = "")
@IdClass(BceFrameAttrEntityPK.class)
public class BceFrameAttrEntity
{
	private long attrId;
	private long bceFrameId;
	private String formId;
	private Long moduleId;
	private Long groupId;
	private Long isVisible;
	private Long isEditable;
	private String fieldHeight;
	private String fieldWidth;
	private Long colSpan;
	private Long isNullable;
	private Long editType;
	private Long maxLength;
	private String resSrc;
	private String resParam;
	private String defaultValue;
	private String valueClass;
	private Long isValidate;
	private Long seqNo;
	private Long isLog;
	private String ext1;
	private String ext2;
	private Long ext3;
	private Long state;
	private Long isMultivalueable;
	private String attrName;
	private String i18NRes;
	private Boolean mutiFlag;
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

	@Id
	@Column(name = "FORM_ID")
	public String getFormId()
	{
		return formId;
	}

	public void setFormId(String formId)
	{
		this.formId = formId;
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
	@Column(name = "GROUP_ID")
	public Long getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Long groupId)
	{
		this.groupId = groupId;
	}

	@Basic
	@Column(name = "IS_VISIBLE")
	public Long getIsVisible()
	{
		return isVisible;
	}

	public void setIsVisible(Long isVisible)
	{
		this.isVisible = isVisible;
	}

	@Basic
	@Column(name = "IS_EDITABLE")
	public Long getIsEditable()
	{
		return isEditable;
	}

	public void setIsEditable(Long isEditable)
	{
		this.isEditable = isEditable;
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
	@Column(name = "IS_VALIDATE")
	public Long getIsValidate()
	{
		return isValidate;
	}

	public void setIsValidate(Long isValidate)
	{
		this.isValidate = isValidate;
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
	@Column(name = "IS_LOG")
	public Long getIsLog()
	{
		return isLog;
	}

	public void setIsLog(Long isLog)
	{
		this.isLog = isLog;
	}

	@Basic
	@Column(name = "EXT_1")
	public String getExt1()
	{
		return ext1;
	}

	public void setExt1(String ext1)
	{
		this.ext1 = ext1;
	}

	@Basic
	@Column(name = "EXT_2")
	public String getExt2()
	{
		return ext2;
	}

	public void setExt2(String ext2)
	{
		this.ext2 = ext2;
	}

	@Basic
	@Column(name = "EXT_3")
	public Long getExt3()
	{
		return ext3;
	}

	public void setExt3(Long ext3)
	{
		this.ext3 = ext3;
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
		BceFrameAttrEntity that = (BceFrameAttrEntity) o;
		return attrId == that.attrId &&
				bceFrameId == that.bceFrameId &&
				Objects.equals(formId, that.formId) &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(groupId, that.groupId) &&
				Objects.equals(isVisible, that.isVisible) &&
				Objects.equals(isEditable, that.isEditable) &&
				Objects.equals(fieldHeight, that.fieldHeight) &&
				Objects.equals(fieldWidth, that.fieldWidth) &&
				Objects.equals(colSpan, that.colSpan) &&
				Objects.equals(isNullable, that.isNullable) &&
				Objects.equals(editType, that.editType) &&
				Objects.equals(maxLength, that.maxLength) &&
				Objects.equals(resSrc, that.resSrc) &&
				Objects.equals(resParam, that.resParam) &&
				Objects.equals(defaultValue, that.defaultValue) &&
				Objects.equals(valueClass, that.valueClass) &&
				Objects.equals(isValidate, that.isValidate) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(isLog, that.isLog) &&
				Objects.equals(ext1, that.ext1) &&
				Objects.equals(ext2, that.ext2) &&
				Objects.equals(ext3, that.ext3) &&
				Objects.equals(state, that.state) &&
				Objects.equals(isMultivalueable, that.isMultivalueable) &&
				Objects.equals(attrName, that.attrName) &&
				Objects.equals(i18NRes, that.i18NRes) &&
				Objects.equals(mutiFlag, that.mutiFlag) &&
				Objects.equals(regionId, that.regionId) &&
				Objects.equals(effType, that.effType);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(attrId, bceFrameId, formId, moduleId, groupId, isVisible, isEditable, fieldHeight, fieldWidth, colSpan, isNullable, editType, maxLength, resSrc, resParam, defaultValue, valueClass, isValidate, seqNo, isLog, ext1, ext2, ext3, state, isMultivalueable, attrName, i18NRes, mutiFlag, regionId, effType);
	}
}
