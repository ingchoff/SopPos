package com.project.authenservice.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private String fname;

    @NotBlank
    @Size(min = 4, max = 40)
    private String sname;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;


    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

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
}
