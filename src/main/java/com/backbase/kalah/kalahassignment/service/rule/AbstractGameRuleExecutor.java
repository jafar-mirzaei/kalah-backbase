package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractGameRuleExecutor implements GameRuleExecutor {
  @Autowired protected UserRequestSessionData userRequestSessionData;
}
