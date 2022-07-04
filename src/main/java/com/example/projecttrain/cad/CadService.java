package com.example.projecttrain.cad;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface CadService {



    public CadEntity getBookMarks( MultipartFile multipartFile) throws Exception;

    public void uploadToLocal(MultipartFile file);
}
