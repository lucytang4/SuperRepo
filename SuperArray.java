/*
Team Right Hand Drive-- Lucy Tang, Alan Chen
APCS1 pd5
HW42--Array of Titanium
2015-12-6
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

public class SuperArray implements ListInt{

		//~~~~~INSTANCE VARS~~~~~
		//underlying container, or "core" of this data structure:
    private int[] _data;

		//position of last meaningful value
    private int _lastPos;

		//size of this instance of SuperArray
	private int _size;

		
		//~~~~~METHODS~~~~~
    //default constructor â€“ initializes 10-item array
    public SuperArray() {
        _data = new int[10];
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
        int[] temp = new int[_size*2];
        for (int x = 0; x<_size ; x++) temp[x] = get(x);
        _data = temp;
    }

		
    //accessor -- return value at specified index
    public int get( int index ) { 
        return _data[index];
    }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) { 
        int temp = _data[index];
        _data[index] = newVal;
        return temp;
    }
    
     // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add (int newVal) {
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
    public void add( int index, int newVal ) { 
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
        _data[_lastPos] = 0;
        _lastPos -= 1;
    }


    //return number of meaningful items in _data
    public int size() { 
        return _size;
    }

		//main method for testing
	public static void main( String[] args ) {
		ListInt test = new SuperArray();

		System.out.println(((SuperArray)test)._size); // 0
		System.out.println(((SuperArray)test)._lastPos); // -1
		test.add(3);
		test.add(3);
		test.add(3);
		test.add(1,5);
		test.add(11,5);
		System.out.println(test); // [3,5,3,3]
		System.out.println(((SuperArray)test)._size); // 4
		System.out.println(((SuperArray)test)._lastPos); // 3
		test.remove(1);
		System.out.println(test); //[3,3,3]
		System.out.println(((SuperArray)test)._size); //3
		System.out.println(((SuperArray)test)._lastPos); //2
		System.out.println(test.size()); //3
		test.set(1,10);
		System.out.println(test); //[3,10,3]
		
	}//end main
		
}//end class
