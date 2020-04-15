package SqlUtil;

@Table("User")
public class User {
	
	@Column("id")
    private int id;

	@Column("username")
    private String username;

	@Column("age")
    private int age;

    @Column("email")
    private String email;

    @Column("telephone")
    private String telephone;
    
    public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public String getUsername() {
		return username;
	}
    
    public void setUsername(String username) {
		this.username = username;
	}
    
    public int getAge() {
		return age;
	}
    
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
