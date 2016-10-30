package services;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Definition of Counter Manager, which holds the actual counter
 * and manages the listener subscription and notifications.
 */
public class CounterManager
{
	/*The actual counter, already providing atomic operations.*/
	private AtomicInteger counter;
	private ArrayList<CounterListener> listeners;
	
	public CounterManager()
	{
		counter=new AtomicInteger(0);
		listeners=new ArrayList<>();
	}
	
	public synchronized void addCounterListener(CounterListener l)
	{
		/*When a new listener subscribes, the current counter value is sent to it.*/
		l.onCounterValue(new CounterEvent(this,counter.get()));

		listeners.add(l);
    }
    
    public synchronized void removeCounterListener(CounterListener l)
    {
    	listeners.remove(l);
    }
     
    private synchronized void fireCounterEvent(int counterValue)
    {
        CounterEvent event=new CounterEvent(this,counterValue);
        
        listeners.forEach(p->p.onCounterValue(event));
    }
    
    public void increment()
    {
    	/*Increments counter and notifies all listeners of the new value*/
    	fireCounterEvent(counter.incrementAndGet());
    }
}