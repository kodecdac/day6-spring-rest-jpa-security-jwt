package in.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cdac.model.UserModel;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long>{

	UserModel findByUsername(String username);
}
