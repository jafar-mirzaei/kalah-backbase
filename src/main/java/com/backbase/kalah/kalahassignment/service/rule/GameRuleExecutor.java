package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;

public interface GameRuleExecutor {
  void executeRule(
      final UserRequestSessionData userRequestSessionData);
}
