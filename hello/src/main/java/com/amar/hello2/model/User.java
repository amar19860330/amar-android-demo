package com.amar.hello2.model;

/**
 * Created by SAM on 2015/3/12.
 */
public class User
{
    String name;
    String email;
    String pw;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPw()
    {
        return pw;
    }

    public void setPw( String pw )
    {
        this.pw = pw;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
