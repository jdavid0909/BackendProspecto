package com.sti.project.dto.openapi;

import com.sti.project.dto.ProspectoDto;
import com.sti.project.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for StudentDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseProspectoDto")
public class ResponseProspectoDto extends BaseResponse<ProspectoDto>{
}
