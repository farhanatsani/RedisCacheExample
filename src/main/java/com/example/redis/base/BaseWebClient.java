package com.example.redis.base;

import java.util.Map;

public interface BaseWebClient {

    Object get(Map<String, String> parameters);

    Object post(Object object);

}
