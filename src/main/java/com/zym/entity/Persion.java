package com.zym.entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//自定义库表明，不写 默认类名
@Entity
@Getter
@Setter

// @Table(name = "t_persion")  自定义库表名
public class Persion {

    /**
     * @ID  @GeneratedValue  设置库表的唯一标识主键
     */
    @Id
    @GeneratedValue
    private Long id;

    //定义字段的 名字 属性
    @Column(name = "name" ,nullable = true ,length = 20)
    private String name;

    @Column(name = "agee" , nullable = true , length = 4)
    private String age;

    @Override
    public String toString() {
        return "Persion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Persion() {
    }

    public Persion(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
