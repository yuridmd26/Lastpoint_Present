package br.com.present.commons.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "TB_CLASS_COURSE")
public class ClassCourseEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -7922624077537457076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CCO_ID", nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CCO_COUID", nullable=false)
    private CourseEntity courseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CCO_CLAID", nullable=false)
    private ClassEntity classEntity;

}