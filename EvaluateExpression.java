
public class EvaluateExpression {

	int position = -1;
	int charAtPos;
	String equation;
	boolean infinity;

	public EvaluateExpression(String equation) {
		this.equation = equation.replaceAll("3.14159", "180");
		infinity = false;
	}

	public boolean getInfinity() {
		return this.infinity;
	}

	void nextCharacter() {
		if (++position < equation.length()) {
			charAtPos = equation.charAt(position);
		} else {
			charAtPos = -1;
		}
	}

	boolean parseChar(int charToParse) {
		while (charAtPos == ' ') {
			nextCharacter();
		}
		if (charAtPos == charToParse) {
			nextCharacter();
			return true;
		}
		return false;
	}

	double evaluateExpression() {
		nextCharacter();
		final double x = parseExpression();
		if (position < equation.length()) {
			throw new RuntimeException("Unexpected: " + (char) charAtPos);
		}
		return x;
	}

	double parseExpression() {
		double x = parseTerm();
		for (;;) {
			if (parseChar('+')) {
				x += parseTerm(); // addition
			} else if (parseChar('-')) {
				x -= parseTerm(); // subtraction
			} else {
				return x;
			}
		}
	}

	double parseTerm() {
		double x = parseFactor();
		for (;;) {
			if (parseChar('*')) {
				x *= parseFactor(); // multiplication
			} else if (parseChar('/')) {
				x /= parseFactor(); // division
			} else {
				return x;
			}
		}
	}

	double parseFactor() {
		if (parseChar('+')) {
			return parseFactor(); // unary plus
		}
		if (parseChar('-')) {
			return -parseFactor(); // unary minus
		}

		double x;
		final int startPos = position;
		if (parseChar('(')) { // parentheses
			x = parseExpression();
			parseChar(')');
		} else if ((charAtPos >= '0' && charAtPos <= '9') || charAtPos == '.') { // numbers
			while ((charAtPos >= '0' && charAtPos <= '9') || charAtPos == '.') {
				nextCharacter();
			}
			x = Double.parseDouble(equation.substring(startPos, position));
		} else if (charAtPos >= 'a' && charAtPos <= 'z') { // functions
			while (charAtPos >= 'a' && charAtPos <= 'z') {
				nextCharacter();
			}
			final String function = equation.substring(startPos, position);
			x = parseFactor();
			x = evaluateFunction(function, x);
		} else {
			throw new RuntimeException("Unexpected: " + (char) charAtPos);
		}

		if (parseChar('^')) {
			x = Math.pow(x, parseFactor()); // exponentiation
		}

		return x;
	}

	double evaluateFunction(String funcName, double param) {
		double result = param;
		if (funcName.equals("ln")) {
			result = Math.log(param);
		} else if (funcName.equals("sqrt")) {
			result = Math.sqrt(param);
		} else if (funcName.equals("sin")) {
			result = Math.sin(Math.toRadians(param));
		} else if (funcName.equals("cos")) {
			result = Math.cos(Math.toRadians(param));
		} else if (funcName.equals("tan")) {
			System.out.println(Math.cos(Math.toRadians(param)));
			if (Math.round(Math.cos(Math.toRadians(param))) == 0.0) {
				this.infinity = true;
			}
			result = Math.tan(Math.toRadians(param));
		} else {
			throw new RuntimeException("Unknown function: " + funcName);
		}

		return result;
	}
}
