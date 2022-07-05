package com.itudy.api.util;

import com.itudy.api.domain.common.entity.FileInfo;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

/**
 * 로컬 파일 저장 util
 */
@Component
public class FileUtil {

    private final String originPath;

    public FileUtil(@Value("${static.path}") String originPath) {
        this.originPath = originPath;
    }

    public byte[] getFile(FileInfo fileInfo) {
        File file = new File(originPath + fileInfo.getUploadPath() +"/"+ fileInfo.getSaveName());
        byte[] byfile = null;

        try{
            byfile = Files.readAllBytes(file.toPath());
        }catch (Exception e){
            throw new ApiException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }

        return byfile;
    }

    public String saveFile(MultipartFile multipartFile, String name, String uploadPath){

        File file = new File(originPath + uploadPath + "/" + name);

        try{
            multipartFile.transferTo(file);
        }catch (Exception e){
            throw new ApiException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }
        return name;
    }

}
