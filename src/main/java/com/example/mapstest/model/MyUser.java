package com.example.mapstest.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserLocation userLocation;
}
