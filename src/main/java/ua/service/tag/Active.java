package ua.service.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Active extends SimpleTagSupport {

	private String key = "";
	private String value = "";

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter writer = pageContext.getOut();
		Map<String, String[]> parameters = getMapParameters(pageContext);

		String[] values = parameters.get(key);
		if (values != null)
			for (String v : values) {
				if (value.equals(v))
					writer.print("class=\"active\"");
			}
	}

	private Map<String, String[]> getMapParameters(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		return request.getParameterMap();
	}

	public void setKey(String key) {
		this.key = key == null ? "" : key;
	}

	public void setValue(String value) {
		this.value = value == null ? "" : value;
	}

}
