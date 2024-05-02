package com.mtgsell.mtgsellapp.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SuccessResponse {
    private String message;

    public SuccessResponse(String message) {
        this.message = message;
    }
}
