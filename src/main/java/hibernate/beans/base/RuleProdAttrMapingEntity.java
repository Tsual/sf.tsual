package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RULE_PROD_ATTR_MAPING", schema = "BASE", catalog = "")
public class RuleProdAttrMapingEntity
{
	private long mapingKey;
	private String attrCode;
	private String attrId;
	private String offerId;
	private String productId;
	private String state;
	private String remarks;

	@Id
	@Column(name = "MAPING_KEY")
	public long getMapingKey()
	{
		return mapingKey;
	}

	public void setMapingKey(long mapingKey)
	{
		this.mapingKey = mapingKey;
	}

	@Basic
	@Column(name = "ATTR_CODE")
	public String getAttrCode()
	{
		return attrCode;
	}

	public void setAttrCode(String attrCode)
	{
		this.attrCode = attrCode;
	}

	@Basic
	@Column(name = "ATTR_ID")
	public String getAttrId()
	{
		return attrId;
	}

	public void setAttrId(String attrId)
	{
		this.attrId = attrId;
	}

	@Basic
	@Column(name = "OFFER_ID")
	public String getOfferId()
	{
		return offerId;
	}

	public void setOfferId(String offerId)
	{
		this.offerId = offerId;
	}

	@Basic
	@Column(name = "PRODUCT_ID")
	public String getProductId()
	{
		return productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
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
		RuleProdAttrMapingEntity that = (RuleProdAttrMapingEntity) o;
		return mapingKey == that.mapingKey &&
				Objects.equals(attrCode, that.attrCode) &&
				Objects.equals(attrId, that.attrId) &&
				Objects.equals(offerId, that.offerId) &&
				Objects.equals(productId, that.productId) &&
				Objects.equals(state, that.state) &&
				Objects.equals(remarks, that.remarks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(mapingKey, attrCode, attrId, offerId, productId, state, remarks);
	}
}
