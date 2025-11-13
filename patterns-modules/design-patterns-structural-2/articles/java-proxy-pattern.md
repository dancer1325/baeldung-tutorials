https://www.baeldung.com/java-proxy-pattern

* Proxy pattern
  * allows us to
    * 👀create an intermediary👀 / 
      * acts -- as an -- interface to another resource
      * hide the component's underlying complexity
  * use cases
    * heavy Java object (_Examples:_ JDBC connection or SessionFactory) / 
      * requires initial configuration
      * be initialized on demand,
      * AFTERWARD, reuse them -- for -- ALL calls
  * uses
    * Virtual Proxy
      * 's goal
        * simplified version -- of -- complex OR heavy object
      * == skeleton object / 
        * on demand (lazy initialization), loads the original object
    * Remote Proxy
      * 's goal
        * original object
          * present | DIFFERENT address space
          * LOCALLY represented
      * responsible for
        * doing ALL necessary boilerplate stuff
          * _Example:_ creating and maintaining the connection, encoding, decoding, etc.
    * Protection Proxy
      * 's goal
        * add a layer of security | original underlying object
          * Reason:🧠provide controlled access -- based on -- access rights of the client🧠

* _Example:_
  * [here](../src/main/java/com/baeldung/proxy)
