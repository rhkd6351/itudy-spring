package com.itudy.api.domain.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Builder
@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper<T> {

    public T data;
    public String message;
    public int code;

}
