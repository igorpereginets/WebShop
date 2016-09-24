package ua.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.UserFilterForm;
import ua.DTO.SaveForms.UserSaveForm;
import ua.entity.Role;
import ua.entity.User;
import ua.repository.UserRepository;
import ua.service.specification.adminFilterAdapter.UserFilterAdapter;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public User save(UserSaveForm userSaveForm) {
		User user = new User();
		user.setAddress(userSaveForm.getAddress());
		user.setBirthday(LocalDate.parse(userSaveForm.getBirthday()));
		user.setEmail(userSaveForm.getEmail());
		user.setFirstName(userSaveForm.getFirstName());
		user.setGender(userSaveForm.isGender());
		user.setId(userSaveForm.getId());
		user.setLastName(userSaveForm.getLastName());
		user.setLogin(userSaveForm.getLogin());
		user.setMoney(userSaveForm.getMoney());
		user.setPassword(userSaveForm.getPassword());
		user.setRate(userSaveForm.getRate());
		user.setTelephone(userSaveForm.getTelephone());
		user.setRole(Role.ROLE_USER);
		return userRepository.save(user);
	}
	
	public UserSaveForm findWithSaveForm(int id) {
		User user = userRepository.findOneWithAddress(id);
		UserSaveForm userSaveForm = new UserSaveForm();
		userSaveForm.setAddress(user.getAddress());
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
		return userSaveForm;
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<User> findAll(Pageable pageable, UserFilterForm filter) {
		return userRepository.findAll(new UserFilterAdapter(filter), pageable);
	}

	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}
}
