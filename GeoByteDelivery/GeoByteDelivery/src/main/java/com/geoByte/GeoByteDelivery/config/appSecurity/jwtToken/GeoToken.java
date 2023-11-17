package com.geoByte.GeoByteDelivery.config.appSecurity.jwtToken;

import com.geoByte.GeoByteDelivery.data.model.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jwt_token")
public class GeoToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String accessToken;
    @Column(unique = true)
    private String refreshToken;
    private boolean isRevoked;
    private boolean isExpired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_jwt_id")
    private User appUser;
}
