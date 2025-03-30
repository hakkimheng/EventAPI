package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse  <T>{
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    private HttpStatus status;
    private LocalDateTime timestamp;

}
