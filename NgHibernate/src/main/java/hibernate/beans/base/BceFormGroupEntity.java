package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_FORM_GROUP", schema = "BASE", catalog = "")
public class BceFormGroupEntity
{
	private long groupId;
	private Long bceFrameId;
	private String formId;
	private Long moduleId;
	private String groupName;
	private Long seqNo;
	private Long isAllowStract;
	private Long isClosed;
	private String groupStyle;
	private String attr1;
	private String attr2;
	private String attr3;
	private String attr4;
	private String attr5;
	private Long state;

	@Id
	@Column(name = "GROUP_ID")
	public long getGroupId()
	{
		return groupId;
	}

	public void setGroupId(long groupId)
	{
		this.groupId = groupId;
	}

	@Basic
	@Column(name = "BCE_FRAME_ID")
	public Long getBceFrameId()
	{
		return bceFrameId;
	}

	public void setBceFrameId(Long bceFrameId)
	{
		this.bceFrameId = bceFrameId;
	}

	@Basic
	@Column(name = "FORM_ID")
	public String getFormId()
	{
		return formId;
	}

	public void setFormId(String formId)
	{
		this.formId = formId;
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
	@Column(name = "GROUP_NAME")
	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
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
	@Column(name = "IS_ALLOW_STRACT")
	public Long getIsAllowStract()
	{
		return isAllowStract;
	}

	public void setIsAllowStract(Long isAllowStract)
	{
		this.isAllowStract = isAllowStract;
	}

	@Basic
	@Column(name = "IS_CLOSED")
	public Long getIsClosed()
	{
		return isClosed;
	}

	public void setIsClosed(Long isClosed)
	{
		this.isClosed = isClosed;
	}

	@Basic
	@Column(name = "GROUP_STYLE")
	public String getGroupStyle()
	{
		return groupStyle;
	}

	public void setGroupStyle(String groupStyle)
	{
		this.groupStyle = groupStyle;
	}

	@Basic
	@Column(name = "ATTR_1")
	public String getAttr1()
	{
		return attr1;
	}

	public void setAttr1(String attr1)
	{
		this.attr1 = attr1;
	}

	@Basic
	@Column(name = "ATTR_2")
	public String getAttr2()
	{
		return attr2;
	}

	public void setAttr2(String attr2)
	{
		this.attr2 = attr2;
	}

	@Basic
	@Column(name = "ATTR_3")
	public String getAttr3()
	{
		return attr3;
	}

	public void setAttr3(String attr3)
	{
		this.attr3 = attr3;
	}

	@Basic
	@Column(name = "ATTR_4")
	public String getAttr4()
	{
		return attr4;
	}

	public void setAttr4(String attr4)
	{
		this.attr4 = attr4;
	}

	@Basic
	@Column(name = "ATTR_5")
	public String getAttr5()
	{
		return attr5;
	}

	public void setAttr5(String attr5)
	{
		this.attr5 = attr5;
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
		BceFormGroupEntity that = (BceFormGroupEntity) o;
		return groupId == that.groupId &&
				Objects.equals(bceFrameId, that.bceFrameId) &&
				Objects.equals(formId, that.formId) &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(groupName, that.groupName) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(isAllowStract, that.isAllowStract) &&
				Objects.equals(isClosed, that.isClosed) &&
				Objects.equals(groupStyle, that.groupStyle) &&
				Objects.equals(attr1, that.attr1) &&
				Objects.equals(attr2, that.attr2) &&
				Objects.equals(attr3, that.attr3) &&
				Objects.equals(attr4, that.attr4) &&
				Objects.equals(attr5, that.attr5) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(groupId, bceFrameId, formId, moduleId, groupName, seqNo, isAllowStract, isClosed, groupStyle, attr1, attr2, attr3, attr4, attr5, state);
	}
}
