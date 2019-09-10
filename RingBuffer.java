/*  Name: Ethan Chen
 *  PennKey: etc
 *  Recitation: 217
 *
 *  Execution: java RingBuffer
 * 
 * Makes the ring buffer data structure
 */

public class RingBuffer {
    private double[] bufferArray; // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int currentSize;      // number of items in the buffer
    
    
    /*
     * Constuctor: creates a RingBuffer with the given capacity
     */
    public RingBuffer(int capacity) {
        bufferArray = new double[capacity];
        bufferArray[0] = first;
    }
    
    
    /*
     * Input: none
     * Output: the current size of the buffer as an int
     * 
     * Description: Returns the number of items currently in the buffer 
     */
    public int currentSize() {
        return currentSize;
    }
    
     
    /*
     * Input: none
     * Output: true if the buffer is empty, false if not
     * 
     * Description: Checks to see if the buffer is empty (size equals 0)
     */
    public boolean isEmpty() {
        if (currentSize == 0) return true;
        else return false;
    }
    
    
    /*
     * Input: none
     * Output: true if the buffer is full, false if not
     * 
     * Description: Checks to see if the buffer is full (size equals capacity)
     */
    public boolean isFull() {
        if (currentSize == bufferArray.length) return true;
        else return false;
    }
    
    
    /*
     * Input: x - the item to add
     * Output: void
     * 
     * Description: Add item x to the end of the buffer
     */
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("ERROR: Attempting to enqueue " +
                                       "to a full buffer.");
        }
        else {
            //first time enqueing, set inital position in buffer
            if (currentSize == 0) {
                bufferArray[0] = x;
                first = 0;
                last = 1;
                currentSize++;
            }
            else {
                //place subsequent items in proper places
                bufferArray[last] = x;
                //increment last and currentSize for next items
                last++;
                currentSize++;
                //makes sure it loops around
                if (last == bufferArray.length) {
                    last = 0;
                }
            }
        }
    }
    
    
    /*
     * Input: none
     * Output: the item at the front of the buffer
     * 
     * Description: Delete and return the item from the front of the buffer
     */
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to dequeue " +
                                       "from an empty buffer.");
        }
        else {
            //remove items at the front
            double front = bufferArray[first];
            bufferArray[first] = 0;
            //increment to make sure removing from the front
            first++;
            currentSize--;
            //loop around
            if (first == bufferArray.length) {
                first = 0;
            }
            return front;
        }
    }
    
    
    /*
     * Input: none
     * Output: the item at the front of the buffer
     * 
     * Description: Return, but do not delete, the item at the front
     */
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to peek " +
                                       "at an empty buffer.");
        }
        else {
            return bufferArray[first];
        }
    }
    
    /*
     * Input: none
     * Output: a copy of the buffer array
     * 
     * Description: Returns a COPY of the buffer array
     */
    public double[] getBufferArray() {
        return bufferArray;
    }
    

    /*
     * Input: none
     * Output: void
     * 
     * Description: Print the contents of the RingBuffer object for debugging
     */
    private void printBufferContents() {
        // print out first, last, and currentSize
        System.out.println("first:        " + first);
        System.out.println("last:         " + last);
        System.out.println("currentSize:  " + currentSize);
        
        // print bufferArray's length and contents if it is not null
        // otherwise just print a message that it is null
        if (bufferArray != null) {
            System.out.println("array length: " + bufferArray.length);
            System.out.println("Buffer Contents:");
            for (int i = 0; i < bufferArray.length; i++) {
                System.out.println(bufferArray[i]);
            }
        } else {
            System.out.println("bufferArray is null");
        }
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        // create a RingBuffer with bufferSize elements
        // where bufferSize is a command-line argument
        int bufferSize = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(bufferSize);
        
        // test code for RingBuffer
        buffer.enqueue(5);
        buffer.enqueue(3);
        buffer.dequeue();
        buffer.enqueue(7);
        System.out.println(buffer.peek());
        buffer.printBufferContents();
    }
        
}
