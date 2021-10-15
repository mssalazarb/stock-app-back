package com.pichincha.stock.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author mssalazarb
 * @version 1
 */
@Data
@Builder
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
