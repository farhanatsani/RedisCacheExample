package com.example.redis.parameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParameterController {

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
                .body(parameter);
    }

    @GetMapping(value = "/api/parameters/{key}")
    public ResponseEntity<?> getParameter(@PathVariable String key) {
        Parameter foundParameter = redisParameterServiceImpl.getParameterByKey(key);

        if(foundParameter == null) {
            throw new NullPointerException("Parameter not exist");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundParameter);
    }

}
