package in.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cdac.model.UserModel;
import in.cdac.repository.UserModelRepository;

@Service
public class UserModelService {

	@Autowired
	private UserModelRepository userModelRepository;
	
	
	
	public UserModel createUser(UserModel userModel) {
		userModelRepository.save(userModel);
		return userModel;
	}
	
	
	public UserModel readByUserName(String username) {
		return userModelRepository.findByUsername(username);
	}
	
}
