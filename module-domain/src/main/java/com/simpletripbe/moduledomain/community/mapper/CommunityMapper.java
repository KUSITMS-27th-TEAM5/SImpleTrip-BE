package com.simpletripbe.moduledomain.community.mapper;

import com.simpletripbe.moduledomain.community.dto.InfoDTO;
import com.simpletripbe.moduledomain.community.entity.Community;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface CommunityMapper {

    // 조건이 여러 개라면 @Mappings로 묶어서 안에 Mapping을 세팅해준다.
    @Mappings({
            // source에는 있지만 target에는 없는 경우 target쪽 매핑 무시
            @Mapping(source = "id", target = "id", ignore = true),
            @Mapping(target = "content", ignore = true),
            @Mapping(target = "createDate", ignore = true)
    })
    List<InfoDTO> toDTO(List<Community> communityList);

}