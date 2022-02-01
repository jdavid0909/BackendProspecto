package com.sti.project.dto;

import com.sti.project.model.status.ModelStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import javax.validation.constraints.*;

/**
 * Student DTO class to encapsulate implementation of entity.
 * @author Laurent G. Cáceres (caceresbjm97@gmail.com)
 * @version 1.0.0
 */

@JsonSerialize
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProspectoDto {


    @JsonProperty("prospectoId")
    private String prospectoId;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 32)
    private String prospectoName;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Integer prospectoAge;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 64)
    @Email(message = "El Email ingresado es invalido")
    private String prospectoMail;

    @JsonProperty("prospectoStatus")
    private ModelStatus prospectoStatus;
}
