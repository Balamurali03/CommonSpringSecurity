package com.project.appAuthunticate.JwtService;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.appAuthunticate.JwtEntity.JwtEntity;
import com.project.appAuthunticate.JwtRepo.JwtRepo;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private JwtRepo repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("got into loadUserByUsername method");
		JwtEntity userData = repo.findByEmail(email).get();
		if(userData == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new CustomUserDetails(userData);
	}
}





 class CustomUserDetails implements UserDetails {

   
	private static final long serialVersionUID = 1L;
	private JwtEntity user;

    public CustomUserDetails(JwtEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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

