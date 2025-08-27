package com.calculator2.calculatorV2.io;

import com.calculator2.calculatorV2.OperationController;
import com.calculator2.calculatorV2.operation.firstClassCollection.OperationManager;
import com.calculator2.calculatorV2.operation.plusOperation.OperationStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputHandlerTest {

  private OperationController operationController = new OperationController();
  private List<Integer> changeToNumber = new ArrayList<>();
  private OperationManager operationManager = new OperationManager();

  @Test
  @DisplayName("계산기 숫자입력과 합계를 출력한다.")
  public void testNumbersAndSumValues() {

    //given
    OperationStrategy strategy= operands -> operands.stream().reduce(0, Integer::sum);

    //when
    List<Integer> input=List.of(2,30,55);

    //then
   assertEquals(87,strategy.apply(input));
//    assertThat(input).isEqualTo(87);
  }
}
