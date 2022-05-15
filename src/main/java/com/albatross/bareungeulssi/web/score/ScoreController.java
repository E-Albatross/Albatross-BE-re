package com.albatross.bareungeulssi.web.score;


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

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.gson.JsonParser.parseReader;
import static com.google.gson.JsonParser.parseString;

@Slf4j
@RestController
@RequestMapping(value = "/score", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class ScoreController {

    @Autowired
    TextBookRepository textBookRepository;

    @PostMapping("/{imageName}/{literatureId}/{fontPath}")
    public Object jsonList(@RequestBody Map<String, Object> param, @PathVariable String imageName, @PathVariable String fontPath, @PathVariable Long literatureId){
        //HashMap<String, Object> result = new HashMap<String, Object>();
        Gson gson = new Gson();

        JsonElement elementSyllable = parseString(param.get("syllable").toString()); //"syllable" 키 값으로 찾기
        JsonElement elementCharacter = parseString(param.get("character").toString()); //"character" 키 값으로 찾기

        Map<String, Object> syllableMap = gson.fromJson(elementSyllable.toString(), Map.class); //"syllable" 키로 찾은 json
        JsonElement lineZeroSyllable = parseString(syllableMap.get("0").toString()); //"0" 키로 찾기
        List<List<Integer>> lineZeroSyllableList = gson.fromJson(lineZeroSyllable, (new TypeToken<List<List<Integer>>>() { }).getType());

        JsonElement lineOneSyllable = parseString(syllableMap.get("1").toString()); //"0" 키로 찾기
        List<List<Integer>> lineOneSyllableList = gson.fromJson(lineOneSyllable, (new TypeToken<List<List<Integer>>>() { }).getType());

        //JsonElement lineTwoSyllable = parseString(syllableMap.get("2").toString()); //"0" 키로 찾기
        //List<List<Integer>> lineTwoSyllableList = gson.fromJson(lineTwoSyllable, (new TypeToken<List<List<Integer>>>() { }).getType());

        //JsonElement lineThreeSyllable = parseString(syllableMap.get("3").toString()); //"0" 키로 찾기
        //List<List<Integer>> lineThreeSyllableList = gson.fromJson(lineThreeSyllable, (new TypeToken<List<List<Integer>>>() { }).getType());




        //JsonElement lineOneSyllable = elementSyllable.getAsJsonObject();
        //List<List<Integer>> syllableList = gson.fromJson(elementSyllable, (new TypeToken<List<List<Integer>>>() { }).getType());
        //List<List<List<Integer>>> characterList = gson.fromJson(elementCharacter, (new TypeToken<List<List<List<Integer>>>>() { }).getType());

        //result.put("resultSyllable", syllableList);
        //result.put("resultChar", characterList);

        return lineOneSyllableList;
    }
}
