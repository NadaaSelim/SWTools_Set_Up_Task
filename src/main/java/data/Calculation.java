package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int calcID;
	
	private int number1;
	private int number2;
	private String operation;
	private double result;
	
	
	
	public double perform() {
		 switch (operation) {
         case "+":
             result  = number1 + number2;
             break;
         case "-":
        	 result  = number1 - number2;
             break;
         case "*":
        	 result  = number1 * number2;
             break;
         case "/":
        	 result  = number1 / number2;
             break;
         default:
             break;
		 }
		 
		 return result;
	}

	public int getCalcID() {
		return calcID;
	}

	public void setCalcID(int calcID) {
		this.calcID = calcID;
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
	

}
