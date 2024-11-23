package com.bynav.generalbe.document.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class DocumentResponse {
    byte[] document;
    String contentType;
}
