package services;

/*
 * Defines the behaviour of a Counter Listener.
 * A Counter Listener is notified of new counter values.
 */
public interface CounterListener
{
	public void onCounterValue(CounterEvent event);
}