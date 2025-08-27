package com.calculator2.calculatorV2.io;

import com.calculator2.calculatorV2.OperationController;
import com.calculator2.calculatorV2.operation.firstClassCollection.OperationManager;

import java.util.List;

public class OutputHandler {

  private OperationController operationController;
  private List<Integer> changeToNumber;
  private OperationManager operationManager;

  public OutputHandler(OperationController operationController,OperationManager operationManager) {
    this.operationController = operationController;
    this.operationManager = operationManager;
  }

  public void displayCalculate(List<Integer> numbers, int result) {
    System.out.println("계산기 입력: " + numbers);
    System.out.println("합계: " + result);
  }
}
