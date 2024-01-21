# fxconcurrent
Boilerplate framework for using JavaFX Concurrent API for Client/Server calls and Task Progress (via ControlsFX).

This library provides boilerplate classes for using the JavaFX Concurrent API in conjunction with ControlsFX Task Progress View, with minimal couplings between the two but nevertheless making more sense to bundle together in one library as the GUI part is very small and is needed by one of the Service-oriented classes due to how the progress viewer binds to the services and tasks.

Although this was written as a full working example of a client/server model where the server is represented by an HTTP Servlet, the majority of the code is not dependent on that client/server model being present, and the authorization services don't use the progress viewer, so almost everything is optional, and the classes have tried to allow for derivation and reuse in other architectures. At the very least, the code examples solve a lot of tricky problems that aren't always easy to find in documentation.

