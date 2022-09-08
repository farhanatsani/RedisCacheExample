package com.example.redis.components;

import java.util.Map;

public interface WebClientInterface {

    Object get(Map<String, String> parameters);

    Object post(Object object);

}
