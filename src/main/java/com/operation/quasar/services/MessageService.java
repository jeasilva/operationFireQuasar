package com.operation.quasar.services;

import com.operation.quasar.exceptions.MessageException;
import com.operation.quasar.util.ErrorCodes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {

    private static final String EMPTY = "";
    private static final String BLANK = " ";

    public String getMessage(List<List<String>> messageList) throws MessageException {

        List<String> listLetters = getMessageLetters(messageList);
        if (!validateMessagesSize(messageList, listLetters.size())) {
            throw MessageException.builder().message(ErrorCodes.MESSAGE_SIZE_INCORRECT).build();
        }
        removeGap(messageList, listLetters.size());
        String message = completeMessage(messageList);
        if (!validateMessagePhrases(listLetters, message)) {
            throw MessageException.builder().message(ErrorCodes.MESSAGE_SIZE_INCORRECT).build();
        }
        return message;
    }

    private List<String> getMessageLetters(List<List<String>> messageList) {

        List<String> wordsList = new ArrayList<>();
        for (List<String> m : messageList) {
            wordsList = Stream.concat(wordsList.stream(), m.stream()).distinct().collect(Collectors.toList());
        }
        wordsList.remove(EMPTY);
        return wordsList;
    }

    private void removeGap(List<List<String>> msgList, int gapSize) {

        int s = 0;
        for (int i = 0; i < msgList.size(); i++) {
            s = msgList.get(i).size();
            msgList.set(i, msgList.get(i).subList(s - gapSize, s));
        }
    }

    private boolean validateMessagesSize(List<List<String>> messages, int size){
        for(List<String> m : messages){
            if(m.size() < size){
                return false;
            }
        }
        return true;
    }

    private boolean validateMessagePhrases(List<String> phrases, String message){
        List<String> msg = Arrays.stream(message.split(BLANK)).collect(Collectors.toList());
        Collections.sort(phrases);
        Collections.sort(msg);
        return Arrays.equals(phrases.toArray(), msg.toArray());
    }

    public String completeMessage(List<List<String>> msgList) {

        String phrase = "";
        for (List<String> m : msgList) {

            if (m.size() > 0 && !m.get(0).equals(EMPTY)) {
                phrase = (m.size() == 1) ? m.get(0) : m.get(0) + BLANK;
                msgList.stream().forEach(s -> s.remove(0));
                return phrase + completeMessage(msgList);
            }
        }
        return phrase;
    }

}
