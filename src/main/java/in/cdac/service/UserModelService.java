package in.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cdac.model.UserModel;
import in.cdac.repository.UserModelRepository;

import java.util.List;
import java.util.Optional;

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

	public UserModel readById(long id) {
		return userModelRepository.findById(id).get();
	}


	public List<UserModel> readAllUser() {
		return userModelRepository.findAll();
	}

	public void deleteById(long id) {
		userModelRepository.deleteById(id);
	}

	public void updateById(long id, UserModel userModel) {
		Optional<UserModel> user = userModelRepository.findById(id);

		if(user.isPresent()) {
			UserModel dbUser =  user.get();

			dbUser.setEmail(userModel.getEmail());
			dbUser.setPassword(userModel.getPassword());
			dbUser.setMobile(userModel.getMobile());

			userModelRepository.saveAndFlush(dbUser);
		}

	}

}
