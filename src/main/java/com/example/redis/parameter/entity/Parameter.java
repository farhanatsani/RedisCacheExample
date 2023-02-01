package com.example.redis.parameter.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "parameter")
@Getter @Setter
@RequiredArgsConstructor(
        onConstructor_=
        @ConstructorProperties({"key", "description", "paramGroup", "value", "valueType"})
)
@SuperBuilder
public class Parameter implements Serializable {

    @Id
    @SequenceGenerator(name = "parameter_id_seq", sequenceName = "parameter_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_id_seq")
    private Integer id;

    @Column(unique = true, nullable = false, length = 30)
    private String key;

    @Column(unique = true, nullable = false, length = 200)
    private String description;

    @Column(nullable = false, length = 30, name = "param_group")
    private String paramGroup;

    @Column(nullable = false, length = 100)
    private String value;

    @Column(nullable = false, length = 30, name = "value_type")
    private String valueType;

    @CreationTimestamp
    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;
}
