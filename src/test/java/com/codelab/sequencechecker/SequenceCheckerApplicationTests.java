package com.codelab.sequencechecker;

import com.codelab.sequencechecker.controller.SeqCheckerController;
import com.codelab.sequencechecker.model.Sequence;
import com.codelab.sequencechecker.repository.SeqCheckerRepository;
import com.codelab.sequencechecker.service.SequenceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
class SequenceCheckerApplicationTests {
    private static final Logger logTest = LogManager.getLogger(SequenceCheckerApplicationTests.class);

    @Mock
    private Sequence sequence;

    @Mock
    private SeqCheckerRepository seqCheckerRepository;

    @Mock
    private SequenceService sequenceService;

    @InjectMocks
    SeqCheckerController controller;

    private final Sequence dummySeq = new Sequence();

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddSequenceFailure() {
        dummySeq.setSequence("0102030533").setTag(533L);

        Mockito.when(sequenceService.addSequence(dummySeq)).thenReturn(new Sequence());

        ResponseEntity<?> response = controller.addSequence(sequenceService.addSequence(dummySeq));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddSequenceSuccess() {
        dummySeq.setSequence("0102030533").setTag(533L);

        Mockito.when(sequenceService.addSequence(dummySeq)).thenReturn(new Sequence().setSequence("145247855"));

        ResponseEntity<?> response = controller.addSequence(sequenceService.addSequence(dummySeq));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGettingAllSequence() {

        List<Sequence> allSeq = new ArrayList<>();
        allSeq.add(dummySeq.setSequence("89898978").setTag(4578L));


        Mockito.when(sequenceService.getAllSequences()).thenReturn(allSeq);

        ResponseEntity<?> response = controller.getSequences();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGettingJackpot() {

        dummySeq.setSequence("89898978").setTag(4578L);


        Mockito.when(sequenceService.jackPot("89898978")).thenReturn(dummySeq);

        ResponseEntity<?> response = controller.getJackPot(dummySeq);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testCheckSequenceSeries4() {

        List<Sequence> allSeq = new ArrayList<>();
        allSeq.add(dummySeq.setSequence("89898978").setTag(4578L));


        Mockito.when(sequenceService.check4Sequence("89898978")).thenReturn(allSeq);

        ResponseEntity<?> response = controller.get4SequenceList(
                new Sequence().setSequence("89898978"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCheckSequenceSeries3() {

        List<Sequence> allSeq = new ArrayList<>();
        allSeq.add(dummySeq.setSequence("89898978").setTag(4578L));


        Mockito.when(sequenceService.check3Sequence("89898978")).thenReturn(allSeq);

        ResponseEntity<?> response = controller.get3SequenceList(
                new Sequence().setSequence("89898978"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCheckSequenceSeries2() {

        List<Sequence> allSeq = new ArrayList<>();
        allSeq.add(dummySeq.setSequence("89898978").setTag(4578L));


        Mockito.when(sequenceService.check2Sequence("89898978")).thenReturn(allSeq);

        ResponseEntity<?> response = controller.get2SequenceList(
                new Sequence().setSequence("89898978"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCheckSequenceSeries1() {

        List<Sequence> allSeq = new ArrayList<>();
        allSeq.add(dummySeq.setSequence("89898978").setTag(4578L));


        Mockito.when(sequenceService.check1Sequence("89898978")).thenReturn(allSeq);

        ResponseEntity<?> response = controller.get1SequenceList(
                new Sequence().setSequence("89898978"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
