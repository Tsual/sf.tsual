package sf.hibernate.beans.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_MODULE", schema = "BASE", catalog = "")
public class BceModuleEntity
{
	private long moduleId;
	private String moduleName;
	private String javaPackage;
	private String htmlDir;
	private String configDatasource;
	private String runDatasource;
	private String iconUrl;
	private String remarks;
	private Long state;

	@Id
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
	@Column(name = "MODULE_NAME")
	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	@Basic
	@Column(name = "JAVA_PACKAGE")
	public String getJavaPackage()
	{
		return javaPackage;
	}

	public void setJavaPackage(String javaPackage)
	{
		this.javaPackage = javaPackage;
	}

	@Basic
	@Column(name = "HTML_DIR")
	public String getHtmlDir()
	{
		return htmlDir;
	}

	public void setHtmlDir(String htmlDir)
	{
		this.htmlDir = htmlDir;
	}

	@Basic
	@Column(name = "CONFIG_DATASOURCE")
	public String getConfigDatasource()
	{
		return configDatasource;
	}

	public void setConfigDatasource(String configDatasource)
	{
		this.configDatasource = configDatasource;
	}

	@Basic
	@Column(name = "RUN_DATASOURCE")
	public String getRunDatasource()
	{
		return runDatasource;
	}

	public void setRunDatasource(String runDatasource)
	{
		this.runDatasource = runDatasource;
	}

	@Basic
	@Column(name = "ICON_URL")
	public String getIconUrl()
	{
		return iconUrl;
	}

	public void setIconUrl(String iconUrl)
	{
		this.iconUrl = iconUrl;
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
		BceModuleEntity that = (BceModuleEntity) o;
		return moduleId == that.moduleId &&
				Objects.equals(moduleName, that.moduleName) &&
				Objects.equals(javaPackage, that.javaPackage) &&
				Objects.equals(htmlDir, that.htmlDir) &&
				Objects.equals(configDatasource, that.configDatasource) &&
				Objects.equals(runDatasource, that.runDatasource) &&
				Objects.equals(iconUrl, that.iconUrl) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(state, that.state);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(moduleId, moduleName, javaPackage, htmlDir, configDatasource, runDatasource, iconUrl, remarks, state);
	}
}
