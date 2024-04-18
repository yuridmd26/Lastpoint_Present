package br.com.present.commons.model;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_CLASS")
public class ClassEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6357209732953249482L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLA_ID", nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLA_GROID", nullable=false)
    private GroupEntity groupEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLA_DISID", nullable=false)
    private DisciplineEntity disciplineEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLA_EVEID", referencedColumnName = "EVE_ID")
    private EventEntity eventEntity;

}
