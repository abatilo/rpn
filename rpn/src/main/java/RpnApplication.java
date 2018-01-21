import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RpnApplication {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("CTRL + C to exit");
    System.out.print("# ");
    while (s.hasNextLine()) {
      try {
        System.out.println("=> " + eval(s.nextLine()));
      } catch (Exception e) {
        System.out.println("Invalid expression");
      }
      System.out.print("# ");
    }
  }

  public static String eval(String s) {
    StringTokenizer tokenizer = new StringTokenizer(s);
    ArrayDeque<String> stack = new ArrayDeque<>();
    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      if (isOperator(token)) {
        String rightOperand = stack.pop();
        String leftOperand = stack.pop();
        stack.push(String.valueOf(evalSingle(leftOperand + " " + rightOperand + " " + token)));
      } else {
        stack.push(token);
      }
    }
    if (stack.size() != 1) {
      throw new IllegalArgumentException("Invalid expression");
    }
    return stack.pop();
  }

  private static boolean isOperator(String op) {
    return (op.equals("+") ||
        op.equals("-") ||
        op.equals("/") ||
        op.equals("*"));
  }

  private static float evalSingle(String s) {
    String[] tokens = s.split(" ");
    if (tokens.length != 3) {
      throw new IllegalArgumentException("Unparseable evaluation string");
    }

    Float leftOperand = 0.0f;
    try {
      leftOperand = Float.valueOf(tokens[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("First operand is not a valid float");
    }

    Float rightOperand = 0.0f;
    try {
      rightOperand = Float.valueOf(tokens[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Second operand is not a valid " +
          "float");
    }

    String op = tokens[2];
    if (!(op.equals("+") ||
          op.equals("-") ||
          op.equals("/") ||
          op.equals("*"))) {
      throw new IllegalArgumentException("Operand is not valid");
          }

    switch (op) {
      case "+":
        return leftOperand + rightOperand;
      case "-":
        return leftOperand - rightOperand;
      case "/":
        return leftOperand / rightOperand;
      case "*":
        return leftOperand * rightOperand;
      default:
        return 0.0f;
    }
  }
}
