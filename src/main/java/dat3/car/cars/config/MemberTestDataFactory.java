package dat3.car.cars.config;

import dat3.car.cars.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberTestDataFactory {
    public static List<Member> generateTestMembers(String passwordUsedByAll) {
        List<Member> members = new ArrayList<>();

        String[] firstNames = {"Anders", "Bent", "Christine", "Dorte", "Erik", "Freja", "Gustav", "Helle", "Inge", "Jens"};
        String[] lastNames = {"Nielsen", "Jensen", "Hansen", "Pedersen", "Andersen", "Christensen", "Larsen", "Sørensen", "Rasmussen", "Petersen"};
        String[] streets = {"Bredgade", "Kongens Nytorv", "Østergade", "Nørregade", "Vesterbrogade", "Amagertorv", "Frederiksborggade", "Strøget", "Gothersgade", "Rosenborggade"};
        String[] cities = {"København", "Aarhus", "Odense", "Aalborg", "Esbjerg", "Randers", "Kolding", "Horsens", "Vejle", "Roskilde"};
        String[] zips = {"1000", "8000", "5000", "9000", "6700", "8900", "6000", "8700", "7100", "4000"};

        for (int i = 1; i <= 50; i++) {
            String user = "member" + i;
            String email = user + "@testmail.com";
            String firstName = firstNames[i % 10];
            String lastName = lastNames[i % 10];
            String street = streets[i % 10] + " " + (i % 50 + 1);
            String city = cities[i % 10];
            String zip = zips[i % 10];

            Member member = new Member(user, passwordUsedByAll, email, firstName, lastName, street, city, zip);
            members.add(member);
        }
        return members;
    }
}