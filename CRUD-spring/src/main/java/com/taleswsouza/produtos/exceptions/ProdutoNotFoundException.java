package com.taleswsouza.produtos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Não há este produto")
public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String message) {
       super(message);
    }
}
