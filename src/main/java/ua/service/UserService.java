package ua.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.DTO.RegistrationForm;
import ua.DTO.FilterForms.adminFilter.UserFilterForm;
import ua.DTO.SaveForms.UserSaveForm;
import ua.entity.Role;
import ua.entity.User;
import ua.repository.UserRepository;
import ua.service.interfaces.FileWriter;
import ua.service.interfaces.FileWriter.Folder;
import ua.service.specification.adminFilterAdapter.UserFilterAdapter;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private FileWriter fileWriter;

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public User save(UserSaveForm userSaveForm) {
		if (userSaveForm == null)
			return null;
		User user = new User();
		user.setAddress(userSaveForm.getAddress());
		if (userSaveForm.getBirthday() != null)
			user.setBirthday(LocalDate.parse(userSaveForm.getBirthday()));
		user.setEmail(userSaveForm.getEmail());
		user.setFirstName(userSaveForm.getFirstName());
		user.setGender(userSaveForm.isGender());
		user.setId(userSaveForm.getId());
		user.setLastName(userSaveForm.getLastName());
		user.setLogin(userSaveForm.getLogin());
		user.setMoney(userSaveForm.getMoney());
		if (user.getId() == 0 && userSaveForm.getPassword() != null)
			user.setPassword(encoder.encode(userSaveForm.getPassword()));
		else
			user.setPassword(userSaveForm.getPassword());
		user.setRate(userSaveForm.getRate());
		user.setTelephone(userSaveForm.getTelephone());
		user.setRole(Role.ROLE_USER);
		user = userRepository.saveAndFlush(user);
		
		String pathToFile;
		if (userSaveForm.getId() == 0)
			pathToFile = fileWriter.save(Folder.USERS, userSaveForm.getFile(), user.getId());
		else if (userSaveForm.getFile().isEmpty())
			pathToFile = userSaveForm.getPathToFile();
		else
			pathToFile = fileWriter.update(Folder.USERS, userSaveForm.getFile(), user.getId());

		user.setPathToFile(pathToFile);
		
		return userRepository.save(user);
	}

	public UserSaveForm findWithSaveForm(int id) {
		User user = userRepository.findOneWithAddress(id);
		if (user == null)
			return null;

		UserSaveForm userSaveForm = new UserSaveForm();
		userSaveForm.setAddress(user.getAddress());
		if (user.getBirthday() != null)
			userSaveForm.setBirthday(user.getBirthday().toString());
		userSaveForm.setEmail(user.getEmail());
		userSaveForm.setFirstName(user.getFirstName());
		userSaveForm.setGender(user.isGender());
		userSaveForm.setId(user.getId());
		userSaveForm.setLastName(user.getLastName());
		userSaveForm.setLogin(user.getLogin());
		userSaveForm.setMoney(user.getMoney());
		userSaveForm.setPassword(user.getPassword());
		userSaveForm.setRate(user.getRate());
		userSaveForm.setTelephone(user.getTelephone());
		userSaveForm.setPathToFile(user.getPathToFile());
		return userSaveForm;
	}

	public void delete(int id) {
		File pathToFolder = fileWriter.getPathToFolder(Folder.USERS, id);
		try {
			FileUtils.deleteDirectory(pathToFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		userRepository.delete(id);
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<User> findAll(Pageable pageable, UserFilterForm filter) {
		return userRepository.findAll(new UserFilterAdapter(filter), pageable);
	}

	public User findByLogin(String login) {
		if (login == null || login.isEmpty())
			return null;
		return userRepository.findByLogin(login);
	}

	public User findByEmail(String email) {
		if (email == null || email.isEmpty())
			return null;
		return userRepository.findByEmail(email);
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User save(RegistrationForm registerUser) {
		if (registerUser == null)
			return null;
		User user = new User();
		user.setLogin(registerUser.getLogin());
		user.setEmail(registerUser.getEmail());
		user.setFirstName(registerUser.getFirstName());
		user.setLastName(registerUser.getLastName());
		if (registerUser.getPassword() != null) {
			String encodedPass = encoder.encode(registerUser.getPassword());
			user.setPassword(encodedPass);
		}
		user.setTelephone(registerUser.getTelephone());
		user.setRole(Role.ROLE_USER);
		user = userRepository.saveAndFlush(user);
		
		String pathToFile = fileWriter.save(Folder.USERS, null, user.getId());
		user.setPathToFile(pathToFile);
		
		return userRepository.save(user);
	}

	public User findByLoginWithAddress(String login) {
		return userRepository.findByLoginWithAddress(login);
	}

}
