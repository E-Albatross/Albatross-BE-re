package com.albatross.bareungeulssi.web.literature;

import com.albatross.bareungeulssi.domain.entity.Literature;
import com.albatross.bareungeulssi.domain.repository.LiteratureRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/basic/literatures")
public class LiteratureResponseController {

    @Autowired
    LiteratureRepository literatureRepository;

    @PostConstruct
    public void init(){
        Literature literature = new Literature();
        literature.setId(1L);
        literature.setTitle("별 헤는 밤");
        literature.setAuthor("윤동주");
//        String plot1 = "계절이 지나가는 하늘에는\n" +
//                "가을로 가득 차 있습니다.\n" +
//                "\n" +
//                "나는 아무 걱정도 없이\n" +
//                "가을 속의 별들을 다 헤일 듯합니다...\n" +
//                "\n" +
//                "가슴 속에 하나 둘 새겨지는 별을\n" +
//                "이제 다 못 헤는 것은\n" +
//                "쉬이 아침이 오는 까닭이요,\n" +
//                "내일 밤이 남은 까닭이요,\n" +
//                "아직 나의 청춘이 다하지 않은 까닭입니다.\n" +
//                "\n" +
//                "별 하나에 추억과\n" +
//                "별 하나에 사랑과\n" +
//                "별 하나에 쓸쓸함과\n" +
//                "별 하나에 동경과\n" +
//                "별 하나에 시와\n" +
//                "별 하나에 어머니, 어머니\n" +
//                "어머님, 나는 별 하나에 아름다운 말 한 마디씩 불러 봅니다. 소학교 때 책상을 같이했던 아이들의 이름과, 패, 경, 옥 이런 이국 소녀들의 이름과, 벌써 아기 어머니 된 계집애들의 이름과, 가난한 이웃 사람들의 이름과, 비둘기, 강아지, 토끼, 노새, 노루, '프랑시스 잠', '라이너 마리아 릴케', 이런 시인의 이름을 불러 봅니다.\n" +
//                "이네들은 너무나 멀리 있습니다.\n" +
//                "별이 아스라이 멀 듯이,\n" +
//                "어머님,\n" +
//                "그리고 당신은 멀리 북간도에 계십니다\n" +
//                "나는 무엇인지 그리워\n" +
//                "이 많은 별빛이 내린 언덕 위에\n" +
//                "내 이름자를 써 보고,\n" +
//                "흙으로 덮어 버리었읍니다.\n" +
//                "딴은, 밤을 새워 우는 벌레는\n" +
//                "부끄러운 이름을 슬퍼하는 까닭입니다.\n" +
//                "그러나 겨울이 지나고 나의 별에도 봄이 오면\n" +
//                "무덤 위에 파란 잔디가 피어나듯이\n" +
//                "내 이름자 묻힌 언덕 위에도\n" +
//                "자랑처럼 풀이 무성할 게외다.";
//        plot1 = plot1.replace("\n", "\\n");
//        plot1 = plot1.replace("'", "\'");
//        literature.setPlot(plot1);
        literature.setLink("https://albatrossliteraturestorage.s3.ap-northeast-2.amazonaws.com/%EC%9C%A4%EB%8F%99%EC%A3%BC-%EB%B3%84%ED%97%A4%EB%8A%94%EB%B0%A4-%ED%95%98%EB%8A%98%EA%B3%BC_%EB%B0%94%EB%9E%8C%EA%B3%BC_%EB%B3%84%EA%B3%BC_%EC%8B%9C.txt");
        literature.setCheckNew(true);
        literature.setCheckBest(false);
        literatureRepository.save(literature);

//

        //literatureRepository.save(new Literature(2L, "서시", "윤동주", plot2,false, true));
    }


//    @GetMapping
//    public String literatures(Model model) {
//
//        //List<Literature> literatureList = literatureRepository.findByTitle({제목});
//        List<Literature> literatures = literatureRepository.findAll();
//        model.addAttribute("literatures", literatures);
//        return "basic/literatures";
//    }

    //작품 목록 - JSON에 작품 목록이 다 담겨야함. Gson이용
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String literatures(){
        //List<Literature> literatureList= literatureRepository.findAll();
        List<Literature> literatureList1= literatureRepository.findByCheckNew(true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json1 = gson.toJson(literatureList1);

        List<Literature> literatureList2= literatureRepository.findByCheckBest(true);
        String json2 = gson.toJson(literatureList2);
        String json = json1+json2;
        return json;
    }

//    @GetMapping("/{id}")
//    public String literature(@PathVariable Long id, Model model){
//        Optional<Literature> optionalLiterature  = literatureRepository.findById(id);
//        Literature literature = optionalLiterature.get();
//        model.addAttribute("literature", literature);
//        return "basic/literature";
//    }

    //작품 상세 - JSON으로 id, 제목, 작가, 글 리턴
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public String literature(@PathVariable Long id){
        Optional<Literature> optionalLiterature  = literatureRepository.findById(id);
        Literature literature = optionalLiterature.get();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(literature);
        return json;
    }
}
