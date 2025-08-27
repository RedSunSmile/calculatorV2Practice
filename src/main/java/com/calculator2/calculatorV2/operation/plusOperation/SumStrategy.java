package com.calculator2.calculatorV2.operation.plusOperation;

import java.util.List;

public class SumStrategy implements OperationStrategy{
  public int apply(List<Integer> operands){
    return operands.stream().reduce(0,Integer::sum);
  }
}
