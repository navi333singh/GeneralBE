package com.bynav.generalbe.document.dto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="document_id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="type")
    private String type;

    @Column(name = "data")
    private byte[] data;

    @Column(name="description")
    private String desc;

    @Column(name="last_modified")
    private String date_last_modi;

    @Column(name="folder_id")
    private int folder_id;

}
