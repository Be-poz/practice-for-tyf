package com.example.practicefortyf.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class TeamTest {

    @Autowired
    private EntityManager em;


    @Test
    public void fetchJoinTest() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Member member3 = new Member("member3");
        Member member4 = new Member("member4");
        Sponsor sponsor1 = new Sponsor("sponsor1");
        Sponsor sponsor2 = new Sponsor("sponsor2");
        Sponsor sponsor3 = new Sponsor("sponsor3");
        Sponsor sponsor4 = new Sponsor("sponsor4");
        Team team1 = new Team("team1");
        Team team2 = new Team("team2");
        team1.addMember(member1);
        team1.addMember(member2);
        team1.addSponsor(sponsor1);
        team1.addSponsor(sponsor2);
        team2.addMember(member3);
        team2.addMember(member4);
        team2.addSponsor(sponsor3);
        team2.addSponsor(sponsor4);

        em.persist(team1);
        em.persist(team2);

        em.flush();
        em.clear();

        List<Team> teams = em.createQuery(
                "select t from Team t",
                Team.class
        ).getResultList();

        System.out.println("**************");
        System.out.println("asdf");
        for (Team team : teams) {
            for (Member member : team.getMembers()) {
                System.out.println(member.getName());
            }
            for (Sponsor sponsor : team.getSponsors()) {
                System.out.println(sponsor.getName());
            }
        }
    }

    @Test
    public void cascadeTest() {
        Team team1 = new Team("team1");
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        team1.addMember(member1);
        team1.addMember(member2);
        em.persist(team1);

        Team team2 = new Team("team2");
        Member member3 = new Member("member3");
        team2.addMember(member3);
        em.persist(team2);

        Team team3 = new Team("team3");
        em.persist(team3);

        em.flush();
        em.clear();

        List<Team> teams = em.createQuery(
                "select distinct t from Team t left join fetch t.members", Team.class
        ).getResultList();

        System.out.println("*****");
    }
}