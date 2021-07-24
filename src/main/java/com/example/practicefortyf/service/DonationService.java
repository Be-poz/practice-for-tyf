package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.dto.DonationResponse;
import com.example.practicefortyf.repository.DonationRepository;
import com.example.practicefortyf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {

    private final MemberRepository memberRepository;
    private final DonationRepository donationRepository;

    public DonationResponse createDonation(String name, Long amount) {
        Member findMember = memberRepository.findByName(name);
        Donation donation = new Donation(amount);
        // Donation savedDonation = donationRepository.save(donation);
        findMember.addDonation(donation);
//        donationRepository.flush();

        return new DonationResponse(donation);
    }
}
/*
위의 코드는 Donation 요청이 들어오면 Member를 찾아서 addDonation으로 Donation을 추가해주려는 코드입니다. 제가 처음에 의도하고자 했던 코드는 addDonation으로 Member한테 Donation을 추가해주려는 일이었습니다. 그리고 그 Donation에 대한 응답을(id포함) return 해주려고 했습니다.

문제는 저 메서드가 끝나면서 트랜잭션이 종료되고 flush가 일어나면서 Donation이 쿼리가 나가면서 id 값을 가지고 올텐데 그 전에 return new DonationResponse(donation)을 하려고 하니 id가 null 인채로 반환이 된다는 문제가 있었습니다.

결국 이 이슈로 인해 1. donationRepository.flush()를 호출해서 처리. 2. 그냥 cascade 없애고 donationRepository.save(donation) 을 해주기.  이 2개의 안 중에 후자를 택하게 되었습니다.

서론이 길었네요. 질문 드리겠습니다.
jpaRepository.flush() 호출은 지양해야 하는 메서드인지 궁금합니다. 이것을 넣자니 굉장히 찝찝하더라구요 ..
위와 같이 cascade를 걸어놨는데 바로  return 의 결과로 id를 리턴해야 할 떄에는 어떤식으로 처리해야할지 궁금합니다! cascade를 포기해야 하는 것인가요?
CascadeType.PERSIST 를 걸어줄 베스트 프랙티스에 대한 예시를 알고 싶습니다 !!

긴 글 읽어주셔서 감사합니다!

 */