package sf.hibernate.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_BAT_INPUT_FIELD_FORMAT", schema = "BASE", catalog = "")
@IdClass(BceBatInputFieldFormatEntityPK.class)
public class BceBatInputFieldFormatEntity
{
	private long configId;
	private Long moduleId;
	private long listType;
	private long seqNo;
	private String fieldName;
	private String title;
	private String verifySrvname;
	private Long state;

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

	@Id
	@Column(name = "LIST_TYPE")
	public long getListType()
	{
		return listType;
	}

	public void setListType(long listType)
	{
		this.listType = listType;
	}

	@Id
	@Column(name = "SEQ_NO")
	public long getSeqNo()
	{
		return seqNo;
	}

	public void setSeqNo(long seqNo)
	{
		this.seqNo = seqNo;
	}

	@Basic
	@Column(name = "FIELD_NAME")
	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	@Basic
	@Column(name = "TITLE")
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Basic
	@Column(name = "VERIFY_SRVNAME")
	public String getVerifySrvname()
	{
		return verifySrvname;
	}

	public void setVerifySrvname(String verifySrvname)
	{
		this.verifySrvname = verifySrvname;
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
		BceBatInputFieldFormatEntity that = (BceBatInputFieldFormatEntity) o;
		return configId == that.configId &&
				listType == that.listType &&
				seqNo == that.seqNo &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(fieldName, that.fieldName) &&
				Objects.equals(title, that.title) &&
				Objects.equals(verifySrvname, that.verifySrvname) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(configId, moduleId, listType, seqNo, fieldName, title, verifySrvname, state);
	}
}
