package com.tien4112004.auth_service.controller;

import com.tien4112004.auth_service.dto.user.UserDto;
import com.tien4112004.auth_service.mapper.UserMapper;
import com.tien4112004.auth_service.service.UserService;
import com.tien4112004.commonlib.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService _userService;
    private final UserMapper _userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        _userService = userService;
        _userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<ResultResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = _userMapper.toUserDtos(_userService.getUsersByIds());
        return ResponseEntity.ok(new ResultResponse<>(true, 200, "Users retrieved successfully", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse<UserDto>> getUserById(@PathVariable UUID id) {
        UserDto user = _userMapper.toUserDto(_userService.getUserById(id));
        return ResponseEntity.ok(new ResultResponse<>(true, 200, "User retrieved successfully", user));
    }

//    @PostMapping
//    public ResponseEntity<ResultResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
//        UserDto createdUser = _userMapper.toUserDto(_userService.createUser(_userMapper.toUser(userDto)));
//        return ResponseEntity.status(201).body(new ResultResponse<>(true, 201, "User created successfully", createdUser));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse<UserDto>> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserDto updatedUser = _userMapper.toUserDto(_userService.updateUser(id, _userMapper.toUser(userDto)));
        return ResponseEntity.ok(new ResultResponse<>(true, 200, "User updated successfully", updatedUser));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResultResponse<Void>> deleteUser(@PathVariable UUID id) {
//        _userService.deleteUser(id);
//        return ResponseEntity.ok(new ResultResponse<>(true, 200, "User deleted successfully", null));
//    }
}