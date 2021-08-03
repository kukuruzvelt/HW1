package task.users;

public class User {
    public void setPassword(String password) {
        this.password = password;
    }

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

    public boolean equals(User user) {
        return (this.email.equals(user.email) && this.password.equals(user.password));
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString(){
        return ("{\"email\":\"" + email+"\",\"password\":\""+password+"\"}\n");
    }
}
