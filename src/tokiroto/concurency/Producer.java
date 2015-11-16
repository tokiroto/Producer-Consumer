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
 * Producer Class
 * @author Tokiroto
 */
public class Producer extends Thread {
    private Random rand;
    private ArrayQueue storage;
    private final int RANGE = 100;
    private int tasks;
    private int product;
    private int number;
    
    public Producer(ArrayQueue storage, int timesToProduce, int number) {
        this.rand = new Random();
        this.storage = storage;
        this.tasks = timesToProduce;
        this.number = number;
    }
    
    public void run() {
        while(tasks > 0) {
            if (storage.hasSpace()) {
                this.product = rand.nextInt(RANGE);
                storage.push(this.product);
                System.out.println("Producer #" + this.number + " produced : " + this.product);
                tasks--;
            }else{
                sleepThread();
            } 
        }
        System.out.println("Producer #" + this.number + " produced all the products!");
    }
        
    private synchronized void sleepThread() {
        try {
            synchronized(this) {
                wait(rand.nextInt(100) * 10);
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " thread #" + this.number + " was interrupted!");
        }  
    }
}
