package com.calculator2.calculatorV2.io;

import com.calculator2.calculatorV2.OperationController;
import com.calculator2.calculatorV2.operation.plusOperation.OperationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.*;


public class InputHandlerTest {

  OperationController controller = new OperationController();

  @Test
  @DisplayName("사용자가 계산기 숫자문자를 입력하여 숫자를 생성한다.")
  public void adaptNumbersByInput() {

//given
    InputHandler handler = new InputHandler(controller);
//when
    int numbers = OperationType.SUM.execute(List.of(1, 2, 3));
//then
    assertThat(numbers).isEqualTo(6);

  }

  @Test
  @DisplayName("사용자가 숫자나 구분자 입력은 모두 더하기로 계산한다.")
  public void plusToChangeFromString() {
    //given
    String firstInput = "1,2|3\n4:5";

    //when
    List<String> express = controller.separatorInput(firstInput);
    boolean check = express.stream()
      .allMatch(s->s.matches("\\d+")); //모든요소가 숫자인가?

    //then
    assertThat(check).isTrue();
  }

}
