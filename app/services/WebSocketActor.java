package services;

import akka.actor.*;
import controllers.CounterController;
import play.libs.Json;
import services.WebSocketFrame.FrameType;

public class WebSocketActor extends UntypedActor implements CounterListener
{
    public static Props props(ActorRef out)
    {
        return Props.create(WebSocketActor.class,out);
    }

    private ActorRef out;

    public WebSocketActor(ActorRef out)
    {
        this.out = out;
    }
    
    @Override
    public void preStart()
    {
    	/*Subscribes listener on socket connection*/
    	CounterController.counterManager.addCounterListener(this);
    }

    @Override
    public void onReceive(Object message) throws Exception
    {
    	/*If a frame of the type INCREMENT is received, then the counter is incremented*/
    	if(message instanceof String)
    	{
    		WebSocketFrame frame=Json.fromJson(Json.parse((String)message), WebSocketFrame.class);
    		
    		if(frame.type==FrameType.INCREMENT)
    		{
				CounterController.counterManager.increment();
    		}
    	}
    }
    
    @Override
    public void postStop()
    {
    	/*Removes listener when the connection is dropped*/
    	CounterController.counterManager.removeCounterListener(this);
    }

	@Override
	public void onCounterValue(CounterEvent event)
	{
		/*
		 * Creates a frame of the type UPDATE, containing the counter value
		 * and sends it through the WebSocket
		 */
		WebSocketFrame frame=new WebSocketFrame();
		frame.type=FrameType.UPDATE;
		frame.data=Integer.toString(event.getCounterValue());
		
		out.tell(Json.toJson(frame).toString(),self());
	}
}