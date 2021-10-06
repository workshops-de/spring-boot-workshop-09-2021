package de.workshops.bookdemo.config;

import static de.workshops.bookdemo.generated.public_.Tables.USERS;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.workshops.bookdemo.user.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private DSLContext jooq;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return jooq.select(USERS.fields()).from(USERS).where(USERS.USERNAME.eq(username)).fetchSingleInto(User.class);
	}

}
