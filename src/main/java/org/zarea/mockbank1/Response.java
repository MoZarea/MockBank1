package org.zarea.mockbank1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Response<T> {
    private Integer code;
    private String message;
    private T data;
}
