package com.nadyne.Akilahyz.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nadyne.Akilahyz.model.UserModel;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userEmail;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(UserModel user) {
		this.userEmail = user.getEmail();
		this.password = user.getPassword();
	}

	public UserDetailsImpl() {	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
