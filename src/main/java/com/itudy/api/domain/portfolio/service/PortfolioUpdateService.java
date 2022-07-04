package com.itudy.api.domain.portfolio.service;

import com.itudy.api.common.entity.CommonPdfVO;
import com.itudy.api.common.service.CommonPdfService;
import com.itudy.api.domain.portfolio.dto.ProjectDTO;
import com.itudy.api.domain.portfolio.dto.TechDTO;
import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.portfolio.entity.ProjectVO;
import com.itudy.api.domain.portfolio.repository.PortfolioRepository;
import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.domain.study.service.PositionFindService;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioUpdateService {

    private final PortfolioRepository portfolioRepository;
    private final TechFindService techFindService;
    private final UserFindService userFindService;
    private final PositionFindService positionFindService;
    private final CommonPdfService commonPdfService;
    @Value("${server.url}")
    private String host;

    @Transactional
    public Long save(String title,
                     String description,
                     List<ProjectDTO> projects,
                     List<Long> techIds,
                     String positionName,
                     MultipartFile file){

        UserVO user = userFindService.getMyUserWithAuthorities();
        PositionVO position = positionFindService.findById(positionName);
        CommonPdfVO pdf = commonPdfService.save(file);

        List<ProjectVO> projectVOS = projects.stream().map(
                i -> {
                    ProjectVO project = ProjectVO.builder()
                            .title(i.getTitle())
                            .description(i.getDescription())
                            .build();

                    for (TechDTO tech : i.getTechs())
                        project.addTech(techFindService.findByIdx(tech.getIdx()));

                    return project;
                }
        ).toList();

        PortfolioVO portfolio = PortfolioVO.builder()
                .title(title)
                .description(description)
                .user(user)
                .position(position)
                .file(host + "/api/v1/common/files/" + pdf.getName())
                .build();

        for(ProjectVO project : projectVOS)
            portfolio.addProject(project);

        for(Long id : techIds)
            portfolio.addTech(techFindService.findByIdx(id));


        return portfolioRepository.save(portfolio).getIdx();
    }


}
