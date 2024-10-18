package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.ChangePasswordDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.UserMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.exception.UsernameAlreadyExistsException;
import com.example.projectbase.repository.RoleRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.UserService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  @Override
  public UserDto createUser(UserCreateDto userCreateDto) {
    boolean userExists = userRepository.findByUsername(userCreateDto.getUsername()).isPresent();
    if (userExists) {
      throw new UsernameAlreadyExistsException("Username '" + userCreateDto.getUsername() + "' already exists.");
    }
    User user = userMapper.toUser(userCreateDto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setNumberOfTabs(0);
    user.setRole(roleRepository.findByRoleName(RoleConstant.USER));
    userRepository.save(user);
    return userMapper.toUserDto(user);
  }

  @Override
  public CommonResponseDto changePassword(ChangePasswordDto changePasswordDto) {
    Optional<User> optionalUser = userRepository.findById(changePasswordDto.getUserId());

    if (optionalUser.isEmpty()) {
      return new CommonResponseDto(false, "User not found");
    }

    User user = optionalUser.get();

    if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
      return new CommonResponseDto(false, "Old password is incorrect");
    }

    user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
    userRepository.save(user);

    return new CommonResponseDto(true, "Password changed successfully");
  }

  @Override
  public UserDto getUserById(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    return userMapper.toUserDto(user);
  }

  @Override
  public PaginationResponseDto<UserDto> getUsers(PaginationFullRequestDto request) {
    Pageable pageable = PaginationUtil.buildPageable(request);
    Page<User> usersPage = userRepository.findAll(pageable);
    PagingMeta pagingMeta = new PagingMeta(
            usersPage.getTotalElements(),
            usersPage.getTotalPages(),
            usersPage.getNumber(),
            usersPage.getSize(),
            request.getSortBy(),
            request.getIsAscending().toString()
    );
    List<UserDto> userList= usersPage.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    return new PaginationResponseDto<>(pagingMeta, userList);
  }

  @Override
  public UserDto getCurrentUser(UserPrincipal principal) {
    User user = userRepository.getUser(principal);
    return userMapper.toUserDto(user);
  }

  @Override
  public CommonResponseDto deleteUser(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));
    userRepository.delete(user);
    return  new CommonResponseDto(true, ResponeConstant.SUCCESS);
  }
}
