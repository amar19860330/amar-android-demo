package com.amar.hello2.expandlist;

/**
 * Created by SAM on 2015/1/12.
 */
public class Person
{
    public int id;
    public String name;
    public int age;
    public int sex;
    public int type; // 1级 2级 3级
    public int parentid; //父亲节点id
    public int visible = 1;  //1显示 2隐藏

    public Person( int id,String name,int age,int sex,int type,int parentid )
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.type = type;
        this.parentid = parentid;
    }

    public Person( int id,String name,int age,int sex,int type )
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.type = type;
    }
}
