package com.example.redis.parameters;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "parameter")
@RequiredArgsConstructor(
        onConstructor_=
        @ConstructorProperties({"key", "description", "paramGroup", "value", "valueType"})
)
@NoArgsConstructor
public class Parameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true, nullable = false, length = 30)
    private String key;

    @NonNull
    @Column(unique = true, nullable = false, length = 200)
    private String description;

    @NonNull
    @Column(nullable = false, length = 30, name = "param_group")
    private String paramGroup;

    @NonNull
    @Column(nullable = false, length = 100)
    private String value;

    @NonNull
    @Column(nullable = false, length = 30, name = "value_type")
    private String valueType;

    @CreationTimestamp
    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;
}
