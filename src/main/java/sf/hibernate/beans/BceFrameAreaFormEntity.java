package sf.hibernate.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_AREA_FORM", schema = "BASE", catalog = "")
@IdClass(BceFrameAreaFormEntityPK.class)
public class BceFrameAreaFormEntity
{
	private long bceFrameId;
	private String formId;
	private Long moduleId;
	private Long formType;
	private String templateId;
	private Long cols;
	private String dataModel;
	private String serviceName;
	private String queryMethod;
	private String countMethod;
	private String conditionName;
	private String parameterName;
	private Long isInitial;
	private Long isEditable;
	private Long needRefresh;
	private Long multSelect;
	private Long pageSize;
	private String width;
	private String height;
	private String rowHeight;
	private Long footDisplay;
	private String mo;
	private String operator;
	private String validation;
	private String onDbclick;
	private String onValuechange;
	private String onRowchange;
	private Long state;

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
	@Column(name = "FORM_TYPE")
	public Long getFormType()
	{
		return formType;
	}

	public void setFormType(Long formType)
	{
		this.formType = formType;
	}

	@Basic
	@Column(name = "TEMPLATE_ID")
	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	@Basic
	@Column(name = "COLS")
	public Long getCols()
	{
		return cols;
	}

	public void setCols(Long cols)
	{
		this.cols = cols;
	}

	@Basic
	@Column(name = "DATA_MODEL")
	public String getDataModel()
	{
		return dataModel;
	}

	public void setDataModel(String dataModel)
	{
		this.dataModel = dataModel;
	}

	@Basic
	@Column(name = "SERVICE_NAME")
	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	@Basic
	@Column(name = "QUERY_METHOD")
	public String getQueryMethod()
	{
		return queryMethod;
	}

	public void setQueryMethod(String queryMethod)
	{
		this.queryMethod = queryMethod;
	}

	@Basic
	@Column(name = "COUNT_METHOD")
	public String getCountMethod()
	{
		return countMethod;
	}

	public void setCountMethod(String countMethod)
	{
		this.countMethod = countMethod;
	}

	@Basic
	@Column(name = "CONDITION_NAME")
	public String getConditionName()
	{
		return conditionName;
	}

	public void setConditionName(String conditionName)
	{
		this.conditionName = conditionName;
	}

	@Basic
	@Column(name = "PARAMETER_NAME")
	public String getParameterName()
	{
		return parameterName;
	}

	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}

	@Basic
	@Column(name = "IS_INITIAL")
	public Long getIsInitial()
	{
		return isInitial;
	}

	public void setIsInitial(Long isInitial)
	{
		this.isInitial = isInitial;
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
	@Column(name = "NEED_REFRESH")
	public Long getNeedRefresh()
	{
		return needRefresh;
	}

	public void setNeedRefresh(Long needRefresh)
	{
		this.needRefresh = needRefresh;
	}

	@Basic
	@Column(name = "MULT_SELECT")
	public Long getMultSelect()
	{
		return multSelect;
	}

	public void setMultSelect(Long multSelect)
	{
		this.multSelect = multSelect;
	}

	@Basic
	@Column(name = "PAGE_SIZE")
	public Long getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Long pageSize)
	{
		this.pageSize = pageSize;
	}

	@Basic
	@Column(name = "WIDTH")
	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	@Basic
	@Column(name = "HEIGHT")
	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}

	@Basic
	@Column(name = "ROW_HEIGHT")
	public String getRowHeight()
	{
		return rowHeight;
	}

	public void setRowHeight(String rowHeight)
	{
		this.rowHeight = rowHeight;
	}

	@Basic
	@Column(name = "FOOT_DISPLAY")
	public Long getFootDisplay()
	{
		return footDisplay;
	}

	public void setFootDisplay(Long footDisplay)
	{
		this.footDisplay = footDisplay;
	}

	@Basic
	@Column(name = "MO")
	public String getMo()
	{
		return mo;
	}

	public void setMo(String mo)
	{
		this.mo = mo;
	}

	@Basic
	@Column(name = "OPERATOR")
	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	@Basic
	@Column(name = "VALIDATION")
	public String getValidation()
	{
		return validation;
	}

	public void setValidation(String validation)
	{
		this.validation = validation;
	}

	@Basic
	@Column(name = "ON_DBCLICK")
	public String getOnDbclick()
	{
		return onDbclick;
	}

	public void setOnDbclick(String onDbclick)
	{
		this.onDbclick = onDbclick;
	}

	@Basic
	@Column(name = "ON_VALUECHANGE")
	public String getOnValuechange()
	{
		return onValuechange;
	}

	public void setOnValuechange(String onValuechange)
	{
		this.onValuechange = onValuechange;
	}

	@Basic
	@Column(name = "ON_ROWCHANGE")
	public String getOnRowchange()
	{
		return onRowchange;
	}

	public void setOnRowchange(String onRowchange)
	{
		this.onRowchange = onRowchange;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameAreaFormEntity that = (BceFrameAreaFormEntity) o;
		return bceFrameId == that.bceFrameId &&
				Objects.equals(formId, that.formId) &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(formType, that.formType) &&
				Objects.equals(templateId, that.templateId) &&
				Objects.equals(cols, that.cols) &&
				Objects.equals(dataModel, that.dataModel) &&
				Objects.equals(serviceName, that.serviceName) &&
				Objects.equals(queryMethod, that.queryMethod) &&
				Objects.equals(countMethod, that.countMethod) &&
				Objects.equals(conditionName, that.conditionName) &&
				Objects.equals(parameterName, that.parameterName) &&
				Objects.equals(isInitial, that.isInitial) &&
				Objects.equals(isEditable, that.isEditable) &&
				Objects.equals(needRefresh, that.needRefresh) &&
				Objects.equals(multSelect, that.multSelect) &&
				Objects.equals(pageSize, that.pageSize) &&
				Objects.equals(width, that.width) &&
				Objects.equals(height, that.height) &&
				Objects.equals(rowHeight, that.rowHeight) &&
				Objects.equals(footDisplay, that.footDisplay) &&
				Objects.equals(mo, that.mo) &&
				Objects.equals(operator, that.operator) &&
				Objects.equals(validation, that.validation) &&
				Objects.equals(onDbclick, that.onDbclick) &&
				Objects.equals(onValuechange, that.onValuechange) &&
				Objects.equals(onRowchange, that.onRowchange) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, formId, moduleId, formType, templateId, cols, dataModel, serviceName, queryMethod, countMethod, conditionName, parameterName, isInitial, isEditable, needRefresh, multSelect, pageSize, width, height, rowHeight, footDisplay, mo, operator, validation, onDbclick, onValuechange, onRowchange, state);
	}
}
