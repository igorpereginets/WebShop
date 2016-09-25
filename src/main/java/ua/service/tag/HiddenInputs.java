package ua.service.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HiddenInputs extends SimpleTagSupport {
	
	private final StringWriter writer = new StringWriter();
	private final List<String> excludeParam = new ArrayList<>();
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		Map<String, String[]> parameters = getMapParameters(pageContext);
		
		for (Entry<String, String[]> entry : parameters.entrySet()) {
			if (!excludeParam.contains(entry.getKey()) && entry.getValue() != null)
				hideParam(entry);
		}
		
		out.println(writer.toString());
	}

	private Map<String, String[]> getMapParameters(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		return request.getParameterMap();
	}

	private void hideParam(Entry<String, String[]> values) {
		for (String value : values.getValue()) {
			writer.append("<input type='hidden' name='");
			writer.append(values.getKey());
			writer.append("' value='");
			writer.append(value);
			writer.append("'>");
		}
	}
	
	public void setExcludeParams(String excludeParams) {
		StringTokenizer tokenizer = new StringTokenizer(excludeParams, ", ");
		while (tokenizer.hasMoreTokens()) {
			String nextToken = tokenizer.nextToken();
			
			if(!nextToken.isEmpty())
				excludeParam.add(nextToken);
		}
	}
	
}
