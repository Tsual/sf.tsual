package hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_SIMPLE_FUNC", schema = "BASE", catalog = "")
public class BceSimpleFuncEntity
{
	private long funcId;
	private Long bceFrameId;
	private Long moduleId;
	private String rowsetName;
	private String ordDatasource;
	private String ordBo;
	private String insDatasource;
	private String insBo;
	private String dealService;
	private String dealWorkflow;
	private Long state;

	@Id
	@Column(name = "FUNC_ID")
	public long getFuncId()
	{
		return funcId;
	}

	public void setFuncId(long funcId)
	{
		this.funcId = funcId;
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
	@Column(name = "ROWSET_NAME")
	public String getRowsetName()
	{
		return rowsetName;
	}

	public void setRowsetName(String rowsetName)
	{
		this.rowsetName = rowsetName;
	}

	@Basic
	@Column(name = "ORD_DATASOURCE")
	public String getOrdDatasource()
	{
		return ordDatasource;
	}

	public void setOrdDatasource(String ordDatasource)
	{
		this.ordDatasource = ordDatasource;
	}

	@Basic
	@Column(name = "ORD_BO")
	public String getOrdBo()
	{
		return ordBo;
	}

	public void setOrdBo(String ordBo)
	{
		this.ordBo = ordBo;
	}

	@Basic
	@Column(name = "INS_DATASOURCE")
	public String getInsDatasource()
	{
		return insDatasource;
	}

	public void setInsDatasource(String insDatasource)
	{
		this.insDatasource = insDatasource;
	}

	@Basic
	@Column(name = "INS_BO")
	public String getInsBo()
	{
		return insBo;
	}

	public void setInsBo(String insBo)
	{
		this.insBo = insBo;
	}

	@Basic
	@Column(name = "DEAL_SERVICE")
	public String getDealService()
	{
		return dealService;
	}

	public void setDealService(String dealService)
	{
		this.dealService = dealService;
	}

	@Basic
	@Column(name = "DEAL_WORKFLOW")
	public String getDealWorkflow()
	{
		return dealWorkflow;
	}

	public void setDealWorkflow(String dealWorkflow)
	{
		this.dealWorkflow = dealWorkflow;
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
		BceSimpleFuncEntity that = (BceSimpleFuncEntity) o;
		return funcId == that.funcId &&
				Objects.equals(bceFrameId, that.bceFrameId) &&
				Objects.equals(moduleId, that.moduleId) &&
				Objects.equals(rowsetName, that.rowsetName) &&
				Objects.equals(ordDatasource, that.ordDatasource) &&
				Objects.equals(ordBo, that.ordBo) &&
				Objects.equals(insDatasource, that.insDatasource) &&
				Objects.equals(insBo, that.insBo) &&
				Objects.equals(dealService, that.dealService) &&
				Objects.equals(dealWorkflow, that.dealWorkflow) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(funcId, bceFrameId, moduleId, rowsetName, ordDatasource, ordBo, insDatasource, insBo, dealService, dealWorkflow, state);
	}
}
