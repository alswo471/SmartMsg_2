package mj_crossShot.models;






import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Member {
	
	@NotBlank
	private String memId;
	
	@NotBlank
	private String pwd;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String phone;
	private int admin;
		
}
