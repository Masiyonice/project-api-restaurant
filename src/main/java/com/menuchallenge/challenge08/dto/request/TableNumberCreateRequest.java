package com.menuchallenge.challenge08.dto.request;

import com.menuchallenge.challenge08.constant.TableNumber;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableNumberCreateRequest {

	private TableNumber tableNumber;

}
