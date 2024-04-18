package br.com.present.commons.model;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_EVENT")
public class EventEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 4364005233605991370L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVE_ID", nullable=false)
    private Long id;
	
	@Column(name = "EVE_CODE", nullable=false, length=50)
    private String code;

    @Column(name = "EVE_NAME", nullable=false, length=100)
    private String name;

    @Column(name = "EVE_DESCRIPTION")
    private String description;

    @Column(name = "EVE_STARTDATETIME", nullable=false)
    private OffsetDateTime startDateTime;

    @Column(name = "EVE_ENDDATETIME", nullable=false)
    private OffsetDateTime endDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVE_USEID", nullable=false)
    private UserEntity userEntity;
    
    @OneToOne(mappedBy = "eventEntity")
    private ClassEntity classEntity;

    @OneToMany(mappedBy = "eventEntity")
    private List<AttendanceEntity> attendancesEntity;
}
