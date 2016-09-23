package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.Status;
import ua.service.StatusService;

public class StatusEditor extends PropertyEditorSupport {

	private StatusService statusService;
	
	public StatusEditor(StatusService statusService) {
		this.statusService = statusService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Status status = statusService.findOne(Integer.parseInt(text));
		setValue(status);
	}

	
}
