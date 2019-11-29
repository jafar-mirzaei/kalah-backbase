package com.backbase.kalah.kalahassignment.persistance.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
  private Long id;
  private Date creationDate;
  private Date lastUpdate;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @CreatedDate
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(final Date creationDate) {
    this.creationDate = creationDate;
  }

  @LastModifiedDate
  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(final Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
