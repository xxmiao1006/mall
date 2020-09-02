package com.macro.cloud.Controller;

import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;
import com.macro.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserHystrixController {

    @Autowired
    UserService userService;

    @GetMapping("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable Long id) {
        //System.out.println("在这。。。。");
        return userService.getUser(id);
    }

    @GetMapping("/testCommand/{id}")
    public CommonResult testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }

    @GetMapping("/testException/{id}")
    public CommonResult testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }

    @GetMapping("/testCache/{id}")
    public CommonResult<User> testCache(@PathVariable Long id) {
        CommonResult<User> user  = userService.getUserCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return user;
    }

    @GetMapping("/testRemoveCache/{id}")
    public CommonResult testRemoveCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.removeCache(id);
        userService.getUserCache(id);
        return new CommonResult("操作成功", 200);
    }
}
