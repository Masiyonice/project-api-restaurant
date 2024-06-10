package com.menuchallenge.challenge08.dto.request;

import com.menuchallenge.challenge08.constant.TransTypeOption;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransTypeRequest {

	public TransTypeOption transTypeOption;
}
