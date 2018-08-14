package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BS_I18N_RESOURCE", schema = "BASE", catalog = "")
public class BsI18NResourceEntity
{
	private String resKey;
	private String zhCn;
	private String enUs;
	private String state;
	private String remarks;

	@Id
	@Column(name = "RES_KEY")
	public String getResKey()
	{
		return resKey;
	}

	public void setResKey(String resKey)
	{
		this.resKey = resKey;
	}

	@Basic
	@Column(name = "ZH_CN")
	public String getZhCn()
	{
		return zhCn;
	}

	public void setZhCn(String zhCn)
	{
		this.zhCn = zhCn;
	}

	@Basic
	@Column(name = "EN_US")
	public String getEnUs()
	{
		return enUs;
	}

	public void setEnUs(String enUs)
	{
		this.enUs = enUs;
	}

	@Basic
	@Column(name = "STATE")
	public String getState()
	{
		return state;
	}

	public void setState(String state)
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
		BsI18NResourceEntity that = (BsI18NResourceEntity) o;
		return Objects.equals(resKey, that.resKey) &&
				Objects.equals(zhCn, that.zhCn) &&
				Objects.equals(enUs, that.enUs) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(resKey, zhCn, enUs, state, remarks);
	}
}
