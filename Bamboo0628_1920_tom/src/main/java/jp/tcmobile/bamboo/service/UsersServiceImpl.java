package jp.tcmobile.bamboo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UserRepository userRepository;

	public void saveTest(User user) {
		userRepository.saveAndFlush(user);
	}
	
}
