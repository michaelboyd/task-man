package com.taskman;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class TaskManagerApplicationTests {
	
	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//given the following inputs
		int numOne = 20;
		int numTwo = 30;
		
		//when the method is executed 
		int result = underTest.add(numOne, numTwo);
		
		//then the result is 50
		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}
	
	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
		
	}

}
