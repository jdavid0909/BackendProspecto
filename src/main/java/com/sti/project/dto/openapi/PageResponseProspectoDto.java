package com.sti.project.dto.openapi;


import com.sti.project.dto.ProspectoDto;
import com.sti.project.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseProspectoDto")
public class PageResponseProspectoDto extends PageResponseDto<ProspectoDto>{
}
