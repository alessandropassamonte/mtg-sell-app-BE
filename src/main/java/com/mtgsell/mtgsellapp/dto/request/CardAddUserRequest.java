package com.mtgsell.mtgsellapp.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CardAddUserRequest {

    private List<Long> cardsId;
}
