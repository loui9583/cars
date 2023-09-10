- Where and why you have used a @OneToMany annotation
I member og car, da både member og car kan være en del af mange
reservationer


- Where an why you have used a @ManyToOne annotation
I reservation da en reservation kun har en member og en car

- The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
CascadeType bestemmer hvad der sker med child columns hvis deres parent ændrer sig
  (fx hvis de bliver orphaned om de så skal slettes eller bare være orphaned)
FetchType: Lazy=Man får kun det men beder om. Eager: Du får alle rows
mappedBy: fortæller hvordan et relationship bliver mappet mellem to klasser

- How/where you have (if done) added user defined queries to you repositories
  I car repository


    @Query("SELECT c FROM Car c WHERE c.brand = :brand AND c.model = :model")
    List<Car> findByBrandAndModel(@Param("brand") String brand, @Param("model") String model);

    @Query("SELECT AVG(c.pricePrDay) FROM Car c")
    Double calculateAveragePricePerDay();

Da jeg ikke kunne bruge jpa navne queries til at få mit resultat (jeg ved i hvertfald ikke hvordan)
}


- a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
Tilføjet miljøvariabler inde i azure ligesom man skal gøre lokalt i Intellij
- a few words about where you have used inheritance in your project, and how it's reflected in your database
- What are the pros & cons of using the Single Table Strategy for inheritance?
?
- how are passwords stored in the database with the changes suggested in part-6 of the exercise
Jeg kunne ikke få det til at virke men jeg går ud fra det er lagret krypteret også har programmet en hemmelig
krypteringsnøgle
