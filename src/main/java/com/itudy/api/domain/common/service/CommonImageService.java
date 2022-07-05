package com.itudy.api.domain.common.service;

import com.itudy.api.domain.common.entity.CommonImageVO;
import com.itudy.api.domain.common.entity.FileInfo;
import com.itudy.api.domain.common.repository.CommonImageRepository;
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
public class CommonImageService {
    private static final String UPLOADPATH = "/img";
    CommonImageRepository commonImageRepository;
    FileUtil fileUtil;
    int maxFileSize;

    public CommonImageService(CommonImageRepository commonImageRepository, FileUtil fileUtil, @Value("${static.max-file-size}") int maxFileSize) {
        this.commonImageRepository = commonImageRepository;
        this.fileUtil = fileUtil;
        this.maxFileSize = maxFileSize;
    }

    public CommonImageVO save(CommonImageVO vo) {
        return commonImageRepository.save(vo);
    }

    @Transactional
    public CommonImageVO save(MultipartFile mf){

        if(mf.getSize() == 0L)
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);

        String extension =  "." + Objects.requireNonNull(mf.getContentType()).split("/")[1];

        if(!(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".bmp")
                || extension.equals(".heif") || extension.equals(".heic")))
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);

        if (mf.getSize() > maxFileSize)
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);

        UUID saveName = UUID.randomUUID();
        fileUtil.saveFile(mf, saveName + extension, UPLOADPATH);

        CommonImageVO vo = CommonImageVO.builder()
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
        Optional<CommonImageVO> optionalImage = commonImageRepository.findByName(name);

        if(optionalImage.isEmpty())
            throw new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION);

        return fileUtil.getFile(optionalImage.get().getFileInfo());
    }



}
