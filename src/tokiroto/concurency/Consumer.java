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

import java.util.Random;

/**
 * Consumer Class
 * @author Tokiroto
 */
public class Consumer extends Thread {
    private ArrayQueue store;
    private Random rand;
    private int number;
    private int products = 0;
    
    public Consumer(ArrayQueue store, int prodCount, int number) {
        this.store = store;
        this.rand = new Random();
        this.number = number;
        this.products = prodCount;
    }
    
    public void run() {
        while (products > 0) {
            if (!store.isEmpty()) {
               System.out.println("Consumer #" + this.number + " got : " + store.pop());
               store.remove(); 
               products--;                             
            }else{
               sleepThread();
            }
        }
        System.out.println("Consumer #" + this.number + " consumed all the products!");
    }
    
    private void sleepThread() {
        try {
            synchronized(this){
                wait(rand.nextInt(50) * 10);
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " thread #" + this.number + " was interrupted!");
        }  
    }
}
