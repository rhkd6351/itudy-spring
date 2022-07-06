package com.itudy.api.domain.study.service;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.portfolio.service.PortfolioFindService;
import com.itudy.api.domain.portfolio.service.TechFindService;
import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.domain.study.domain.RecruitmentVO;
import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.study.dto.PositionDemandDTO;
import com.itudy.api.domain.study.repository.RecruitmentRepository;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentUpdateService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentFindService recruitmentFindService;
    private final UserFindService userFindService;
    private final TechFindService techFindService;
    private final StudyFindService studyFindService;
    private final PortfolioFindService portfolioFindService;
    private final PositionFindService positionFindService;


    @Transactional
    public Long save(String title,
                     String description,
                     Long studyIdx,
                     Long portfolioIdx,
                     List<Long> techIds,
                     List<PositionDemandDTO> positionDemands
    ) {
        UserVO user = userFindService.getMyUserWithAuthorities();
        StudyVO study = studyFindService.findByIdx(studyIdx);
        PortfolioVO portfolio = portfolioFindService.findByIdx(portfolioIdx);

        if (user != studyFindService.findLeader(study)
        || user != portfolio.getUser())
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);

        RecruitmentVO recruitment = RecruitmentVO.builder()
                .title(title)
                .description(description)
                .study(study)
                .portfolio(portfolio)
                .hits(0L)
                .build();

        for (Long idx : techIds)
            recruitment.addTech(techFindService.findByIdx(idx));

        for (PositionDemandDTO dto : positionDemands) {
            PositionVO position = positionFindService.findById(dto.getPosition());
            recruitment.addDemand(position, dto.getDemand());
        }

        return recruitmentRepository.save(recruitment).getIdx();
    }

    @Transactional
    public Long update(Long recruitmentIdx,
                       String title,
                       String description,
                       Long portfolioIdx,
                       List<Long> techIds,
                       List<PositionDemandDTO> positionDemands) {
        UserVO user = userFindService.getMyUserWithAuthorities();
        PortfolioVO portfolio = portfolioFindService.findByIdx(portfolioIdx);

        if (user != portfolio.getUser())
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);

        RecruitmentVO recruitment = recruitmentFindService.findByIdx(recruitmentIdx);

        recruitment.setTitle(title);
        recruitment.setDescription(description);
        recruitment.removeTechs();
        recruitment.removeDemands();
        recruitmentRepository.saveAndFlush(recruitment);

        for (Long idx : techIds)
            recruitment.addTech(techFindService.findByIdx(idx));

        for (PositionDemandDTO dto : positionDemands) {
            PositionVO position = positionFindService.findById(dto.getPosition());
            recruitment.addDemand(position, dto.getDemand());
        }

        return recruitmentIdx;
    }
}
