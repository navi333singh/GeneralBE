package com.bynav.generalbe.document.dto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="folder_id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name = "parent_id")
    private int parent_id;
}
