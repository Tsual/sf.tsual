package sf.hibernate.beans.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceBatInputFieldFormatEntityPK implements Serializable
{
	private long configId;
	private long listType;
	private long seqNo;

	@Column(name = "CONFIG_ID")
	@Id
	public long getConfigId()
	{
		return configId;
	}

	public void setConfigId(long configId)
	{
		this.configId = configId;
	}

	@Column(name = "LIST_TYPE")
	@Id
	public long getListType()
	{
		return listType;
	}

	public void setListType(long listType)
	{
		this.listType = listType;
	}

	@Column(name = "SEQ_NO")
	@Id
	public long getSeqNo()
	{
		return seqNo;
	}

	public void setSeqNo(long seqNo)
	{
		this.seqNo = seqNo;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceBatInputFieldFormatEntityPK that = (BceBatInputFieldFormatEntityPK) o;
		return configId == that.configId &&
				listType == that.listType &&
				seqNo == that.seqNo;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(configId, listType, seqNo);
	}
}
