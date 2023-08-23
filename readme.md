## The idea with, and reasons for why to use, a ORM-mapper

En ORM-mapper er et værktøj, der forenkler arbejdet med både objektorienterede programmeringssprog og relationelle
databaser. Den oversætter automatisk data mellem de to formater, hvilket gør det muligt for udviklere at interagere med
databaseelementer, som om de var almindelige objekter. Dette reducerer behovet for komplekse SQL-forespørgsler og manuel
oversættelse mellem objekter og database-tabeller, hvilket resulterer i mere vedligeholdelig og effektiv kode.
___

## The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected

### _Hibernate og ORM:_

Hibernate er et kraftfuldt framework, der revolutionerer måden, vi interagerer med databaser på, ved at tilbyde utallige
fordele og forenkle komplekse opgaver. Det introducerer konceptet Object-Relational Mapping (ORM), der gør det muligt
for udviklere at arbejde med databaser ved hjælp af objektorienterede programmeringsprincipper.

### _Database Uafhængig Query:_

Hibernate Query Language (HQL) fungerer som den objektorienterede modpart til SQL. Den genererer databaseuafhængige
forespørgsler og eliminerer behovet for at skrive forespørgsler, der er specifikke for hver database. Denne innovation
forhindrer de vedligeholdelsesproblemer, der opstår, når databaser ændres for et projekt.

### _Automatisk Tabeloprettelse:_

Hibernate strømliner processen med at oprette databasetabeller ved at tilbyde automatisk tabeloprettelseskapacitet.
Manuel oprettelse af tabeller bliver unødvendig, da Hibernate tager sig af denne opgave og forbedrer udviklingsarbejdet.

### _Forenkler Komplekse Joins:_

Håndtering af komplekse datahentninger, der involverer flere tabeller, bliver ubesværet i Hibernate-frameworket. Dets
robuste funktioner gør datahentning fra forskellige tabeller til en enkel proces, hvilket bidrager til forbedret
produktivitet.

### _ORM Værktøj:_

Et ORM-værktøj forenkler forskellige aspekter af datamanagement, herunder oprettelse, manipulation og adgang. Denne
teknik mapper objekter til data, der er gemt i databaser, og broen mellem objektorienteret programmering og relationelle
databaser.

### _Java Persistence API (JPA):_

Java Persistence API (JPA) udgør en vigtig standard for objektorienteret databaseopbevaring i Java-applikationer. Ved at
definere grænseflader og annoteringer letter JPA den problemfri kortlægning af Java-objekter til databasetabeller og
omvendt.
JPA udnytter Java's objektorienterede natur til at interagere med relationelle databaser. Gennem
javax.persistence-pakken leverer den en omfattende samling af klasser og grænseflader, der udgør kernen i
JPA-funktionalitet.

### _Spring Data JPA:_

Spring Data JPA, en del af det større Spring Data-økosystem, forenkler implementeringen af JPA-baserede repositories.
Spring Data JPA forenkler udviklingen af Spring-drevne applikationer, der anvender datatilgangsteknologier. Det
eliminerer behovet for at skrive overdreven standardkode og letter oprettelsen af effektive datatilgangslag.

### _Forbedret Datatilgang:_

Med Spring Data JPA er udfordringerne forbundet med implementering af et datatilgangslag markant reduceret. Udviklere
kan oprette repository-grænseflader, inklusive brugerdefinerede findermetoder, mens Spring automatiserer
implementeringsprocessen.

Hibernate sammen med Java Persistence API og Spring Data JPA en stærk trio, der transformerer måden, applikationer
interagerer med databaser på.

___

## How to create simple Java entities and map them to a database via the Spring Data API

Opret en Java-klasse og tilføj @Entity-annotationen for at gøre den til en databaseentitet.
Definér attributter og brug @Id og @GeneratedValue til primær nøgle.
Brug @Column til at tilpasse kolonneindstillinger som navn og størrelse.
Brug Lombok-annotationer som @Getter, @Setter og @NoArgsConstructor.
Konfigurér databaseforbindelsen.
Hibernate opretter tabeller baseret på dine klasser ved opstart.
## How to control the mapping between individual fields in an Entity class and their matching columns in the database

Det gjorde min repo klasse helt magisk

## How to auto generate IDs, and how to ensure we are using a specific database's preferred way of doing it (Auto Increment in our case for MySQL)

@GeneratedValue

## How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern

Jeg brugte det standard repository metoder såsom save, saveall, findall osv. Så lavede jeg to metoder "selv" så jeg havde noget af teste på,
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);

## How to write simple "integration" tests, using H2 as a mock-database instead of MySQL

Jeg lavede en simpel junit klasse og gav den annotationen @DataJpaTest også @Autowired jeg min repo klasse 

## How to add (dev) connection details for you local MySQL database

jeg bruge miljø variabler til det, i min application.properties klasse skrev jeg spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_USERNAME}
spring.datasource.password=${JDBC_PASSWORD}

også i edit configuration environmental variables skrev jeg så min database login JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/pizza?user=root;JDBC_PASSWORD=****;JDBC_USERNAME=root
