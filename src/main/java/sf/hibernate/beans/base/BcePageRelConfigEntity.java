package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_PAGE_REL_CONFIG", schema = "BASE", catalog = "")
public class BcePageRelConfigEntity
{
	private long pageRelId;
	private long relateObjId;
	private Long pageId;
	private String templateId;
	private Long seqNo;
	private long preRelateId;
	private String relateType;
	private long state;
	private String remarks;

	@Id
	@Column(name = "PAGE_REL_ID")
	public long getPageRelId()
	{
		return pageRelId;
	}

	public void setPageRelId(long pageRelId)
	{
		this.pageRelId = pageRelId;
	}

	@Basic
	@Column(name = "RELATE_OBJ_ID")
	public long getRelateObjId()
	{
		return relateObjId;
	}

	public void setRelateObjId(long relateObjId)
	{
		this.relateObjId = relateObjId;
	}

	@Basic
	@Column(name = "PAGE_ID")
	public Long getPageId()
	{
		return pageId;
	}

	public void setPageId(Long pageId)
	{
		this.pageId = pageId;
	}

	@Basic
	@Column(name = "TEMPLATE_ID")
	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
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
	@Column(name = "PRE_RELATE_ID")
	public long getPreRelateId()
	{
		return preRelateId;
	}

	public void setPreRelateId(long preRelateId)
	{
		this.preRelateId = preRelateId;
	}

	@Basic
	@Column(name = "RELATE_TYPE")
	public String getRelateType()
	{
		return relateType;
	}

	public void setRelateType(String relateType)
	{
		this.relateType = relateType;
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
		BcePageRelConfigEntity that = (BcePageRelConfigEntity) o;
		return pageRelId == that.pageRelId &&
				relateObjId == that.relateObjId &&
				preRelateId == that.preRelateId &&
				state == that.state &&
				Objects.equals(pageId, that.pageId) &&
				Objects.equals(templateId, that.templateId) &&
				Objects.equals(seqNo, that.seqNo) &&
				Objects.equals(relateType, that.relateType) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pageRelId, relateObjId, pageId, templateId, seqNo, preRelateId, relateType, state, remarks);
	}
}
