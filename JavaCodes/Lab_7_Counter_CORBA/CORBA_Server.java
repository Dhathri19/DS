// CORBA is a set of specifications that enable communication between distributed objects in a networked system.

// To create a simple CORBA based client-server based application-
// 1. IDL(Interface Definition Language)--> IDL is used to define the interfaces of objects that will be accessible remotely.
// 2. Use IDL to generate Java stubs and skeletons. 
// 3. Stubs are used to make remote calls and skeletons are used on server side to receive and process calls.
// 4. Server side implementation--> Extend the generated skeleton and implement the methods defined in the IDL interface.

// ORB (Object Request Broker)--> Handling remote method invocations.

import org.omg.CORBA.*;
import HelloWorld.HelloWorldHelper;

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            HelloWorldImpl helloWorld = new HelloWorldImpl();

            // Register the HelloWorld object with the ORB
            orb.connect(helloWorld);

            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            NameComponent path[] = ncRef.to_name("HelloWorld");
            ncRef.rebind(path, helloWorld);

            System.out.println("Server is ready and waiting for client requests...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
