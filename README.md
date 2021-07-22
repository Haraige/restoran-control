# Restoran-control

## Welcome to the app!

<p>You can launch services from Intellij IDEA or used the launch-*.sh scripts</p>

### Ports
<ul>
    <li>localhost:8080 - Auth Server (Keycloak)</li>
    <li>localhost:8761 - Discovery Service (Spring Eureka)</li>
    <li>localhost:9061 - Api Gateway (Spring Cloud Gateway)</li>
    <li>localhost:8095 - Hall Service</li>
    <li>localhost:8086 - Personnel Department Service</li>
</ul>

###Auth info
<ul>
    <li>Keycloak admin panel (login: admin, password: Pa55w0rd)</li>
    <li>Default waiter (login: waiter, password: 123)</li>
    <li>Default cooker (login: cooker, password: 123)</li>
</ul>

### ABOUT APP

<p>This application gives you the opportunity to book tables in a restaurant
for a specific time, allows the administrator to change their location
and add new ones, waiters can view reservations on their tables,
the administrator can accept and fire employees. The dishes ordered in the
hall will be transferred to the kitchen service, where they will be
distributed among the chefs, depending on their workload 
and specialization.</p>

#### ADDITIONAL INFO
<ul>
    <li>At this moment you can interact with by using SwaggerUI or Postman</li>
    <li>Currently you can add cookers and waiters only through Keycloak
(soon going be adding opportunity to create personnel only through
personnel department)</li>
    <li>Now tests are absent(</li>
</ul>

#### <i>App not finished at all, and it will be developing in future