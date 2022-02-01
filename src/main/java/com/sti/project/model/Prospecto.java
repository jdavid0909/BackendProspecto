package com.sti.project.model;

import com.sti.project.model.status.ModelStatus;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

/**
 * Student class to represent Student entity.
 * @author Laurent G. Cáceres (caceresbjm97@gmail.com)
 * @version 1.0.0
 */

@Entity
@Table(name = "t_prospecto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Prospecto {

    @Id
    @Column(name = "prospectoid", nullable = false, unique = true, length = 64)
    private String prospectoId;

    @Column(name = "prospectoname", nullable = false)
    private String prospectoName;

    @Column(name = "prospectoage", nullable = false)
    private Integer prospectoAge;

    @Column(name = "prospectomail", nullable = false)
    private String prospectoMail;

    @Column(name = "prospectostatus", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus prospectoStatus;

    /**
     * Adds fields which are not populated by Student DTO.
     * @return
     */
    public static Prospecto buildFromDto(Prospecto prospecto){
        prospecto.setProspectotId(UUID.randomUUID().toString());
        prospecto.setProspectoStatus(ModelStatus.ACTIVE);
        return prospecto;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Prospecto prospecto = (Prospecto) o;
        return this.prospectoId == prospecto.prospectoId
                && (this.prospectoName.equals(prospecto.prospectoName))
                && (this.prospectoAge.equals(prospecto.prospectoAge));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.prospectoName == null ? 0 : this.prospectoName.hashCode());
        hash = 31 * hash + (this.prospectoId == null ? 0 : this.prospectoId.hashCode());
        hash = 31 * hash + (this.prospectoAge == null ? 0 : this.prospectoAge.hashCode());
        return hash;
    }


    private void setProspectotId(final String prospectoId){
        this.prospectoId = prospectoId;
    }

    public void setProspectoStatus(ModelStatus modelStatus){
        this.prospectoStatus = modelStatus;
    }
}
