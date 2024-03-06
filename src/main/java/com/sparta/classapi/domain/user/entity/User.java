package com.sparta.classapi.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender" )
    @Enumerated(value = EnumType.STRING)
    private Gender gender; // 성별 저장

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "auth", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum auth;

    @Builder
    public User(String email, String password, Gender gender, String phoneNumber, String address, UserRoleEnum auth) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.auth = auth;
    }


}
