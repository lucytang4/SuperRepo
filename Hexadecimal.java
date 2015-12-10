/*
Lucy Tang
APCS1 pd5
HW45-Come Together
2015-12-10
*/

public class Hexadecimal implements Comparable{

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF";


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
        _decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
        _decNum = n;
	_hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
        _decNum = hexToDec(s);
	_hexNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
        return this._hexNum;  
    }

    //accessor
    public int getDec(){ return _decNum;}


    /*=====================================
      String decToHex(int) -- converts base-10 input to hexadecimal
      pre:  n >= 0
      post: returns String of bits
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "2"
      decToHex(14) -> "E"
      decToHex(16) -> "10"
      =====================================*/
    public static String decToHex( int n ) {
	String s = "";
	int q = n/16; //quotient
	int r = n%16; //remainder
	if (n == 0) return "0";
	while (!(q == 0 && r == 0)){
	    s = HEXDIGITS.substring(r,r+1) + s; //adding on the left
	    r = q%16;
	    q /= 16;
	}
	return s;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decToHexR(1) -> "1"
      decToHexR(2) -> "2"
      decToHexR(14) -> "E"
      decToHexR(16) -> "10"
      =====================================*/
    public static String decToHexR( int n ) { 
	int q = n/16;
	int r = n%16;
	if (n == 0) return "0";
	//base case: if the quotient cannot be recursed, terminate recursion.
	if( q == 0 && r == 0){
	    return "";
	}
	//recursion: add the Hexadecimal representation of the remainder to the returned string and recursively add decToHex of the quotient before it.
	else{
	    return decToHexR(q) + HEXDIGITS.substring(r,r+1);
	}
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to hexadecimal
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("2") -> 2
      hexToDec("E") -> 14
      hexToDec("10") -> 16
      =====================================*/
    public static int hexToDec( String s ) {
	int num = 0;
	int exp = s.length() - 1;
	for (int i = 0; i < s.length(); i++){
	    int value = HEXDIGITS.indexOf(s.substring(i,i+1));
	    num += value * Math.pow(16,exp);
	    //num += (Integer.parseInt(s.substring(i,i+1)) * Math.pow(2,exp));
	    //add on right
	    exp--;
	}
	return num;
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("2") -> 2
      hexToDecR("E") -> 14
      hexToDecR("10") -> 16
      =====================================*/
    public static int hexToDecR( String s ) {
	int exp = s.length() - 1;
	//base case: If length of string has reached 0, terminate recursion.
	if (s.length() == 0){
	    return 0;
	}
	//recursion: add the numbers (decimal representation of hexadecimal) * (16^digit location of hexadecimal - 1), and hexToDecR of the next digit to the stack.
	else{
	    int num = HEXDIGITS.indexOf(s.substring(0,1));
	    num *= Math.pow(16,exp);
	    return num + hexToDecR(s.substring(1));
	}
    }

    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexadecimal values
      =============================================*/
    public boolean equals( Object other ) { 
        return this.compareTo(other) == 0;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other == null)
	    throw new NullPointerException("input is a null");
	if (!(other instanceof Hexadecimal))
	    throw new ClassCastException("input not a Hexadecimal");
	Rational temp1 = new Rational(_decNum,1);
	Rational temp2 = null;
	if (other instanceof Rational)
	    temp2 = (Rational)other;
	if (other instanceof Binary)
	    temp2 = new Rational(((Binary)other).getDec(),1);
	if (other instanceof Hexadecimal)
	    temp2 = new Rational(((Hexadecimal)other).getDec(),1);
        //if (this._decNum > ((Hexadecimal)other)._decNum) return 1;
	//if (this._decNum < ((Hexadecimal)other)._decNum) return -1;
	//if (this._decNum > ((Hexadecimal)other).getDec()) return 1;
	//if (this._decNum < ((Hexadecimal)other).getDec()) return -1;
	//return 0;
	return temp1.compareTo(temp2);
    }


    //main method for testing
    public static void main( String[] args ) {
	
	System.out.println();
	System.out.println( "Testing ..." );

	System.out.println("Created Hexadecimal h1 with decimal value of 14.");
	Hexadecimal h1 = new Hexadecimal(14);
	System.out.println("Created Hexadecimal h2 with decimal value of 14.");
	Hexadecimal h2 = new Hexadecimal(14);
	System.out.println("Created Hexadecimal h3, alias of Hexadecimal h1 with decimal value of 14.");
	Hexadecimal h3 = h1;
	System.out.println("Created Hexadecimal h4 with decimal value of 16.");
	Hexadecimal h4 = new Hexadecimal(16);
	System.out.println("Created Hexadecimal h5 with decimal value of 0.");
	Hexadecimal h5 = new Hexadecimal(0);

	System.out.println("\ntoString() and decToHex.");
	System.out.println("h1.toString(), should return E.");
	System.out.println( h1 );//E
	System.out.println("h2.toString(), should return E.");
	System.out.println( h2 );//E
	System.out.println("h3.toString(), should return E.");
	System.out.println( h3 );//E  
	System.out.println("h4.toString(), should return 10.");
	System.out.println( h4 );//10  
	System.out.println("h5.toString(), should return 0.");
	System.out.println( h5 );

	System.out.println( "\n==..." );
	System.out.println("h1 == h2 should return false.");
	System.out.println( h1 == h2 ); //should be false
	System.out.println("h1 == h3 should return true.");
	System.out.println( h1 == h3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println("h1.equals(h2) should return true.");
	System.out.println( h1.equals(h2) ); //should be true
	System.out.println("h1.equals(h3) should return true.");
	System.out.println( h1.equals(h3) ); //should be true
	System.out.println("h3.equals(h1) should return true.");
	System.out.println( h3.equals(h1) ); //should be true
	System.out.println("h4.equals(h2) should return false.");
	System.out.println( h4.equals(h2) ); //should be false
	System.out.println("h1.equals(h4) should return false.");
	System.out.println( h1.equals(h4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println("h1.compareTo(h2) should return 0.");
	System.out.println( h1.compareTo(h2) ); //should be 0
	System.out.println("h1.compareTo(h3) should return 0.");
	System.out.println( h1.compareTo(h3) ); //should be 0
	System.out.println("h1.compareTo(h4) should return neg.");
	System.out.println( h1.compareTo(h4) ); //should be neg
	System.out.println("h4.compareTo(h1) should return pos.");
	System.out.println( h4.compareTo(h1) ); //should be pos
	
	System.out.println( "\n various conversion tests");
	System.out.println("hexToDec(E) should return 14.");
	System.out.println(hexToDec(h1._hexNum));
	System.out.println("hexToDecR(E) should return 14.");
	System.out.println(hexToDecR(h1._hexNum));
	System.out.println("decToHex(14) should return E.");
	System.out.println(decToHex(h1._decNum));
	System.out.println("decToHexR(14) should return E.");
	System.out.println(decToHexR(h1._decNum));
	System.out.println("hexToDec(10) should return 16.");
	System.out.println(hexToDec(h4._hexNum));
	System.out.println("hexToDecR(10) should return 16.");
	System.out.println(hexToDecR(h4._hexNum));
	System.out.println("decToHex(16) should return 10.");
	System.out.println(decToHex(h4._decNum));
	System.out.println("decToHexR(16) should return 10.");
	System.out.println(decToHexR(h4._decNum));
	System.out.println("hexToDec(0) should return 0.");
	System.out.println(hexToDec(h5._hexNum));
	System.out.println("hexToDecR(0) should return 0.");
	System.out.println(hexToDecR(h5._hexNum));
	System.out.println("decToHex(0) should return 0.");
	System.out.println(decToHex(h5._decNum));
	System.out.println("decToHexR(0) should return 0.");
	System.out.println(decToHexR(h5._decNum));
	 
    }//end main()

} //end class
