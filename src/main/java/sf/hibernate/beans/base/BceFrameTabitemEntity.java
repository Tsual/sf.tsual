package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_TABITEM", schema = "BASE", catalog = "")
public class BceFrameTabitemEntity
{
	private long tabItemId;
	private long tabId;
	private long moduleId;
	private String src;
	private String title;
	private String i18Nres;
	private String isdeletable;
	private String width;
	private String srcParams;
	private long isinitial;
	private String operator;
	private String mo;
	private long state;
	private String remarks;
	private String onclose;

	@Id
	@Column(name = "TAB_ITEM_ID")
	public long getTabItemId()
	{
		return tabItemId;
	}

	public void setTabItemId(long tabItemId)
	{
		this.tabItemId = tabItemId;
	}

	@Basic
	@Column(name = "TAB_ID")
	public long getTabId()
	{
		return tabId;
	}

	public void setTabId(long tabId)
	{
		this.tabId = tabId;
	}

	@Basic
	@Column(name = "MODULE_ID")
	public long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(long moduleId)
	{
		this.moduleId = moduleId;
	}

	@Basic
	@Column(name = "SRC")
	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
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
	@Column(name = "I18NRES")
	public String getI18Nres()
	{
		return i18Nres;
	}

	public void setI18Nres(String i18Nres)
	{
		this.i18Nres = i18Nres;
	}

	@Basic
	@Column(name = "ISDELETABLE")
	public String getIsdeletable()
	{
		return isdeletable;
	}

	public void setIsdeletable(String isdeletable)
	{
		this.isdeletable = isdeletable;
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
	@Column(name = "SRC_PARAMS")
	public String getSrcParams()
	{
		return srcParams;
	}

	public void setSrcParams(String srcParams)
	{
		this.srcParams = srcParams;
	}

	@Basic
	@Column(name = "ISINITIAL")
	public long getIsinitial()
	{
		return isinitial;
	}

	public void setIsinitial(long isinitial)
	{
		this.isinitial = isinitial;
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
	@Column(name = "STATE")
	public long getState()
	{
		return state;
	}

	public void setState(long state)
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

	@Basic
	@Column(name = "ONCLOSE")
	public String getOnclose()
	{
		return onclose;
	}

	public void setOnclose(String onclose)
	{
		this.onclose = onclose;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameTabitemEntity that = (BceFrameTabitemEntity) o;
		return tabItemId == that.tabItemId &&
				tabId == that.tabId &&
				moduleId == that.moduleId &&
				isinitial == that.isinitial &&
				state == that.state &&
				Objects.equals(src, that.src) &&
				Objects.equals(title, that.title) &&
				Objects.equals(i18Nres, that.i18Nres) &&
				Objects.equals(isdeletable, that.isdeletable) &&
				Objects.equals(width, that.width) &&
				Objects.equals(srcParams, that.srcParams) &&
				Objects.equals(operator, that.operator) &&
				Objects.equals(mo, that.mo) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(onclose, that.onclose);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(tabItemId, tabId, moduleId, src, title, i18Nres, isdeletable, width, srcParams, isinitial, operator, mo, state, remarks, onclose);
	}
}
