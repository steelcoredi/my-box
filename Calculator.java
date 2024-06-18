import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
  private static final Map<Character, Integer> toArabMap = new HashMap<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите выражение: ");
    String ex = sc.nextLine();
    try {
	System.out.println("Результат: ");
	if (isRoman(ex)) {
	  System.out.println(calcRom(ex));
	} else {
	  System.out.println(calcArab(ex));
	}
    } catch (Exception exception) {
	System.out.println("Ошибка: " + exception.getMessage());
    }
  }

  static {
    toArabMap.put('I', 1);
    toArabMap.put('V', 5);
    toArabMap.put('X', 10);
  }

  private static boolean isRoman(String ex) {
    return ex.matches("[IVX+\\-*/]+");
  }

  private static int romanToInteger(String roman) {
    int result = 0; //
    int prevValue = 0;
    for (char c : roman.toCharArray()) {
	int value = toArabMap.get(c);
	if (value > prevValue) {
	  result += value - 2 * prevValue;
	  result += value;
	}
	prevValue = value;
    }
    return result;
  }

  private static String integerToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    int[] arr = {10, 9, 5, 4, 1};
    String[] symbols = {"X", "IX", "V", "IV", "I"};
    for (int i = 0; i < arr.length && num >= 0; i++) {
	while (num >= arr[i]) {
	  num -= arr[i];
	  sb.append(symbols[i]);
	}
    }
    return sb.toString();
  }

  private static int calcArab(String expression) throws Exception {
    String[] sign = expression.split("(?<=[-+*/])|(?=[-+*/])");
    int result = Integer.parseInt(sign[0]);
    if (result < 1 || result > 10) {
	throw new Exception("Числа должны быть от 1 до 10 включительно");
    }
    for (int i = 1; i < sign.length; i += 2) {
	String operator = sign[i];
	int operand = Integer.parseInt(sign[i + 1]);
	if (operand < 1 || operand > 10) {
	  throw new Exception("Числа должны быть от 1 до 10 включительно");
	}
	switch (operator) {
	  case "+":
	    result += operand;
	    break;
	  case "-":
	    result -= operand;
	    break;
	  case "*":
	    result *= operand;
	    break;
	  case "/":
	    result /= operand;
	    break;
	  default:
	    throw new Exception("Не допустимый оператор");
	}
    }
    return result;
  }

  private static String calcRom(String expression) throws Exception {
    String[] signs = expression.split("(?<=[-+*/])|(?=[-+*/])");
    int c = romanToInteger(signs[0]); // к 33
    if (c < 1 || c > 10) {
	throw new Exception("Числа должны быть от I до X включительно");
    }
    for (int i = 1; i < signs.length; i += 2) {
	String operator = signs[i];
	int operand = romanToInteger(signs[i + 1]);
	if (operand < 1 || operand > 10) {
	  throw new Exception("Числа должны быть от I до X включительно");
	}
	switch (operator) {
	  case "+":
	    c += operand;
	    break;
	  case "-":
	    c -= operand;
	    break;
	  case "*":
	    c *= operand;
	    break;
	  case "/":
	    c /= operand;
	    break;
	  default:
	    throw new Exception("Не допустимый оператор");
	}
    }
    return integerToRoman(c);
  }
}
