package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FRAME_PAGE_ROLE", schema = "BASE", catalog = "")
@IdClass(BceFramePageRoleEntityPK.class)
public class BceFramePageRoleEntity
{
	private long bceFrameId;
	private Long moduleId;
	private long pageFramePageId;
	private long roleId;
	private Long maxNum;
	private Long seqNo;
	private Long state;
	private String remarks;

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
	@Column(name = "PAGE_FRAME_PAGE_ID")
	public long getPageFramePageId()
	{
		return pageFramePageId;
	}

	public void setPageFramePageId(long pageFramePageId)
	{
		this.pageFramePageId = pageFramePageId;
	}

	@Id
	@Column(name = "ROLE_ID")
	public long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(long roleId)
	{
		this.roleId = roleId;
	}

	@Basic
	@Column(name = "MAX_NUM")
	public Long getMaxNum()
	{
		return maxNum;
	}

	public void setMaxNum(Long maxNum)
	{
		this.maxNum = maxNum;
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
	@Column(name = "STATE")
	public Long getState()
	{
		return state;
	}

	public void setState(Long state)
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
		BceFramePageRoleEntity that = (BceFramePageRoleEntity) o;
		return bceFrameId == that.bceFrameId &&
				pageFramePageId == that.pageFramePageId &&
				roleId == that.roleId &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(maxNum, that.maxNum) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(bceFrameId, moduleId, pageFramePageId, roleId, maxNum, seqNo, state, remarks);
	}
}
