(Spørgsmål er besvaret under)
## Mulige API kald: 
### Cars
- GET /api/cars
- GET /api/cars/includeAll 
- GET /api/cars/id/{id}
- GET /api/cars/id/{id}/includeAll
- POST /api/cars
- PUT /api/cars/id/{id}
- PATCH /api/cars/id/pricePrDay/{id}/{pricePrDay}
- PATCH /api/cars/id/bestDiscount/{id}/{bestDiscount}
### Members
- GET /api/members
- GET /api/members/includeAll
- GET /api/members/{username}
- GET /api/members/{username}/includeAll
- POST /api/members
- PUT /api/members/{username}
- PATCH /api/members/ranking/{username}/{value}
- DELETE /api/members/{username}
____________
## Afleveringsspørgsmål:

### What are the benefits of using a RESTful API, what is JSON, and why does JSON fit so well with REST?

Enkelhed: RESTful API'er er lette at forstå og implementere.
Løs kobling: API'en og klienten er uafhængige, hvilket muliggør ændringer i en komponent uden at påvirke den anden.
Brugen af JSON med RESTful API'er er godt, fordi:
Læsbarhed: JSON er let at læse for både mennesker og maskiner. Det at repræsentere objekter i et
simplet tekstformat gør at alle slags systemer kan forstå og bruge JSON.
Dataudveksling: JSON tillader effektiv og pålidelig dataudveksling mellem klient og server.
Letvægt: JSON er en letvægtsdataformat, der kræver mindre båndbredde.
Fleksibilitet: Det kan repræsentere forskellige typer data og strukturer.

### How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data?
### What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints?

Jeg har oprettet entiteter, der repræsenterer bil og member objekter Hibernate sørger for at oprette entity klasserne som database tables.
Jeg har brugt DTO'er (Data Transfer Objects) til at adskille API-laget fra datalaget. Jeg har oprettet to DTO'er: *Request og *Response som giver mig mulighed for at styre, hvordan data overføres mellem klient og server.
Request-klasser:
Request-klasser bruges til at definere, hvordan data skal sendes fra klienten til serveren. De hjælper med at validere og strukturere de data, klienten sender til serveren, og sikrer, at de opfylder de nødvendige krav. Request-klasser hjælper også med at beskytte mod sikkerhedssårbarheder og adskille inputdata fra forretningslogikken.
Response-klasser:

### Explain shortly the concept mocking in relation to software testing
### How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code

Mocking er lave en midlertig database der mimere den rigtige databases opførsel eller
man kan bruge mockito hvor man lave nogen fake reponses på kald og man så kan arbejde med den respons ligesom hvis det var
en rigtig database der havde givet dataen.
Response-klasser bruges til at definere, hvordan data skal sendes fra serveren til klienten som svar på en anmodning. De hjælper med at formatere og styre, hvilke oplysninger der skal vises for klienten. Response-klasser giver også mulighed for at formatere data ensartet og beskytte følsomme oplysninger.

### Explain the concept Build Server and the role Github Actions play here

Byggeserver: Bruges til at opbygge og teste softwaren under udvikling.
Produktionsserver: Bruges til at køre den færdige og testede software, der er klar til brug af brugerne i produktionen.

GitHub Actions giver mulighed for at automatisere bygnings- og testprocessen hver gang der er en ændring i dit GitHub-repository. Dette sikrer, at software altid bygges og testes på en konsistent måde.
Det fungerer som en CI-platform, der udløser byggeopgaver, når kode ændres. Dette hjælper med at identificere fejl tidligt i udviklingsprocessen.

###  Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
Maven giver en standardiseret mappestruktur hvilket gør det nemt at automatisere testning under bygning af programmet. Vores pom.xml gør det også nemt at bygge projektet og downloade dependencies automatisk.
Maven har også Lifecycle pluginsne som gør det nemt at deploye projektet og sikre sig at alt virker og er tester og bygget før det bliver lagt op.
Vores yaml fil på github actions kan så kalde maven metoder og deploye helt automatisk.

### -  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin

MySQL er DBaaS (Database as a Service), hvorved det er en administreret databasehostingtjeneste. DBaaS administrerer databaseinfrastrukturen, så brugere undgår selv at skulle lave servervedligeholdelse og backups.
Azure Web App er PaaS (Platform as a Service), hvilket er en administreret hostingplatform til webapplikationer. PaaS abstraherer infrastrukturhåndtering, så udviklere kan fokusere på at udvikle og deploye applikationskode uden at bekymre sig om infrastrukturen.
