package com.simpletripbe.moduledomain.mycarrier.api;

import com.simpletripbe.moduledomain.ModuleDomainApplication;
import com.simpletripbe.moduledomain.mycarrier.entity.MyCarrier;
import com.simpletripbe.moduledomain.mycarrier.repository.MyCarrierRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ModuleDomainApplication.class)
public class MainCarrierServiceTest {

    @Autowired
    MainCarrierService mainCarrierService;
    @Autowired
    MyCarrierRepository myCarrierRepository;

    @Test
    @DisplayName(("전체 국가 리스트 조회 TEST"))
    public void selectAll() {

        //given
        MyCarrier content = MyCarrier.builder()
                .image("sampleImage")
                .dbsts("A")
                .link("sampleLink")
                .country("Korea")
                .file("sampleFile")
                .createDate(LocalDateTime.now())
                .build();
        myCarrierRepository.save(content);

        //when
        List<String> result = mainCarrierService.selectAll();

        //then
        String resultInstance = result.get(0);
        assertThat(resultInstance).isInstanceOf(String.class);
        assertThat(result.size()).isEqualTo(1);

    }

    @Test
    void selectDetailAll() {
    }

    @Test
    void addCarrier() {
    }

    @Test
    void editCarrier() {
    }

    @Test
    void deleteCarrier() {
    }

    @Test
    void addStamp() {
    }

    @Test
    void updateDetailInfo() {
    }
}