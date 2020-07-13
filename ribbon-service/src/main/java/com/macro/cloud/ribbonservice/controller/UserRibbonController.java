package com.macro.cloud.ribbonservice.controller;

import com.macro.cloud.ribbonservice.domain.CommonResult;
import com.macro.cloud.ribbonservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserRibbonController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable Long id){
        return restTemplate.getForObject(userServiceUrl,CommonResult.class,id);
    }

    @GetMapping("/username")
    public CommonResult getByUsername(@RequestParam String username){
        return restTemplate.getForObject(userServiceUrl,CommonResult.class,username);
    }

    @GetMapping("/getEntityByUsername")
    public CommonResult getEntityByUsername(@RequestParam  String username) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(userServiceUrl + "/user/getByUsername?username={1}", CommonResult.class, username);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult("操作失败", 500);
        }
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return restTemplate.postForObject(userServiceUrl + "/user/create", user, CommonResult.class);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return restTemplate.postForObject(userServiceUrl + "/user/update", user, CommonResult.class);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, CommonResult.class, id);
    }

    @GetMapping("/getAll")
    public CommonResult getAll(){
        return restTemplate.getForObject(userServiceUrl+"/user/getAll",CommonResult.class);
    }

}
