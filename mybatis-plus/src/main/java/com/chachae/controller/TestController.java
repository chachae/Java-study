package com.chachae.controller;

import com.chachae.bean.Result;
import com.chachae.entity.bo.User;
import com.chachae.entity.dto.LoginDTO;
import com.chachae.exceptions.ApiException;
import com.chachae.service.LoginService;
import com.chachae.service.PermissionService;
import com.chachae.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author chachae
 * @date 2019/12/19 19:26
 */
@RestController
@RequestMapping("/test")
public class TestController {

  @Resource private LoginService loginService;
  @Resource RoleService roleService;
  @Resource private PermissionService permissionService;

  @GetMapping("/exception")
  public void exception(String args) {
    try {
      int a = Integer.valueOf(args) / 0;
    } catch (Exception e) {
      throw ApiException.argError("参数异常");
    }
  }

  @PostMapping("/login")
  public Result<User> login(@Valid LoginDTO dto) {
    User user = this.loginService.getUserByLoginDTO(dto);
    return Result.ok(user);
  }

  @GetMapping("pms/{userId}")
  public Result<Set<String>> getPmsExpressionByUserId(@PathVariable Long userId) {
    Set<String> set = this.permissionService.getExpressionByUserId(userId);
    return Result.ok(set);
  }

  @GetMapping("pms/all")
  public Result<Set<String>> getPmsExpressionByUserId() {
    Set<String> set = this.permissionService.getAllExpression();
    return Result.ok(set);
  }

  @GetMapping("roles/{userId}")
  public Result<Set<String>> getRoleByUserId(@PathVariable Long userId) {
    Set<String> set = this.roleService.getRoleByUserId(userId);
    return Result.ok(set);
  }
}