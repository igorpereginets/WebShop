package ua.service.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AllParams extends SimpleTagSupport {

	private final StringWriter writer = new StringWriter();
	private String change = "";
	private String value = "";
	private final static String AMPER = "&";
	private final static String QUEST = "?";
	private final static String EQUAL = "=";

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		PageContext pageContext = (PageContext) getJspContext();
		Map<String, String[]> map = getMapParameters(pageContext);
		
		out.println(getParametersWithChange(map));
	}

	private String getParametersWithChange(Map<String, String[]> map) {
		writer.append(QUEST);
		writer.append(change);
		writer.append(EQUAL);
		writer.append(value);

		for (Entry<String, String[]> entry : map.entrySet()) {
			for (String val : entry.getValue()) {
				String key = entry.getKey();
				if (!val.isEmpty() && !key.equals(change)) {
					writer.append(AMPER);
					writer.append(key);
					writer.append(EQUAL);
					writer.append(val);
				}
			}
		}

		return writer.toString();
	}
	
	private Map<String, String[]> getMapParameters(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		return request.getParameterMap();
	}
	
	public void setChange(String change) {
		this.change = change == null ? "" : change;
	}

	public void setValue(String value) {
		this.value = value == null ? "" : value;
	}

}
