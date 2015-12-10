/*
Lucy Tang
APCS1 pd5
HW45-Come Together
2015-12-10
*/

/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *****************************/

public class SuperArray{

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor â€“ initializes 10-item array
    public SuperArray() {
        _data = new Comparable[10];
        _size = 0;
        _lastPos = -1;
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
        String ret = "[";
        for(int x = 0; x<_size; x++) ret+=_data[x]+",";
        if(ret.length()>1) return ret.substring(0,ret.length()-1) + "]";
        else return ret + "]";
    }

		
    //double capacity of this SuperArray
    private void expand() {
        Comparable[] temp = new Comparable[_size*2];
        for (int x = 0; x<_size ; x++) temp[x] = get(x);
        _data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { 
        return _data[index];
    }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) { 
        Comparable temp = _data[index];
        _data[index] = newVal;
        return temp;
    }
    
    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add (Comparable newVal) {
        _lastPos+=1;
        _size+=1;
        if(_lastPos != _data.length) _data[_lastPos] = newVal;
        else { 
            expand();
            _data[_lastPos] = newVal; }
    }
    
    //inserts an item at index
    //shifts existing elements to the right
    //FAILS for index >= _size
    public void add( int index, Comparable newVal ) { 
        if(index > _data.length) {
            System.out.println("input a valid index u fool");
            System.out.println("nothing changed");
        }
        else {
            _size+=1;
            _lastPos+=1;
            for (int x = _lastPos ; x>index ; x--) {
                _data[x] = _data[x-1]; }
            _data[index] = newVal;
        }
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) { 
        _size -= 1;
        for (int i = index; i < _lastPos; i++) _data[i] = _data[i + 1];
        _lastPos -= 1;
    }


    //return number of meaningful items in _data
    public int size() { 
        return _size;
    }

    //index of first occurence of target, iterative
    public int linSearch(Comparable other){
	for (int pos = 0; pos < _data.length; pos++){
	    if (_data[pos].equals(other)){
		return pos;
	    }
	}
	return -1;
    }

    //sees if _data is sorted
    public boolean isSorted(){
	for (int i = 0; i <  _data.length; i++){
	    if (_data[i].compareTo(_data[i+1]) > 0) return false;
	}
	return true;
    }
	    /*
	    //use Rational
	    Rational temp1 = null;
	    Rational temp2 = null;
	    if (_data[i] instanceof Rational){
		if (_data[i+1] instanceof Rational && _data[i].compareTo(_data[i+1]) > 0) return false;
		else{
		    temp1 = (Rational)_data[i];
		    if (_data[i+1] instanceof Binary)
			temp2 = new Rational(((Binary)_data[i+1]).getDec(),1);
		    if (_data[i+1] instanceof Hexadecimal)
			temp2 = new Rational(((Hexadecimal)_data[i+1]).getDec(),1);
		    //if (temp1.compareTo(temp2) > 0) return false;
		}
	    }
	    if (_data[i] instanceof Binary){
		if (_data[i+1] instanceof Binary && _data[i].compareTo(_data[i+1]) > 0) return false;
		else{
		    temp1 = new Rational(((Binary)_data[i]).getDec(),1);
		    if (_data[i+1] instanceof Rational)
			temp2 = (Rational)_data[i+1];
		    if (_data[i+1] instanceof Hexadecimal)
			temp2 = new Rational(((Hexadecimal)_data[i+1]).getDec(),1);
		    //if (temp1.compareTo(temp2) > 0) return false;
		}
	    }
	    if (_data[i] instanceof Hexadecimal){
		if (_data[i+1] instanceof Hexadecimal && _data[i].compareTo(_data[i+1]) > 0) return false;
		else{
		    temp1 = new Rational(((Hexadecimal)_data[i]).getDec(),1);
		    if (_data[i+1] instanceof Binary)
			temp2 = new Rational(((Binary)_data[i+1]).getDec(),1);
		    if (_data[i+1] instanceof Rational)
			temp2 = (Rational)_data[i+1];
		    //if (temp1.compareTo(temp2) > 0) return false;
		}
	    }
	    if (temp1.compareTo(temp2) > 0) return false;
	}
	return true;
    }
    */
	    

    //main method for testing
    public static void main( String[] args ) {

	SuperArray a = new SuperArray();
	//a._data[0] = new Rational(2,3);
	//a._data[1] = new Binary(2);
	//a._data[2] = new Rational(5,2);
	//a._data[3] = new Rational(7,2);
	//a._data[4] = new Hexadecimal(16);

	a.add(new Rational(2,3));
	a.add(new Binary(2));
	a.add(new Rational(5,2));
	a.add(new Rational(7,2));
	a.add(new Hexadecimal(16));

	Rational b = new Rational(4,6);
	Binary c = new Binary("10");
	Hexadecimal d = new Hexadecimal("E");
	
	System.out.println(a.linSearch(b)); //should be 0
	System.out.println(a.linSearch(c)); //should be 1
	System.out.println(a.linSearch(d)); //should be 4
	System.out.println(a.isSorted()); //should be true


		
    }//end main
		
}//end class
