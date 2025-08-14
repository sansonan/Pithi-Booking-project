package com.sonan.pithi_booking.controller;

import com.sonan.pithi_booking.dto.ApiResponseDTO;
import com.sonan.pithi_booking.dto.PageDTO;
import com.sonan.pithi_booking.dto.ProductDTO;
import com.sonan.pithi_booking.dto.UserDTO;
import com.sonan.pithi_booking.entity.Product;
import com.sonan.pithi_booking.entity.User;
import com.sonan.pithi_booking.service.UserService;
import com.sonan.pithi_booking.service.impl.UserServiceImpl;
import com.sonan.pithi_booking.service.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:4200")
@Controller
@RequestMapping("/api/user") // Add this line
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register-user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(userMapper.toDto(savedUser));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        User userId = userService.getById(id);// throw if not found
        return ResponseEntity.ok(userMapper.toDto(userId));
    }
    @GetMapping("/filter")
    public ResponseEntity<ApiResponseDTO<PageDTO<UserDTO>>> filter(@RequestParam Map<String, String> filters) {
        Page<User> page = userService.getAllUsers(filters);
        Page<UserDTO> dtoPage = page.map(userMapper::toDto);
        PageDTO<UserDTO> pageDTO = new PageDTO<>(dtoPage);

        ApiResponseDTO<PageDTO<UserDTO>> response = ApiResponseDTO.<PageDTO<UserDTO>>builder()
                .success(true)
                .message("Product list fetched successfully")
                .data(pageDTO)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        User upadted = userService.upadteUser(id, userMapper.toEntity(updatedUser));
       return ResponseEntity.ok(userMapper.toDto(upadted));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
       return ResponseEntity.noContent().build();
    }

}
