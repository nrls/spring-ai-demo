package com.onur.aidemo.chat.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GenerateGeneralResponse {

    private String message;
}
