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
 * Main class to test
 * @author Tokiroto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int size = 5;
        int times = 5;

        ArrayQueue queue = new ArrayQueue(size);
        Producer producer = new Producer(queue, times, 1);
        Producer producer2 = new Producer(queue, times, 2);
        Consumer consumer = new Consumer(queue, times * 2, 1);
        //Consumer consumer2 = new Consumer(queue, times, 2);
        producer.start();
        producer2.start();
        consumer.start();
        //consumer2.start();
        //testQueue(queue);
    }
    
    static void testQueue(ArrayQueue queue) {
        Random rand = new Random();
        while (queue.hasSpace()) {
            queue.push(rand.nextInt(10));
        }
        
        int num = queue.pop();
        queue.remove();
        num = queue.pop();
        queue.remove();
        queue.push(5);
        System.out.println("Queue output : \n");
        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
            queue.remove();
        }
    }
}
