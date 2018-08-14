package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_SIMPLE_FUNC_FIELD_MAPPING", schema = "BASE", catalog = "")
public class BceSimpleFuncFieldMappingEntity
{
	private long fieldId;
	private Long funcId;
	private Long moduleId;
	private String fieldCode;
	private String ordField;
	private String insField;
	private Long state;

	@Id
	@Column(name = "FIELD_ID")
	public long getFieldId()
	{
		return fieldId;
	}

	public void setFieldId(long fieldId)
	{
		this.fieldId = fieldId;
	}

	@Basic
	@Column(name = "FUNC_ID")
	public Long getFuncId()
	{
		return funcId;
	}

	public void setFuncId(Long funcId)
	{
		this.funcId = funcId;
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
	@Column(name = "FIELD_CODE")
	public String getFieldCode()
	{
		return fieldCode;
	}

	public void setFieldCode(String fieldCode)
	{
		this.fieldCode = fieldCode;
	}

	@Basic
	@Column(name = "ORD_FIELD")
	public String getOrdField()
	{
		return ordField;
	}

	public void setOrdField(String ordField)
	{
		this.ordField = ordField;
	}

	@Basic
	@Column(name = "INS_FIELD")
	public String getInsField()
	{
		return insField;
	}

	public void setInsField(String insField)
	{
		this.insField = insField;
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
		BceSimpleFuncFieldMappingEntity that = (BceSimpleFuncFieldMappingEntity) o;
		return fieldId == that.fieldId &&
				Objects.equals(funcId, that.funcId) &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(fieldCode, that.fieldCode) &&
				Objects.equals(ordField, that.ordField) &&
				Objects.equals(insField, that.insField) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(fieldId, funcId, moduleId, fieldCode, ordField, insField, state);
	}
}
