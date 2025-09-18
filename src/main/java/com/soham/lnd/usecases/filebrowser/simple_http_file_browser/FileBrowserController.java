package com.soham.lnd.usecases.filebrowser.simple_http_file_browser;

import com.soham.lnd.usecases.filebrowser.simple_http_file_browser.model.FileResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/browser")
@CrossOrigin("*")
public class FileBrowserController {

    @GetMapping("/explore")
    public List<FileResult> getContentsInDirectory(@RequestParam("dir") String directory){
        List<String> fileNames = new ArrayList<>();
        File file = new File(directory);
        if(file.exists()){
            if(!file.isDirectory()){
                throw new IllegalArgumentException("Not a Directory!");
            }
            return Arrays.stream(file.listFiles()).map(x->new FileResult(x.getAbsolutePath(),x.isDirectory())).toList();

        }else{
            throw new IllegalArgumentException("Directory does not exist");
        }
    }
}
