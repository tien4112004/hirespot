package com.tien4112004.auth_service.service;


import com.tien4112004.auth_service.dto.user.UserCollectionRequestDto;
import com.tien4112004.auth_service.entity.User;
import com.tien4112004.auth_service.mapper.UserMapper;
import com.tien4112004.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository _userRepository;
    private final UserMapper _userMapper;

    UserService(UserRepository userRepository, UserMapper userMapper) {
        _userRepository = userRepository;
        _userMapper = userMapper;
    }

    public Page<User> getAllUsers(UserCollectionRequestDto filter) {
        Pageable pageable = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                Sort.by(
                        filter.getSortDirection().equalsIgnoreCase("asc")
                                ? Sort.Direction.ASC : Sort.Direction.DESC,
                        filter.getSortBy()
                )
        );

        return _userRepository.findAll(pageable);
    }

    public List<User> getUsersByIds(List<UUID> ids) {
        return _userRepository.findAllById(ids);
    }

    public User getUserById(UUID id) {
        return _userRepository.findUserById(id);
    }

    public boolean isEmailTaken(String email) {
        return _userRepository.findUserByEmail(email) != null;
    }

    public boolean isUsernameTaken(String username) {
        return _userRepository.findUserByUsername(username) != null;
    }
}
