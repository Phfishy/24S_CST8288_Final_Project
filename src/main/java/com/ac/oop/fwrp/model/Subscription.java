package com.ac.oop.fwrp.model;

public class Subscription {
  private Long id;

  private Long userId;

  private Integer communicationMethod;

  private String foodPreferences;

  private boolean isActivated;

  private double latitude;

  private double longitude;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getCommunicationMethod() {
    return communicationMethod;
  }

  public void setCommunicationMethod(Integer communicationMethod) {
    this.communicationMethod = communicationMethod;
  }

  public boolean isActivated() {
    return isActivated;
  }

  public void setActivated(boolean activated) {
    isActivated = activated;
  }

  public String getFoodPreferences() {
    return foodPreferences;
  }

  public void setFoodPreferences(String foodPreferences) {
    this.foodPreferences = foodPreferences;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
