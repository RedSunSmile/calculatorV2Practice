package com.calculator2.calculatorV2;

import com.calculator2.calculatorV2.io.InputHandler;
import com.calculator2.calculatorV2.io.OutputHandler;
import com.calculator2.calculatorV2.operation.firstClassCollection.OperationManager;


import javax.script.ScriptException;
import java.util.*;

import static java.util.stream.StreamSupport.stream;

// " 2 + 3 * 4 / 2"와 같은 문자열을 입력할경우 10을 출력해야 한다
// 구분자 (, : | \) 가 들어가도 계산가능
// 예외처리  Error 로처리
//=>공백이 중간에 들어간다


public class CalculatorV2Application {

  public static void main(String[] args) throws ScriptException {
    OperationController operationController = new OperationController();
    InputHandler inputHandler = new InputHandler(operationController);
    OperationManager operationManager = new OperationManager();
    OutputHandler outputHandler=new OutputHandler(operationController,operationManager);
    String input = inputHandler.getCalculatorFromUser();
    List<Integer> numbers = inputHandler.getNumbers(input);
    List<String> operators = inputHandler.getOperator(input);

    int result=operationManager.calculate(operators,numbers);
    outputHandler.displayCalculate(numbers,result);

  }
}
