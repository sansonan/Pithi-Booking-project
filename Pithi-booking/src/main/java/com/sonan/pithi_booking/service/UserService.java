package com.sonan.pithi_booking.service;

import com.sonan.pithi_booking.entity.User;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Map;

public interface UserService {

    User registerUser(User user);
    User getById(Long id);
    User upadteUser(Long id, User updatedUser);
    void deleteUser(Long id);
    List<User> getAllUsers();
    Page<User> getAllUsers(Map<String, String> params);


}
