package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;

public interface GameRuleChain {
  void applyRule(
      final UserRequestSessionData userRequestSessionData);
}
