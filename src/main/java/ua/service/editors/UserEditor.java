package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.User;
import ua.service.UserService;

public class UserEditor extends PropertyEditorSupport {

	private UserService userService;

	public UserEditor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user = userService.findOne(Integer.parseInt(text));
		setValue(user);
	}
	
	
}
