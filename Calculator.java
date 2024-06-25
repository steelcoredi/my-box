import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static final Map<Character, Integer> toArabMap = new HashMap<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите выражение: ");
    String ex = sc.nextLine();
    try {
	System.out.println("Результат: ");
	System.out.println(calc(ex));
    } catch (Exception exception) {
	System.out.println("Ошибка: " + exception.getMessage());
    }
  }
 static {
    toArabMap.put('I', 1);
    toArabMap.put('V', 5);
    toArabMap.put('X', 10);
  }
  static String calc(String input) throws Exception {
    if (isRoman(input)) {
	return calcRom(input);
    } else {
	return String.valueOf(calcArab(input));
    }
  }

  static boolean isRoman(String ex) {
    return ex.matches("[IVX+\\-*/]+");
  }

  static int romanToInteger(String roman) {
    int result = 0;
    int prevValue = 0;
    for (int i = roman.length() - 1; i >= 0; i--) {
	int value = toArabMap.get(roman.charAt(i));
	if (value < prevValue) {
	  result -= value;
	} else {
	  result += value;
	}
	prevValue = value;
    }
    return result;
  }

  static String integerToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    int[] arr = {10, 9, 5, 4, 1};
    String[] symbols = {"X", "IX", "V", "IV", "I"};
    for (int i = 0; i < arr.length; i++) {
	while (num >= arr[i]) {
	  sb.append(symbols[i]);
	  num -= arr[i];
	}
    }
    return sb.toString();
  }

  static int calcArab(String expression) throws Exception {
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

  static String calcRom(String expression) throws Exception {
    String[] signs = expression.split("(?<=[-+*/])|(?=[-+*/])");
    int c = romanToInteger(signs[0]);
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
    if (c == 0) {
	throw new Exception("Римское число не может быть равно 0");
    }
    return integerToRoman(c);
  }
}
