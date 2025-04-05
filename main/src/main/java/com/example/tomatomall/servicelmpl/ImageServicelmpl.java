package com.example.tomatomall.servicelmpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.service.ImageService;
import com.example.tomatomall.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServicelmpl implements ImageService {

    @Autowired
    OssUtil ossUtil;

    @Override
    public String upload(MultipartFile file){
        try {
            return ossUtil.upload(file.getOriginalFilename(), file.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
            throw TomatoException.fileUploadFail();
        }
    }
}
