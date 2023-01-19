import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler{
    
    ArrayList<String> stringsStored = new ArrayList<String>();
    ArrayList<String> searchResults = new ArrayList<String>();
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Search Engine");
        } else if (url.getPath().equals("/list")) {
            return String.format("Not yet implemented.");
        } else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    num++;
                    stringsStored.add(parameters[1]);
                    return String.format("'%s' added to our Search Engine. Search Engine now contains %d Strings", parameters[1], num);
                }
            }        
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                searchResults.clear();
                if (parameters[0].equals("s")) {
                    for (int i = 0; i < stringsStored.size(); i++) {
                        if (stringsStored.get(i).contains(parameters[1])) {
                            searchResults.add(stringsStored.get(i));
                        }
                    }
                    return String.format("Search Results: %s", searchResults.toString());
                }
            }
            return String.format("404 Not found!");
        }
    }
}
    class SearchEngine {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }
    
            int port = Integer.parseInt(args[0]);
    
            Server.start(port, new Handler());
        }
}
