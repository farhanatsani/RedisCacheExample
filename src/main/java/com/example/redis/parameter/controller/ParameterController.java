package com.example.redis.parameter.controller;

import com.example.redis.base.BaseController;
import com.example.redis.base.RspMsgConstants;
import com.example.redis.parameter.entity.Parameter;
import com.example.redis.parameter.service.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParameterController extends BaseController {
    private ParameterService databaseParameterServiceImpl;
    private ParameterService redisParameterServiceImpl;
    public ParameterController(ParameterService databaseParameterServiceImpl, ParameterService redisParameterServiceImpl) {
        this.databaseParameterServiceImpl = databaseParameterServiceImpl;
        this.redisParameterServiceImpl = redisParameterServiceImpl;
    }
    @PostMapping(value = "/api/parameters")
    public ResponseEntity<?> saveParameter(@RequestBody Parameter parameter) {
        databaseParameterServiceImpl.save(parameter);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createResponse(parameter, HttpStatus.CREATED.value(),
                        RspMsgConstants.constructMessage("Customer", RspMsgConstants.$_SUCCESSFULLY_SAVE))
                );
    }
    @GetMapping(value = "/api/parameters/{key}")
    public ResponseEntity<?> getParameter(@PathVariable String key) {
        Parameter foundParameter = redisParameterServiceImpl.getParameterByKey(key);

        if(foundParameter == null) {
            throw new NullPointerException("Parameter not exist");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createResponse(foundParameter, HttpStatus.OK.value(), RspMsgConstants.DATA_AVAILABLE));
    }
}
