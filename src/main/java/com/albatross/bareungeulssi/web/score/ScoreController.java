package com.albatross.bareungeulssi.web.score;


import com.albatross.bareungeulssi.domain.entity.TextBook;
import com.albatross.bareungeulssi.domain.repository.RecordRepository;
import com.albatross.bareungeulssi.domain.repository.TextBookRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.Reader;
import java.util.*;

import static com.google.gson.JsonParser.parseReader;
import static com.google.gson.JsonParser.parseString;

@Slf4j
@RestController
@RequestMapping(value = "/score", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class ScoreController {

    @Autowired
    TextBookRepository textBookRepository;
    @Autowired
    RecordRepository recordRepository;

    ArrayList<Feedback> feedbacks = new ArrayList<>(); //피드백 담을 리스트

    //{"score": 95, "feedback":{"0":[[x,y],1]}
    @PostMapping("/{imageName}/{literatureId}/{fontPath}")
    public String jsonList(@RequestBody List<Map<String, Object>> param, @PathVariable String imageName, @PathVariable Long literatureId, @PathVariable String fontPath){
        //HashMap<String, Object> result = new HashMap<String, Object>();
        /*
        [
          {
          }
        ]
        들어오는 json이 이렇게([]) 싸여있으므로 List로 Map을 감싸줘야함.
        */
        Gson gson = new Gson();

        JsonElement elementUserSyllable = parseString(param.get(0).get("syllable").toString()); //"syllable" 키 값으로 찾기 - 음절 정보
        JsonElement elementUserCharacter = parseString(param.get(0).get("character").toString()); //"character" 키 값으로 찾기 - 음소 정보

        //TODO. List-> ArrayList?

        Map<String, Object> userSyllableMap = gson.fromJson(elementUserSyllable.toString(), Map.class); //"syllable" 키로 찾은 json
        JsonElement userLineZeroSyllable = parseString(userSyllableMap.get("0").toString()); //"0" 키로 찾기
        //List<List<Integer>> userLineZeroSyllableList = gson.fromJson(userLineZeroSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //0번째 줄의 음절 정보 배열
        ArrayList<ArrayList<Integer>> userLineZeroSyllableList = gson.fromJson(userLineZeroSyllable, (new TypeToken<ArrayList<ArrayList<Integer>>>() { }).getType()); //0번째 줄의 음절 정보 배열

        JsonElement userLineOneSyllable = parseString(userSyllableMap.get("1").toString()); //"1" 키로 찾기
        //List<List<Integer>> userLineOneSyllableList = gson.fromJson(userLineOneSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //1번째 줄의 음절 정보 배열
        ArrayList<ArrayList<Integer>> userLineOneSyllableList = gson.fromJson(userLineOneSyllable, (new TypeToken<ArrayList<ArrayList<Integer>>>() { }).getType()); //1번째 줄의 음절 정보 배열

        JsonElement userLineTwoSyllable = parseString(userSyllableMap.get("2").toString()); //"2" 키로 찾기
        //List<List<Integer>> userLineTwoSyllableList = gson.fromJson(userLineTwoSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //2번째 줄의 음절 정보 배열
        ArrayList<ArrayList<Integer>> userLineTwoSyllableList = gson.fromJson(userLineTwoSyllable, (new TypeToken<ArrayList<ArrayList<Integer>>>() { }).getType()); //2번째 줄의 음절 정보 배열

        JsonElement userLineThreeSyllable = parseString(userSyllableMap.get("3").toString()); //"3" 키로 찾기
        //List<List<Integer>> userLineThreeSyllableList = gson.fromJson(userLineThreeSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //3번째 줄의 음절 정보 배열
        ArrayList<ArrayList<Integer>> userLineThreeSyllableList = gson.fromJson(userLineThreeSyllable, (new TypeToken<ArrayList<ArrayList<Integer>>>() { }).getType()); //3번째 줄의 음절 정보 배열

        //1,2,3,4번째 줄 음절 리스트 모은 리스트
        ArrayList<ArrayList<ArrayList<Integer>>> userSyllableList = new ArrayList<>();
        //List<List<List<Integer>>> userSyllableList = new ArrayList<>();
        userSyllableList.add(userLineZeroSyllableList);
        userSyllableList.add(userLineOneSyllableList);
        userSyllableList.add(userLineTwoSyllableList);
        userSyllableList.add(userLineThreeSyllableList);

        //DB에서 textBook 정보 불러오기
        TextBook textBook = textBookRepository.findByLiteratureIdAndFontPath(literatureId, fontPath);

        Map<String, Object> textBookMap= textBook.getTextBookAnalysis();

        JsonElement elementTextBookSyllable = parseString(textBookMap.get("syllable").toString()); //음절 정보
        //JsonElement elementTextBookCharacter = parseString(textBookMap.get("character").toString());

        Map<String, Object> textBookSyllableMap = gson.fromJson(elementTextBookSyllable.toString(), Map.class);
        JsonElement textBookLineZeroSyllable = parseString(textBookSyllableMap.get("0").toString()); //"0" 키로 찾기
        List<List<Integer>> textBookLineZeroSyllableList = gson.fromJson(textBookLineZeroSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //0번째 줄의 음절 정보 배열

        JsonElement textBookLineOneSyllable = parseString(textBookSyllableMap.get("1").toString()); //"1" 키로 찾기
        List<List<Integer>> textBookLineOneSyllableList = gson.fromJson(textBookLineOneSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //1번째 줄의 음절 정보 배열

        JsonElement textBookLineTwoSyllable = parseString(textBookSyllableMap.get("2").toString()); //"2" 키로 찾기
        List<List<Integer>> textBookLineTwoSyllableList = gson.fromJson(textBookLineTwoSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //2번째 줄의 음절 정보 배열

        JsonElement textBookLineThreeSyllable = parseString(textBookSyllableMap.get("3").toString()); //"3" 키로 찾기
        List<List<Integer>> textBookLineThreeSyllableList = gson.fromJson(textBookLineThreeSyllable, (new TypeToken<List<List<Integer>>>() { }).getType()); //3번째 줄의 음절 정보 배열


        int score=100;
        ArrayList<Integer> cyList = new ArrayList<>();
        //첫번째 줄 음절 점수 계산
        //int userLineZeroSyllableListSize = userLineZeroSyllableList.size();

        //1. 전체적인 기울기에 유의해서 작성해보세요! : cy값으로 판단
        for(int i=0; i<4; i++) { //i번째 줄 - TODO.i<4로 바꿔야함!
            int userLineSyllableCnt = userSyllableList.get(i).size();
            for (int j = 0; j < userLineSyllableCnt; i++) { //j번째 음절
                cyList.add(userSyllableList.get(i).get(j).get(4));
                cyList.sort(Comparator.naturalOrder()); //오름차순 정렬

                int midIdx = cyList.size()/2;
                int mid = cyList.get(midIdx); //cy 중간값
                log.info("middle cy: {}", mid);

                int x1 = userSyllableList.get(i).get(userLineSyllableCnt-1).get(0)+20; //마지막 음절의 x좌표 + 20
                int y1 = userSyllableList.get(i).get(userLineSyllableCnt-1).get(4); //마지막 음절의 cy좌표

                if(Math.abs(userLineZeroSyllableList.get(j).get(4)-mid)>=6 ||
                        (i<userLineSyllableCnt-1 && Math.abs(userSyllableList.get(i).get(j).get(4)-userSyllableList.get(i).get(j+1).get(4))>=4)){
                    score -= 3;
                    Feedback feedback = new Feedback(score, 1, x1, y1);
                    feedbacks.add(feedback);
                    break; //기울기 피드백 하나만 생성
                }
            }
            cyList.clear();
        }


        //2. 글자 간격을 주의해서 작성해보세요!(pass)

        //TODO. 3. 이 글자의 전체적인 크기에 주의해서 작성해보세요!
        //3-1. 폰트 음절 바운딩 박스 개수 != 사용자 글씨 음절 바운딩 박스 개수
        //1)폰트 음절 바운딩 박스의 x값 기준으로 범위를 설정해서 그 안에 바운딩 박스 몇 개 있는가
        //2)w가 30 아래면, 좌우로 탐색해서 더 작은 값과 이 w 합치기
        //1,2 둘 다 해보기 TODO. 2번 먼저 해보기
        //폰트와의 비교

        //TODO. 4,5,6 폰트/사용자 자음, 모음, 받침 비율 >임계값 -> feedback에 저장
        //TODO. 4. 이 글자의 자음에 주의해서 작성해보세요!
        //TODO. 5. 이 글자의 모음에 주의해서 작성해보세요!
        //TODO. 6. 이 글자의 받침에 주의해서 작성해보세요!


        //점수가 음수일때, 점수가 100점일때 정리 - null반환보다는 나을듯?

        if(score<0){ //음수일때
            score=0;
            feedbacks.add(new Feedback(0,-1,-1,-1));
        }
        else if(score==100){ //100점일때
            score=100;
            feedbacks.add(new Feedback(100,-1,-1,-1));
        }

        //점수 DB에 저장
        recordRepository.updateScore(score, imageName);

        String json = gson.toJson(feedbacks);
        feedbacks.clear();
        return json;
    }
}
