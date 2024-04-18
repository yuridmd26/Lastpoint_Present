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
@Table(name = "TB_GROUP")
public class GroupEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6849900097903796506L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRO_ID", nullable=false)
    private Long id;
	
	@Column(name = "GRO_CODE", nullable=false, length=50)
    private String code;

    @Column(name = "GRO_NAME", nullable=false, length=100)
    private String name;

	@Column(name = "GRO_DESCRIPTION")
    private String description;

}
