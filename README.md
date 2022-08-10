##  Trilha microservicos

### Desafios realizados:


### Desafio Opcional 2 - Service Discovery (Serviço de Descoberta): 
  
 Service Discovery é um tipo de catálogo que vai conter o endereço de todas as
instâncias e microsserviços que estão registrados nele.
  
Vantagens de se utilizar o service discovery em Microsserviços:
-   Ter um serviço onde as instâncias possam se registrar;
-   Descobrir de forma dinâmica, o endereço das instâncias do serviço que deseja consumir.
  
Então a proposta é termos um projeto que vai atuar no service discovery. Para isso, vamos ter um servidor e os microsserviços que vão poder se registrar nele para ocorrer a comunicação.

Para criarmos o servidor com o service discovery vamos usar o Eureka Server. 
Para o service discovery, crie o projeto chamado "server" utilizando o SpringInitializr. Como parte da configuração, adicione a dependência Eureka Discovery Server.  

### Desafio Opcional 3:   

Quando o pagamento for confirmado, uma mensagem será enviada para o microsserviço de pedidos, indicando que o pedido está pago.

Para isso, o microsserviço de pagamento deve realizar  uma requisição do tipo PUT passando o Id do pedido, e o status como pago. O microsserviço de pedidos recebe a requisição, e troca o status.

Para realizar a comunicação síncrona, utilize o Spring Cloud OpenFeign, que é uma solução do Spring que utiliza um cliente HTTP justamente para fazer integrações de Back-End para Back-End.
