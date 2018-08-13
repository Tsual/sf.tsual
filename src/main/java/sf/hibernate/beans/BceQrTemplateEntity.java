package sf.hibernate.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BCE_QR_TEMPLATE", schema = "BASE", catalog = "")
public class BceQrTemplateEntity
{
	private long templateId;
	private String templateName;
	private String contentClass;
	private String filePath;
	private boolean state;
	private String remarks;
	private String jsFile;
	private String jsFunction;

	@Id
	@Column(name = "TEMPLATE_ID")
	public long getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(long templateId)
	{
		this.templateId = templateId;
	}

	@Basic
	@Column(name = "TEMPLATE_NAME")
	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	@Basic
	@Column(name = "CONTENT_CLASS")
	public String getContentClass()
	{
		return contentClass;
	}

	public void setContentClass(String contentClass)
	{
		this.contentClass = contentClass;
	}

	@Basic
	@Column(name = "FILE_PATH")
	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	@Basic
	@Column(name = "STATE")
	public boolean isState()
	{
		return state;
	}

	public void setState(boolean state)
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

	@Basic
	@Column(name = "JS_FILE")
	public String getJsFile()
	{
		return jsFile;
	}

	public void setJsFile(String jsFile)
	{
		this.jsFile = jsFile;
	}

	@Basic
	@Column(name = "JS_FUNCTION")
	public String getJsFunction()
	{
		return jsFunction;
	}

	public void setJsFunction(String jsFunction)
	{
		this.jsFunction = jsFunction;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BceQrTemplateEntity that = (BceQrTemplateEntity) o;
		return templateId == that.templateId &&
				state == that.state &&
				Objects.equals(templateName, that.templateName) &&
				Objects.equals(contentClass, that.contentClass) &&
				Objects.equals(filePath, that.filePath) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(jsFile, that.jsFile) &&
				Objects.equals(jsFunction, that.jsFunction);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(templateId, templateName, contentClass, filePath, state, remarks, jsFile, jsFunction);
	}
}
