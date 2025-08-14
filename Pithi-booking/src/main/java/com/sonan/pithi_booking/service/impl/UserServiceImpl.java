package com.sonan.pithi_booking.service.impl;

import com.sonan.pithi_booking.dto.UserDTO;
import com.sonan.pithi_booking.entity.User;
import com.sonan.pithi_booking.exception.ResourceNotFoundException;
import com.sonan.pithi_booking.repository.UserRepository;
import com.sonan.pithi_booking.service.UserService;
import com.sonan.pithi_booking.service.util.PageUtil;
import com.sonan.pithi_booking.service.util.spec.UserFilter;
import com.sonan.pithi_booking.service.util.spec.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }



    @Override
    public User upadteUser(Long id, User updatedUser) {
        User exitedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        exitedUser.setEmail(updatedUser.getEmail());
        exitedUser.setAddress(updatedUser.getAddress());
        exitedUser.setPhone(updatedUser.getPhone());
        exitedUser.setProfileImageUrl(updatedUser.getProfileImageUrl());
        return userRepository.save(exitedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public Page<User> getAllUsers(Map<String, String> params) {
        UserFilter userFilter = new UserFilter();

        // Handle pagination
        int pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        int pageNumber = PageUtil.DEFAULT_PAGE_NUM;


        String name = params.get("username");
        if (name != null && !name.isEmpty()) {
            userFilter.setUsername(name.trim());
        }
        try {
            if (params.containsKey(PageUtil.PAGE_SIZE)) {
                pageSize = Integer.parseInt(params.get(PageUtil.PAGE_SIZE));
            }
        } catch (NumberFormatException ignored) {}

        try {
            if (params.containsKey(PageUtil.PAGE_NUM)) {
                pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUM));
            }
        } catch (NumberFormatException ignored) {}

        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
        UserSpec userSpec = new UserSpec(userFilter);

        return userRepository.findAll(userSpec, pageable);
    }


    private void createUserWithRole(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
//                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
               // .roles(Set.of(role))
                .enabled(true)
                .accountNonLocked(false)
                .build();
        userRepository.save(user);
    }
}
