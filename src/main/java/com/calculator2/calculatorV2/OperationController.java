package com.calculator2.calculatorV2;


import java.util.List;
import static com.calculator2.calculatorV2.operation.firstClassCollection.OperationManager.firstAndSecondSplit;
import static com.calculator2.calculatorV2.operation.firstClassCollection.OperationManager.numberFromString;

public class OperationController {

  //첫번째 구분자 [.|:], 두번째구분자 [\\s*[+]\\s*] ㅎ적용
  public List<String> separatorInput(String firstInput) {
    List<String> result = firstAndSecondSplit(firstInput);
    return result;
  }

  public List<Integer> changeToInt(List<String> values) {
    return numberFromString(values);
  }

}
