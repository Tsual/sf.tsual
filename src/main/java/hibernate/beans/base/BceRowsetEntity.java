package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_ROWSET", schema = "BASE", catalog = "")
public class BceRowsetEntity
{
	private long rowsetId;
	private Long moduleId;
	private long rowsetType;
	private String rowsetKey;
	private String rowsetMethod;
	private Long state;
	private String remarks;

	@Id
	@Column(name = "ROWSET_ID")
	public long getRowsetId()
	{
		return rowsetId;
	}

	public void setRowsetId(long rowsetId)
	{
		this.rowsetId = rowsetId;
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
	@Column(name = "ROWSET_TYPE")
	public long getRowsetType()
	{
		return rowsetType;
	}

	public void setRowsetType(long rowsetType)
	{
		this.rowsetType = rowsetType;
	}

	@Basic
	@Column(name = "ROWSET_KEY")
	public String getRowsetKey()
	{
		return rowsetKey;
	}

	public void setRowsetKey(String rowsetKey)
	{
		this.rowsetKey = rowsetKey;
	}

	@Basic
	@Column(name = "ROWSET_METHOD")
	public String getRowsetMethod()
	{
		return rowsetMethod;
	}

	public void setRowsetMethod(String rowsetMethod)
	{
		this.rowsetMethod = rowsetMethod;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceRowsetEntity that = (BceRowsetEntity) o;
		return rowsetId == that.rowsetId &&
				rowsetType == that.rowsetType &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(rowsetKey, that.rowsetKey) &&
				Objects.equals(rowsetMethod, that.rowsetMethod) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(rowsetId, moduleId, rowsetType, rowsetKey, rowsetMethod, state, remarks);
	}
}
