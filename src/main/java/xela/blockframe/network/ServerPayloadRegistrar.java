package xela.blockframe.network;

import xela.blockframe.network.payloads.movement.DoubleJump;


///The server payload register actually registers and defines what to do when a packet is inbound
/// It's important to note: the server HANDLES MOVEMENT!
/// For example, the player starts sprinting, until the the server gives the ok to the client the player CANNOT SPRINT
/// This is as far as i understand what's happening

public class ServerPayloadRegistrar {
    static public  void init(){

        DoubleJump.registerDoubleJump();
    }




}
