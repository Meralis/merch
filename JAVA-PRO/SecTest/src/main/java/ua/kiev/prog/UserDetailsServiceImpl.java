package ua.kiev.prog;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<CustomUser> dbUser = userService.findByLogin(login);
        if (dbUser.isEmpty()) {
            throw new UsernameNotFoundException(login + " not found");
        }
        CustomUser user = dbUser.get();

        List<GrantedAuthority> roles = Arrays.asList(
                new SimpleGrantedAuthority(user.getRole().toString())
        );

        return new User(user.getLogin(), user.getPassword(), roles);
    }
}