package hibernate.beans.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceFrameButtonEntityPK implements Serializable
{
	private long bceFrameId;
	private String areaId;
	private long buttonId;

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

	@Column(name = "AREA_ID")
	@Id
	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	@Column(name = "BUTTON_ID")
	@Id
	public long getButtonId()
	{
		return buttonId;
	}

	public void setButtonId(long buttonId)
	{
		this.buttonId = buttonId;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameButtonEntityPK that = (BceFrameButtonEntityPK) o;
		return bceFrameId == that.bceFrameId &&
				buttonId == that.buttonId &&
				Objects.equals(areaId, that.areaId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, areaId, buttonId);
	}
}
