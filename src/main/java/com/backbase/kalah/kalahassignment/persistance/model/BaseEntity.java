package com.backbase.kalah.kalahassignment.persistance.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author Jafar Mirzaei (jm.csh2009@gmail.com)
 * @version 1.0 2018.0519
 * @since 1.0
 */

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  private Long version;

  @Version
  public Long getVersion() {
    return version;
  }

  public void setVersion(final Long version) {
    this.version = version;
  }
}