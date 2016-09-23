package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.State;
import ua.service.StateService;

public class StateEditor extends PropertyEditorSupport {

	private StateService stateService;

	public StateEditor(StateService stateService) {
		this.stateService = stateService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		State state = stateService.findOne(Integer.parseInt(text));
		setValue(state);
	};
	
	
}
