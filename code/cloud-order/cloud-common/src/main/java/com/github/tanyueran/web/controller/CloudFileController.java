package com.github.tanyueran.web.controller;


import com.github.tanyueran.modal.dao.CloudFile;
import com.github.tanyueran.service.CloudFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author tanxin
 * @since 2020-09-09
 */
@RestController
@RequestMapping("/file")
@RefreshScope
@Api(value = "文件controller", tags = "文件上传下载类")
public class CloudFileController {

    @Value("${file.path}")
    private String path;

    @Autowired
    private CloudFileService cloudFileService;

    @PostMapping("/upload")
    @ApiOperation(value = "附件上传")
    public CloudFile uploadFile(
            @ApiParam(name = "file", value = "附件") @RequestParam("file") MultipartFile file,
            @ApiParam(name = "fileName", value = "附件名称") @RequestParam("fileName") String fileName,
            @ApiParam(name = "id", value = "主键") @RequestParam("id") String id) throws Exception {
        if (file == null) {
            throw new Exception("附件不可为空");
        }
        // 根据年月建目录
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        String filePath = year + File.separator + month;
        File file1 = new File(path + File.separator + filePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String idName = id + "." + file.getOriginalFilename().split("\\.")[1];
        file.transferTo(new File(file1, idName));
        CloudFile cloudFile = new CloudFile();
        cloudFile.setId(id);
        cloudFile.setFileName(fileName);
        cloudFile.setFileType(file.getContentType());
        cloudFile.setUrl(filePath + File.separator + idName);
        cloudFileService.save(cloudFile);
        return cloudFile;
    }

    @GetMapping("/view/{fileId}")
    @ApiOperation("图片预览")
    public void viewFile(
            @PathVariable("fileId") @ApiParam("文件的主键") String fileId,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        CloudFile cloudFile = cloudFileService.getById(fileId);
        if (cloudFile == null) {
            throw new Exception("图片不存在");
        }
        String url = cloudFile.getUrl();
        File file = new File(path + File.separator + url);
        FileInputStream fileInputStream = new FileInputStream(file);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        byte[] bytes = new byte[2048];
        httpServletResponse.setContentType(cloudFile.getFileType());

        while (fileInputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
        outputStream.close();
    }

    @GetMapping("/download/{fileId}")
    @ApiOperation("文件下载")
    public void download(
            @PathVariable("fileId") @ApiParam("文件的主键") String fileId,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        CloudFile cloudFile = cloudFileService.getById(fileId);
        if (cloudFile == null) {
            throw new Exception("文件不存在");
        }
        String url = cloudFile.getUrl();
        File file = new File(path + File.separator + url);
        FileInputStream fileInputStream = new FileInputStream(file);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        byte[] bytes = new byte[2048];
        httpServletResponse.setContentType(cloudFile.getFileType());
        String fileName = cloudFile.getFileName() + "." + cloudFile.getUrl().split("\\.")[1];
        httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        httpServletResponse.addHeader("Content-Length", "" + file.length());

        while (fileInputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
        outputStream.close();
    }
}
