package com.example.redis.customer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private Integer id;
    @JsonAlias("customer_name")
    private String customerName;
    @JsonAlias("phone_no")
    private String phoneNo;
    @JsonAlias("address")
    private String address;
}
