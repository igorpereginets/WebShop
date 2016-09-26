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
		if (text == null || text.isEmpty()) {
			setValue(null);
			return;
		}
		State state = null;

		if (NumberUtils.isDigits(text))
			state = stateService.findOne(Integer.parseInt(text));
		else {
			state = stateService.findByName(text);
			if (state == null)
				state = stateService.save(new State(text));
		}

		setValue(state);
	};

}
