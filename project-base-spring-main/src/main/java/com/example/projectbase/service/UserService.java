package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.ChangePasswordDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.request.UserUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.security.UserPrincipal;

public interface UserService {

  UserDto createUser(UserCreateDto userCreateDto);

  CommonResponseDto changePassword(ChangePasswordDto changePasswordDto);

  UserDto getUserById(String userId);

  UserDto getUserByUsername(String username);

  PaginationResponseDto<UserDto> getUsers(PaginationFullRequestDto request);

  UserDto getCurrentUser(UserPrincipal principal);

  CommonResponseDto deleteUser(String userId);
}
