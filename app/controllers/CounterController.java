package controllers;

import java.util.ArrayList;

import play.mvc.*;
import services.*;
import views.html.*;

public class CounterController extends Controller
{
	public static final CounterManager counterManager=new CounterManager();
	
	/*
	 * Method to render the page on client request.
	 * */
    public Result index()
    {
        return ok(index.render());
    }
    
    /*
     * Method called when one client attempts to open a WebSocket.
     * Creates an Actor to handle the connection.
     */
    public LegacyWebSocket<String> socket()
    {
    	return WebSocket.withActor(WebSocketActor::props);
    }
}