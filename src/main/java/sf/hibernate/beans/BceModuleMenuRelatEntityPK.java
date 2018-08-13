package sf.hibernate.beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BceModuleMenuRelatEntityPK implements Serializable
{
	private long moduleId;
	private long menuId;

	@Column(name = "MODULE_ID")
	@Id
	public long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(long moduleId)
	{
		this.moduleId = moduleId;
	}

	@Column(name = "MENU_ID")
	@Id
	public long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(long menuId)
	{
		this.menuId = menuId;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceModuleMenuRelatEntityPK that = (BceModuleMenuRelatEntityPK) o;
		return moduleId == that.moduleId &&
				menuId == that.menuId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(moduleId, menuId);
	}
}
