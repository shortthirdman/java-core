package com.shortthirdman.core.filesystem.jackson.model;

import java.util.Date;
 
public class User {
   private Integer id;
   private String firstName;
   private String lastName;
 
   public User() {
   }
 
   public User(Integer id, String firstName, String lastName, Date birthDate) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
   }
 
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
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
 
   @Override
   public String toString() {
      return "User [id=" + id + ", firstName=" + firstName + ", " +
            "lastName=" + lastName + "]";
   }
}