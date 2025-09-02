package com.calculator2.calculatorV2.operation.firstClassCollection;

import com.calculator2.calculatorV2.operation.plusOperation.OperationStrategy;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationManager {
  private static final Map<String, OperationStrategy> operations =Map.of(
      "+", operands -> operands.stream().reduce(0, Integer::sum)
  );


  public static int calculate(List<String> operators, List<Integer> operands) throws NullPointerException, IllegalArgumentException {
    int result = operands.get(0);
    for (int i = 0; i < operators.size(); i++) {
      String execute = operators.get(i);
      OperationStrategy strategy = operations.get(execute);
      if (strategy == null) {
        throw new NullPointerException("지원하지 않는 연산자입니다: " + execute);
      }
      result=strategy.apply(List.of(result, operands.get(i + 1)));
    }
    return result;
  }

  public static List<Integer> extractNumbers(String calculatorInput) {
    List<Integer> numbers = new ArrayList<>();
    Matcher numberSign = Pattern.compile("\\d+").matcher(calculatorInput);
    while (numberSign.find()) {
      numbers.add(Integer.parseInt(numberSign.group()));
    }
    return numbers;
  }

  public static List<String> extractOperators(String calculatorSign) {
    List<String> operators = new ArrayList<>();
    Matcher operatorsSign = Pattern.compile("[+]").matcher(calculatorSign);
    while (operatorsSign.find()) {
      operators.add(operatorsSign.group());
    }
    return operators;
  }

  public static List<String> firstAndSecondSplit(String firstInput) throws  NullPointerException {
    //firstInput = "1,2|3\n4:5";
    String firstSplit = firstInput.replaceAll("[,|:\\n]+", "+");
  //  System.out.println("firstSplit="+firstSplit);
    List<Integer> operands = extractNumbers(firstSplit);
    List<String> operators=extractOperators(firstSplit);

    int result = calculate(operators, operands);
    String resultToString=Integer.toString(result);

    return new ArrayList<>(List.of(resultToString));
  }


  public static List<Integer> numberFromString(List<String> values) {
    return values.stream()
      .filter(s -> s != null && ! s.trim().isEmpty())
      .flatMap(s -> Arrays.stream(s.split("\\s*[+]\\s*")))
      .map(s -> {
        return numberAndException(s);
      })
      .toList();
  }

  static int numberAndException(String strings) {
    try {
      return Integer.parseInt(strings.trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("문자열이 숫자가 아닙니다 Error ",e);
    }
  }
}
