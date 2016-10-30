package services;

import java.util.EventObject;

/*
 * Definition of the Counter Event, which contains information
 * about the source of the event and the counter value.
 */
public class CounterEvent extends EventObject
{
    private int counterValue;
    
    public CounterEvent(Object source,int counterValue)
    {
        super(source);
        this.counterValue=counterValue;
    }
    
    public int getCounterValue()
    {
        return counterValue;
    }
}