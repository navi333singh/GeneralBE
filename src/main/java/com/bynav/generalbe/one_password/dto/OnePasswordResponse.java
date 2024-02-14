package com.bynav.generalbe.one_password.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class OnePasswordResponse {
    ArrayList<OnePassword> OnePasswordList;
}
