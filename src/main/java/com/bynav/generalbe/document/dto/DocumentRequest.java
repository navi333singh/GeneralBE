package com.bynav.generalbe.document.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class DocumentRequest {
    String newName;
    String desc;
    MultipartFile file;
}
