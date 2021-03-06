package domain;

public class User {
	private String username;
	private String password;
	private String email;
	private boolean isPremium;
	private boolean isAdmin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPremium() {
		return isPremium;
	}
	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "[username=" + username + ", password=" + password + ", email=" + email + ", isPremium=" + isPremium
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	public String toColumns() {
		return "<td>" + username + "</td><td>" + password + "</td><td>" + email + "</td><td>" + isPremium
				+ "</td><td>" + isAdmin + "</td>";
	}
	
}
