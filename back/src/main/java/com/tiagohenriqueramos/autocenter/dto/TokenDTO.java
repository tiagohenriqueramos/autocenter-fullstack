package com.tiagohenriqueramos.autocenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
	
	private String token;
	private String email;
	private String tipo;

}