package com.pooproject.products_system.exceptions;

public class ProductDAOException extends RuntimeException {

    public ProductDAOException(String message) {
        super(message);
    }

    public ProductDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
