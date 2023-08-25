package com.codelab.sequencechecker.service;

import com.codelab.sequencechecker.exception.SequenceException;
import com.codelab.sequencechecker.model.Sequence;
import com.codelab.sequencechecker.repository.SeqCheckerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SequenceService {

    private static final Logger log = LogManager.getLogger(SequenceService.class);

    @Autowired
    private SeqCheckerRepository seqRep;

    public SequenceService(SeqCheckerRepository seqChecker){
        this.seqRep= seqChecker;
    }


    /**
     * Add sequences in db
     * @param seq
     * @return sequence
     */
    public Sequence addSequence(Sequence seq){

        return seqRep.save(seq);
    }

    /**
     * Retrieve all sequences from db
     * @return List
     */
    public List<Sequence> getAllSequences(){

        return seqRep.findAll();
    }

    /**
     * Retrieve the jackpot number
     * @param sequence Sequence
     * @return
     */

    public Sequence jackPot(String sequence){
        Sequence jackpot=null;

        try{

            // Check if the sequence is empty
            if (sequence.isEmpty()) {
                log.error("Error occurred, unable to get sequences: Empty input sequence");
                jackpot= new Sequence().setSequence("NoEntryFound!");
                return jackpot;
            }

            int sizeOfSeq=getAllSequences().size();

            int availSeq=0;
            for(int i=0; i < sizeOfSeq; i++){

                if( getAllSequences().get(i).getSequence().equals(sequence)){

                    log.info("JackPot1===> -----> "+getAllSequences().get(i).getSequence());
                    getAllSequences().get(i).setTag((long) availSeq);

                    jackpot=new Sequence().setSequence(sequence);

                    break;
                }
                else { jackpot=new Sequence().setSequence("Missing JP#!");
                }
            }

            log.info("---00->JackPot===>> "+jackpot);

            return jackpot;


        }catch (Exception e){

            return jackpot.setSequence("Check_Data_Entry!");
        }

    }

    /**
     * Reverse a sequence
     * @param sequence String
     * @return String
     */
    private String reverseInputSeq(String sequence){

        StringBuilder reversedSeq = new StringBuilder();

        // Iterate over the characters of the input string in reverse order
        for (int i = sequence.length() - 1; i >= 0; i--) {
            reversedSeq.append(sequence.charAt(i));
        }

        return reversedSeq.toString();

    }


    /**
     * Eliminate duplicates
     * @param listSeq List<Sequence>
     * @return List<Sequence>
     */
    private List<Sequence> removeDuplicates(List<Sequence> listSeq) {

        Set<Sequence> uniqueSequences=new HashSet<>(listSeq);

        List<Sequence> uniqueList = new ArrayList<>(uniqueSequences);

        return uniqueList;
    }

    /**
     * Reverses input sequence passed here and compare it with db sequences
     * @param allSequences
     * @param bound
     * @param index
     * @param sequence
     * @param listSeq
     * @return
     */
    private List<Sequence> reverseAllSequences(List<Sequence> allSequences,
                                               int bound,
                                               int index,
                                               String sequence,
                                               List<Sequence> listSeq,int multiplier ){
        int availSeq=0;
        int matchingIndex = 0;

        String retrievedSeqRev=reverseInputSeq(allSequences.get(index).getSequence());
        String seqRev=reverseInputSeq(sequence);

        String allSeqReversed=retrievedSeqRev.substring(0, bound);
        String inputRev=seqRev.substring(0, bound);

        if (allSequences.get(index).getSequence().length() >= bound
                && allSeqReversed.equals(inputRev)) {

            if(!sequence.equals(allSequences.get(index).getSequence())){

            listSeq.add(allSequences.get(index));
                removeDuplicates(listSeq);

                sumAwardWon(allSequences,multiplier,matchingIndex,availSeq);




            }
        }
        return listSeq;

    }

    /**
     * Checks if passed in string matches in
     * @param sequence Sequence
     * @return List<Sequence>
     */
    public List<Sequence> check4Sequence(String sequence) {
        List<Sequence> listSeq = new ArrayList<>();

        int sum =0;

        try{
        List<Sequence> allSequences = getAllSequences();
        int bound = 8;

        // Check if the sequence is empty
        if (sequence.isEmpty()) {

            log.error("Error occurred, unable to get 4 sequence: Empty input sequence");

            listSeq.add(new Sequence().setSequence("NoEntryFound!"));

            return listSeq;
        }
            int availSeq=0;
            int matchingIndex = 0;

        for (int i = 0; i < allSequences.size(); i++) {

            if (!sequence.equals(allSequences.get(i).getSequence())) {// eliminate duplicated jackpot

                if (allSequences.get(i).getSequence().length() >= bound
                        && allSequences.get(i).getSequence().substring(0, bound)
                        .equals(sequence.substring(0, bound))) {

                    log.info("-----4th--->>" + allSequences.get(i));

                    availSeq++;

                    log.info("-4th--->counted Sequences{}",availSeq);

                    allSequences.get(i).setTag((long) availSeq);

                    listSeq.add(allSequences.get(i));
                }
                int multiplier=300;

                reverseAllSequences(allSequences, bound, i, sequence, listSeq,multiplier);

                removeDuplicates(listSeq);


                sumAwardWon(allSequences,1,matchingIndex,availSeq);
            }
        }

        return listSeq;
        }catch (Exception e){

            listSeq.add(new Sequence().setSequence("CheckDataEntry!"));

            log.info("CheckDataEntry-=-=-=-=-=->"+sum);

            return listSeq;
        }

    }

    /**
     * Checks 3 sequence
     * @param sequence
     * @return List<Sequence>
     */
    public List<Sequence> check3Sequence(String sequence){

        List<Sequence> listSeq = new ArrayList<>();


        try{
        List<Sequence> allSequences = getAllSequences();
        int bound = 6;

        // Check if the sequence is empty
        if (sequence.isEmpty()) {
            log.error("Error occurred, unable to get 3 sequence: Empty input sequence");
            listSeq.add(new Sequence().setSequence("NoEntryFound!"));
            return listSeq;
        }

            int availSeq=0;
            int matchingIndex = 0;
        for (int i = 0; i < allSequences.size(); i++) {
            if (!sequence.equals(allSequences.get(i).getSequence())) {
            if (allSequences.get(i).getSequence().length() >= bound
                    && allSequences.get(i).getSequence().substring(0, bound)
                    .equals(sequence.substring(0, bound))) {

                log.info("-----3rd--->>" + allSequences.get(i));
                availSeq++;
                matchingIndex=i;

                log.info("-3rd--->counted Sequences{}",availSeq);
                allSequences.get(i).setTag((long) availSeq);
                listSeq.add(allSequences.get(i));

            }
                int multiplier=10;
            reverseAllSequences(allSequences,bound,i,sequence,listSeq,multiplier);
            removeDuplicates(listSeq);

            sumAwardWon(allSequences,multiplier,matchingIndex,availSeq);
        }
        }

        return listSeq;
    }catch (Exception e){

        listSeq.add(new Sequence().setSequence("CheckDataEntry!"));

        return listSeq;
    }
    }

    /**
     * Checks 2 sequence
     * @param sequence
     * @return List<Sequence>
     */
    public List<Sequence> check2Sequence(String sequence){

        List<Sequence> listSeq = new ArrayList<>();

        try{

        List<Sequence> allSequences = getAllSequences();
        int bound = 4;

        // Check if the sequence is empty
        if (sequence.isEmpty()) {
            log.error("Error occurred, unable to get 2 sequence: Empty input sequence");
            listSeq.add(new Sequence().setSequence("NoEntryFound!"));
            return listSeq;
        }
            int availSeq=0;
            int matchingIndex = 0;
        for (int i = 0; i < allSequences.size(); i++) {

            if (!sequence.equals(allSequences.get(i).getSequence())) {

                if (allSequences.get(i).getSequence().length() >= bound
                        && allSequences.get(i).getSequence().substring(0, bound)
                        .equals(sequence.substring(0, bound))) {

                    matchingIndex=i;
                    log.info("-----2nd--->>" + allSequences.get(i));
                    availSeq++;
                    log.info("-2nd--->counted Sequences {}",availSeq);

                    listSeq.add(allSequences.get(i));
                }
                int multiplier=2;
                removeDuplicates(listSeq);

                reverseAllSequences(allSequences, bound, i, sequence, listSeq,multiplier);

                sumAwardWon(allSequences,multiplier,matchingIndex,availSeq);

            }
        }

        return listSeq;

        }catch (Exception e){

            listSeq.add(new Sequence().setSequence("CheckDataEntry!"));

            return listSeq;
        }
    }

    /**
     * Checks the 1st character in sequence
     * @param sequence
     * @return List<Sequence>
     */
    public List<Sequence> check1Sequence(String sequence){

        List<Sequence> listSeq = new ArrayList<>();

        try{
        List<Sequence> allSequences = getAllSequences();
        int bound = 2;

        // Check if the sequence is empty
        if (sequence.isEmpty()) {
            log.error("Error occurred, unable to get 1 sequence: Empty input sequence");
            listSeq.add(new Sequence().setSequence("NoEntryFound!"));
            return listSeq;
        }

            int availSeq=0;
            int matchingIndex = 0;
        for (int i = 0; i < allSequences.size(); i++) {


            if (!sequence.equals(allSequences.get(i).getSequence())) {

                if (allSequences.get(i).getSequence().length() >= bound
                        && allSequences.get(i).getSequence().substring(0, bound)
                        .equals(sequence.substring(0, bound))) {

                    matchingIndex=i;

                    log.info("-----1st--->>" + allSequences.get(i));
                    availSeq++;

                    log.info("-1--->counted Sequences {}",availSeq);

                    listSeq.add(allSequences.get(i));


                }
                int multiplier=1;

                reverseAllSequences(allSequences, bound, i, sequence, listSeq,multiplier);

                removeDuplicates(listSeq);
                sumAwardWon(allSequences,multiplier,matchingIndex,availSeq);
            }
        }




        return listSeq;

        }catch (Exception e){

            listSeq.add(new Sequence().setSequence("CheckDataEntry!"));

            return listSeq;
        }
    }


    /**
     * Computes the won award for all sequences that match
     * @param allSequences Integer
     * @param multiplier Integer
     * @param matchingIndex Integer
     * @param sumCounted Integer
     */
    public void sumAwardWon(List<Sequence> allSequences,
                            int multiplier,
                            int matchingIndex,
                            int sumCounted){

        int awardedCash=sumCounted*multiplier;

        allSequences.get(matchingIndex).setTag((long)awardedCash);

        log.info("awarded----------->>>{}",allSequences.get(matchingIndex).getTag());


    }

    /**
     * Find sequence by id
     * @param id
     * @return Sequence
     */
    public Sequence findSequenceById(Long id){
        return seqRep.findById(id).orElseThrow(
                ()->new SequenceException("The specified id: "+id+" not found!"));
    }

    /**
     * Delete Sequence by id
     * @param id
     * @return
     */
    public JSONObject deleteSequenceById(Long id){
        Sequence seq= findSequenceById(id);
        seqRep.delete(seq);

        JSONObject resp= new JSONObject();
        resp.put("Success","Removed sequence with id: "+id);

        return resp;
}

}
