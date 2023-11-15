import org.omg.CORBA.*;
import HelloWorld.HelloWorld;
import HelloWorld.HelloWorldHelper;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            NameComponent path[] = ncRef.to_name("HelloWorld");
            HelloWorld helloWorld = HelloWorldHelper.narrow(ncRef.resolve(path));

            String message = helloWorld.sayHello();
            System.out.println("Message from server: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
