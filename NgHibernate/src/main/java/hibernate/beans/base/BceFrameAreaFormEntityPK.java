package hibernate.beans.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceFrameAreaFormEntityPK implements Serializable
{
	private long bceFrameId;
	private String formId;

	@Column(name = "BCE_FRAME_ID")
	@Id
	public long getBceFrameId()
	{
		return bceFrameId;
	}

	public void setBceFrameId(long bceFrameId)
	{
		this.bceFrameId = bceFrameId;
	}

	@Column(name = "FORM_ID")
	@Id
	public String getFormId()
	{
		return formId;
	}

	public void setFormId(String formId)
	{
		this.formId = formId;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameAreaFormEntityPK that = (BceFrameAreaFormEntityPK) o;
		return bceFrameId == that.bceFrameId &&
				Objects.equals(formId, that.formId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, formId);
	}
}
