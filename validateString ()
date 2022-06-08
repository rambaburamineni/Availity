import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class ValidateString {

	public void validateString(String input) {
		Stack<Character> stack = new Stack<Character>();
		try {
			for (int i = 0; i < input.length(); i++) {
				char ch = input.charAt(i);
				if (ch == '(') {
					stack.push(ch);
					continue;
				}
				if (ch == ')') {
					stack.pop();
				}
			}
			if (stack.isEmpty())
				System.out.println("Given String is Valid");
			else
				System.out.println("Given String is not Valid one");
		} catch (EmptyStackException e) {
			System.out.println("Given String is not Valid one");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String that needs to be validated");
		String input = sc.nextLine();
		ValidateString validateString = new ValidateString();
		validateString.validateString(input);
		sc.close();
	}

}
