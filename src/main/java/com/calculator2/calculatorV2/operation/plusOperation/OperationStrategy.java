package com.calculator2.calculatorV2.operation.plusOperation;

import java.util.List;
@FunctionalInterface
public interface OperationStrategy {
  int apply(List<Integer> operands);

}
