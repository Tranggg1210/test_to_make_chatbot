package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.ChangePasswordDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.security.CurrentUser;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
@Validated
public class UserController {

  private final UserService userService;

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API create user")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @PostMapping(UrlConstant.User.CREATE_USER)
  public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto) {
    return VsResponseUtil.success(userService.createUser(userCreateDto));
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API change password")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @PutMapping(UrlConstant.User.CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
    return VsResponseUtil.success(userService.changePassword(changePasswordDto));
  }

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API get user")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(UrlConstant.User.GET_USER)
  public ResponseEntity<?> getUserById(@PathVariable String userId) {
    return VsResponseUtil.success(userService.getUserById(userId));
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API get current user login")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping(UrlConstant.User.GET_CURRENT_USER)
  public ResponseEntity<?> getCurrentUser(@Parameter(name = "principal", hidden = true)
                                          @CurrentUser UserPrincipal principal) {
    return VsResponseUtil.success(userService.getCurrentUser(principal));
  }

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API get all user")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(UrlConstant.User.GET_USERS)
  public ResponseEntity<?> getCustomers(@Valid @ParameterObject PaginationFullRequestDto requestDTO) {
    return VsResponseUtil.success(userService.getUsers(requestDTO));
  }

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API delete user")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping(UrlConstant.User.DELETE_USER)
  public ResponseEntity<?> DeleteUserById(@RequestParam String userId) {
    return VsResponseUtil.success(userService.deleteUser(userId));
  }
}
