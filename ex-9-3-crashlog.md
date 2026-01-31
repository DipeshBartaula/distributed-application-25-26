2026-01-31T07:33:13.908+01:00 ERROR 85250 --- [demo] [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

   orderController defined in file [/Users/dipeshbartaula/Desktop/Hochschule Labs/Distributed Application/distributed-applications-25-26/target/classes/com/hsfulda/demo/products/controller/OrderController.class]
      ↓
   orderAdapter defined in file [/Users/dipeshbartaula/Desktop/Hochschule Labs/Distributed Application/distributed-applications-25-26/target/classes/com/hsfulda/demo/products/services/OrderAdapter.class]
      ↓
   orderFacade defined in file [/Users/dipeshbartaula/Desktop/Hochschule Labs/Distributed Application/distributed-applications-25-26/target/classes/com/hsfulda/demo/products/facade/OrderFacade.class]
┌─────┐
|  orderService (field private com.hsfulda.demo.user.UserService com.hsfulda.demo.products.services.OrderService.userService)
↑     ↓
|  userServiceImpl (field private com.hsfulda.demo.products.services.OrderService com.hsfulda.demo.user.UserServiceImpl.orderService)
└─────┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.

