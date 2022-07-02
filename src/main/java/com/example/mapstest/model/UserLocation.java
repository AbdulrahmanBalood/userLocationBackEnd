package com.example.mapstest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class UserLocation {
    @Id
    private Integer id;
    private Double lat;
    private Double lng;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;
}
