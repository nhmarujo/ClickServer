package services;

/*
 * Definition of the frames exchanged through the WebSocket.
 * A simpler approach could have been chosen (with no complex object),
 * but this one is better for scalability, organization, and demonstration purposes.
 */
public class WebSocketFrame
{
	public FrameType type;
	public String data;

	/*Possible types of frame*/
	public enum FrameType
	{
	    INCREMENT,
	    UPDATE
	}
}