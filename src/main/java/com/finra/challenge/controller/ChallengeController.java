package com.finra.challenge.controller;

import com.finra.challenge.model.InputModel;
import com.finra.challenge.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController(value = "/")
public class ChallengeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeController.class);

    @RequestMapping(path = "getAlphaNumericValues",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getAlphaNumericValues(@RequestBody InputModel input) throws Exception{
        System.out.print("Inside method");
        String inputNumber = input.number;
        if(!isNumeric(inputNumber)){
            LOGGER.error("Enter a valid number");
            return new ResponseEntity<>("Enter a valid number",HttpStatus.BAD_REQUEST);
        }
        if(inputNumber.length()>10 || inputNumber.length()<7){
            LOGGER.error("Enter a valid number of length 7 to 10");
            return new ResponseEntity<>("Enter a valid number of length 7 to 10",HttpStatus.BAD_REQUEST);
        }
        Set<String> alphaNumericValues = new HashSet<>();
        createAlphaNumerics(input.number.toCharArray(),input.number.length()-1,alphaNumericValues);
        int resultsSize = alphaNumericValues.size();
        int remainder = resultsSize%10;
        int division = resultsSize/10;
        int pages = remainder>0?division+1:division;
        if(input.pageSize>pages){
            LOGGER.error("Requested page results exceeds the available results");
            return new ResponseEntity<>("Requested page results exceeds the available results",HttpStatus.BAD_REQUEST);
        }
        ResponseModel response = new ResponseModel(filterValues(alphaNumericValues,input.pageSize,resultsSize),
                                                    resultsSize,
                                                    input.pageSize,
                                                    pages,resultsSize);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public static List<String> filterValues(Set<String> alphaNumericValues,int pageNumber, int resultsSize){
        int startIndex = pageNumber*10;
        int endIndex = (pageNumber+1)*10;
        if(endIndex>resultsSize){
            endIndex = resultsSize;
        }
        List<String> resultList = new ArrayList<>(alphaNumericValues);
        Collections.sort(resultList);
        return resultList.subList(startIndex,endIndex);
    }

    public static void createAlphaNumerics(char[] number,int index,Set<String> alphaNumbers){
        if(index<0){
            return;
        }
        char[] getAlphabets = getAlphabetsForGivenNumber(number[index]);
        for(int i=0;i<getAlphabets.length;i++){
            number[index] = getAlphabets[i];
            alphaNumbers.add(String.valueOf(number));
            createAlphaNumerics(number,index-1,alphaNumbers);
        }
    }

    public static boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static char[] getAlphabetsForGivenNumber(char a){
        switch (a){
            case '1':
                char[] oneAlphabets = {'1'};
                return oneAlphabets;
            case '2':
                char[] twoAlphabets = {'A','B','C','2'};
                return twoAlphabets;
            case '3':
                char[] threeAlphabets = {'D','E','F','3'};
                return threeAlphabets;
            case '4':
                char[] fourAlphabets = {'G','H','I','4'};
                return fourAlphabets;
            case '5':
                char[] fiveAlphabets = {'J','K','L','5'};
                return fiveAlphabets;
            case '6':
                char[] sixAlphabets = {'M','N','O','6'};
                return sixAlphabets;
            case '7':
                char[] sevenAlphabets = {'P','Q','R','S','7'};
                return sevenAlphabets;
            case '8':
                char[] eightAlphabets = {'T','U','V','8'};
                return eightAlphabets;
            case '9':
                char[] nineAlphabets = {'W','X','Y','Z','9'};
                return nineAlphabets;
            case '0':
                char[] zeroAlphabets = {'0'};
                return zeroAlphabets;
            default:
                char[] defAlphabets = {};
                return defAlphabets;
        }
    }
}
