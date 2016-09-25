package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.User;
import ua.service.UserService;

public class UserEditor extends PropertyEditorSupport {

	private final UserService userService;

	public UserEditor(UserService userService) {
		if (userService == null)
			throw new IllegalArgumentException("userService = null");
		this.userService = userService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			User user = userService.findOne(Integer.parseInt(text));
			setValue(user);
		} else
			setValue(null);
	}

}
