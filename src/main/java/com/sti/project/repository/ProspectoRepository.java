package com.sti.project.repository;

import java.util.Optional;

import com.sti.project.model.Prospecto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Student entity.
 * @author Laurent G. Cáceres (caceresbjm97@gmail.com)
 * @version 1.0.0
 */
@Repository
public interface ProspectoRepository  extends JpaRepository<Prospecto, String>{


    /**
     * Find Prospecto by its name.
     * @param prospectoName String
     * @return Optional Student
     */
    Optional<Prospecto> findByProspectoName(String prospectoName);

    /**
     * Find Paginated Prospecto by name.
     * @param prospectoName
     * @param pageable
     * @return
     */
    Page<Prospecto> findByProspectoNameContaining(String prospectoName, Pageable pageable);

}
