/*
Lucy Tang
APCS1 pd5
HW45-Come Together
2015-12-10
*/

public class Rational implements Comparable{
    //instance variables for class Rational
    private int numerator;
    private int denominator;
    //=======================================================================================
    //constructors
    public Rational(){ //default constuctor sets value of instance to 0/1
	numerator=0;
	denominator=1;
    }
    public Rational(int num, int den){
	if (den==0){
	    numerator=0; //resets value of instance to 0/1 if denominator is 0
	    denominator=1;
	    System.out.println("Please enter non-zero denominator!");
	}
	else{ //redefine values of instance
	    numerator=num;
	    denominator=den;
        }
    }
    //=======================================================================================
    //methods
    public String toString(){ //returns a string representation of the rational number
	String rationalNum = "" ;
    
	rationalNum += numerator;
	rationalNum += "/" ;
	rationalNum += denominator;
	return rationalNum; //format: "numerator/denominator"
    }    
    public double floatValue(){ //returns a floating point value of nuumber
        double returnValue; //using the most precise floating point primitive
	
        returnValue = ((double)numerator)/denominator;
        //calculates the rational number with precision by converting the first int
	//to a double, thus making the end value have double precision
        return returnValue;
    }  
    public void multiply(Rational frac){ //Rational object * this Rational object
	numerator *= frac.numerator; //multiplies numerators
	denominator *= frac.denominator; //multiplies denominators
    }     
    public void divide(Rational frac){ // Rational object / this Rational object
        numerator *= frac.denominator; //multiplies numerator by divisor's denominator
	denominator *= frac.numerator; //multiplies denominator by divisor's numerator 
    }
    public void add(Rational frac){ //Rational object + this Rational object
	if (denominator == frac.denominator){ 
	    numerator += frac.numerator; //add numerators if denominators are equal
	}
	else{ //cross multiply and simplify
	    int objectNumerator = numerator*frac.denominator;
	    int thisNumerator = frac.numerator*denominator;

	    numerator = objectNumerator + thisNumerator;
	    denominator *= frac.denominator;
	}
    }
    public void subtract(Rational frac){ //Rational object - this Rational object
	if (denominator == frac.denominator){
	    numerator -= frac.numerator; //subtract numerators if denominators are equal
	}
	else{ //cross multiply and simplify
	    int thisNumerator = numerator*frac.denominator;
	    int objectNumerator = frac.numerator*denominator;

	    numerator = thisNumerator - objectNumerator;
	    denominator *= frac.denominator;
	}
    }
    public int gcd(int a, int b){ //finds greatest common divisor between a and b
        int largerNum;
        int currentGCD;
        int remainder;
        
        if (a > b){ //determines larger of the two numbers
            largerNum = a;
            currentGCD = b;
            remainder = a % b;
        }
        else{
            largerNum = b;
            currentGCD = a;
            remainder = b % a;
        }
        
        while (remainder > 0){ //applies Euclid's algorithm
            largerNum = currentGCD;
            currentGCD = remainder;
            remainder = largerNum % currentGCD;
        }
        
        return currentGCD;
    }
    public void reduce(){ //reduces this Rational to lowest terms
	int gcd = gcd(this.numerator, this.denominator);

	numerator /= gcd;
	denominator /= gcd;
    }
    public int compareTo(Object other){ //compares the values of two fractions
	if (other == null)
	    throw new NullPointerException("input is a null");
	if (!(other instanceof Rational))
	    throw new ClassCastException("input not a Rational");
	reduce();
	((Rational)other).reduce();
	int thisNumerator = numerator*((Rational)other).denominator; // cross multiply
        int objectNumerator = ((Rational)other).numerator*denominator;
	if (thisNumerator > objectNumerator) // if this is greater than frac
	    return 1;
    	if (thisNumerator < objectNumerator) // if this is less than frac
	    return -1;
    	return 0; // if this is equal to frac
    }
    public boolean equals(Object other){
        return this.compareTo(other) == 0;
    }
	
    //=======================================================================================
    //main method
    public static void main (String[] args) { 
        Rational r = new Rational(2,4); 
        Rational s = new Rational(1,2);
	System.out.println(r.equals(s)); //true
	Rational x = new Rational(1,3);
	System.out.println(r.equals(x)); //false
	/*	TEST METHODS - HW33
		System.out.println(r); //(2/3)
		System.out.println(s); //(1/2)
		r.add(s); //(2/3)+(1/2)
		System.out.println(r); //(7/6)
		System.out.println(s); //(1/2)
		r.subtract(s); //(7/6)-(1/2)
		System.out.println(r); //(8/12)
		System.out.println(s); //(1/2)
		r.reduce();//(8/12) -> (2/3)
		System.out.println(r);//(2/3)
		TEST METHODS - HW32
		System.out.println(s.floatValue()); //should output 0.5
		System.out.println(r); //should output 2/3
		System.out.println(s); //should output 1/2 
		r.multiply(s); //(2/3)*(1/2) 
		System.out.println(r); //should output 2/6 
		System.out.println(s); //should output 1/2 
		r.divide(s); //(2/6)/(1/2) 
		System.out.println(r); //should output 4/6 
		System.out.println(s); //should output 1/2 
	*/
    } //end main
    
} //end Rational

