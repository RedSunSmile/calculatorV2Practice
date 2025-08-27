package com.calculator2.calculatorV2.io;

import com.calculator2.calculatorV2.OperationController;

import javax.script.ScriptException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputHandler {

  public static final Scanner SCANNER = new Scanner(System.in);
  private OperationController operationController;

  public InputHandler(OperationController operationController) {
    this.operationController = operationController;
  }

  public String getCalculatorFromUser() {
    System.out.print("계산할 숫자를 입력하세요: ");
    return SCANNER.nextLine();
  }

  public List<Integer> getNumbers(String input) {
    List<String> segments = operationController.separatorInput(input);
    return operationController.changeToInt(segments);
  }

  public List<String> getOperator(String input) {
    List<String> segments = operationController.separatorInput(input);
    return segments.stream()
      .filter(s -> s.matches("[+]"))
      .collect(Collectors.toList());
  }

}
