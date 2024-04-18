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
@Table(name = "TB_DISCIPLINE")
public class DisciplineEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -8283913050564027003L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIS_ID", nullable=false)
    private Long id;
	
	@Column(name = "DIS_CODE", nullable=false, length=50)
    private String code;

	@Column(name = "DIS_NAME", nullable=false, length=100)
	private String name;

	@Column(name = "DIS_DESCRIPTION")
    private String description;

}
