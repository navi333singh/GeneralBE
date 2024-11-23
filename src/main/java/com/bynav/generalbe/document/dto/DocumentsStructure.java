package com.bynav.generalbe.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsStructure {

    int id;
    String name;
    String type;
    List<DocumentsStructure> fileStructure;
}
