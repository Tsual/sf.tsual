package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_CONFTOOL_MENU", schema = "BASE", catalog = "")
public class BceConftoolMenuEntity
{
	private long menuId;
	private String menuName;
	private String menuIcon;
	private String menuUrl;
	private String pageTitle;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private Long state;

	@Id
	@Column(name = "MENU_ID")
	public long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(long menuId)
	{
		this.menuId = menuId;
	}

	@Basic
	@Column(name = "MENU_NAME")
	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	@Basic
	@Column(name = "MENU_ICON")
	public String getMenuIcon()
	{
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon)
	{
		this.menuIcon = menuIcon;
	}

	@Basic
	@Column(name = "MENU_URL")
	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	@Basic
	@Column(name = "PAGE_TITLE")
	public String getPageTitle()
	{
		return pageTitle;
	}

	public void setPageTitle(String pageTitle)
	{
		this.pageTitle = pageTitle;
	}

	@Basic
	@Column(name = "EXT1")
	public String getExt1()
	{
		return ext1;
	}

	public void setExt1(String ext1)
	{
		this.ext1 = ext1;
	}

	@Basic
	@Column(name = "EXT2")
	public String getExt2()
	{
		return ext2;
	}

	public void setExt2(String ext2)
	{
		this.ext2 = ext2;
	}

	@Basic
	@Column(name = "EXT3")
	public String getExt3()
	{
		return ext3;
	}

	public void setExt3(String ext3)
	{
		this.ext3 = ext3;
	}

	@Basic
	@Column(name = "EXT4")
	public String getExt4()
	{
		return ext4;
	}

	public void setExt4(String ext4)
	{
		this.ext4 = ext4;
	}

	@Basic
	@Column(name = "EXT5")
	public String getExt5()
	{
		return ext5;
	}

	public void setExt5(String ext5)
	{
		this.ext5 = ext5;
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
		BceConftoolMenuEntity that = (BceConftoolMenuEntity) o;
		return menuId == that.menuId &&
				Objects.equals(menuName, that.menuName) &&
				Objects.equals(menuIcon, that.menuIcon) &&
				Objects.equals(menuUrl, that.menuUrl) &&
				Objects.equals(pageTitle, that.pageTitle) &&
				Objects.equals(ext1, that.ext1) &&
				Objects.equals(ext2, that.ext2) &&
				Objects.equals(ext3, that.ext3) &&
				Objects.equals(ext4, that.ext4) &&
				Objects.equals(ext5, that.ext5) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(menuId, menuName, menuIcon, menuUrl, pageTitle, ext1, ext2, ext3, ext4, ext5, state);
	}
}
