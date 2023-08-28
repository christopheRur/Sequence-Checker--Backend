package com.codelab.sequencechecker.controller;

import com.codelab.sequencechecker.model.Sequence;
import com.codelab.sequencechecker.service.SequenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeqCheckerController {

    private static final Logger log = LogManager.getLogger(SeqCheckerController.class);
    private SequenceService seqServ;

    public SeqCheckerController(SequenceService sequenceService) {
        this.seqServ = sequenceService;
    }

    @PostMapping("/addseq")
    public ResponseEntity<?> addSequence(@RequestBody Sequence sequence) {
        try {
            if (sequence.getSequence().isEmpty() || sequence.getSequence().length() < 6) {

                return ResponseEntity.badRequest().body("CheckDataEntry!!!");

            } else return new ResponseEntity<>(seqServ.addSequence(sequence), HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getCause());
            return ResponseEntity.badRequest().body("Error occurred, unable to add sequence!");
        }

    }

    /**
     * Retrieves the jackpot
     *
     * @param jsnBody
     * @return
     */
    @PostMapping("/getjackpot")
    public ResponseEntity<?> getJackPot(@RequestBody Sequence jsnBody) {

        log.info("=---->" + jsnBody.toString());


        try {
            if (jsnBody.getSequence().isEmpty() || jsnBody.getSequence().length() < 5) {

                return new ResponseEntity<>(seqServ.jackPot("SequenceTooShort!"), HttpStatus.OK);

            } else return new ResponseEntity<>(seqServ.jackPot(jsnBody.getSequence()), HttpStatus.OK);

        } catch (Exception e) {
            log.info("----===-=-=-=->" + e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred, unable to get sequence: " + jsnBody.getSequence().toString());
        }
    }


    @PostMapping("/get4Seq")
    public ResponseEntity<?> get4SequenceList(@RequestBody Sequence jsnBody) {

        log.info("/get4Seq--==---->" + jsnBody.toString());

        try {
            if (jsnBody.getSequence().isEmpty() || jsnBody.getSequence().length() < 5) {

                return new ResponseEntity<>(seqServ.check4Sequence("SequenceTooShort!"), HttpStatus.OK);

            } else return new ResponseEntity<>(seqServ.check4Sequence(jsnBody.getSequence()), HttpStatus.OK);

        } catch (Exception e) {
            log.info("--=-=->" + e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred, unable to get 4 sequence: " + jsnBody.getSequence().toString());
        }
    }

    @PostMapping("/get3Seq")
    public ResponseEntity<?> get3SequenceList(@RequestBody Sequence jsnBody) {

        log.info("/get3Seq--==---->" + jsnBody.toString());

        try {
            if (jsnBody.getSequence().isEmpty() || jsnBody.getSequence().length() < 5) {

                return new ResponseEntity<>(seqServ.check3Sequence("SequenceTooShort!"), HttpStatus.OK);

            } else return new ResponseEntity<>(seqServ.check3Sequence(jsnBody.getSequence()), HttpStatus.OK);

        } catch (Exception e) {
            log.info("----->" + e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred, unable to get 3 sequence: " + jsnBody.getSequence().toString());
        }
    }

    @PostMapping("/get2Seq")
    public ResponseEntity<?> get2SequenceList(@RequestBody Sequence jsnBody) {

        log.info("/get2Seq--==---->" + jsnBody.toString());

        try {
            if (jsnBody.getSequence().isEmpty() || jsnBody.getSequence().length() < 5) {

                return new ResponseEntity<>(seqServ.check2Sequence("SequenceTooShort!"), HttpStatus.OK);

            } else return new ResponseEntity<>(seqServ.check2Sequence(jsnBody.getSequence()), HttpStatus.OK);

        } catch (Exception e) {
            log.info("------=-=-=->" + e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred, unable to get 2 sequence: " + jsnBody.getSequence().toString());
        }
    }

    @PostMapping("/get1Seq")
    public ResponseEntity<?> get1SequenceList(@RequestBody Sequence jsnBody) {

        log.info("/get1Seq--==---->" + jsnBody.toString());

        try {
            if (jsnBody.getSequence().isEmpty() || jsnBody.getSequence().length() < 5) {

                return new ResponseEntity<>(seqServ.check1Sequence("SequenceTooShort!"), HttpStatus.OK);

            } else return new ResponseEntity<>(seqServ.check1Sequence(jsnBody.getSequence()), HttpStatus.OK);

        } catch (Exception e) {
            log.info("------=-=-=->" + e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred, unable to get 2 sequence: " + jsnBody.getSequence().toString());
        }
    }

    @GetMapping("/getseq")
    public ResponseEntity<?> getSequences() {

        try {
            return new ResponseEntity<>(seqServ.getAllSequences(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred, unable to get all sequences");
        }

    }
}

