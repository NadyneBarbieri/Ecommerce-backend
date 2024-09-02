package com.nadyne.Akilahyz.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nadyne.Akilahyz.model.UserModel;
import com.nadyne.Akilahyz.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		
		Optional<UserModel> email = userRepository.findByEmail(userName);	
	  
		email.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return email.map(UserDetailsImpl::new).get();
	}
}
