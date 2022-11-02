package My.toyproject;

import My.toyproject.domain.*;
import My.toyproject.domain.role.Role;
import My.toyproject.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class SampleCategory {

    private final SampleService sampleService;

    @PostConstruct
    public void init() {
        sampleService.InitDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class SampleService {

        private final PasswordEncoder passwordEncoder;
        private final EntityManager em;

        public void InitDB() {

            /**
             * 회원 생성
             */
            Member member1 = createMember("고수", "user", "1234", "01011112222", "user@user.com", true, new Address("123", "서울 용산구 독서당로", "한남아파트 1003동 1004호"), Role.USER);
            Member member2 = createMember("원빈", "admin", "1234", "01022223333", "admin@admin.com", true, new Address("456", "경기 성남시 분당구 문정로", "분당아파트 1002동 1050호"), Role.ADMIN);
            Member member3 = createMember("현빈", "test", "1234", "01033332222", "test@test.com", true, new Address("134", "부산 강서구 가락대로 ", "부산아파트 108동 1200호"), Role.USER);

//            /**
//             * 회원 장바구니 생성
//             */
//            createCart(member1);
//            createCart(member2);
//            createCart(member3);

            /**
             * 카테고리(parent)
             */
            Category outer = createCategory("아우터");
            Category top = createCategory("상의");
            Category bottoms = createCategory("하의");
            Category shoes = createCategory("신발");

            /**
             * 카테고리(child)
             */
            //아우터
            Category jacket = createCategory("자켓", outer);
            Category suit = createCategory("수트", outer);
            Category coat = createCategory("코트", outer);
            Category padding = createCategory("패딩", outer);

            //상의
            Category longSleeve = createCategory("긴팔", top);
            Category knit = createCategory("니트", top);
            Category cardigan = createCategory("가디건", top);
            Category shortSleeve = createCategory("반팔", top);

            //하의
            Category slacks = createCategory("슬렉스", bottoms);
            Category denim = createCategory("데님", bottoms);
            Category sweatPants = createCategory("트레이닝", bottoms);
            Category shorts = createCategory("반바지", bottoms);

            //신발
            Category loafers = createCategory("로퍼", shoes);
            Category derbyShoes = createCategory("더비슈즈", shoes);
            Category sneakers = createCategory("스니커즈", shoes);
            Category slippers = createCategory("슬리퍼", shoes);

            /**
             * 아이템 이미지
             */
            //아우터=========================================================================================================================================================
            //아우터/자켓
            ItemImage jacketImage1 = createItemImage("위츠 트위드 울 자켓", "http://www.modernif.co.kr/web/product/tiny/202210/30448103f7ccd7a487d579d924c4a050.png");
            ItemImage jacketImage2 = createItemImage("레이건 울 블레이져", "http://www.modernif.co.kr/web/product/tiny/20200422/1079ede85f50474a911179073fa755d8.jpg");
            ItemImage jacketImage3 = createItemImage("로던 울 투버튼 자켓", "http://www.modernif.co.kr/web/product/tiny/202011/09da8c4535eef3e5f76b16c9643de07b.png");
            ItemImage jacketImage4 = createItemImage("비건 투웨이 레더 자켓", "http://www.modernif.co.kr/web/product/tiny/202203/1427c0821405196a81d576f471d1cd5a.jpg");
            ItemImage jacketImage5 = createItemImage("플리 비건 레더자켓", "http://www.modernif.co.kr/web/product/tiny/202203/38a4cf061dd9f760dc1c550f856d9bd0.jpg");
            ItemImage jacketImage6 = createItemImage("제논 미니멀 숏자켓", "https://www.modernif.co.kr/web/product/tiny/202203/d88860ba2e20261aa6c57e98e1955693.jpg");
            ItemImage jacketImage7 = createItemImage("토닛 오버핏 청자켓", "https://www.modernif.co.kr/web/product/tiny/20200422/9e0bcf2969c96bfb4bf72b0bb0c851fc.jpg");
            ItemImage jacketImage8 = createItemImage("로인 레더 트러커 자켓", "https://www.modernif.co.kr/web/product/tiny/202202/c5897107516d85a066bcf6d3805f965b.jpg");

            //아우터/수트
            ItemImage suitImage1 = createItemImage("리밋 투버튼 수트 세트", "http://www.modernif.co.kr/web/product/tiny/202103/70116efd1a996f5b8ce3ac054f613bce.jpg");
            ItemImage suitImage2 = createItemImage("리밋 투버튼 와이드 세트", "http://www.modernif.co.kr/web/product/tiny/202103/950211e64c499a4b83f8653b48957c55.jpg");
            ItemImage suitImage3 = createItemImage("랜드 3버튼 수트 셋업", "http://www.modernif.co.kr/web/product/tiny/202203/8316b457761ed6fbe42c33fb55449451.jpg");
            ItemImage suitImage4 = createItemImage("로위 투버튼 수트", "http://www.modernif.co.kr/web/product/tiny/20200423/ebd9386f71ec79012eb78177020135cd.jpg");
            ItemImage suitImage5 = createItemImage("로이 투버튼 수트 세트", "https://www.modernif.co.kr/web/product/tiny/202204/46f5fe742da805053cccab36d0ac770f.jpg");
            ItemImage suitImage6 = createItemImage("어텐 투버튼 와이드 세트", "https://www.modernif.co.kr/web/product/tiny/202112/659d673541ace4d42bbc30e0aaad6292.jpg");
            ItemImage suitImage7 = createItemImage("로던 울 수트", "https://www.modernif.co.kr/web/product/tiny/202011/c9a58e8c651b00bba230d62917171916.jpg");
            ItemImage suitImage8 = createItemImage("마일드 체크 수트", "https://www.modernif.co.kr/web/product/tiny/20200423/1bef818f874595fc60fe8567b6e7829a.jpg");

            //아우터/코트
            ItemImage coatImage1 = createItemImage("라드 울 싱글 코트", "http://www.modernif.co.kr/web/product/tiny/202101/76c4f787d7180a726fd48ae0e3d76080.jpg");
            ItemImage coatImage2 = createItemImage("비드 울 더블 코트", "http://www.modernif.co.kr/web/product/tiny/20200423/b1af21cf74c438edb6b7d2ead041caef.jpg");
            ItemImage coatImage3 = createItemImage("쿼드 울 발마칸 코트", "http://www.modernif.co.kr/web/product/tiny/202111/a8d16a9b72ca36f97ee2bf820bd5de64.jpg");
            ItemImage coatImage4 = createItemImage("레논 로브 핸드메이드 코트", "http://www.modernif.co.kr/web/product/tiny/202111/4c6fe72e5b53cfc89006b6964ad262c4.jpg");
            ItemImage coatImage5 = createItemImage("리븐 트렌치 코트", "https://www.modernif.co.kr/web/product/tiny/202203/7f4b305a4969aba2439e1c5fa4910a24.jpg");
            ItemImage coatImage6 = createItemImage("비크 나그랑 맥코트", "https://www.modernif.co.kr/web/product/tiny/202203/30c1e680d9b403fdf339a09b64e93064.png");
            ItemImage coatImage7 = createItemImage("디너리 트렌치 코트", "https://www.modernif.co.kr/web/product/tiny/20200422/08c91b6fd2187592373a9302febae4e3.jpg");
            ItemImage coatImage8 = createItemImage("글리코 울 더블코트", "https://www.modernif.co.kr/web/product/tiny/20200423/9822cd4f397146100926a05879274a5f.jpg");

            //아우터/패딩
            ItemImage paddingImage1 = createItemImage("프리느 유틸 웰론 패딩", "http://www.modernif.co.kr/web/product/tiny/202210/f0fff0dd1991c0a88e8b893b874234c2.jpg");
            ItemImage paddingImage2 = createItemImage("루프 유틸리티 숏 패딩 자켓", "http://www.modernif.co.kr/web/product/tiny/202210/24325da0a2933ad3347b9af1d73dd2de.jpg");
            ItemImage paddingImage3 = createItemImage("디보 프리미엄 덕다운 패딩", "http://www.modernif.co.kr/web/product/tiny/202210/5ee1287744e9c8efe84d5e631dc532cc.jpg");
            ItemImage paddingImage4 = createItemImage("주드 웰론 숏 패딩", "http://www.modernif.co.kr/web/product/tiny/202011/db1cfe097642f70955af287529ded9fe.jpg");
            ItemImage paddingImage5 = createItemImage("스탠 덕다운 숏 패딩", "https://www.modernif.co.kr/web/product/tiny/202011/2c347e8873e30e2c929b4cffda0219cd.jpg");
            ItemImage paddingImage6 = createItemImage("1+1 웰론 숏 패딩", "https://www.modernif.co.kr/web/product/tiny/20200423/21fef4eab792e4b6c59119a694ca979b.jpg");
            ItemImage paddingImage7 = createItemImage("배색 덕다운 숏 패딩", "https://www.modernif.co.kr/web/product/tiny/202011/4f05ce475daf83e0e72b51b0c5bde1ce.jpg");
            ItemImage paddingImage8 = createItemImage("레온 숏 패딩", "https://www.modernif.co.kr/web/product/tiny/20200423/37fc109de60a489a0fc792ef93ca78be.jpg");

            //상의===========================================================================================================================================================
            //상의/긴팔
            ItemImage longSleeveImage1 = createItemImage("USA 절개 오버핏 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202210/30cf0c015081b44be25bfabc8ba810e8.jpg");
            ItemImage longSleeveImage2 = createItemImage("USA 세미 오버 후드 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202210/40313f555648209e0595f214e2f06b76.jpg");
            ItemImage longSleeveImage3 = createItemImage("USA 코튼 오버핏 맨투맨", "https://www.modernif.co.kr/web/product/tiny/202210/bd4a829947d465d31befd60f375bb3b8.jpg");
            ItemImage longSleeveImage4 = createItemImage("USA 코튼 오버핏 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202209/94a807a435985647b185c27254280aab.jpg");
            ItemImage longSleeveImage5 = createItemImage("수피마 코튼 오버핏 맨투맨", "https://www.modernif.co.kr/web/product/tiny/202209/bfaa8d12e612e0aca843e53fe27db14e.png");
            ItemImage longSleeveImage6 = createItemImage("데닌 라운드 긴팔 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202209/8d4aae4cd80629b5916b0582661460de.png");
            ItemImage longSleeveImage7 = createItemImage("벨류 실켓 긴팔 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202209/ceded3367f4c5ccb40de4a4b8813d47e.png");
            ItemImage longSleeveImage8 = createItemImage("프리스턴 레터링 후드 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202209/059e3e5f115dd5dd2f405997fb1ac8d5.png");

            //상의/니트
            ItemImage knitImage1 = createItemImage("워셔블 드랍 하찌 니트", "https://www.modernif.co.kr/web/product/tiny/202210/0255defb7e7832992ad9836495c1752f.jpg");
            ItemImage knitImage2 = createItemImage("로코 반집업 오버핏 니트", "https://www.modernif.co.kr/web/product/tiny/202210/3490ee174b2a5b047f1183d572091b55.jpg");
            ItemImage knitImage3 = createItemImage("포니 캐시 라운드 니트", "https://www.modernif.co.kr/web/product/tiny/202210/2cc89aca8307d177d9f2271b32abe633.png");
            ItemImage knitImage4 = createItemImage("볼드 캐시 집업 카라니트", "https://www.modernif.co.kr/web/product/tiny/202210/297e8d5606ab376a6a836c1fae684ab0.png");
            ItemImage knitImage5 = createItemImage("우즈 캐시 단가라 니트", "https://www.modernif.co.kr/web/product/tiny/202210/2fdb55713be758cb10ae58ca06d46830.jpg");
            ItemImage knitImage6 = createItemImage("워셔블 반폴라 니트", "https://www.modernif.co.kr/web/product/tiny/202210/c871ac51d819c91bb6939a6f3e96b990.jpg");
            ItemImage knitImage7 = createItemImage("브러쉬드 스트라이프 니트", "https://www.modernif.co.kr/web/product/tiny/202210/badb3e1467877aa74ce604542e22e0e3.png");
            ItemImage knitImage8 = createItemImage("하찌 루즈핏 카라 니트", "https://www.modernif.co.kr/web/product/tiny/202210/a6e0e7dc3d5f73450fe630784ffb77f3.jpg");

            //상의/가디건
            ItemImage cardiganImage1 = createItemImage("메리노울 꽈배기 집업 가디건", "https://www.modernif.co.kr/web/product/tiny/202210/da34fae3c04dd9a95a2530abf6914074.png");
            ItemImage cardiganImage2 = createItemImage("유니 카라 니트 가디건", "https://www.modernif.co.kr/web/product/tiny/202210/4b6741c760d88564b15f7e890e981079.jpg");
            ItemImage cardiganImage3 = createItemImage("룩턴 비스코스 카라 가디건", "https://www.modernif.co.kr/web/product/tiny/202210/68afaaa3364d7ebcb6546d83fa94a1b0.png");
            ItemImage cardiganImage4 = createItemImage("리트 단가라 집업 가디건", "https://www.modernif.co.kr/web/product/tiny/202209/bde21cdf081ccacb7672dbd2fb226d6d.png");
            ItemImage cardiganImage5 = createItemImage("엠버 하찌 카라 가디건", "https://www.modernif.co.kr/web/product/tiny/202201/cdf8c7d8a1c386b5934a128f7f9088fd.jpg");
            ItemImage cardiganImage6 = createItemImage("밀리 라운드 집업 가디건", "https://www.modernif.co.kr/web/product/tiny/202201/7d897d05889f76443481bc6a70df4a09.jpg");
            ItemImage cardiganImage7 = createItemImage("더젠 포켓 라운드 가디건", "https://www.modernif.co.kr/web/product/tiny/202209/36f0a5038b1986d57fed2f9cd439a5fb.png");
            ItemImage cardiganImage8 = createItemImage("스텔 소프트 라운드 가디건", "https://www.modernif.co.kr/web/product/tiny/202209/64b9f26e413fdc57c78532cfcff3e99e.png");

            //상의/반팔
            ItemImage shortSleeveImage1 = createItemImage("엘르 스트라이프 카라 니트", "https://www.modernif.co.kr/web/product/tiny/202207/28b636b51c568b8b2fdc6ffdd8ca964f.png");
            ItemImage shortSleeveImage2 = createItemImage("로츠 단가라 반팔 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202207/07b2bf5f6a53ebdf3d58b36441a56f3e.png");
            ItemImage shortSleeveImage3 = createItemImage("부클 스트라이프 카라 니트", "https://www.modernif.co.kr/web/product/tiny/202207/ffc25981bf11edbbf42b8adfb3c4e1ad.png");
            ItemImage shortSleeveImage4 = createItemImage("레브 레터링 반팔 티셔츠", "https://www.modernif.co.kr/web/product/tiny/202207/c89b5e9cded7c4f08a36d62add15608e.png");
            ItemImage shortSleeveImage5 = createItemImage("런드 오픈 카라 니트", "https://www.modernif.co.kr/web/product/tiny/202207/30bc0bbe76f3844aeb106ed113c062b6.jpg");
            ItemImage shortSleeveImage6 = createItemImage("헨리넥 케이블 반팔 니트", "https://www.modernif.co.kr/web/product/tiny/202207/14aa2808a3d9ea66a35a733c6136d57a.png");
            ItemImage shortSleeveImage7 = createItemImage("리먼 린넨 브이넥 니트", "https://www.modernif.co.kr/web/product/tiny/202205/bd857893b7d914d96827d6492b91b1b6.png");
            ItemImage shortSleeveImage8 = createItemImage("로사 린넨 카라 니트", "https://www.modernif.co.kr/web/product/tiny/20200609/7afd19675d6909a8f768a096527035f8.jpg");

            //하의===========================================================================================================================================================
            //하의/슬렉스
            ItemImage slacksImage1 = createItemImage("윈디 레귤러 벤딩 슬렉스", "https://www.modernif.co.kr/web/product/tiny/202210/0a6e01a05f5fbda3ca6cbd94bba566b8.png");
            ItemImage slacksImage2 = createItemImage("마니브 밴딩 스판 슬랙스", "https://www.modernif.co.kr/web/product/tiny/202202/3cc4788ebcdeef61125aeb3e03ab553e.jpg");
            ItemImage slacksImage3 = createItemImage("루니 밴딩 슬랙스", "https://www.modernif.co.kr/web/product/tiny/202104/4bc39405587bca1a8d29636d19954a30.jpg");
            ItemImage slacksImage4 = createItemImage("켈리 반밴딩 슬랙스", "https://www.modernif.co.kr/web/product/tiny/202202/c244d7528118331a47f83f07068d5262.jpg");
            ItemImage slacksImage5 = createItemImage("카디 스판 슬랙스", "https://www.modernif.co.kr/web/product/tiny/202010/eef05f683f64d94ccfee1a73e4050b85.jpg");
            ItemImage slacksImage6 = createItemImage("마론 린넨 밴딩 슬렉스", "https://www.modernif.co.kr/web/product/tiny/202207/dacd59e8ea5d82625dd90ff18d87c596.png");
            ItemImage slacksImage7 = createItemImage("라이프 하프 밴딩 슬랙스", "https://www.modernif.co.kr/web/product/tiny/202206/4a0e706736c493c46ed90e05cc462f03.png");
            ItemImage slacksImage8 = createItemImage("노드 썸머 밴딩 슬랙스", "https://www.modernif.co.kr/web/product/tiny/202205/ac5ec00ec5e06621c9ba38a2af069aeb.png");

            //하의/데님
            ItemImage denimImage1 = createItemImage("키퍼 원턱 와이드 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/36d3be99ffc34330049873b82d5c81df.jpg");
            ItemImage denimImage2 = createItemImage("로핀 레귤러 생지 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/cdfeb1b5d78a50f5d266ab9ad2a8ec57.png");
            ItemImage denimImage3 = createItemImage("러트 세미와이드 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/9b57a510430cb91d87e247260f5e999e.jpg");
            ItemImage denimImage4 = createItemImage("커리 피그먼트 와이드 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/a7feb062d1a040f3bb22eb402a8635e1.jpg");
            ItemImage denimImage5 = createItemImage("라텔 논페이드 와이드 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/4c57bcf9ec7b9e7e74702307d7e1806f.png");
            ItemImage denimImage6 = createItemImage("에필 세미 와이드 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/1b7014d87e4095d05193a871c55fbcfb.png");
            ItemImage denimImage7 = createItemImage("포멀 그레이쉬 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202210/44be89442faa38ea07ee2c59eb3c9c1a.png");
            ItemImage denimImage8 = createItemImage("플렌 세미 와이드 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202209/9685ee9a8faa49d58ee8db7144a1226a.png");

            //하의/트레이닝
            ItemImage sweatPantsImage1 = createItemImage("베른 2way 스웻팬츠", "https://www.modernif.co.kr/web/product/tiny/202111/7bd4f3954a1c4b801d4663e5b57e20dc.jpg");
            ItemImage sweatPantsImage2 = createItemImage("웜히트 핀턱 와이드 스웻팬츠", "https://www.modernif.co.kr/web/product/tiny/202209/f57c1802aaa3247fe1c30e2ea7d910ca.png");
            ItemImage sweatPantsImage3 = createItemImage("로프 와이드 스웻팬츠", "https://www.modernif.co.kr/web/product/tiny/202209/b8aa0e44fb63515abc0e701fb74af904.png");
            ItemImage sweatPantsImage4 = createItemImage("1+1 와이드 스웻팬츠", "https://www.modernif.co.kr/web/product/tiny/202202/90f962cbb347250f4cd67c87c5d62e75.jpg");
            ItemImage sweatPantsImage5 = createItemImage("모딕 와이드 스웻팬츠", "https://www.modernif.co.kr/web/product/tiny/202202/3f8a5f7b5a292fc493862cde160f880e.jpg");
            ItemImage sweatPantsImage6 = createItemImage("어핀 기모 와이드 스웻팬츠", "https://www.modernif.co.kr/web/product/tiny/202201/3250dae1672cb7b86b145e02380e7d16.jpg");
            ItemImage sweatPantsImage7 = createItemImage("2way 린넨 체크팬츠", "https://www.modernif.co.kr/web/product/tiny/20200529/4ffa53627c63809cda887388d66916f5.jpg");
            ItemImage sweatPantsImage8 = createItemImage("데니 해비 조거팬츠", "https://www.modernif.co.kr/web/product/tiny/202009/3c8919e25b132934a5841f2a46a786aa.jpg");

            //하의/반바지
            ItemImage shortsImage1 = createItemImage("지엘 트레이닝 반바지", "https://www.modernif.co.kr/web/product/tiny/202206/e72cfc6b0bc8898c58333d0ed387ffe7.png");
            ItemImage shortsImage2 = createItemImage("먼트 밴딩 반바지", "https://www.modernif.co.kr/web/product/tiny/202207/950320bca8d623167f6bda6ff4d3b8ff.jpg");
            ItemImage shortsImage3 = createItemImage("투턱 밴딩 버뮤다 팬츠", "https://www.modernif.co.kr/web/product/tiny/202206/2b4480864757468e815509b19d7023ff.png");
            ItemImage shortsImage4 = createItemImage("캠퍼 린넨 밴딩 반바지", "https://www.modernif.co.kr/web/product/tiny/202108/ebe0f6945b2fa78086d84210efd003f4.jpg");
            ItemImage shortsImage5 = createItemImage("라인 하프 데님팬츠", "https://www.modernif.co.kr/web/product/tiny/202206/75b7dd60c06012d44f539d9c78fe0dd1.png");
            ItemImage shortsImage6 = createItemImage("에어쿨 로렌 숏 팬츠", "https://www.modernif.co.kr/web/product/tiny/202205/9f5516baba8f7d58bc66c27a9cd0e89c.png");
            ItemImage shortsImage7 = createItemImage("네이처 밴딩 반바지", "https://www.modernif.co.kr/web/product/tiny/202205/ccce7fbe41276eb6b95fd5651fc2339f.png");
            ItemImage shortsImage8 = createItemImage("에어쿨 스판 숏 팬츠", "https://www.modernif.co.kr/web/product/tiny/202205/1f34a872dc37c62bd8f5d30006ff1f74.png");

            //신발===========================================================================================================================================================
            //신발/로퍼
            ItemImage loafersImage1 = createItemImage("로니 벨크로 로퍼", "https://www.modernif.co.kr/web/product/tiny/20200424/2b6c1983d8e16d442a1ab1802a907076.jpg");
            ItemImage loafersImage2 = createItemImage("제드 블로퍼", "https://www.modernif.co.kr/web/product/tiny/20200611/fa6803d82129e29873c9447bce970d55.jpg");
            ItemImage loafersImage3 = createItemImage("스퀘어 블로퍼", "https://www.modernif.co.kr/web/product/tiny/20200424/4767420028a03287a7119f4b3ff935a6.jpg");
            ItemImage loafersImage4 = createItemImage("베타 페니로퍼", "https://www.modernif.co.kr/web/product/tiny/20200422/4e22e2ca7bf68137aa880a8a6514aba1.jpg");
            ItemImage loafersImage5 = createItemImage("테슬로퍼", "https://www.modernif.co.kr/web/product/tiny/20200508/a42edc76b2636bc0d579392d4d78fffe.jpg");
            ItemImage loafersImage6 = createItemImage("노아로퍼", "https://www.modernif.co.kr/web/product/tiny/20200508/6fbd797fefdb17ae8a385a4c9b49609f.jpg");
            ItemImage loafersImage7 = createItemImage("로빈 스퀘어 블로퍼", "https://www.modernif.co.kr/web/product/tiny/202206/aefeb31f2748bd76096a909e55c24808.jpg");
            ItemImage loafersImage8 = createItemImage("마룬 레더 블로퍼", "https://www.modernif.co.kr/web/product/tiny/202105/69392e42c29c99bdd35a5aa430544add.jpg");

            //신발/더비슈즈
            ItemImage derbyShoesImage1 = createItemImage("브릴 볼드 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/202208/bda11a72057dfce29da574bb7b6ccdeb.jpg");
            ItemImage derbyShoesImage2 = createItemImage("헤일로 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/20200422/87b7ceb42ab339444b80c84de0673b7d.jpg");
            ItemImage derbyShoesImage3 = createItemImage("이디 레더 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/202209/f02864d1e1a0adae147763cb7bb122f2.jpg");
            ItemImage derbyShoesImage4 = createItemImage("로컨 레더 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/202111/119598cae3082eb38a82e0f1dee19970.jpg");
            ItemImage derbyShoesImage5 = createItemImage("스콘 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/202108/b9fde8ab9c7728773eef8977906357a9.jpg");
            ItemImage derbyShoesImage6 = createItemImage("리츠 스퀘어 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/202101/96f7ca3b84b513330ff507048513734b.jpg");
            ItemImage derbyShoesImage7 = createItemImage("파인 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/20200508/3f3fd716bc8ba199194173898293b2c8.jpg");
            ItemImage derbyShoesImage8 = createItemImage("워트 더비슈즈", "https://www.modernif.co.kr/web/product/tiny/202012/eecf14d233f0139d107c6218f44f5c49.jpg");

            //신발/스니커즈
            ItemImage sneakersImage1 = createItemImage("로너 독일군 스니커즈", "https://www.modernif.co.kr/web/product/tiny/202108/68397bbe8a9f80ab7c83bfc88b5567de.jpg");
            ItemImage sneakersImage2 = createItemImage("우트 레더 스니커즈", "https://www.modernif.co.kr/web/product/tiny/202209/2d48dd428a03676870989d741272dacc.jpg");
            ItemImage sneakersImage3 = createItemImage("패널 오버솔 스니커즈", "https://www.modernif.co.kr/web/product/tiny/202203/a559443c36b7030f3d8680868c1d12ba.jpg");
            ItemImage sneakersImage4 = createItemImage("홀드 레더 스니커즈", "https://www.modernif.co.kr/web/product/tiny/202203/300dece739b50fbcc412e969e4e3a890.jpg");
            ItemImage sneakersImage5 = createItemImage("니콜 코튼 스니커즈", "https://www.modernif.co.kr/web/product/tiny/202203/55b4eaea294dc8fece118716cdaa2edf.jpg");
            ItemImage sneakersImage6 = createItemImage("오트 레더 스니커즈", "https://www.modernif.co.kr/web/product/tiny/202201/316ee785c038f76054554916b91fa975.jpg");
            ItemImage sneakersImage7 = createItemImage("네트 스니커즈", "https://www.modernif.co.kr/web/product/tiny/20200422/98ebffa3ea6436b2b8865e8227fcdf07.jpg");
            ItemImage sneakersImage8 = createItemImage("커먼 스니커즈", "https://www.modernif.co.kr/web/product/tiny/20200422/be33a89c769bacfa0198c7fdafc03f66.jpg");

            //신발/슬리퍼
            ItemImage slippersImage1 = createItemImage("드앤 미니멀 슬리퍼", "https://www.modernif.co.kr/web/product/tiny/202207/20ee69c494dbc08e490a74a07f149983.jpg");
            ItemImage slippersImage2 = createItemImage("리든 레더 슬리퍼", "https://www.modernif.co.kr/web/product/tiny/202206/cbaf276c0ef3f2f2bbec0a4b42fd7b9d.jpg");
            ItemImage slippersImage3 = createItemImage("오르 데일리 슬리퍼", "https://www.modernif.co.kr/web/product/tiny/20200603/7e5b2c11cbf1bfda25aca372b902a423.jpg");
            ItemImage slippersImage4 = createItemImage("피아 라운드 슬리퍼", "https://www.modernif.co.kr/web/product/tiny/202105/936d6549e8b78bc6d49bf617cf59bc48.jpg");
            ItemImage slippersImage5 = createItemImage("루킹 오버솔 플립플롭", "https://www.modernif.co.kr/web/product/tiny/202206/cad37a381ee6f937ac47c2731f047031.jpg");
            ItemImage slippersImage6 = createItemImage("레더 플립플롭", "https://www.modernif.co.kr/web/product/tiny/20200427/aef8157f3a8ebb465483a0698af17171.jpg");
            ItemImage slippersImage7 = createItemImage("타비 플립플롭", "https://www.modernif.co.kr/web/product/tiny/202205/60387ff8e4d179a247260245c03dfc46.jpg");
            ItemImage slippersImage8 = createItemImage("루킨 레더 슬리퍼", "https://www.modernif.co.kr/web/product/tiny/202205/3dfcfdbb63d622508c81d33b807f545e.jpg");

            /**
             * 아이템
             */
            //아우터===========================================================================================================================================================
            //아우터/자켓
            Item jacket1 = createItem("위츠 트위드 울 자켓", 97000, 20, jacket, jacketImage1);
            Item jacket2 = createItem("레이건 울 블레이져", 87000, 10, jacket, jacketImage2);
            Item jacket3 = createItem("로던 울 투버튼 자켓", 80000, 5, jacket, jacketImage3);
            Item jacket4 = createItem("비건 투웨이 레더 자켓", 59900, 0, jacket, jacketImage4);
            Item jacket5 = createItem("플리 비건 레더자켓", 109000, 5, jacket, jacketImage5);
            Item jacket6 = createItem("제논 미니멀 숏자켓", 99000, 15, jacket, jacketImage6);
            Item jacket7 = createItem("토닛 오버핏 청자켓", 67000, 25, jacket, jacketImage7);
            Item jacket8 = createItem("로인 레더 트러커 자켓", 49900, 10, jacket, jacketImage8);

            //아우터/수트
            Item suit1 = createItem("리밋 투버튼 수트 세트", 79900, 20, suit, suitImage1);
            Item suit2 = createItem("리밋 투버튼 와이드 세트", 80900, 10, suit, suitImage2);
            Item suit3 = createItem("랜드 3버튼 수트 셋업", 87000, 5, suit, suitImage3);
            Item suit4 = createItem("로위 투버튼 수트", 67000, 10, suit, suitImage4);
            Item suit5 = createItem("로이 투버튼 수트 세트", 79000, 0, suit, suitImage5);
            Item suit6 = createItem("어텐 투버튼 와이드 세트", 85000, 30, suit, suitImage6);
            Item suit7 = createItem("로던 울 수트", 80000, 10, suit, suitImage7);
            Item suit8 = createItem("마일드 체크 수트", 75000, 5, suit, suitImage8);

            //아우터/코트
            Item coat1 = createItem("라드 울 싱글 코트", 99900, 20, coat, coatImage1);
            Item coat2 = createItem("비드 울 더블 코트", 99900, 10, coat, coatImage2);
            Item coat3 = createItem("쿼드 울 발마칸 코트", 155000, 5, coat, coatImage3);
            Item coat4 = createItem("레논 로브 핸드메이드 코트", 169000, 5, coat, coatImage4);
            Item coat5 = createItem("리븐 트렌치 코트", 54000, 10, coat, coatImage5);
            Item coat6 = createItem("비크 나그랑 맥코트", 98000, 15, coat, coatImage6);
            Item coat7 = createItem("디너리 트렌치 코트", 109000, 20, coat, coatImage7);
            Item coat8 = createItem("글리코 울 더블코트", 128000, 0, coat, coatImage8);

            //아우터/패딩
            Item padding1 = createItem("프리느 유틸 웰론 패딩", 54000, 20, padding, paddingImage1);
            Item padding2 = createItem("루프 유틸리티 숏패딩 자켓", 59000, 10, padding, paddingImage2);
            Item padding3 = createItem("디보 프리미엄 덕다운 패딩", 99900, 5, padding, paddingImage3);
            Item padding4 = createItem("주드 웰론 숏 패딩", 72000, 5, padding, paddingImage4);
            Item padding5 = createItem("스탠 덕다운 숏 패딩", 59900, 20, padding, paddingImage5);
            Item padding6 = createItem("1+1 웰론 숏 패딩", 89000, 15, padding, paddingImage6);
            Item padding7 = createItem("배색 덕다운 숏 패딩", 127000, 10, padding, paddingImage7);
            Item padding8 = createItem("레온 숏 패딩", 58000, 0, padding, paddingImage8);

            //상의===========================================================================================================================================================
            //상의/긴팔
            Item longSleeve1 = createItem("USA 절개 오버핏 티셔츠", 27000, 20, longSleeve, longSleeveImage1);
            Item longSleeve2 = createItem("USA 세미 오버 후드 티셔츠", 47000, 30, longSleeve, longSleeveImage2);
            Item longSleeve3 = createItem("USA 코튼 오버핏 맨투맨", 37000, 15, longSleeve, longSleeveImage3);
            Item longSleeve4 = createItem("USA 코튼 오버핏 티셔츠", 27000, 20, longSleeve, longSleeveImage4);
            Item longSleeve5 = createItem("수피마 코튼 오버핏 맨투맨", 34000, 10, longSleeve, longSleeveImage5);
            Item longSleeve6 = createItem("데닌 라운드 긴팔 티셔츠", 29000, 5, longSleeve, longSleeveImage6);
            Item longSleeve7 = createItem("벨류 실켓 긴팔 티셔츠", 24000, 0, longSleeve, longSleeveImage7);
            Item longSleeve8 = createItem("프리스턴 레터링 후드 티셔츠", 39000, 30, longSleeve, longSleeveImage8);

            //상의/니트
            Item knit1 = createItem("워셔블 드랍 하찌 니트", 37000, 20, knit, knitImage1);
            Item knit2 = createItem("로코 반집업 오버핏 니트", 38000, 30, knit, knitImage2);
            Item knit3 = createItem("포니 캐시 라운드 니트", 33900, 20, knit, knitImage3);
            Item knit4 = createItem("볼드 캐시 집업 카라니트", 43000, 25, knit, knitImage4);
            Item knit5 = createItem("우즈 캐시 단가라 니트", 36000, 0, knit, knitImage5);
            Item knit6 = createItem("워셔블 반폴라 니트", 37500, 20, knit, knitImage6);
            Item knit7 = createItem("브러쉬드 스트라이프 니트", 37000, 30, knit, knitImage7);
            Item knit8 = createItem("하찌 루즈핏 카라 니트", 40000, 20, knit, knitImage8);

            //상의/가디건
            Item cardigan1 = createItem("메리노울 꽈배기 집업 가디건", 64000, 30, cardigan, cardiganImage1);
            Item cardigan2 = createItem("유니 카라 니트 가디건", 45000, 10, cardigan, cardiganImage2);
            Item cardigan3 = createItem("룩턴 비스코스 카라 가디건", 37000, 15, cardigan, cardiganImage3);
            Item cardigan4 = createItem("리트 단가라 집업 가디건", 40000, 0, cardigan, cardiganImage4);
            Item cardigan5 = createItem("엠버 하찌 카라 가디건", 54000, 5, cardigan, cardiganImage5);
            Item cardigan6 = createItem("밀리 라운드 집업 가디건", 53000, 30, cardigan, cardiganImage6);
            Item cardigan7 = createItem("더젠 포켓 라운드 가디건", 40000, 25, cardigan, cardiganImage7);
            Item cardigan8 = createItem("스텔 소프트 라운드 가디건", 40000, 10, cardigan, cardiganImage8);

            //상의/반팔
            Item shortSleeve1 = createItem("엘르 스트라이프 카라 니트", 28000, 0, shortSleeve, shortSleeveImage1);
            Item shortSleeve2 = createItem("로츠 단가라 반팔 티셔츠", 32000, 15, shortSleeve, shortSleeveImage2);
            Item shortSleeve3 = createItem("부클 스트라이프 카라 니트", 36000, 20, shortSleeve, shortSleeveImage3);
            Item shortSleeve4 = createItem("레브 레터링 반팔 티셔츠", 24000, 30, shortSleeve, shortSleeveImage4);
            Item shortSleeve5 = createItem("런드 오픈 카라 니트", 36000, 20, shortSleeve, shortSleeveImage5);
            Item shortSleeve6 = createItem("헨리넥 케이블 반팔 니트", 32000, 15, shortSleeve, shortSleeveImage6);
            Item shortSleeve7 = createItem("리먼 린넨 브이넥 니트", 29000, 25, shortSleeve, shortSleeveImage7);
            Item shortSleeve8 = createItem("로사 린넨 카라 니트", 37000, 10, shortSleeve, shortSleeveImage8);

            //하의===========================================================================================================================================================
            //하의/슬렉스
            Item slacks1 = createItem("윈디 레귤러 벤딩 슬렉스", 39000, 20, slacks, slacksImage1);
            Item slacks2 = createItem("마니브 밴딩 스판 슬랙스", 42000, 10, slacks, slacksImage2);
            Item slacks3 = createItem("루니 밴딩 슬랙스", 36000, 30, slacks, slacksImage3);
            Item slacks4 = createItem("켈리 반밴딩 슬랙스", 32000, 30, slacks, slacksImage4);
            Item slacks5 = createItem("카디 스판 슬랙스", 30000, 25, slacks, slacksImage5);
            Item slacks6 = createItem("마론 린넨 밴딩 슬렉스", 34000, 15, slacks, slacksImage6);
            Item slacks7 = createItem("라이프 하프 밴딩 슬랙스", 32900, 20, slacks, slacksImage7);
            Item slacks8 = createItem("노드 썸머 밴딩 슬랙스", 32000, 0, slacks, slacksImage8);

            //하의/데님
            Item denim1 = createItem("키퍼 원턱 와이드 데님팬츠", 40000, 30, denim, denimImage1);
            Item denim2 = createItem("로핀 레귤러 생지 데님팬츠", 39000, 15, denim, denimImage2);
            Item denim3 = createItem("러트 세미와이드 데님팬츠", 38000, 10, denim, denimImage3);
            Item denim4 = createItem("커리 피그먼트 와이드 데님팬츠", 44000, 10, denim, denimImage4);
            Item denim5 = createItem("라텔 논페이드 와이드 데님팬츠", 39900, 20, denim, denimImage5);
            Item denim6 = createItem("에필 세미 와이드 데님팬츠", 36000, 15, denim, denimImage6);
            Item denim7 = createItem("포멀 그레이쉬 데님팬츠", 40000, 0, denim, denimImage7);
            Item denim8 = createItem("플렌 세미 와이드 데님팬츠", 40000, 10, denim, denimImage8);

            //하의/트레이닝
            Item sweatpants1 = createItem("베른 2way 스웻팬츠", 34000, 20, sweatPants, sweatPantsImage1);
            Item sweatpants2 = createItem("웜히트 핀턱 와이드 스웻팬츠", 40000, 10, sweatPants, sweatPantsImage2);
            Item sweatpants3 = createItem("로프 와이드 스웻팬츠", 36000, 15, sweatPants, sweatPantsImage3);
            Item sweatpants4 = createItem("1+1 와이드 스웻팬츠", 43000, 10, sweatPants, sweatPantsImage4);
            Item sweatpants5 = createItem("모딕 와이드 스웻팬츠", 22000, 10, sweatPants, sweatPantsImage5);
            Item sweatpants6 = createItem("어핀 기모 와이드 스웻팬츠", 37000, 0, sweatPants, sweatPantsImage6);
            Item sweatpants7 = createItem("2way 린넨 체크팬츠", 32000, 10, sweatPants, sweatPantsImage7);
            Item sweatpants8 = createItem("데니 해비 조거팬츠", 43000, 15, sweatPants, sweatPantsImage8);

            //하의/반바지
            Item shorts1 = createItem("지엘 트레이닝 반바지", 26000, 10, shorts, shortsImage1);
            Item shorts2 = createItem("먼트 밴딩 반바지", 29000, 5, shorts, shortsImage2);
            Item shorts3 = createItem("투턱 밴딩 버뮤다 팬츠", 34000, 20, shorts, shortsImage3);
            Item shorts4 = createItem("캠퍼 린넨 밴딩 반바지", 24000, 10, shorts, shortsImage4);
            Item shorts5 = createItem("라인 하프 데님팬츠", 37000, 10, shorts, shortsImage5);
            Item shorts6 = createItem("에어쿨 로렌 숏 팬츠", 28000, 0, shorts, shortsImage6);
            Item shorts7 = createItem("네이처 밴딩 반바지", 24000, 0, shorts, shortsImage7);
            Item shorts8 = createItem("에어쿨 스판 숏 팬츠", 19000, 10, shorts, shortsImage8);

            //신발===========================================================================================================================================================
            //신발/로퍼
            Item loafers1 = createItem("로니 벨크로 로퍼", 48000, 10, loafers, loafersImage1);
            Item loafers2 = createItem("제드 블로퍼", 52000, 5, loafers, loafersImage2);
            Item loafers3 = createItem("스퀘어 블로퍼", 99000, 0, loafers, loafersImage3);
            Item loafers4 = createItem("베타 페니로퍼", 118000, 10, loafers, loafersImage4);
            Item loafers5 = createItem("테슬로퍼", 119000, 20, loafers, loafersImage5);
            Item loafers6 = createItem("노아로퍼", 122000, 20, loafers, loafersImage6);
            Item loafers7 = createItem("로빈 스퀘어 블로퍼", 49000, 15, loafers, loafersImage7);
            Item loafers8 = createItem("마룬 레더 블로퍼", 108000, 10, loafers, loafersImage8);

            //신발/더비슈즈
            Item derbyShoes1 = createItem("브릴 볼드 더비슈즈", 59000, 0, derbyShoes, derbyShoesImage1);
            Item derbyShoes2 = createItem("헤일로 더비슈즈", 139000, 10, derbyShoes, derbyShoesImage2);
            Item derbyShoes3 = createItem("이디 레더 더비슈즈", 58000, 20, derbyShoes, derbyShoesImage3);
            Item derbyShoes4 = createItem("로컨 레더 더비슈즈", 120000, 15, derbyShoes, derbyShoesImage4);
            Item derbyShoes5 = createItem("스콘 더비슈즈", 59000, 10, derbyShoes, derbyShoesImage5);
            Item derbyShoes6 = createItem("리츠 스퀘어 더비슈즈", 59000, 10, derbyShoes, derbyShoesImage6);
            Item derbyShoes7 = createItem("파인 더비슈즈", 57000, 20, derbyShoes, derbyShoesImage7);
            Item derbyShoes8 = createItem("워트 더비슈즈", 125000, 0, derbyShoes, derbyShoesImage8);

            //신발/스니커즈
            Item sneakers1 = createItem("로너 독일군 스니커즈", 54000, 15, sneakers, sneakersImage1);
            Item sneakers2 = createItem("우트 레더 스니커즈", 118000, 5, sneakers, sneakersImage2);
            Item sneakers3 = createItem("패널 오버솔 스니커즈", 78000, 15, sneakers, sneakersImage3);
            Item sneakers4 = createItem("홀드 레더 스니커즈", 118000, 20, sneakers, sneakersImage4);
            Item sneakers5 = createItem("니콜 코튼 스니커즈", 59000, 10, sneakers, sneakersImage5);
            Item sneakers6 = createItem("오트 레더 스니커즈", 120000, 10, sneakers, sneakersImage6);
            Item sneakers7 = createItem("네트 스니커즈", 112000, 0, sneakers, sneakersImage7);
            Item sneakers8 = createItem("커먼 스니커즈", 119000, 10, sneakers, sneakersImage8);

            //신발/슬리퍼
            Item slippers1 = createItem("드앤 미니멀 슬리퍼", 48000, 10, slippers, slippersImage1);
            Item slippers2 = createItem("리든 레더 슬리퍼", 89000, 20, slippers, slippersImage2);
            Item slippers3 = createItem("오르 데일리 슬리퍼", 35000, 20, slippers, slippersImage3);
            Item slippers4 = createItem("피아 라운드 슬리퍼", 59000, 5, slippers, slippersImage4);
            Item slippers5 = createItem("루킹 오버솔 플립플롭", 55000, 10, slippers, slippersImage5);
            Item slippers6 = createItem("레더 플립플롭", 46000, 0, slippers, slippersImage6);
            Item slippers7 = createItem("타비 플립플롭", 29000, 0, slippers, slippersImage7);
            Item slippers8 = createItem("루킨 레더 슬리퍼", 89000, 10, slippers, slippersImage8);
        }

//        private Cart createCart(Member member) {
//            Cart cart = new Cart();
//            cart.setMember(member);
//            em.persist(cart);
//            return cart;
//        }

        private Member createMember(String name, String loginId, String password, String mobile, String email, boolean enabled, Address address, Role role) {
            Member member = Member.builder()
                    .name(name)
                    .loginId(loginId)
                    .password(passwordEncoder.encode(password))
                    .mobile(mobile)
                    .email(email)
                    .enabled(enabled)
                    .address(address)
                    .role(role)
                    .build();

            em.persist(member);
            return member;
        }

        private ItemImage createItemImage(String name, String url) {
            ItemImage itemImage = new ItemImage();
            itemImage.setName(name);
            itemImage.setUrl(url);

            em.persist(itemImage);
            return itemImage;
        }

        private Item createItem(String name, int price, int quantity, Category category, ItemImage itemImage) {
            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            item.setStockQuantity(quantity);
            item.addCategory(category);
            item.addItemImage(itemImage);

            em.persist(item);
            return item;
        }

        private Category createCategory(String name) {
            Category category = Category.builder()
                    .name(name)
                    .build();

            em.persist(category);
            return category;
        }

        private Category createCategory(String name, Category parent) {
            Category category = Category.builder()
                    .name(name)
                    .parent(parent)
                    .build();

            em.persist(category);
            return category;
        }
    }
}
