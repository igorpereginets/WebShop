package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.State;
import ua.service.StateService;

public class StateEditor extends PropertyEditorSupport {

	private final StateService stateService;

	public StateEditor(StateService stateService) {
		if (stateService == null)
			throw new IllegalArgumentException("stateService = null");
		this.stateService = stateService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			State state = stateService.findOne(Integer.parseInt(text));
			setValue(state);
		} else
			setValue(null);
	};

}
