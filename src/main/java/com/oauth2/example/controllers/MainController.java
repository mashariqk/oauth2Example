package com.oauth2.example.controllers;

import com.oauth2.example.dtos.UserDTO;
import com.oauth2.example.entities.Role;
import com.oauth2.example.entities.User;
import com.oauth2.example.repositories.RoleRepository;
import com.oauth2.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
public class MainController {

    @Autowired
    private PasswordEncoder userPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Resource(name = "roleRepository")
    private RoleRepository roleRepository;

    @RequestMapping("/user/profile")
    @Secured({"ROLE_CUSTOMER"})
    public Principal getUserDetails(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @Transactional
    public String registerNewUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userPasswordEncoder.encode(userDTO.getPassword()));
        Role customerRole = roleRepository.findOneByRole("ROLE_CUSTOMER");
        Set<Role> roles = new HashSet<>();
        roles.add(customerRole);
        user.setEnabled(false);
        user.setRoles(roles);
        userRepository.save(user);
        return "Success";
    }

    @RequestMapping(value = "/admin/user")
    public Principal getAdminUserDetails(@RequestParam String username){
        return new Principal() {
            @Override
            public String getName() {
                return userRepository.findOneByUsername(username).getUsername();
            }
        };

    }

    /**
     * This method is provided just to confirm that everything else apart from the configured
     * spring filter passes through
     * @return
     */
    @GetMapping("/search/random")
    public String fetchSomeDocks(){
        return "Passed The Filter";
    }

}
