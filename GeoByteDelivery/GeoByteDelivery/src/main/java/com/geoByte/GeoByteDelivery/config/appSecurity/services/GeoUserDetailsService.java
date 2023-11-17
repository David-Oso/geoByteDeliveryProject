package com.geoByte.GeoByteDelivery.config.appSecurity.services;

import com.geoByte.GeoByteDelivery.config.appSecurity.user.SecuredUser;
import com.geoByte.GeoByteDelivery.data.model.User;
import com.geoByte.GeoByteDelivery.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User name not found"));
        return new SecuredUser(user);
    }
}
