package com.maldo.backend.users.services.implementations;

import com.maldo.backend.users.domain.SignUpDTO;
import com.maldo.backend.users.entity.Roles;
import com.maldo.backend.users.entity.Users;
import com.maldo.backend.users.repositories.RoleRepository;
import com.maldo.backend.users.repositories.UsersRepository;
import com.maldo.backend.users.services.interfaces.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserDtoServiceImpl implements UserDtoService
{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository userRepository;

    @Override
    public void createUser(SignUpDTO dataTransfer)
    {
        try
        {
            Users user = new Users();
            Roles roles = roleRepository.findByName("player").get();

            user.setName(dataTransfer.getName());
            user.setUsername(dataTransfer.getUsername());
            user.setLastName(dataTransfer.getLastname());
            user.setSecondLastName(dataTransfer.getSecondLastName());
            user.setEmail(dataTransfer.getEmail());
            user.setPwd(passwordEncoder.encode(dataTransfer.getPwd()));
            user.setActive(true);
            user.setCreatedDate(new Date());
            user.setLastModifiedDate(new Date());
            user.setRoleId(roles.getId());

            userRepository.saveAndFlush(user);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}
