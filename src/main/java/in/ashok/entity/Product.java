package in.ashok.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;

	@NotBlank(message="Name is Mandatory")
	@Size(min=3,max=15, message="Name Should be 3 to 15 Character")
	private String name;
	
	@NotNull(message="Price is Mandatory")
	private Integer price;
	
	@NotNull(message="Quantity is Mandatory")
	private Integer qty;

}
