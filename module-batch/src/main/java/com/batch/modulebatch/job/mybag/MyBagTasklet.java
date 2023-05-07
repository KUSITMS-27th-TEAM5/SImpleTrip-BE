package com.batch.modulebatch.job.mybag;

import com.simpletripbe.moduledomain.batch.api.BatchService;
import com.simpletripbe.moduledomain.batch.dto.MyBagSaveDTO;
import com.simpletripbe.moduledomain.mycarrier.api.MainCarrierService;
import com.simpletripbe.moduledomain.mycarrier.dto.CarrierListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
@JobScope
public class MyBagTasklet implements Tasklet {

    private final BatchService batchService;
    private final MainCarrierService mainCarrierService;

    @Value("#{jobParameters['country']}")
    private String country;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        try {

            List<CarrierListDTO> result = mainCarrierService.selectDetailAll(country);

            for (int i=0; i<result.size(); i++) {

                if(LocalDate.now().isAfter(result.get(i).getEndDate())) {

                    MyBagSaveDTO dto = new MyBagSaveDTO();
                    dto.setCountry(result.get(i).getCountry());
                    dto.setEndDate(result.get(i).getEndDate());

                    batchService.saveMyBag(dto);

                }

            }
            return RepeatStatus.FINISHED;
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("날짜 형식이 올바르지 않습니다.(YYYY-MM-DD)");
        }

    }

}
