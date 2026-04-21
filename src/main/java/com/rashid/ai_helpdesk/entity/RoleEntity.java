package com.rashid.ai_helpdesk.entity;

import jakarta.persistence.*;



@Entity
@Table(name="roles")

public class RoleEntity {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Integer id;


  @Enumerated(EnumType.STRING)
  @Column(length = 20)
    private RoleEnum name;


  

    public Role(Erole name){

        this.name = name;
    }


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id= id;
    }

    public Erole getName(){
        return name;
    }

    public void setName(Erole name){
        this.name = name;
    }











}
