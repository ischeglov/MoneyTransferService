package ru.netology.moneytransferservice.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmRequest {

    @NotBlank(message = "номер операции не может быть пустым")
    @Pattern(regexp = "^[0-9]*$")
    private String operationId;

    @NotBlank(message = "код не должен быть пустым")
    private String code;
}
