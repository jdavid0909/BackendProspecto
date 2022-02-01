package com.sti.project.service.implementation;


import com.sti.project.dto.ProspectoDto;
import com.sti.project.model.status.exception.ProspectoNotFoundException;
import com.sti.project.model.Prospecto;
import com.sti.project.model.mapper.ProspectoMapper;
import com.sti.project.model.status.ModelStatus;
import com.sti.project.repository.ProspectoRepository;
import com.sti.project.service.ProspectoService;

import com.sti.project.utils.SortingPagingUtils;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Student entity.
 * @author Laurent G. Cáceres (caceresbjm97@gmail.com)
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ProspectoServiceImpl implements ProspectoService{

    private final ProspectoRepository prospectoRepository;

    private final ProspectoMapper prospectoMapper;

    private final SortingPagingUtils sortingPagingUtils;


    @Override
    public ProspectoDto saveProspecto(final ProspectoDto prospectoDto) {
        Prospecto prospecto = Prospecto
                .buildFromDto(this.prospectoMapper.dtoToProspecto(prospectoDto));
        this.prospectoRepository.save(prospecto);
        return prospectoMapper.prospectoToDto(prospecto);
    }

    @Override
    public ProspectoDto findProspectoById(final String prospectoId) {
        Prospecto prospecto = prospectoRepository.findById(prospectoId)
                .orElseThrow(() -> ProspectoNotFoundException
                        .buildStudentNotFoundExceptionForId(prospectoId));
        return prospectoMapper
                .prospectoToDto(
                        isActiveProspecto(prospecto, "prospectoId", prospectoId));
    }

    @Override
    public ProspectoDto findProspectoByName(final String prospectoName) {
        Prospecto prospecto = this.prospectoRepository.findByProspectoName(prospectoName)
                .orElseThrow(() -> ProspectoNotFoundException
                        .buildStudentNotFoundExceptionForField("prospectoName", prospectoName));
        return prospectoMapper
                .prospectoToDto(
                        isActiveProspecto(prospecto, "prospectoName", prospectoName));
    }

    @Override
    public Page<ProspectoDto> findPaginatedSortedProspects
            (final String prospectoName, final int page, final int size, final String[] sort) {
        //Evaluate if we should sort by two fields.
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<ProspectoDto> prospectoDtos;
        if(prospectoName == null) {
            prospectoDtos = prospectoMapper
                    .prospectoToDto(prospectoRepository.findAll(pageable).toList());
        } else {
            prospectoDtos = prospectoMapper
                    .prospectoToDto(prospectoRepository
                            .findByProspectoNameContaining(prospectoName, pageable).toList());
        }
        return new PageImpl<>(prospectoDtos);
    }

    @Override
    public void deleteProspectoById(final String prospectoId){
        Prospecto prospecto = prospectoMapper.dtoToProspecto(findProspectoById(prospectoId));
        prospecto.setProspectoStatus(ModelStatus.INACTIVE);
        prospectoRepository.save(prospecto);
    }

    /**
     * Return student if status code is ACTIVE.
     * @param prospecto Prospecto
     * @param queryField String
     * @param queryFieldValue String
     * @return Prospecto
     * @throws ProspectoNotFoundException ex
     */
    private Prospecto isActiveProspecto(Prospecto prospecto, String queryField, String queryFieldValue){
        if(prospecto.getProspectoStatus().getStatusCode() == 0){
            return prospecto;
        }
        throw ProspectoNotFoundException
                .buildStudentNotFoundExceptionForField(queryField, queryFieldValue);
    }
}
