package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_BUTTON", schema = "BASE", catalog = "")
@IdClass(BceFrameButtonEntityPK.class)
public class BceFrameButtonEntity
{
	private long bceFrameId;
	private Long moduleId;
	private String areaId;
	private long buttonId;
	private String eventClick;
	private Long seqNo;
	private String enable;
	private String mo;
	private String operator;
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
	@Column(name = "AREA_ID")
	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	@Id
	@Column(name = "BUTTON_ID")
	public long getButtonId()
	{
		return buttonId;
	}

	public void setButtonId(long buttonId)
	{
		this.buttonId = buttonId;
	}

	@Basic
	@Column(name = "EVENT_CLICK")
	public String getEventClick()
	{
		return eventClick;
	}

	public void setEventClick(String eventClick)
	{
		this.eventClick = eventClick;
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
	@Column(name = "ENABLE")
	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
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
		BceFrameButtonEntity that = (BceFrameButtonEntity) o;
		return bceFrameId == that.bceFrameId &&
				buttonId == that.buttonId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(areaId, that.areaId) &&
				Objects.equals(eventClick, that.eventClick) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(enable, that.enable) &&
				Objects.equals(mo, that.mo) &&
				Objects.equals(operator, that.operator) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, moduleId, areaId, buttonId, eventClick, seqNo, enable, mo, operator, state);
	}
}
