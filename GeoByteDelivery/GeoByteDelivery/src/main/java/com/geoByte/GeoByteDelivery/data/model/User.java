package com.geoByte.GeoByteDelivery.data.model;

import com.geoByte.GeoByteDelivery.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private final Role role = Role.USER;
    @Column(name = "is_enable")
    private Boolean isEnable = false;
    private final LocalDateTime createdAt = LocalDateTime.now();
}
