package com.calculator2.calculatorV2.operation.firstClassCollection;

import com.calculator2.calculatorV2.operation.plusOperation.OperationStrategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.calculator2.calculatorV2.operation.firstClassCollection.OperationManager.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class OperationManagerTest {
  Map<String, OperationStrategy> operations = new HashMap<>();

  @Test
  @DisplayName("숫자와 더하기 연산을 계산한다.")
  public void testCalculate() throws NullPointerException {
    //given
    List<String> operators = new ArrayList<>();
    OperationStrategy strategy = operands -> operands.stream().reduce(0, Integer::sum);
    String express = "1,3+5:8|1";
    Matcher operatorsSign = Pattern.compile("[+]").matcher(express);
    while (operatorsSign.find()) {
      operators.add(operatorsSign.group());
    }

    //when
    int expectedResult = 18;
    List<Integer> operands = List.of(1, 3, 5, 8, 1);
    int result = strategy.apply(operands);

    //then
    assertThat(operators).contains("+");
    assertThat(result).isEqualTo(expectedResult);
  }

  @DisplayName("사용자가 구분자에 해당하는 3가지기호가 입력시 더하기 연산을 실행한다.")
  @ParameterizedTest
  @CsvSource({"','", "'|'", "':'"})
  public void testThreeSeparatorsAndPlus(String separator) {
    assertNotNull(separator);
    //given
    String firstInput = "1" + separator + "3" + separator + "5";
    String firstSplit = firstInput.replaceAll("[,|:\\\\n]+", "+");
    List<Integer> operands = extractNumbers(firstSplit);
    List<String> operators = extractOperators(firstSplit);

    //when
    int numbers = calculate(operators, operands);
    String changeToString = Integer.toString(numbers);

    //then
    assertThat(firstInput).containsPattern(Pattern.quote(separator));
  }

  @Test
  @DisplayName("입력받은 계산기 문자열들은 비어있지않고 붙여서 숫자로 처리한다.")
  public void testnumberFromString() {
    //given
    String convertNumbers = "1,2|3\n4:5";

    //when
    // Matcher strings = Pattern.compile("\\s*[+]\\s*").matcher("1+2+3");
    String replaced = convertNumbers.replaceAll("[,|:\\n]+", "+");
    String[] parts = replaced.split("\\s*[+]\\s*");
    List<Integer> numbers = Arrays.stream(replaced.split("\\+"))
      .filter(s -> ! s.trim().isEmpty())
      .map(Integer::parseInt)
      .toList();

    //then
    assertThat(numbers).containsExactly(1, 2, 3, 4, 5);

  }

  @DisplayName("문자열 입력 시 숫자 변환 예외 발생 확인 테스트입니다.")
  @ParameterizedTest
  @ValueSource(strings = {"abc", "가나다"})
  public void numberAndExceptionTest(String input) {
    assertThrows(RuntimeException.class, () -> numberAndException(input));

  }

}
