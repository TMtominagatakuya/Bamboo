package jp.tcmobile.bamboo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.tcmobile.bamboo.model.Authorization;
import jp.tcmobile.bamboo.model.Authorization.Role;
import jp.tcmobile.bamboo.model.Test;
import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UserRepository userRepository;

	public void saveTest(User user) {
		userRepository.saveAndFlush(user);
	}
	
	public User emptyUser() {
		User user = new User();
		//Authorizationを読み込み、userにsetする。そのauthrizatinoにuserをセットする。
		//Authenticationを読み込み、userとつなげる
		return user;
	}
	
}
