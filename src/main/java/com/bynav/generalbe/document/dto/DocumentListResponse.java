package com.bynav.generalbe.document.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentListResponse {
    List<DocumentList> documentList;
}
