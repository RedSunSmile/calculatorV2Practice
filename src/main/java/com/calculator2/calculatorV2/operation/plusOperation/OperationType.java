package com.calculator2.calculatorV2.operation.plusOperation;

import java.util.List;

public enum OperationType {
  SUM(new SumStrategy());

  private final OperationStrategy strategy;

  OperationType(OperationStrategy strategy) {
    this.strategy = strategy;
  }

  public int execute(List<Integer> operands){
    return strategy.apply(operands);
  }


}
