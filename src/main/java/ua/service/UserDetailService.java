package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.entity.User;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("-----------------------------------------" + username + "-----------------------------------------");
		User user = userService.findByLogin(username);
		print(user);
		return user;
	}

	private void print(User user) {
		System.out.println(user.getId());
		System.out.println(user.getEmail());
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getLogin());
		System.out.println(user.getPassword());
	}

}
