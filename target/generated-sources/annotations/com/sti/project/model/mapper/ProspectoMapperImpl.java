package com.sti.project.model.mapper;

import com.sti.project.dto.ProspectoDto;
import com.sti.project.dto.ProspectoDto.ProspectoDtoBuilder;
import com.sti.project.model.Prospecto;
import com.sti.project.model.Prospecto.ProspectoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-30T19:25:55-0600",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.14 (Amazon.com Inc.)"
)
@Component
public class ProspectoMapperImpl implements ProspectoMapper {

    @Override
    public Prospecto dtoToProspecto(ProspectoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProspectoBuilder prospecto = Prospecto.builder();

        prospecto.prospectoId( dto.getProspectoId() );
        prospecto.prospectoName( dto.getProspectoName() );
        prospecto.prospectoAge( dto.getProspectoAge() );
        prospecto.prospectoMail( dto.getProspectoMail() );
        prospecto.prospectoStatus( dto.getProspectoStatus() );

        return prospecto.build();
    }

    @Override
    public ProspectoDto prospectoToDto(Prospecto prospecto) {
        if ( prospecto == null ) {
            return null;
        }

        ProspectoDtoBuilder prospectoDto = ProspectoDto.builder();

        prospectoDto.prospectoId( prospecto.getProspectoId() );
        prospectoDto.prospectoName( prospecto.getProspectoName() );
        prospectoDto.prospectoAge( prospecto.getProspectoAge() );
        prospectoDto.prospectoMail( prospecto.getProspectoMail() );
        prospectoDto.prospectoStatus( prospecto.getProspectoStatus() );

        return prospectoDto.build();
    }

    @Override
    public List<Prospecto> dtoToProspecto(List<ProspectoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Prospecto> list = new ArrayList<Prospecto>( dtos.size() );
        for ( ProspectoDto prospectoDto : dtos ) {
            list.add( dtoToProspecto( prospectoDto ) );
        }

        return list;
    }

    @Override
    public List<ProspectoDto> prospectoToDto(List<Prospecto> prospectos) {
        if ( prospectos == null ) {
            return null;
        }

        List<ProspectoDto> list = new ArrayList<ProspectoDto>( prospectos.size() );
        for ( Prospecto prospecto : prospectos ) {
            list.add( prospectoToDto( prospecto ) );
        }

        return list;
    }
}
