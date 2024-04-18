package br.com.present.commons.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "TB_COURSE")
public class CourseEntity extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 6426796528644817071L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COU_ID", nullable=false)
    private Long id;
	
	@Column(name = "COU_CODE", nullable=false, length=50)
    private String code;
	
	@Column(name = "COU_NAME", nullable=false, length=100)
	private String name;

	@Column(name = "COU_DESCRIPTION")
	private String description;
}
