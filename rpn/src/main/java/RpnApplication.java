import java.util.stream.Stream;

public class RpnApplication {
  public static void main(String[] args) {
    System.out.println(eval("2 3 +"));
    System.out.println(eval("2 3 -"));
    System.out.println(eval("2 3 /"));
    System.out.println(eval("2 3 *"));
  }

  public static float eval(String s) {
    String[] tokens = s.split(" ");
    if (tokens.length != 3) {
      throw new IllegalArgumentException("Unparseable evaluation string");
    }
    String op = tokens[2];
    if (!(op.equals("+") ||
          op.equals("-") ||
          op.equals("/") ||
          op.equals("*"))) {
      throw new IllegalArgumentException("Operand is not valid");
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
