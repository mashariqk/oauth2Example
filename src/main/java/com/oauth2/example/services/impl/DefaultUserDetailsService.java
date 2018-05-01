package com.oauth2.example.services.impl;

import com.oauth2.example.dtos.UserDTO;
import com.oauth2.example.entities.Role;
import com.oauth2.example.entities.User;
import com.oauth2.example.repositories.RoleRepository;
import com.oauth2.example.repositories.UserRepository;
import com.oauth2.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService, UserService {

	@Autowired
	private PasswordEncoder userPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Resource(name = "roleRepository")
	private RoleRepository roleRepository;
	@Autowired
	private ConversionService conversionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findOneByUsername(username);
	}

	@Override
	@Transactional
	public boolean createNewUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userPasswordEncoder.encode(userDTO.getPassword()));
		Role customerRole = roleRepository.findOneByRole("ROLE_CUSTOMER");
		Set<Role> roles = new HashSet<>();
		roles.add(customerRole);
		user.setEnabled(false);
		user.setRoles(roles);
		userRepository.save(user);
		return true;
	}


	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserDTO> fetchByExample(UserDTO userDTO) {
		Example<User> example = Example.of(conversionService.convert(userDTO,User.class));
		List<User> users =  userRepository.findAll(example);
		ArrayList<UserDTO> userDtos = new ArrayList<>();
		for (User user: users){
			userDtos.add(conversionService.convert(user,UserDTO.class));
		}
		return userDtos;
	}
}