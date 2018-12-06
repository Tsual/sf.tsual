package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_AREA_PAGETAB", schema = "BASE", catalog = "")
@IdClass(BceFrameAreaPagetabEntityPK.class)
public class BceFrameAreaPagetabEntity
{
	private long tabId;
	private long bceFrameId;
	private String areaId;
	private long moduleId;
	private String getparameter;
	private String width;
	private String height;
	private String tabType;
	private String vmfile;
	private String beforesettab;
	private String aftersettab;
	private long state;
	private String remarks;

	@Id
	@Column(name = "TAB_ID")
	public long getTabId()
	{
		return tabId;
	}

	public void setTabId(long tabId)
	{
		this.tabId = tabId;
	}

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
	@Column(name = "AREA_ID")
	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
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
	@Column(name = "GETPARAMETER")
	public String getGetparameter()
	{
		return getparameter;
	}

	public void setGetparameter(String getparameter)
	{
		this.getparameter = getparameter;
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
	@Column(name = "TAB_TYPE")
	public String getTabType()
	{
		return tabType;
	}

	public void setTabType(String tabType)
	{
		this.tabType = tabType;
	}

	@Basic
	@Column(name = "VMFILE")
	public String getVmfile()
	{
		return vmfile;
	}

	public void setVmfile(String vmfile)
	{
		this.vmfile = vmfile;
	}

	@Basic
	@Column(name = "BEFORESETTAB")
	public String getBeforesettab()
	{
		return beforesettab;
	}

	public void setBeforesettab(String beforesettab)
	{
		this.beforesettab = beforesettab;
	}

	@Basic
	@Column(name = "AFTERSETTAB")
	public String getAftersettab()
	{
		return aftersettab;
	}

	public void setAftersettab(String aftersettab)
	{
		this.aftersettab = aftersettab;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceFrameAreaPagetabEntity that = (BceFrameAreaPagetabEntity) o;
		return tabId == that.tabId &&
				bceFrameId == that.bceFrameId &&
				moduleId == that.moduleId &&
				state == that.state &&
				Objects.equals(areaId, that.areaId) &&
				Objects.equals(getparameter, that.getparameter) &&
				Objects.equals(width, that.width) &&
				Objects.equals(height, that.height) &&
				Objects.equals(tabType, that.tabType) &&
				Objects.equals(vmfile, that.vmfile) &&
				Objects.equals(beforesettab, that.beforesettab) &&
				Objects.equals(aftersettab, that.aftersettab) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(tabId, bceFrameId, areaId, moduleId, getparameter, width, height, tabType, vmfile, beforesettab, aftersettab, state, remarks);
	}
}
