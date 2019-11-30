package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRequestSessionUtil {
  @Autowired
  private UserRequestSessionData userRequestSessionData;

  public UserRequestSessionData getUserRequestSessionData() {
    return userRequestSessionData;
  }

  public void setUserRequestSessionData(final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
  }
}
