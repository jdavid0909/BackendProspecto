package com.sti.project.model.mapper;

import com.sti.project.model.Prospecto;
import com.sti.project.dto.ProspectoDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProspectoMapper {


     Prospecto dtoToProspecto(ProspectoDto dto);

     ProspectoDto prospectoToDto(Prospecto prospecto);

     List<Prospecto> dtoToProspecto(List<ProspectoDto> dtos);

     List<ProspectoDto> prospectoToDto(List<Prospecto> prospectos);
}
