package sf.hibernate.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_BUTTON", schema = "BASE", catalog = "")
public class BceButtonEntity
{
	private long buttonId;
	private Long moduleId;
	private String buttonCode;
	private String buttonName;
	private String text;
	private String i18NRes;
	private String width;
	private String eventClick;
	private Long state;

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
	@Column(name = "BUTTON_CODE")
	public String getButtonCode()
	{
		return buttonCode;
	}

	public void setButtonCode(String buttonCode)
	{
		this.buttonCode = buttonCode;
	}

	@Basic
	@Column(name = "BUTTON_NAME")
	public String getButtonName()
	{
		return buttonName;
	}

	public void setButtonName(String buttonName)
	{
		this.buttonName = buttonName;
	}

	@Basic
	@Column(name = "TEXT")
	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
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
		BceButtonEntity that = (BceButtonEntity) o;
		return buttonId == that.buttonId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(buttonCode, that.buttonCode) &&
				Objects.equals(buttonName, that.buttonName) &&
				Objects.equals(text, that.text) &&
				Objects.equals(i18NRes, that.i18NRes) &&
				Objects.equals(width, that.width) &&
				Objects.equals(eventClick, that.eventClick) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(buttonId, moduleId, buttonCode, buttonName, text, i18NRes, width, eventClick, state);
	}
}
