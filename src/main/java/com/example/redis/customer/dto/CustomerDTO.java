package com.example.redis.customer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private Integer id;
    @JsonAlias("customer_name")
    @NotNull(message = "Customer Name cannot be null")
    private String customerName;
    @JsonAlias("phone_no")
    @NotNull(message = "Customer Phone no cannot be null")
    private String phoneNo;
    @JsonAlias("address")
    @NotNull(message = "Customer Address cannot be null")
    private String address;
}
