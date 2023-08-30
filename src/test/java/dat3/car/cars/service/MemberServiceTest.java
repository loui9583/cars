package dat3.car.cars.service;

import dat3.car.cars.dto.MemberRequest;
import dat3.car.cars.dto.MemberResponse;
import dat3.car.cars.entity.Member;
import dat3.car.cars.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;

    @BeforeEach
    void setUp() {
        // Opret to testmedlemmer før hver test
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository);
    }

    @Test
    void testGetMembersAllDetails() {
        // Test at medlemmer inkl. alle detaljer ikke er null
        List<MemberResponse> members = memberService.getMembers(true);
        for (MemberResponse member : members) {
            assertNotNull(member.getUsername());
            assertNotNull(member.getEmail());
            assertNotNull(member.getFirstName());
            assertNotNull(member.getLastName());
            assertNotNull(member.getStreet());
            assertNotNull(member.getCity());
            assertNotNull(member.getZip());
            assertNotNull(member.getCreated());
            assertNotNull(member.getEdited());
            assertNotNull(member.getRanking());
            assertNotNull(member.getApproved());
        }
    }

    @Test
    void testGetMembersNoDetails() {
        // Test at medlemmer uden detaljer har detaljerne som null
        List<MemberResponse> members = memberService.getMembers(false);
        for (MemberResponse member : members) {
            assertNull(member.getCreated());
            assertNull(member.getEdited());
            assertNull(member.getRanking());
            assertNull(member.getApproved());
        }
    }

    @Test
    void testFindByIdFound() {
        // Test at et medlem findes ved brugernavn
        assertEquals("user1", memberService.getMemberByUsername("user1").getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        // Test at en fejl kastes ved forsøg på at finde et ikke-eksisterende medlem
        assertThrows(ResponseStatusException.class, () -> memberService.findById("user3"));
    }

    @Test
    void testAddMember_UserDoesNotExist() {
        // Test at et medlem kan tilføjes hvis det ikke eksisterer i forvejen
        Member member = new Member("user3", "pw3", "em3", "fN3", "lN3", "strt3", "city3", "zip");
        MemberRequest memberRequest = new MemberRequest(member);
        memberService.addMember(memberRequest);
        assertEquals("em3", memberService.getMemberByUsername("user3").getEmail());
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        // Test at en fejl kastes hvis et medlem forsøges tilføjet, men allerede eksisterer
        MemberRequest memberRequest = new MemberRequest(memberService.getMemberByUsername("user1"));
        assertThrows(ResponseStatusException.class, () -> memberService.addMember(memberRequest));
    }

    @Test
    void testEditMemberWithExistingUsername() {
        // Test at medlemmets information kan redigeres med et eksisterende brugernavn
        //Metoden editMember tager imod et username og et MemberRequest, så det username det bliver
        //fundet et Member fra, bliver ændret til værdierne i fra memberRequest.

        Member member = memberService.getMemberByUsername("user1");
        MemberRequest memberRequest = new MemberRequest(member);
        memberRequest.setCity("Copenhagen");
        memberService.editMember(memberRequest, "user1");
        assertEquals("Copenhagen", memberService.getMemberByUsername("user1").getCity());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        // Test at en fejl kastes hvis medlemsinformation forsøges redigeret med ikke-eksisterende brugernavn
        Member member = memberService.getMemberByUsername("user1");
        MemberRequest memberRequest = new MemberRequest(member);
        memberRequest.setCity("Copenhagen");
        memberService.editMember(memberRequest, "user1");
        assertThrows(ResponseStatusException.class, () -> memberService.editMember(memberRequest, "Paul"));
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        // Test at en fejl kastes hvis medlems brugernavn forsøges ændret
        MemberRequest memberRequest = new MemberRequest(memberService.getMemberByUsername("user1"));
        memberRequest.setUsername("John");
        assertThrows(ResponseStatusException.class, () -> memberService.editMember(memberRequest, "user1"));
    }

    @Test
    void testSetRankingForUser() {
        // Test at en medlems rangering kan ændres og er korrekt efter ændring
        memberService.setRankingForUser("user1",10);
        assertEquals(10, memberService.getMemberByUsername("user1").getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        // Test at en fejl kastes hvis medlems rangering forsøges ændret for et ikke-eksisterende medlem
        assertThrows(ResponseStatusException.class, () -> memberService.getMemberByUsername("user3").setRanking(5));
    }

    @Test
    void testDeleteMemberByUsername() {
        // Test at et medlem kan slettes ved brugernavn og ikke kan findes efter sletning
        memberService.deleteById("user1");
        assertEquals(1, memberService.getMembers(false).size());
        assertThrows(ResponseStatusException.class, () -> memberService.findById("user1"));
    }
}


