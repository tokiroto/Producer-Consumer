/*
    This file is part of ProducerConsumer.

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package tokiroto.concurency;

/**
 * ArrayQueue which stores the data with a customizable size
 * 
 * @author Tokiroto
 */
public class ArrayQueue {
    private int[] storage;
    private int size = 0;
    
    public ArrayQueue(int size) {
        this.storage = new int[size];
    }
    
    /**
     * Gets size of the queue
     * @return Integer
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Adds new element into the queue
     * @param num 
     */
    public synchronized void push(int num) {
        if (size < storage.length) {
            storage[size] = num;
            size++;
        }
        notifyAll();
    }
    
    /**
     * Returns an element 
     * @return Integer
     */
    public synchronized int pop() {
        if (size > 0) {
           return storage[0];
        }
        return 0;
    }
    
    /**
     * Removes an element from the top
     */
    public synchronized void remove() {
        if (size > 0) {
            size--;
            for (int i = 0; i < storage.length; i++) {
                if ((i+1) <= size) {
                    storage[i] = storage[i+1];
                }else{
                    storage[i] = 0;
                }
           }
        }
        notifyAll();
    }
    
    public synchronized boolean hasSpace() {
        return (size < storage.length);
    }
    
    /**
     * Check the element for existence
     * @return boolean
     */
    public synchronized boolean isEmpty() {
        return size <= 0;        
    }
}
