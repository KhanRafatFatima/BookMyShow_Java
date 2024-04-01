package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String mode;

    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Payment() {
    	
    }

	public Payment(String status, String mode, String type) {
		super();
		this.status = status;
		this.mode = mode;
		this.type = type;
	}
    
    
}
