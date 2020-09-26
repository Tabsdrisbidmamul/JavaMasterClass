package com.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=dogs");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Chrome");

            urlConnection.setReadTimeout(30000);
            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if(responseCode != 200) {
                System.out.println("Error reading web page");
                System.out.println(urlConnection.getResponseMessage());
                return;
            }


            BufferedReader inputReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = "";
            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
            inputReader.close();


//            urlConnection.setDoOutput(true);
//            urlConnection.connect();
//
//            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
//            for (Map.Entry<String, List<String>> entry: headerFields.entrySet()) {
//                String key = entry.getKey();
//                List<String> values = entry.getValue();
//
//                System.out.println("key " + key);
//                for (String value: values) {
//                    System.out.println("\t\ttvalue " + value);
//                }
//
//            }

//            String line = "";
//            while (line != null) {
//                line = inputStream.readLine();
//                System.out.println(line);
//            }
//            inputStream.close();

//            URI uri = url.toURI();

//            System.out.println(
//                    "Scheme: " + uri.getScheme() + "\n" +
//                    "Scheme-specific part: " + uri.getSchemeSpecificPart() + "\n" +
//                    "Authority: " + uri.getAuthority() + "\n" +
//                    "User Info: " + uri.getUserInfo() + "\n" +
//                    "Host: " + uri.getHost() + "\n" +
//                    "Port: " + uri.getPort() + "\n" +
//                    "Path: " + uri.getPath() + "\n" +
//                    "Query: " + uri.getQuery() + "\n" +
//                    "Fragment: " + uri.getFragment()
//            );


//            URI uri = new URI("http://username:password@myservercom:5000/catalogue/phones?os=android#samsung");
//            URI baseUri = new URI("http://username:password@myservernewcom:5000");
//            URI uri1 = new URI("/catalogue/phones?os=android#samsung");
//            URI uri2 = new URI("/catalogue/tvs?manufacturer=samsung");
//            URI uri3 = new URI("/stores/locations?zip=12345");
//
//            URI resolvedURI1 = baseUri.resolve(uri1);
//            URI resolvedURI2 = baseUri.resolve(uri2);
//            URI resolvedURI3 = baseUri.resolve(uri3);
//
//            URL url1 = resolvedURI1.toURL();
//            URL url2 = resolvedURI2.toURL();
//            URL url3 = resolvedURI3.toURL();
//
//            System.out.println("URL1 = " + url1);
//            System.out.println("URL2 = " + url2);
//            System.out.println("URL3 = " + url3);
//
//            URI relativizedURI = baseUri.relativize(resolvedURI1);
//            System.out.println("Relativized URI = " + relativizedURI);

//            System.out.println(
//                    "Scheme: " + uri.getScheme() + "\n" +
//                    "Scheme-specific part: " + uri.getSchemeSpecificPart() + "\n" +
//                    "Authority: " + uri.getAuthority() + "\n" +
//                    "User Info: " + uri.getUserInfo() + "\n" +
//                    "Host: " + uri.getHost() + "\n" +
//                    "Port: " + uri.getPort() + "\n" +
//                    "Path: " + uri.getPath() + "\n" +
//                    "Query: " + uri.getQuery() + "\n" +
//                    "Fragment: " + uri.getFragment()
//            );

//            URI uri2 = new URI("hello");
//            System.out.println(
//                    "Scheme: " + uri2.getScheme() + "\n" +
//                            "Scheme-specific part: " + uri2.getSchemeSpecificPart() + "\n" +
//                            "Authority: " + uri2.getAuthority() + "\n" +
//                            "User Info: " + uri2.getUserInfo() + "\n" +
//                            "Host: " + uri2.getHost() + "\n" +
//                            "Port: " + uri2.getPort() + "\n" +
//                            "Path: " + uri2.getPath() + "\n" +
//                            "Query: " + uri2.getQuery() + "\n" +
//                            "Fragment: " + uri2.getFragment()
//            );
//        } catch (URISyntaxException e) {
//            System.out.println("URI Bad Syntax: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("URL Malformed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}

/*
* High Level APIs
* With these APIs, you don't need to handle with ports or sockets, instead we use URIs and URLs to handle traffic
* mainly over the Internet
*
* URL: is a resource identifier that has enough information that allows us to access the information
* URI: is a reassure identifier that does not have enough information to access the information, mainly it shows the
*      path to it
*
* URL is a subset of URI - as it shows both a path and the method to access the information
*
* Scheme
* A scheme is the part of a URI or URL that appears before the colon - e.g. HTTP, FTP, FILE etc.
*
* We mainly use the methods to create relative URIs (have not been converted to URLs) and convert them to URLs as
* most methods accept URIs to being with
*
* High Level API
* When working with this API the following classes we are going to be using are:
* - URI
* - URL
* - URLConnection
* - HttpURLConnection
*
* URI
* A URI can contain 9 components (URI = scheme:[//authority]path[?query][#fragment])
* 1. Scheme
* 2. Scheme-specific part
* 3. Authority
* 4. User-info
* 5. Host
* 6. Port
* 7. Path
* 8. Query
* 9. Fragment
*
* generic form
* https://en.wikipedia.org/wiki/Uniform_Resource_Identifier
*
* scheme:[//[user[:password]@]host[:port]][/path][?query][#fragment]
*
* When a URI specifies a scheme they are known as absolute URIs
* When a URi does not specifies a scheme they are known as relative URIs
*
*
* URI
* We can use this class to create a relative URI in the constructor - it throws URISyntaxException make sure to catch it
*   URI uri = new URI("db://username:password@myservercom:5000/catalogue/phones?os=android#samsung");
*
* We can extract the specifics from the URI like so
*             System.out.println(
                    "Scheme: " + uri.getScheme() + "\n" +
                    "Scheme-specific part: " + uri.getSchemeSpecificPart() + "\n" +
                    "Authority: " + uri.getAuthority() + "\n" +
                    "User Info: " + uri.getUserInfo() + "\n" +
                    "Host: " + uri.getHost() + "\n" +
                    "Port: " + uri.getPort() + "\n" +
                    "Path: " + uri.getPath() + "\n" +
                    "Query: " + uri.getQuery() + "\n" +
                    "Fragment: " + uri.getFragment()
            );
*
* We can convert the URI to a URL like so - using the method toURL() using the URI instance
*   URL url = uri.toURL();
*
* This method does throw MalformedURLException, make sure to catch it
*
* Now a problem can arise when converting to a URL (absolute URI), now like Paths we studied - relative URIs don't
* actually have to exist - they will not throw an error if so.
*
* The only time when it does, is when we convert a reactive URI to an absolute URI - here in this case we do get an
* exception because our made up URI does not exist
*
* By making the URI to an relative URI and we attempt to convert it to an URL - we get a IllegalArgumentException -
* because to convert a URI to a URL - the URI must be an absolute URI - meaning that it contains the: Scheme, the
* Authority, the User-info, Port and Host
*
* Without those there is not a method to access the files or resources from a specified location on the Internet
* It simply does not have enough information to know what to do with a relative path
*
*           URI uri = new URI("/catalogue/phones?os=android#samsung");
            URL url = uri.toURL();
*
*
* In these instances we would want to use a base URI
*
* Base URI
* A base URI will contain the specifics to say a webpage for example - we write a URI that contains the scheme, the
* credentials, the port and the host
*
* From there we would resolve() (append) the relative paths to the Base URIs to form an absolute path
*
*           URI baseUri = new URI("http://username:password@myservercom:5000");
            URI uri = new URI("/catalogue/phones?os=android#samsung");
            URI resolvedURI = baseUri.resolve(uri);
*
*
*
* Reading data from a Webpage
* There are are 2 ways of doing so, using URl or URLConnection
*
* URL
* So we will still need to catch MalformedURLExceptions and IOExceptions here, but the gist of what we are doing here is
* - We create a URL instance using this URL http://example.org/
* - We create a BufferedReader instance, and we use an InputStreamReader() with the value being passed is a Stream
*   using the URl instances openStream() method - which at a high level is establishing a TCP connection using the
*   port, host and other metadata from the URL - this will then create a Stream which we can get from the server
* - We then do the usual to read from a BufferedReader and output the contents of the webpage - which is the raw HTML
*
*           URL url = new URL("http://example.org/");

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = "";
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();
*
*
* URLConnection
* This method allows us to configure conditions on the URLConnection - like get input or write output to a server etc.
* The configuring must be done before the connection is established (connect()) and after the connection - or the
* location of the host has been found (openConnection()).
*
* To write to a webpage it must have a form on the page to allow input to it
*
* But the general is almost identical to the URL method
*
* - We create a URLConnection instance and pass the URL instance to access the method getConnection()
* - We can then do any configuring to the connection here - and it must be done before the call to connect(), if done
*   after then it will result in an error
* - We establish the connection using the connect() method from the URLConnection instance
* - The InputStreamReader will now take the getInputStream() method instead
*           URL url = new URL("http://example.org/");

            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = "";
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();
*
* HttpURLConnection
* This class is sub-class to URLConnection, this class offers more HTTP side of features compared to its parent class
*
* Now in this block of code we have not called the connect(), instead methods like getResponseCode() or
* getInputStream() implicitly call the connect() method within its own method - so calling connect() will be
* redundant - of course calling connect() ourselves does not result in an error - as it will check if connect() has
* been called before.
*
*           URL url = new URL("http://example.org/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Chrome");

            urlConnection.setReadTimeout(30000);
            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if(responseCode != 200) {
                System.out.println("Error reading web page");
                return;
            }


            BufferedReader inputReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = "";
            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
            inputReader.close();
*
* Response Messages
* We can add the getResponseMessage() method to see a detailed response message as to why our request to a webpage
* failed
*             if(responseCode != 200) {
                System.out.println("Error reading web page");
                System.out.println(urlConnection.getResponseMessage());
                return;
            }
*
* POST (written, no examples)
* They are many places to write to forms and send it to a server to have verified in a DB later on
* We would do everything as normal
* - Create a HttpURLConnection instance of the web page
* - Open the connection
* - Configuring the following:
*   - We set the setRequestMethod() to "POST"
*   - We set the setRequestProperty() to "User-Agent", "Chrome" (or any over alternate browser)
*   - We set the setRequestMethod() to "Content-Type", "application/x-www-form-urlencoded"
* - We create a String instance of the parameters -> parameters = "parameter1=25&parameter2=hello" for example
* - We again set the setRequestMethod() again to "Content-Length", Integer.toString(parameter.getBytes().length)
*   We need to tell the HTML how long our input is in bytes, so we convert the String to bytes then get its length
* - We set the setUseCaches() method to false
* - We set the setDoOutput() and setDoInput() methods to true (we want to read and write data to the HTML)
* - We can create a DataOutputStream to write the byte information to the HTML - make sure to both flush and close
*   the stream
* - We then can create a InputStream to read the values
*
* Third-party libraries
* It is recommended to not use java.net packages - as they have been around since Java 1.0 and not have been updated
* to HTTP 5.0 since Java 8
*
* It is better to use Jetty (Eclipse)
* https://www.eclipse.org/jetty/download.html
*
* or Apache
* http://hc.apache.org/downloads.cgi
*
*
*
*
* */
