package com.macro.cloud.service;

import com.macro.cloud.domain.CommonResult;

public interface UserService {

    CommonResult getUser(Long id);

    CommonResult getUserCommand(Long id);

    CommonResult getUserException(Long id);

    CommonResult getUserCache(Long id);

    CommonResult removeCache(Long id);
}
