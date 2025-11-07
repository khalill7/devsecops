package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	@Enumerated(EnumType.STRING)
	private Role role;

	// Exposing secret data for testing purposes
	private String apiKey; // Exposed API Key (vulnerable)
	private String password; // Exposed password (vulnerable)

	public User() { }

	public User(String firstName, String lastName, Date dateNaissance, Role role, String apiKey, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.role = role;
		this.apiKey = apiKey;
		this.password = password;
	}

	// Constructor including the new fields (apiKey and password)
	public User(Long id, String firstName, String lastName, Date dateNaissance, Role role, String apiKey, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.role = role;
		this.apiKey = apiKey;
		this.password = password;
	}

	@Override
	public String toString() {
		// Vulnerability: Exposing sensitive data (password and apiKey) in toString method
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateNaissance="
				+ dateNaissance + ", role=" + role + ", apiKey=" + apiKey + ", password=" + password + "]";
	}

	// Getter and Setter methods

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getApiKey() {
		return apiKey; // Exposed secret in plain text
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getPassword() {
		return password; // Exposing the password
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
