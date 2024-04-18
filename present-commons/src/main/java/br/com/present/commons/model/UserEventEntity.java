package br.com.present.commons.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "TB_USER_EVENT")
public class UserEventEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -644100994415636332L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UEV_ID", nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UEV_USEID", nullable=false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UEV_EVEID", nullable=false)
    private EventEntity eventEntity;

}
