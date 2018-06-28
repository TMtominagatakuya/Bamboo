package jp.tcmobile.bamboo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.tcmobile.bamboo.model.Authentication;
import jp.tcmobile.bamboo.repository.AuthenticationRepository;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Authentication authentication = authenticationRepository.findById(username).orElse(null);		

		if(authentication == null) {
			throw new UsernameNotFoundException(
					"No Employee present with employee no.:" + username);
		}
		return new UserDetail(authentication.getUser());
	}
}