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
@Table(name = "TB_USER")
public class UserEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 8045404851761226898L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USE_ID", nullable=false)
    private Long id;
	
	@Column(name = "USE_CODE", nullable=false, length=50)
    private String code;
	
    @Column(name = "USE_NAME", nullable=false, length=100)
    private String name;

    @Column(name = "USE_LOGIN", nullable=false, length=100)
    private String login;
    
    @Column(name = "USE_PASSWORD", nullable=false, length=100)
    private String password;
    
    @Column(name = "USE_TYPE", nullable=false, length=50)
    private String type;

}
