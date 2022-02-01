package com.sti.project.model.status.exception;

import com.sti.project.model.Prospecto;

/**
 * Student 404 status exception.
 * @author Laurent G. Cáceres (caceresbjm97@gmail.com)
 */
public class ProspectoNotFoundException  extends ResourceNotFoundException{

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildStudentNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Prospecto.class, field, fieldValue);
    }

    /**
     *
     * @param prospectoId
     * @return
     */
    public static ResourceNotFoundException
    buildStudentNotFoundExceptionForId(String prospectoId){
        return resourceNotFoundExceptionOf(Prospecto.class, "prospectoId", prospectoId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildStudentNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Prospecto.class, searchParams);
    }


}
