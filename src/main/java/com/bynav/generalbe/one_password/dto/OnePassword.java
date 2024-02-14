package com.bynav.generalbe.one_password.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "one_password")
public class OnePassword {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="password_id")
    private Integer id;

    @Column(name="website")
    private String webSite;

    @Column(name="username")
    @JsonProperty("username")
    private String user;

    @Column(name="password")
    private String pass;

    @Column(name="description")
    private String desc;

    @Column(name="last_modified")
    private String date_last_modi;

}
