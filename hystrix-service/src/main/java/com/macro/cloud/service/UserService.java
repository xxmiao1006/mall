package com.macro.cloud.service;

import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;

import java.util.concurrent.Future;

public interface UserService {

    CommonResult getUser(Long id);

    CommonResult getUserCommand(Long id);

    CommonResult getUserException(Long id);

    CommonResult getUserCache(Long id);

    CommonResult removeCache(Long id);

    Future<User> getUserFuture(Long id);
}
