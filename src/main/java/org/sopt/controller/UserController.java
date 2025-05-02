package org.sopt.controller;

import org.sopt.constant.PathConstant;
import org.sopt.dto.Request.UserRequest;
import org.sopt.response.Response;
import org.sopt.service.UserService;
import org.sopt.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstant.USER)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody final UserRequest userRequest
    ) {
        userService.createUser(userRequest.name());
        return ApiUtil.successWithNoData(Response.CREATED);
    }
}
