package com.sti.project.controller;

import com.sti.project.dto.ProspectoDto;
import com.sti.project.dto.openapi.PageResponseProspectoDto;
import com.sti.project.dto.openapi.ResponseProspectoDto;
import com.sti.project.dto.pageable.PageResponseDto;
import com.sti.project.dto.pageable.PageResponse;
import com.sti.project.response.BaseResponse;
import com.sti.project.response.Response;
import com.sti.project.response.error.ErrorResponse;
import com.sti.project.service.ProspectoService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for Student entity operations.
 *
 * @author Laurent G. Cáceres (caceresbjm97@gmail.com)
 * @version 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/prospectos")
@CrossOrigin(origins = "http://localhost:127.0.0.1")
@RequiredArgsConstructor
public class ProspectoController {

    @Autowired(required=true)
    ProspectoService prospectoService;

    /**
     * Get Paginated sorted students with given criteria.
     *
     * @param prospectoname String prospectoName
     * @param page          Page number
     * @param size          page size
     * @param sort          Sort params
     * @return ResponseEntity PageResponse StudentDto
     */
    @Operation(summary = "Get a list of paginated/sorted prospectos", operationId = "getProspectos")
    @ApiResponse(responseCode = "200", description = "List of prospectos retrieved successfully."
            , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponseProspectoDto.class))})
    @GetMapping(params = {"prospectoname", "page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<ProspectoDto>> getProspectos(
            @RequestParam(required = false) String prospectoname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "prospectoId, desc") String[] sort) {

        Page<ProspectoDto> studentPage = prospectoService
                .findPaginatedSortedProspects(prospectoname, page, size, sort);

        PageResponseDto<ProspectoDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(studentPage.getSize(), studentPage.getNumberOfElements(),
                studentPage.getTotalPages(), studentPage.getNumber(), studentPage.getContent());
    }


    /**
     * Handler method for saving Validated given student.
     *
     * @param prospectoDto ProspectoDto
     * @return ResponseEntity Response Prospecto
     */
    @Operation(summary = "Save given prospecto.", operationId = "saveProspecto1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prospecto saved successfully"
                    , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ResponseProspectoDto.class))})
            , @ApiResponse(responseCode = "400", description = "Given Prospecto is invalid"
            , content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})}
    )
    @PostMapping
    public ResponseEntity<? extends Response<ProspectoDto>> saveProspecto(@RequestBody @Valid ProspectoDto prospectoDto) {
        ProspectoDto saveProspecto = prospectoService.saveProspecto(prospectoDto);
        BaseResponse<ProspectoDto> prospectoDtoBaseResponse = new BaseResponse<>();
        return prospectoDtoBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Prospecto saved successfully", saveProspecto);
    }

    /**
     * Handler method for fetching a single Student by its ID.
     *
     * @param prospectoId String
     * @return ResponseEntity Student
     */
    @Operation(description = "Find a prospecto by its ID.", operationId = "findProspectoById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prospecto retrieved successfully"
                    , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ProspectoDto.class))})
            , @ApiResponse(responseCode = "404", description = "Prospecto not found"
            , content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/{prospectoId}")
    public ResponseEntity<? extends ProspectoDto> findByProspectoId(@PathVariable final String prospectoId) {
        ProspectoDto retrievedStudent = prospectoService.findProspectoById(prospectoId);
        return new ResponseEntity<>(retrievedStudent, HttpStatus.OK);
    }

    /**
     * Handler method for fetching a single Prospecto by its name.
     *
     * @param prospectoName String
     * @return ResponseEntity Prospecto
     */
    @Operation(description = "Find a Prospecto by its name.", operationId = "findProspectoByName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student retrieved successfully"
                    , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ProspectoDto.class))})
            , @ApiResponse(responseCode = "404", description = "Student not found"
            , content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(params = "prospectoName")
    public ResponseEntity<? extends ProspectoDto> findProspectoByName(@RequestParam("prospectoName") final String prospectoName) {
        ProspectoDto retrievedStudent = prospectoService.findProspectoByName(prospectoName);
        return new ResponseEntity<>(retrievedStudent, HttpStatus.OK);
    }


    /**
     * Handler method for deleting a Prospecto by its ID.
     * @param prospectoId String
     * @return Response null
     */
    @Operation(description = "Delete a Prospecto by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prospecto deleted successfully."
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  String.class))})
            , @ApiResponse(responseCode = "404", description = "Prospecto not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping(value = "/{prospectoId}")
    public ResponseEntity<? extends Response<String>> deleteProspecto(@PathVariable final String prospectoId) {
        prospectoService.deleteProspectoById(prospectoId);
        BaseResponse<String> studentResponse = new BaseResponse<>();
        return studentResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Prospecto with ID: ")
                        .append(prospectoId)
                        .append(" was deleted.").toString(), prospectoId);
    }
}
