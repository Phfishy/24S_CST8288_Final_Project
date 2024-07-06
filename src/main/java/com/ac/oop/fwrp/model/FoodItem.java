package com.ac.oop.fwrp.model;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItem {
  private Long id;

  private Long retailerId;

  private String name;

  private String description;

  private Integer quantity;

  private BigDecimal price;

  private BigDecimal discountedPrice;

  private Date expirationDate;

  private Boolean isSurplus;

  private Boolean isForDonation;

  private Date createdAt;

  private Date updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRetailerId() {
    return retailerId;
  }

  public void setRetailerId(Long retailerId) {
    this.retailerId = retailerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getDiscountedPrice() {
    return discountedPrice;
  }

  public void setDiscountedPrice(BigDecimal discountedPrice) {
    this.discountedPrice = discountedPrice;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Boolean getIsSurplus() {
    return isSurplus;
  }

  public void setIsSurplus(Boolean surplus) {
    isSurplus = surplus;
  }

  public Boolean getIsForDonation() {
    return isForDonation;
  }

  public void setIsForDonation(Boolean forDonation) {
    isForDonation = forDonation;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
