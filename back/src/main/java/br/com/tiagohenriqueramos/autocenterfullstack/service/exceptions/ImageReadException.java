package br.com.tiagohenriqueramos.autocenterfullstack.service.exceptions;

import java.io.IOException;

public class ImageReadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImageReadException(String msg, IOException e) {
		super(msg);
	}

}
