package net.statifybot.croupier.game.draw;

public class SelectedNumber {

	int num;
	String color;
	boolean even;
	String row;
	String column;
	boolean firstHalf;

	public SelectedNumber(int num) {
		this.num = num;

		switch (num) {

		case 0:
			this.color = null;
			this.row = null;
			this.column = null;
			break;

		case 1:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 2:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 3:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 4:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 5:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 6:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 7:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 8:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 9:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 10:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 11:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 12:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "first twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 13:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 14:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 15:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 16:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 17:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 18:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 19:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 20:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 21:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 22:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 23:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 24:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "second twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 25:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 26:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 27:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "thrid twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 28:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 29:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 30:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 31:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 32:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 33:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		case 34:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "3rd column";
			this.firstHalf = num <= 18;
			break;

		case 35:
			this.color = "black";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "2nd column";
			this.firstHalf = num <= 18;
			break;

		case 36:
			this.color = "red";
			this.even = num % 2 == 0;
			this.row = "third twelve";
			this.column = "1st column";
			this.firstHalf = num <= 18;
			break;

		default:

			throw new IndexOutOfBoundsException("Number \"" + num + "\" is not in range");

		}
	}

	public String getColor() {
		return this.color;
	}

	public boolean isEven() {
		return this.even;
	}

	public String getRow() {
		return this.row;
	}

	public String getColumn() {
		return this.column;
	}

	public boolean isFirstHalf() {
		return this.firstHalf;
	}
	
	public String getEven() {
		if(this.even) {
			return "even";
		} else {
			return "odd";
		}
	}
	
	public String getHalf() {
		if(this.firstHalf) {
			return "1-18";
		} else {
			return "19-36";
		}
	}
	}
