package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{  //Option + Enter 하면 MemberRepository 에 있는 메서드를 전부 가져올 수 있음
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //키값을 생성해주는 녀석

    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(), member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}