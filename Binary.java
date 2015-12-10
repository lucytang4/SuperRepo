/*
Lucy Tang
APCS1 pd5
HW45-Come Together
2015-12-10
*/

//skeleton file for class Binary

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
        _decNum = n;
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
        _decNum = binToDec(s);
	_binNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
        return this._binNum;  
    }

    //accessor
    public int getDec(){ return _decNum;}


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	String s = "";
        int q = n/2; //quotient
	int r = n%2; //remainder
	while (!(q == 0 && r == 0)){
	    s = r + s; //adding on the left
	    r = q%2;
	    q /= 2;
	}
	return s;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	int q = n/2;
	int r = n%2;
	if (!(q == 0 && r == 0)) return decToBinR(q) + "" + r; //add on left
	return "" + r;
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int num = 0;
	int exp = s.length() - 1;
        for (int i = 0; i < s.length(); i++){
	    num += (Integer.parseInt(s.substring(i,i+1)) * Math.pow(2,exp));
	    //add on right
	    exp--;
	}
	return num;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) {
	/*
	int num = Integer.parseInt(s.substring(0,1));
	if (s.length() > 0)
	    return (num * Math.pow(2,s.length()-1)) + binToDecR(s.substring(1)); //add on right
	return 0;
    }
	*/
	int exp = s.length() - 1;
	//base case: If length of string has reached 0, terminate recursion.
	if (s.length() == 0){
	  return 0;
	}
	//recursion: add the numbers (decimal representation of hexadecimal) * (16^digit location of hexadecimal - 1), and hexToDecR of the next digit to the stack.
	else{
	    int num = Integer.parseInt(s.substring(0,1));
	    num *= Math.pow(2,exp);
	    return num + binToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) {
	if (this == other){return true;}
	return this.compareTo(other) == 0; 
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other == null)
	    throw new NullPointerException("input is a null");
	if (!(other instanceof Binary))
	    throw new ClassCastException("input not a Binary");
	Rational temp1 = new Rational(_decNum,1);
	Rational temp2 = null;
	if (other instanceof Rational)
	    temp2 = (Rational)other;
	if (other instanceof Binary)
	    temp2 = new Rational(((Binary)other).getDec(),1);
	if (other instanceof Hexadecimal)
	    temp2 = new Rational(((Hexadecimal)other).getDec(),1);
        //if (this._decNum > ((Binary)other)._decNum) return 1;
	//if (this._decNum < ((Binary)other)._decNum) return -1;
	//if (this._decNum > ((Binary)other).getDec()) return 1;
	//if (this._decNum < ((Binary)other).getDec()) return -1;
	//return 0;
	return temp1.compareTo(temp2);
    }


    //main method for testing
    public static void main( String[] args ) {
	
	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 );//101
	System.out.println( b2 );//101
	System.out.println( b3 );//101  
	System.out.println( b4 );//111  

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	 
    }//end main()

} //end class
