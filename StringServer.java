import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler{
    
    String stringsStored = "";
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("String Server");
        } else {
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    if (num == 0) {
                        stringsStored = String.format("%s", parameters[1]);
                    } else {
                        stringsStored = String.format("%s%n%s",stringsStored, parameters[1]);
                    }
                    num++;
                    return stringsStored;
                }
            }        
            return String.format("404 Not found!");
        }
    }
}
    class StringServer {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }
    
            int port = Integer.parseInt(args[0]);
    
            Server.start(port, new Handler());
        }
}
