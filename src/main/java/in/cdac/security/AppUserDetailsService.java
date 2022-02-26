package in.cdac.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.cdac.model.UserModel;
import in.cdac.service.UserModelService;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserModelService userModelService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userModelService.readByUserName(username);
		
		if(userModel != null) {
			User user = new User(userModel.getUsername(), userModel.getPassword(), new ArrayList<>());
			return user;	
		} else {
			throw new UsernameNotFoundException("User doest not exist");
		}
		
	}

	
}
