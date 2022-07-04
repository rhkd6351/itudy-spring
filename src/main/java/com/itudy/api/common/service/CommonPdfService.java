package com.itudy.api.common.service;

import com.itudy.api.common.entity.CommonImageVO;
import com.itudy.api.common.entity.CommonPdfVO;
import com.itudy.api.common.entity.FileInfo;
import com.itudy.api.common.repository.CommonImageRepository;
import com.itudy.api.common.repository.CommonPdfRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import com.itudy.api.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommonPdfService {
    private static final String UPLOADPATH = "/pdf";
    CommonPdfRepository commonPdfRepository;
    FileUtil fileUtil;
    int maxFileSize;

    public CommonPdfService(CommonPdfRepository commonPdfRepository, FileUtil fileUtil, @Value("${static.max-file-size}") int maxFileSize) {
        this.commonPdfRepository = commonPdfRepository;
        this.fileUtil = fileUtil;
        this.maxFileSize = maxFileSize;
    }

    public CommonPdfVO save(CommonPdfVO vo) {
        return commonPdfRepository.save(vo);
    }

    @Transactional
    public CommonPdfVO save(MultipartFile mf){

        if(mf.getSize() == 0L)
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);

        String extension =  "." + Objects.requireNonNull(mf.getContentType()).split("/")[1];

        if(!extension.equals(".pdf"))
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);

        if (mf.getSize() > maxFileSize)
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);

        UUID saveName = UUID.randomUUID();
        fileUtil.saveFile(mf, saveName + extension, UPLOADPATH);

        CommonPdfVO vo = CommonPdfVO.builder()
                .name(saveName.toString())
                .fileInfo(FileInfo.builder()
                        .originalName(mf.getOriginalFilename())
                        .saveName(saveName + extension)
                        .size(mf.getSize())
                        .uploadPath(UPLOADPATH)
                        .extension(extension)
                        .build())
                .build();

        return this.save(vo);
    }

    public byte[] getByName(String name){
        Optional<CommonPdfVO> optionalImage = commonPdfRepository.findByName(name);

        if(optionalImage.isEmpty())
            throw new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION);

        return fileUtil.getFile(optionalImage.get().getFileInfo());
    }



}
