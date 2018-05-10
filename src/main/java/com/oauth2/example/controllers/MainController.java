package com.oauth2.example.controllers;

import com.oauth2.example.dtos.UserDTO;
import com.oauth2.example.exceptions.FEBusinessException;
import com.oauth2.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Resource(name = "userDetailsService")
    private UserService userService;

    @RequestMapping("/user/profile")
    @Secured({"ROLE_CUSTOMER"})
    public Principal getUserDetails(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerNewUser(@RequestBody UserDTO userDTO) throws FEBusinessException {
        Assert.notNull(userDTO, "Request body cannot be empty.");
        Assert.notNull(userDTO.getUsername(), "User name cannot be empty.");
        Assert.notNull(userDTO.getPassword(), "Password cannot be empty.");
        UserDetails user = userDetailsService.loadUserByUsername(userDTO.getUsername());
        if (user != null) {
            throw new FEBusinessException("err_user_present","User exists");
        }
        userService.createNewUser(userDTO);
        return;
    }

    @RequestMapping(value = "/admin/user")
    @Secured("ROLE_ADMIN")
    public Principal getAdminUserDetails(@RequestParam String username) {
        return new Principal() {
            @Override
            public String getName() {
                return userDetailsService.loadUserByUsername(username).getUsername();
            }
        };

    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public List<UserDTO> fetchUsersByExample(@RequestBody UserDTO userDTO) {
        return userService.fetchByExample(userDTO);
    }

    /**
     * This method is provided just to confirm that everything else apart from the configured
     * spring filter passes through
     *
     * @return
     */
    @GetMapping("/search/random")
    public String fetchSomeDocks() {
        return "Passed The Filter";
    }

}
