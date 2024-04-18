package br.com.present.commons.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "TB_ATTENDANCE")
public class AttendanceEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3898884391216000201L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATT_ID", nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATT_USEID", nullable=false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATT_EVEID", nullable=false)
    private EventEntity eventEntity;
	
}
